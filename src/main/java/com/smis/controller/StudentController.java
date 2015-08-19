package com.smis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smis.dto.ApplicationDTO;
import com.smis.dto.JsonDTO;
import com.smis.dto.PageInfo;
import com.smis.dto.StudentDTO;
import com.smis.exception.BusinessJsonException;
import com.smis.exception.ParameterJsonException;
import com.smis.model.Application;
import com.smis.model.Student;
import com.smis.model.StudentCourse;
import com.smis.model.Teacher;
import com.smis.service.ApplicationService;
import com.smis.service.StudentCourseService;
import com.smis.service.StudentService;
import com.smis.service.TeacherService;
import com.smis.utils.ControllerUtils;
import com.smis.utils.CustomConstant;
import com.smis.utils.PropertiesUtils;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Resource
	private StudentService studentService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private StudentCourseService studentCourseService;
	@Resource
	private ApplicationService applicationService;

	/**
	 * loginStudent 单个学生登录系统
	 * 
	 * @param studentDTO
	 * @param result
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonDTO loginStudent(StudentDTO studentDTO, BindingResult result, HttpServletRequest req) throws Exception {
		// 数据验证不通过，抛出参数异常（ParameterException）
		if (result.hasErrors()) {
			throw new ParameterJsonException(ControllerUtils.convertBindingResultToString(result));
		}

		Student student = studentService.getStudentRoleBySid(studentDTO.getSid());

		// 密码验证不通过，抛出业务异常（BusinessJsonException）
		if (!studentDTO.getPassword().equals(student.getPassword())) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.LOGIN_FAILURE));
		}

		// 将登录学生的权限存储在session中
		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(6 * 60 * 60);
		session.setAttribute(CustomConstant.SESSION_ATTRIBUTE_KEY_PERMISSION, student.getRole().getPermission());

		return new JsonDTO();
	}

	/**
	 * logoutStudent 单个学生退出系统
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/logout")
	public JsonDTO logoutStudent(HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		session.invalidate();
		return  new JsonDTO();
	}

	/**
	 * updateStudent 单个学生修改密码
	 * 
	 * @param studentDTO
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonDTO updateStudent(@Valid StudentDTO studentDTO, BindingResult result) throws Exception {
		JsonDTO jsonDTO = null;

		// 数据验证不通过，抛出参数异常（ParameterException）
		if (result.hasErrors()) {
			throw new ParameterJsonException(ControllerUtils.convertBindingResultToString(result));
		}

		Student student = new Student();
		student.setSid(studentDTO.getSid());
		student.setPassword(studentDTO.getPassword());

		// 修改密码
		studentService.updateStudent(student);
		jsonDTO = new JsonDTO();
		jsonDTO.setMsg(PropertiesUtils.getValidationMessage(PropertiesUtils.UPDATE_SUCCESS));

		return jsonDTO;
	}

	/**
	 * showPersonalStudentInfo 单个学生获取个人、所在班级信息
	 * 
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/{sid}/info", method = RequestMethod.GET)
	public Student showPersonalStudentInfo(@PathVariable String sid) throws Exception {
		return studentService.getStudentInfoBySid(sid);
	}

	/**
	 * showPageStudentScore 单个学生获取分页的课程名称、课程成绩、课程学时、课程学分
	 * 
	 * @param sid
	 * @param pageInfo
	 * @return
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/{sid}/score/page/{currRecordIndex}/{pageSize}")
	public List<StudentCourse> showPageStudentScores(@PathVariable String sid, PageInfo pageInfo) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", sid);
		params.put("currRecordIndex", pageInfo.getCurrRecordIndex());
		params.put("pageSize", pageInfo.getPageSize());
		return studentCourseService.getPageStudentScoresBySid(params);
	}

	/**
	 * getStudentScoreTotalCount 获取单个学生课程名称、课程成绩、课程学时、课程学分的总记录条数
	 * 
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/{sid}/score/total-count")
	public Long showStudentScoreTotalCount(@PathVariable String sid) throws Exception {
		return studentCourseService.getStudentScoreTotalCountBySid(sid);
	}

	/**
	 * showManagerTeacherName 获取单个学生所在中队的所有中队长（可能只有一个）
	 * 
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/{sid}/manage-teacher")
	public List<Teacher> showManageTeachers(@PathVariable String sid) throws Exception {
		return teacherService.getManageTeachersBySid(sid);
	}

	/**
	 * 单个学生录入请假信息
	 * 
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/application/add", method = RequestMethod.POST)
	public JsonDTO addApplication(@Valid Application application, BindingResult result) throws Exception {
		JsonDTO jsonDTO = null;

		// 开始日期或者结束日期为null,抛出参数异常（ParameterException）
		if (application.getStartDate() == null) {
			throw new ParameterJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.APPLICATION_STARTDATE_NOT_NULL));
		} else if (application.getOverDate() == null) {
			throw new ParameterJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.APPLICATION_OVERDATE_NOT_NULL));
		}

		// 其他数据验证不通过，抛出参数异常（ParameterException）
		if (result.hasErrors()) {
			// 开始日期或者结束日期格式出错，在Application不能设置Message，所以在这里显示判断并抛出异常
			if (result.getFieldError("startDate") != null) {
				throw new ParameterJsonException(
						PropertiesUtils.getValidationMessage(PropertiesUtils.APPLICATION_STARTDATE_FORMAT_ERROR));
			} else if (result.getFieldError("overDate") != null) {
				throw new ParameterJsonException(
						PropertiesUtils.getValidationMessage(PropertiesUtils.APPLICATION_OVERDATE_FORMAT_ERROR));
			}
			throw new ParameterJsonException(ControllerUtils.convertBindingResultToString(result));
		}

		// 保存请假信息
		applicationService.addApplicationByStudent(application);
		jsonDTO = new JsonDTO();
		jsonDTO.setMsg(PropertiesUtils.getValidationMessage(PropertiesUtils.APPLICATION_ADD_SUCCESS));

		return jsonDTO;
	}

	@ResponseBody
	@RequestMapping(value = "/application/{aid}/delete", method = RequestMethod.GET)
	public JsonDTO deleteApplication(@PathVariable String aid) throws Exception {
		JsonDTO jsonDTO = null;
		// 删除请假信息
		applicationService.deleteApplicationByStudent(Long.parseLong(aid));
		jsonDTO = new JsonDTO();
		jsonDTO.setMsg(PropertiesUtils.getValidationMessage(PropertiesUtils.DELETE_SUCCESS));
		return jsonDTO;
	}

	/**
	 * 单个学生修改请假信息（ startDate, overDate, countDay, leaveReason, leavePlace）
	 * 如果中队长已经审批，则不能修改
	 * 
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/application/update", method = RequestMethod.POST)
	public JsonDTO updateApplication(@Valid ApplicationDTO applicationDTO, BindingResult result) throws Exception {
		JsonDTO jsonDTO = null;

		// 开始日期或者结束日期为null,抛出参数异常（ParameterException）
		if (applicationDTO.getStartDate() == null) {
			throw new ParameterJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.APPLICATION_STARTDATE_NOT_NULL));
		} else if (applicationDTO.getOverDate() == null) {
			throw new ParameterJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.APPLICATION_OVERDATE_NOT_NULL));
		}

		// 数据验证不通过，抛出参数异常（ParameterException）
		if (result.hasErrors()) {
			throw new ParameterJsonException(ControllerUtils.convertBindingResultToString(result));
		}

		// 设置修改的参数对象
		Application application = new Application();
		application.setAid(applicationDTO.getAid());
		application.setStartDate(applicationDTO.getStartDate());
		application.setOverDate(applicationDTO.getOverDate());
		application.setLeavePlace(applicationDTO.getLeavePlace());
		application.setLeaveReason(applicationDTO.getLeaveReason());

		// 修改请假信息
		applicationService.updateApplicationByStudent(application);
		jsonDTO = new JsonDTO();
		jsonDTO.setMsg(PropertiesUtils.getValidationMessage(PropertiesUtils.UPDATE_SUCCESS));
		jsonDTO.setObj(application.getCountDay());

		return jsonDTO;
	}

	/**
	 * showPageStudentScore 单个学生获取分页的请假信息
	 * 
	 * @param sid
	 * @param pageInfo
	 * @return
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/{sid}/application/page/{currRecordIndex}/{pageSize}")
	public List<Application> showPageApplications(Application application, PageInfo pageInfo) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", application.getSid());
		params.put("currRecordIndex", pageInfo.getCurrRecordIndex());
		params.put("pageSize", pageInfo.getPageSize());
		return applicationService.getPageApplicationsBySid(params);
	}

	/**
	 * showApplicationTotalCount 获取单个学生请假信息的总记录条数
	 * 
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/{sid}/application/total-count")
	public Long showApplicationTotalCount(Application application) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", application.getSid());
		return applicationService.getApplicationTotalCountBySid(params);
	}

}