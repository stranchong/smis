package com.smis.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smis.dto.JsonDTO;
import com.smis.dto.PageInfo;
import com.smis.dto.StudentDTO2;
import com.smis.model.Student;
import com.smis.service.ClassService;
import com.smis.service.StudentService;
import com.smis.utils.ControllerUtils;
import com.smis.utils.PropertiesUtils;

@Controller
public class ManagerStudentController {

	@Resource
	private StudentService studentService;
	@Resource
	private ClassService classService;

	/** 录入 */
	@RequestMapping(value = "/manager/student/add/show")
	public ModelAndView showAddStudent() throws Exception {
		ModelAndView mav = new ModelAndView("/manager/student/add");
		mav.addObject("classes", classService.getAllClasses(null));
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/manager/student/add", method = RequestMethod.POST)
	public JsonDTO addStudent(@Valid Student student, BindingResult result) throws Exception {
		ControllerUtils.handleBindingResult(result);
		studentService.addStudent(student);
		return new JsonDTO(PropertiesUtils.getValidationMessage(PropertiesUtils.ADD_SUCCESS));
	}

	/** 删除 */
	@ResponseBody
	@RequestMapping(value = "/manager/student/{sid}/delete")
	public void deleteStudent(@PathVariable String sid, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		studentService.deleteStudent(sid);
		req.getRequestDispatcher("/manager/student/page/1/10.html").forward(req, resp);
	}

	/** 修改 */
	@RequestMapping(value = "/manager/student/update/show")
	public ModelAndView showUpdateStudent(Student student) throws Exception {
		ModelAndView mav = new ModelAndView("/manager/student/update");
		mav.addObject("student", studentService.getStudentRoleBySid(student.getSid()));
		mav.addObject("classes", classService.getAllClasses(null));
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/manager/student/update", method = RequestMethod.POST)
	public JsonDTO updateStudent(StudentDTO2 studentDTO2, BindingResult result) throws Exception {
		ControllerUtils.handleBindingResult(result);
		studentService.updateStudent(studentDTO2.toStudent());
		return new JsonDTO(PropertiesUtils.getValidationMessage(PropertiesUtils.UPDATE_SUCCESS));
	}

	/** show */
	@RequestMapping("/manager/student/page/{currentPage}/{pageSize}")
	public ModelAndView showPageStudents(Student student, PageInfo pageInfo) throws Exception {
		// 封装分页排序参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("currRecordIndex", pageInfo.getCurrRecordIndex());
		params.put("pageSize", pageInfo.getPageSize());
		params.put("orderColumn", pageInfo.getOrderColum() == null ? "class.clid" : pageInfo.getOrderColum());
		params.put("order", pageInfo.getOrder() == null ? "asc" : pageInfo.getOrder());
		// 封装模糊查询参数
		if (student.getSid() != null && !student.getSid().equals("")) {
			params.put("sid", student.getSid());
		}
		if (student.getSname() != null && !student.getSname().equals("")) {
			params.put("sname", student.getSname());
		}
		if (student.getClid() != null && !student.getClid().equals("")) {
			params.put("clid", student.getClid());
		}
		// 设置要返回的ModelAndView
		pageInfo.setTotalCount(studentService.getStudentTotalCount(params).intValue());
		pageInfo.setPageInfo();
		ModelAndView modelAndView = new ModelAndView("/manager/student/page");
		modelAndView.addObject("students", studentService.getPageStudents(params));
		modelAndView.addObject("classes", classService.getAllClasses(null));
		modelAndView.addObject("student", student);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.addObject("moduleName", "student");
		return modelAndView;
	}
}