package com.smis.dto;

import javax.validation.constraints.NotNull;

import com.smis.model.Manager;

public class ManagerDTO {

	@NotNull(message = "{manager.mid.not.null}")
	private Long mid;

	private String username;

	private String password;

	private Long rid;

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
	
	public Manager toManager() {
		Manager manager = new Manager();
		manager.setMid(this.getMid());
		manager.setUsername(this.getUsername());
		manager.setPassword(this.getPassword());
		return manager;
	}

}