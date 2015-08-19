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
import com.smis.model.Course;
import com.smis.service.CourseService;
import com.smis.utils.ControllerUtils;
import com.smis.utils.PropertiesUtils;

@Controller
public class ManagerCourseController {

	@Resource
	private CourseService courseService;

	/** 录入 */
	@RequestMapping(value = "/manager/course/add/show")
	public ModelAndView showAddCourse() throws Exception {
		return new ModelAndView("/manager/course/add");
	}

	@ResponseBody
	@RequestMapping(value = "/manager/course/add", method = RequestMethod.POST)
	public JsonDTO addCourse(@Valid Course course, BindingResult result) throws Exception {
		ControllerUtils.handleBindingResult(result);
		ControllerUtils.validateArgumentByRegex(String.valueOf(course.getCredit()), "\\d*(\\.(5|0))?",
				PropertiesUtils.COURSE_CREDIT_FORMAT);
		courseService.addCourse(course);
		return new JsonDTO(PropertiesUtils.getValidationMessage(PropertiesUtils.ADD_SUCCESS));
	}

	/** 删除 */
	@ResponseBody
	@RequestMapping(value = "/manager/course/{cid}/delete")
	public void deleteCourse(@PathVariable String cid, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		courseService.deleteCourse(cid);
		req.getRequestDispatcher("/manager/course/page/1/10.html").forward(req, resp);
	}

	/** 修改 */
	@RequestMapping(value = "/manager/course/update/show")
	public ModelAndView showUpdateCcourse(Course course) throws Exception {
		ModelAndView mav = new ModelAndView("/manager/course/update");
		mav.addObject("course", courseService.getCourse(course.getCid()));
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/manager/course/update", method = RequestMethod.POST)
	public JsonDTO updateCourse(Course course, BindingResult result) throws Exception {
		ControllerUtils.handleBindingResult(result);
		ControllerUtils.validateArgumentByRegex(String.valueOf(course.getCredit()), "\\d*(\\.(5|0))?",
				PropertiesUtils.COURSE_CREDIT_FORMAT);
		courseService.updateCourse(course);
		return new JsonDTO(PropertiesUtils.getValidationMessage(PropertiesUtils.UPDATE_SUCCESS));
	}

	/** show */
	@RequestMapping("/manager/course/page/{currentPage}/{pageSize}")
	public ModelAndView showPageCourses(Course course, PageInfo pageInfo) throws Exception {
		// 封装分页排序参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("currRecordIndex", pageInfo.getCurrRecordIndex());
		params.put("pageSize", pageInfo.getPageSize());
		params.put("orderColumn", pageInfo.getOrderColum() == null ? "cid" : pageInfo.getOrderColum());
		params.put("order", pageInfo.getOrder() == null ? "asc" : pageInfo.getOrder());
		// 封装模糊查询参数
		if (course.getCid() != null && !course.getCid().equals("")) {
			params.put("cid", course.getCid());
		}
		if (course.getCname() != null && !course.getCname().equals("")) {
			params.put("cname", course.getCname());
		}
		if (course.getCredit() != null) {
			params.put("credit", course.getCredit());
		}
		if (course.getCreditHours() != null) {
			params.put("credit_hours", course.getCreditHours());
		}
		// 设置要返回的ModelAndView
		pageInfo.setTotalCount(courseService.getCourseTotalCount(params).intValue());
		pageInfo.setPageInfo();
		ModelAndView modelAndView = new ModelAndView("/manager/course/page");
		modelAndView.addObject("courses", courseService.getPageCourses(params));
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.addObject("course", course);
		modelAndView.addObject("moduleName", "course");
		return modelAndView;
	}
}