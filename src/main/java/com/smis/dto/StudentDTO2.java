package com.smis.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.smis.model.Student;

public class StudentDTO2 {

	@NotBlank(message = "{student.sid.not.blank}")
	@Length(min = 12, max = 12, message = "{student.sid.length}")
	private String sid;

	@Length(min = 6, max = 20, message = "student.password.length")
	private String password;

	private String sname;

	@NotBlank(message = "student.clid.not.blank")
	private String clid;

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

	public Student toStudent() {
		Student student = new Student();
		student.setSid(this.getSid());
		student.setSname(this.getSname());
		student.setPassword(this.getPassword());
		student.setClid(this.getClid());
		return student;
	}

}