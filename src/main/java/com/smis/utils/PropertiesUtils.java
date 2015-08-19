package com.smis.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesUtils {

	private static Properties props = null;

	public static final String NOT_PERMISSION_ACCESS = "not.permission.access";
	public static final String LOGIN_TIMEOUT = "login.timeout";
	public static final String LOGIN_FAILURE = "login.failure";
	public static final String LOGIN_SUCCESS = "login.success";
	public static final String LOGOUT_SUCCESS = "logout.success";

	public static final String ADD_SUCCESS = "add.success";
	public static final String ADD_SUCCESS_TWO = "add.success.two";
	public static final String UPDATE_SUCCESS = "update.success";
	public static final String DELETE_SUCCESS = "delete.success";

	public static final String BUSINESS_USER_NOT_EXIST = "business.user.not.exist";
	public static final String BUSINESS_RECORD_NOT_EXIST = "business.record.not.exist";
	public static final String BUSINESS_ADD_ERROR = "business.add.record.error";
	public static final String BUSINESS_DELETE_ERROR = "business.delete.record.error";
	public static final String BUSINESS_UPDATE_ERROR = "business.update.record.error";
	public static final String BUSINESS_SELECT_ONE_RECORD_ERROR = "business.select.one.record.error";
	public static final String BUSINESS_SELECT_PAGE_RECORD_ERROR = "business.select.page.record.error";
	public static final String BUSINESS_SELECT_ALL_RECORD_ERROR = "business.select.all.record.error";
	public static final String BUSINESS_SELECT_TOTAL_COUNT_ERROR = "business.select.total.count.error";

	public static final String APPLICATION_ADD_SUCCESS = "application.add.success";
	public static final String APPLICATION_DELETE_FAILURE = "application.delete.failure";
	public static final String APPLICATION_APPROVE_SUCCESS = "application.approve.success";
	public static final String APPLICATION_STARTDATE_NOT_NULL = "application.startDate.not.null";
	public static final String APPLICATION_STARTDATE_FORMAT_ERROR = "application.startDate.format.error";
	public static final String APPLICATION_STARTDATE_LT_TODAY = "application.startDate.lt.today";
	public static final String APPLICATION_OVERDATE_NOT_NULL = "application.overDate.not.null";
	public static final String APPLICATION_OVERDATE_FORMAT_ERROR = "application.overDate.format.error";
	public static final String APPLICATION_OVERDATE_LT_STARTDATE = "application.overDate.lt.startDate";
	public static final String APPLICATION_CAN_NOT_APPLY = "application.can.not.apply";
	public static final String APPLICATION_APPROVE_ALREADY_APPROVE = "application.approve.already.approve";
	public static final String APPLICATION_STARTDATE_LT_TODAY_CAN_NOT_APPROVE = "application.startDate.lt.today.can.not.approve";

	public static final String STUDENT_SID_NOT_NULL = "student.sid.not.null";
	public static final String STUDENT_SID_EXIST = "student.sid.exist";

	public static final String MANAGER_USERNAME_EXIST = "manager.username.exist";
	
	public static final String CLASS_CLID_EXIST = "class.clid.exist";

	public static final String COURSE_CID_EXIST = "course.cid.exist";
	public static final String COURSE_CREDIT_FORMAT = "course.credit.format";

	public static final String DAY_A = "day.a";
	public static final String DAY_B = "day.b";
	public static final String DAY_C = "day.c";
	public static final String DAY_D = "day.d";
	public static final String DAY_E = "day.e";
	public static final String DAY_F = "day.f";
	public static final String DAY_G = "day.g";

	

	static {
		try {
			props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/ValidationMessages.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取ValidationMessages.properties文件中对应Key的value
	 * 
	 * @param key
	 * @return
	 */
	public static String getValidationMessage(String key) {
		return props.getProperty(key);
	}

	/**
	 * 获取ValidationMessages.properties文件中countDay部分相应Key对应的value，并转化为Integer类型
	 * 
	 * @param key
	 * @return
	 */
	public static int getCountDayValidationMessage(String key) {
		return Integer.parseInt(props.getProperty(key));
	}

	/**
	 * 重新加载配置文件
	 */
	public static void reloadProperties() {
		try {
			props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/ValidationMessages.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}