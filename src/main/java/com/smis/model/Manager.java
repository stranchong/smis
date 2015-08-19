package com.smis.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.annotation.JSONField;

public class Manager {

	private Long mid;

	@NotBlank(message = "{manager.username.not.blank}")
	private String username;

	@NotBlank(message = "manager.password.not.blank")
	@Length(min = 6, max = 20, message = "{manager.password.length}")
	@JSONField(serialize = false)
	private String password;

	@JSONField(serialize = false)
	private Long rid;

	/** */
	private Role role;

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
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

}