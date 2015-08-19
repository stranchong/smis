package com.smis.model;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.annotation.JSONField;

public class Student {

	@NotBlank(message = "{student.sid.not.blank}")
	@Length(min = 12, max = 12, message = "{student.sid.length}")
	private String sid;

	@NotBlank(message = "{student.sid.not.blank}")
	@Length(min = 6, max = 20, message = "student.password.length")
	@JSONField(serialize = false)
	private String password;

	@NotBlank(message = "student.sname.not.blank")
	private String sname;

	@NotBlank(message = "student.clid.not.blank")
	private String clid;

	@JSONField(serialize = false)
	private Long rid;

	/*************** 分割线 **************/
	private Class clazz;

	@JSONField(serialize = false)
	private Role role;

	private List<Teacher> teachers;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid == null ? null : sid.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname == null ? null : sname.trim();
	}

	public String getClid() {
		return clid;
	}

	public void setClid(String clid) {
		this.clid = clid == null ? null : clid.trim();
	}

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

}