package com.smis.model;

import org.hibernate.validator.constraints.NotBlank;

public class Faculty {

	private Long fid;

	@NotBlank(message = "{faculty.fname.not.blank}")
	private String fname;

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname == null ? null : fname.trim();
	}

}