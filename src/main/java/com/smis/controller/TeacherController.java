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

import com.smis.dto.ApplicationDTO2;
import com.smis.dto.JsonDTO;
import com.smis.dto.PageInfo;
import com.smis.dto.TeacherDTO;
import com.smis.exception.BusinessJsonException;
import com.smis.exception.ParameterJsonException;
import com.smis.model.Application;
import com.smis.model.Class;
import com.smis.model.Course;
import com.smis.model.Student;
import com.smis.model.StudentCourse;
import com.smis.model.Teacher;
import com.smis.service.ApplicationService;
import com.smis.service.ClassService;
import com.smis.service.CourseService;
import com.smis.service.StudentCourseService;
import com.smis.service.StudentService;
import com.smis.service.TeacherService;
import com.smis.utils.ControllerUtils;
import com.smis.utils.CustomConstant;
import com.smis.utils.PropertiesUtils;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Resource
	private TeacherService teacherService;
	@Resource
	private StudentService studentService;
	@Resource
	private ApplicationService applicationService;
	@Resource
	private StudentCourseService studentCourseService;
	@Resource
	private CourseService courseService;
	@Resource
	private ClassService classService;

	/**
	 * 单个教职工登录系统
	 * 
	 * @param tid
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonDTO loginTeacher(@Valid TeacherDTO teacherDTO, BindingResult result, HttpServletRequest req) throws Exception {
		JsonDTO jsonDTO = null;

		if (result.hasErrors()) { // 数据验证不通过，抛出参数异常（ParameterException）
			String errMsg = ControllerUtils.convertBindingResultToString(result);
			throw new ParameterJsonException(errMsg);
		}

		Teacher teacher = teacherService.getTeacherRoleByTid(teacherDTO.getTid());

		if (!teacherDTO.getPassword().equals(teacherDTO.getPassword())) { // 密码验证不通过，抛出业务异常（BusinessJsonException）
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.LOGIN_FAILURE));
		}

		// 将登录教职工的权限存储在session中
		HttpSession session = req.getSession();
		session.setAttribute(CustomConstant.SESSION_ATTRIBUTE_KEY_PERMISSION, teacher.getRole().getPermission());
		jsonDTO = new JsonDTO();
		jsonDTO.setObj(teacher.getTname());

		return jsonDTO;
	}

	/**
	 * 单个教职工退出系统
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/logout")
	public JsonDTO logoutTeacher(HttpServletRequest req) throws Exception {
		JsonDTO jsonDTO = new JsonDTO();

		HttpSession session = req.getSession();
		session.invalidate();
		jsonDTO.setMsg(PropertiesUtils.getValidationMessage(PropertiesUtils.LOGOUT_SUCCESS));

		return jsonDTO;
	}

	/**
	 * 单个教职工修改密码
	 * 
	 * @param teacherDTO
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonDTO updateTeacher(@Valid TeacherDTO teacherDTO, BindingResult result) throws Exception {
		JsonDTO jsonDTO = new JsonDTO();

		if (result.hasErrors()) { // 数据验证不通过，抛出参数异常（ParameterException）
			String errMsg = ControllerUtils.convertBindingResultToString(result);
			throw new ParameterJsonException(errMsg);
		}

		Teacher teacher = new Teacher();
		teacher.setTid(teacherDTO.getTid());
		teacher.setPassword(teacherDTO.getPassword());
		teacherService.updateTeacher(teacher);
		jsonDTO = new JsonDTO();
		jsonDTO.setMsg(PropertiesUtils.getValidationMessage(PropertiesUtils.UPDATE_SUCCESS));

		return jsonDTO;
	}

	/**
	 * 教职工获取个人信息
	 * 
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/{tid}/info")
	public Teacher showPersonalTeacherInfo(@PathVariable String tid) throws Exception {
		return teacherService.getTeacherInfoByTid(tid);
	}

	/**
	 * 教职工获取分页的课程名称、班级名称、学生姓名、成绩信息
	 * 
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "{tid}/student-score/page/{currRecordIndex}/{pageSize}")
	public List<StudentCourse> showPageStudentScores(@PathVariable String tid, PageInfo pageInfo) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tid", tid);
		params.put("currRecordIndex", pageInfo.getCurrRecordIndex());
		params.put("pageSize", pageInfo.getPageSize());
		return studentCourseService.getPageStudentScoresByTid(params);
	}

	/**
	 * 教职工获取所教课程学生成绩的总记录条数
	 * 
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/{tid}/student-score/total-count")
	public Long showStudentScoreTotalCount(@PathVariable String tid) throws Exception {
		return studentCourseService.getStudentScoreTotalCountByTid(tid);
	}

	/**
	 * 根据tid获取所教的所有课程
	 * 
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/{tid}/course.html")
	public List<Course> showAllTeachCoursesByTid(@PathVariable String tid) throws Exception {
		return courseService.getAllCoursesByTid(tid);
	}

	/**
	 * 根据cid获取修该课程的所有班级
	 * 
	 * @param cid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/course/{cid}/class.html")
	public List<Class> showAllTeachClassesByCid(@PathVariable String cid) throws Exception {
		return classService.getAllClassesByCid(cid);
	}

	/**
	 * 根据clid获取修该班级的所有学生
	 * 
	 * @param clid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/class/{clid}/student.html")
	public List<Student> showAllTeachStudentsByClid(@PathVariable String clid) throws Exception {
		return studentService.getAllStudentsByClid(clid);
	}

	/**
	 * 录入学生成绩（学生选上课后，在student_course表中就有记录，不过分数为null），所以调用修改的方法
	 * 
	 * @param studentCourse
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/student-score/add", method = RequestMethod.POST)
	public JsonDTO addStudentScore(@Valid StudentCourse studentCourse, BindingResult result) throws Exception {
		return updateStudentScore(studentCourse, result);
	}

	/**
	 * 修改学生成绩
	 * 
	 * @param studentCourse
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/student-score/update", method = RequestMethod.POST)
	public JsonDTO updateStudentScore(@Valid StudentCourse studentCourse, BindingResult result) throws Exception {
		JsonDTO jsonDTO = new JsonDTO();

		if (result.hasErrors()) { // 数据验证不通过，抛出参数异常（ParameterException）
			String errMsg = ControllerUtils.convertBindingResultToString(result);
			throw new ParameterJsonException(errMsg);
		}

		studentCourseService.updateStudentCourse(studentCourse);
		jsonDTO = new JsonDTO();
		jsonDTO.setMsg(PropertiesUtils.getValidationMessage(PropertiesUtils.ADD_SUCCESS_TWO));

		return jsonDTO;
	}

	/**
	 * 获取分页的学生请假信息
	 * 
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/{tid}/application/page/{currRecordIndex}/{pageSize}")
	public List<Application> showPageStudentApplications(@PathVariable String tid, PageInfo pageInfo) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tid", tid);
		params.put("currRecordIndex", pageInfo.getCurrRecordIndex());
		params.put("pageSize", pageInfo.getPageSize());
		return applicationService.getPageStudentApplicationsByTid(params);
	}

	/**
	 * 获取学生请假信息的总记录条数
	 * 
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/{tid}/application/total-count")
	public Long showStudentApplicationTotalCount(Application application) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tid", application.getTid());
		return applicationService.getStudentApplicationTotalCountByTid(params);
	}

	/**
	 * 教职工审批学生请假信息
	 * 
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/application/update", method = RequestMethod.POST)
	public JsonDTO updateApplication(@Valid ApplicationDTO2 applicationDTO2, BindingResult result) throws Exception {
		JsonDTO jsonDTO = null;

		if (result.hasErrors()) { // 数据验证不通过，抛出参数异常（ParameterException）
			String errMsg = ControllerUtils.convertBindingResultToString(result);
			throw new ParameterJsonException(errMsg);
		}

		// 设置审批请假信息的参数
		Application application = new Application();
		application.setAid(applicationDTO2.getAid());
		application.setCountDay(applicationDTO2.getCountDay());
		application.setConfirmMark(applicationDTO2.getConfirmMark());
		application.setConfirmTid(applicationDTO2.getConfirmTid());
		application.setConfirmReply(applicationDTO2.getConfirmReply());
		application.setStartDate(applicationDTO2.getStartDate());

		// 审批请假信息
		applicationService.updateApplicationByTeacher(application);
		jsonDTO = new JsonDTO();
		jsonDTO.setMsg(PropertiesUtils.getValidationMessage(PropertiesUtils.APPLICATION_APPROVE_SUCCESS));
		jsonDTO.setObj(application.getConfirmMark());

		return jsonDTO;
	}
}