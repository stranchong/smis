package com.smis.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 教职工修改密码
 * 
 * @author acer
 * 
 */
public class TeacherDTO {

	@NotBlank(message = "{student.sid.not.blank}")
	private String tid;

	@NotBlank(message = "{student.password.not.blank}")
	@Length(min = 6, max = 20, message = "{student.password.length}")
	private String password;

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

}