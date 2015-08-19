package com.smis.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smis.dto.JsonDTO;
import com.smis.exception.ParameterJsonException;
import com.smis.model.Teacher;
import com.smis.service.TeacherService;
import com.smis.utils.ControllerUtils;

@Controller
public class ManagerTeacherController {

	@Resource
	private TeacherService teacherService;

	@ResponseBody
	@RequestMapping(value = "/manager/teacher/add", method = RequestMethod.POST)
	public JsonDTO addTeacher(@Valid Teacher teacher, BindingResult result) throws Exception {
		JsonDTO jsonDTO = new JsonDTO();

		if (result.hasErrors()) { // 数据验证不通过，抛出参数异常（ParameterException）
			String errMsg = ControllerUtils.convertBindingResultToString(result);
			throw new ParameterJsonException(errMsg);
		} else if (teacher.getIsManager() == true) {
			if (teacher.getFaculty().getFid() == null) {
				throw new ParameterJsonException("所在系部不能为空");
			}
		} else {
			teacherService.addTeacher(teacher);
			jsonDTO.setMsg("增加教职工信息成功");
		}

		return jsonDTO;
	}

	@ResponseBody
	@RequestMapping(value = "/manager/teacher/delete/{tid}")
	public JsonDTO deleteTeacher(@PathVariable String tid) throws Exception {
		JsonDTO jsonDTO = new JsonDTO();

		if (tid == null || tid.trim().equals("")) { // 验证参数为空，抛出参数异常（ParameterJsonException）
			throw new ParameterJsonException("教职工代号参数为空，删除失败");
		} else {
			teacherService.deleteTeacher(tid);
			jsonDTO.setMsg("删除教职工信息成功");
		}

		return jsonDTO;
	}

	@ResponseBody
	@RequestMapping(value = "/manager/teacher/update", method = RequestMethod.POST)
	public JsonDTO updateTeacher(@Valid Teacher teacher, BindingResult result) throws Exception {
		JsonDTO jsonDTO = new JsonDTO();

		if (result.hasErrors()) { // 数据验证不通过，抛出参数异常（ParameterException）
			String errMsg = ControllerUtils.convertBindingResultToString(result);
			throw new ParameterJsonException(errMsg);
		} else if (teacher.getIsManager() == true) {
			if (teacher.getFaculty().getFid() == null) {
				throw new ParameterJsonException("所在系部不能为空");
			}
		} else {
			teacherService.updateTeacher(teacher);
		}

		return jsonDTO;
	}
}