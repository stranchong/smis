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
import com.smis.service.ClassService;
import com.smis.utils.ControllerUtils;
import com.smis.utils.PropertiesUtils;

@Controller
public class ManagerClassController {

	@Resource
	private ClassService classService;

	/** 录入 */
	@RequestMapping(value = "/manager/class/add/show")
	public ModelAndView showAddClass() throws Exception {
		return new ModelAndView("/manager/class/add");
	}

	@ResponseBody
	@RequestMapping(value = "/manager/class/add", method = RequestMethod.POST)
	public JsonDTO addClass(@Valid com.smis.model.Class clazz, BindingResult result) throws Exception {
		ControllerUtils.handleBindingResult(result);
		classService.addClass(clazz);
		return new JsonDTO(PropertiesUtils.getValidationMessage(PropertiesUtils.ADD_SUCCESS));
	}

	/** 删除 */
	@ResponseBody
	@RequestMapping(value = "/manager/class/{clid}/delete")
	public void deleteClass(@PathVariable String clid, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		classService.deleteClass(clid);
		req.getRequestDispatcher("/manager/class/page/1/10.html").forward(req, resp);
	}

	/** 修改 */
	@RequestMapping(value = "/manager/class/update/show")
	public ModelAndView showUpdateClass(com.smis.model.Class clazz) throws Exception {
		ModelAndView mav = new ModelAndView("/manager/class/update");
		mav.addObject("clazz", classService.getClass(clazz.getClid()));
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/manager/class/update", method = RequestMethod.POST)
	public JsonDTO updateClass(com.smis.model.Class clazz, BindingResult result) throws Exception {
		ControllerUtils.handleBindingResult(result);
		classService.updateClass(clazz);
		return new JsonDTO(PropertiesUtils.getValidationMessage(PropertiesUtils.UPDATE_SUCCESS));
	}

	/** show */
	@RequestMapping("/manager/class/page/{currentPage}/{pageSize}")
	public ModelAndView showPageClasses(com.smis.model.Class clazz, PageInfo pageInfo) throws Exception {
		// 封装分页排序参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("currRecordIndex", pageInfo.getCurrRecordIndex());
		params.put("pageSize", pageInfo.getPageSize());
		params.put("orderColumn", pageInfo.getOrderColum() == null ? "clid" : pageInfo.getOrderColum());
		params.put("order", pageInfo.getOrder() == null ? "asc" : pageInfo.getOrder());
		// 封装模糊查询参数
		if (clazz.getClid() != null && !clazz.getClid().equals("")) {
			params.put("clid", clazz.getClid());
		}
		if (clazz.getClname() != null && !clazz.getClname().equals("")) {
			params.put("clname", clazz.getClname());
		}
		// 设置要返回的ModelAndView
		pageInfo.setTotalCount(classService.getClassTotalCount(params).intValue());
		pageInfo.setPageInfo();
		ModelAndView modelAndView = new ModelAndView("/manager/class/page");
		modelAndView.addObject("classes", classService.getPageClasses(params));
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.addObject("clazz", clazz);
		modelAndView.addObject("moduleName", "class");
		return modelAndView;
	}
}