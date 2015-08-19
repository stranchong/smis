package com.smis.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 学生修改密码
 * @author acer
 *
 */
public class StudentDTO {

	@NotBlank(message = "{student.sid.not.blank}")
	private String sid;

	@NotBlank(message = "{student.password.not.blank}")
	@Length(min = 6, max = 20, message = "{student.password.length}")
	private String password;

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

}