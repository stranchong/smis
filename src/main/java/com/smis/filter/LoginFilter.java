package com.smis.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSONObject;
import com.smis.utils.CustomConstant;
import com.smis.utils.PropertiesUtils;

/**
 * 用户登录过滤器
 * 
 * @author acer
 * 
 */
public class LoginFilter extends OncePerRequestFilter {

	// 不过滤的uri
	private final static String[] notFilterA = new String[] { "login.html", "login.jsp" };
	private final static String[] notFilterB = new String[] { "login.html" };

	// 访问权限控制的uri开头部分
	private final static String[] identityFilter = new String[] { "/smis/manager", "/smis/teacher", "/smis/student" };

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 请求的uri
		String uri = request.getRequestURI();
		// 判断是管理员Uri，则调用handleManagerUri
//		if (uri.indexOf(identityFilter[0]) == 0) {
//			handleManagerUri(request, response, filterChain);
//		} else if (uri.indexOf(identityFilter[1]) == 0 || uri.indexOf(identityFilter[2]) == 0) {
//			handleTeacherOrStudentUri(request, response, filterChain);
//		} else {
//			filterChain.doFilter(request, response);
//		}
		
		filterChain.doFilter(request, response);
	}

	/** 处理管理员Uri */
	private void handleManagerUri(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		// 访问管理员登录页面的话，如果已经登录则重定向到index.jsp页面
		if (uri.indexOf("login.jsp") != -1) {
			if (request.getSession().getAttribute(CustomConstant.SESSION_ATTRIBUTE_KEY_PERMISSION) != null) {
				response.sendRedirect("index.jsp");
				return;
			}
		}
		// 是否过滤
		boolean doFilter = true;
		for (String s : notFilterA) {
			if (uri.indexOf(s) != -1) { // 如果uri中包含不过滤的uri，则不进行过滤
				doFilter = false;
				break;
			}
		}
		// 执行过滤
		if (doFilter) {
			// 从session中获取登录者的权限信息
			Integer permission = (Integer) request.getSession().getAttribute(CustomConstant.SESSION_ATTRIBUTE_KEY_PERMISSION);
			// 登陆者的权限信息不为空，继续执行
			if (permission != null) {
				filterChain.doFilter(request, response);
			} else { // 登陆者的权限信息为空，返回登录页面
				response.sendRedirect("login.jsp");
			}
		} else { // 不执行过滤
			filterChain.doFilter(request, response);
		}
	}

	/** 处理教师或学生Uri */
	private void handleTeacherOrStudentUri(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		// 是否过滤
		boolean doFilter = true;
		for (String s : notFilterB) {
			if (uri.indexOf(s) != -1) {
				// 如果uri中包含不过滤的uri，则不进行过滤
				doFilter = false;
				break;
			}
		}
		// 执行过滤
		if (doFilter) {
			// 从session中获取登录者的权限信息
			Integer permission = (Integer) request.getSession().getAttribute(CustomConstant.SESSION_ATTRIBUTE_KEY_PERMISSION);
			boolean isPass = false;
			// 登陆者的权限信息不为空
			if (permission != null) {
				// 登陆者为教职工，且访问的URI路径为/smis/teacher开头，权限验证通过
				if (permission == CustomConstant.ROLE_PERMISSION_TEACHER && uri.indexOf(identityFilter[1]) == 0) {
					isPass = true;
				}
				// 登陆者为学生，且访问的URI路径为/smis/student开头，权限验证通过
				if (permission == CustomConstant.ROLE_PERMISSION_STUDENT && uri.indexOf(identityFilter[2]) == 0) {
					isPass = true;
				}
				// 权限验证通过，继续执行后续操作
				if (isPass) {
					filterChain.doFilter(request, response);
				} else { // 权限验证没通过，发送提示信息给客户端
					sendMsgToClient(response, PropertiesUtils.getValidationMessage(PropertiesUtils.NOT_PERMISSION_ACCESS));
				}
			} else { // 登陆者的权限信息为空，发送错误信息给客户端
				sendMsgToClient(response, PropertiesUtils.getValidationMessage(PropertiesUtils.LOGIN_TIMEOUT));
			}
		} else { // 如果不执行过滤，则继续
			filterChain.doFilter(request, response);
		}
	}

	private void sendMsgToClient(HttpServletResponse response, String msg) throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", false);
		jsonObject.put("msg", msg);
		// 在这里直接使用response向客户端发送异常信息是不会经过web.xml中定义的字符集过滤器，所以要加上这句
		response.setContentType("text/plain;charset=utf-8");
		response.getWriter().write(jsonObject.toJSONString());
	}

}