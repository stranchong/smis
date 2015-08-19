package com.smis.utils;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.smis.exception.ParameterJsonException;

public class ControllerUtils {

	/**
	 * 将result中所有的数据验证错误信息拼接成一个字符串
	 * 
	 * @param result
	 * @return
	 */
	public static String convertBindingResultToString(BindingResult result) {
		List<FieldError> errors = result.getFieldErrors();
		StringBuffer stringBuffer = new StringBuffer();
		for (FieldError error : errors) {
			stringBuffer.append(error.getDefaultMessage() + ",");
		}
		return stringBuffer.substring(0, stringBuffer.length() - 1);
	}

	/**
	 * 对BindingResult的验证结果进行判断，如果验证结果中有没通过的，那么抛出没通过的异常
	 * 
	 * @param result
	 * @throws Exception
	 */
	public static void handleBindingResult(BindingResult result) throws Exception {
		if (result.hasErrors()) {
			List<FieldError> errors = result.getFieldErrors();
			StringBuffer stringBuffer = new StringBuffer();
			for (FieldError error : errors) {
				stringBuffer.append(error.getDefaultMessage() + ",");
			}
			throw new ParameterJsonException(stringBuffer.substring(0, stringBuffer.length() - 1));
		}
	}

	/**
	 * 对参数进行正则表达式验证，验证不通过就抛出异常
	 * 
	 * @param args
	 *            参数值
	 * @param pattern
	 *            正则表达式
	 * @param errMsg
	 *            异常错误信息
	 * @throws Exception
	 */
	public static void validateArgumentByRegex(String args, String pattern, String errMsg) throws Exception {
		if (!((String) args).matches(pattern)) {
			throw new ParameterJsonException(PropertiesUtils.getValidationMessage(errMsg));
		}
	}

}