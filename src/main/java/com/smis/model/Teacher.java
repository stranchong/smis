package com.smis.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.annotation.JSONField;

public class Teacher {

	@NotBlank(message = "{teacher.tid.not.blank}")
	@Length(min = 6, max = 20, message = "{teacher.tid.length}")
	private String tid;

	@NotBlank(message = "{teacher.password.not.blank}")
	@Length(min = 6, max = 20, message = "teacher.password.length")
	@JSONField(serialize = false)
	private String password;

	@NotBlank(message = "{teacher.tname.not.blank}")
	private String tname;

	@NotNull(message = "{teacher.isManager.not.null}")
	private Boolean isManager;

	@NotNull(message = "{teacher.isTeacher.not.null}")
	private Boolean isTeacher;

	@JSONField(serialize = false)
	private Long rid;

	/** 关联表 */
	@JSONField(serialize = false)
	private Role role;
	private Faculty faculty;
	private Level level;
	private List<Course> courses;
	private List<Class> classes;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid == null ? null : tid.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname == null ? null : tname.trim();
	}

	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	public Boolean getIsTeacher() {
		return isTeacher;
	}

	public void setIsTeacher(Boolean isTeacher) {
		this.isTeacher = isTeacher;
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

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Class> getClasses() {
		return classes;
	}

	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

}