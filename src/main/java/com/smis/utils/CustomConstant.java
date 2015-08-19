package com.smis.utils;

public class CustomConstant {
	public final static String SESSION_ATTRIBUTE_KEY_STUDENT = "student";
	public final static String SESSION_ATTRIBUTE_KEY_TEACHER = "teacher";
	public final static String SESSION_ATTRIBUTE_KEY_MANAGER = "manager";
	public final static String SESSION_ATTRIBUTE_KEY_PERMISSION = "permission";

	public final static Integer ROLE_PERMISSION_ADMIN = 0; // 管理员
	public final static Integer ROLE_PERMISSION_TEACHER = 1; // 教职工
	public final static Integer ROLE_PERMISSION_STUDENT = 2; // 学生

	public final static Integer STATE_AGREE = 0; // 同意
	public final static Integer STATE_PROPOSED_AGREE = 1; // 拟同意
	public final static Integer STATE_DISAGREE = 2; // 不同意
	public final static Integer STATE_DO_NOT_APPROVE = 3; // 未审核

	public final static Integer SCHOOL_LEADER = 0; // 校领导
	public final static Integer FACULTY_LEADER = 1; // 系主任
	public final static Integer BATTALION_LEADER = 2; // 大队长
	public final static Integer SQUADRON_LEADER = 3; // 中队长

	public final static Integer NOT_CONFIRM = 0; // 未审批
	public final static Integer ALREADY_CONFIRMED = 1; // 已审批

}
