package com.smis.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

public class CustomExceptionHandler implements HandlerExceptionResolver {

	private static final Logger logger = Logger.getLogger(CustomExceptionHandler.class);

	public CustomExceptionHandler() {

	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		if (ex instanceof BusinessJsonException || ex instanceof ParameterJsonException) { // 业务异常或参数异常处理，返回JSON格式错误信息给客户端
			try {
				// 这里用JSONObject而不用自己定义的JsonDTO的原因是JSONUtils.toJSONString(Object
				// obj)方法参数不能是自定义的类型
				// 而JSONObject类中有个toJSONString的方法可以来转化为JSON格式的字符串
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("success", false);
				jsonObject.put("msg", ex.getMessage());
				// 在这里直接使用response向客户端发送异常信息是不会经过web.xml中定义的字符集过滤器，所以要加上这句
				response.setContentType("text/plain;charset=utf-8");
				response.getWriter().write(jsonObject.toJSONString());
			} catch (IOException e) {
				logger.log(Level.ERROR, e.getMessage());
			}
		} else if(ex instanceof BusinessException || ex instanceof ParameterException){ 
			modelAndView.setViewName("error/common");
			modelAndView.addObject("errMsg", ex.getMessage());
		} else {	// 其他类型异常处理
			modelAndView.setViewName("error/500");
			modelAndView.addObject("errMsg", ex.getMessage());
		}
		
		// 这里如果返回null会抛出异常
		return modelAndView;
	}

}