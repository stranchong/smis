package com.smis.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smis.dto.JsonDTO;
import com.smis.dto.ManagerDTO;
import com.smis.dto.PageInfo;
import com.smis.exception.BusinessJsonException;
import com.smis.model.Manager;
import com.smis.service.ManagerService;
import com.smis.utils.ControllerUtils;
import com.smis.utils.CustomConstant;
import com.smis.utils.PropertiesUtils;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Resource
	private ManagerService managerService;

	/** 访问登录后的页面 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView showIndex(Manager manager) throws Exception {
		ModelAndView mav = new ModelAndView("/manager/index");
		mav.addObject("manager", manager);
		return mav;
	}

	/** 登陆 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonDTO loginManager(@Valid Manager manager, BindingResult result, HttpServletRequest req) throws Exception {
		ControllerUtils.handleBindingResult(result);
		Manager m = managerService.getManagerRoleByUsername(manager.getUsername());
		// 密码验证不通过，抛出业务异常（BusinessJsonException）
		if (!m.getPassword().equals(manager.getPassword())) {
			throw new BusinessJsonException(PropertiesUtils.getValidationMessage(PropertiesUtils.LOGIN_FAILURE));
		}
		// 将登录管理员的权限存储在session中
		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(6 * 60 * 60);
		session.setAttribute(CustomConstant.SESSION_ATTRIBUTE_KEY_PERMISSION, m.getRole().getPermission());
		return new JsonDTO(m.getMid());
	}

	/** 登出 */
	@RequestMapping(value = "/logout")
	public void logoutManager(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getSession().invalidate();
		resp.sendRedirect("login.jsp");
	}

	/** 添加管理员 */
	@RequestMapping(value = "/manager/add/show")
	public ModelAndView showAddManager() throws Exception {
		return new ModelAndView("/manager/add");
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonDTO addManager(@Valid Manager manager, BindingResult result) throws Exception {
		ControllerUtils.handleBindingResult(result);
		managerService.addManager(manager);
		return new JsonDTO(PropertiesUtils.getValidationMessage(PropertiesUtils.ADD_SUCCESS));
	}

	/** 删除管理员 */
	@ResponseBody
	@RequestMapping(value = "/delete/{mid}")
	public void deleteManager(@PathVariable Long mid, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		managerService.deleteManager(mid);
		req.getRequestDispatcher("/manager/page/1/10.html").forward(req, resp);
	}

	/** 修改管理员 */
	@RequestMapping(value = "/update-pwd/show")
	public ModelAndView showUpdateManagerPwd(Manager manager) throws Exception {
		ModelAndView mav = new ModelAndView("/manager/update_pwd");
		mav.addObject("manager", manager);
		return mav;
	}

	@RequestMapping(value = "/update/show")
	public ModelAndView showUpdateManager(Manager manager) throws Exception {
		ModelAndView mav = new ModelAndView("/manager/update");
		mav.addObject("manager", manager);
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonDTO updateManager(@Valid ManagerDTO managerDTO, BindingResult result) throws Exception {
		ControllerUtils.handleBindingResult(result);
		managerService.updateManager(managerDTO.toManager());
		return new JsonDTO(PropertiesUtils.getValidationMessage(PropertiesUtils.UPDATE_SUCCESS));
	}

	/** 获取管理员 */
	@RequestMapping(value = "/page/{currentPage}/{pageSize}")
	public ModelAndView showPageManagers(Manager manager, PageInfo pageInfo) throws Exception {
		// 封装分页排序参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("currRecordIndex", pageInfo.getCurrRecordIndex());
		params.put("pageSize", pageInfo.getPageSize());
		params.put("orderColumn", pageInfo.getOrderColum() == null ? "cid" : pageInfo.getOrderColum());
		params.put("order", pageInfo.getOrder() == null ? "asc" : pageInfo.getOrder());
		// 封装模糊查询参数
		if (manager.getUsername() != null && !manager.getUsername().equals("")) {
			params.put("username", manager.getUsername());
		}
		// 设置要返回的ModelAndView
		pageInfo.setTotalCount(managerService.getManagerTotalCount(params).intValue());
		pageInfo.setPageInfo();
		ModelAndView modelAndView = new ModelAndView("/manager/course/page");
		modelAndView.addObject("managers", managerService.getPageManagers(params));
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.addObject("manager", manager);
		modelAndView.addObject("moduleName", "manager");
		return modelAndView;
	}

}