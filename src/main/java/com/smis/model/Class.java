package com.smis.model;

import org.hibernate.validator.constraints.NotBlank;

public class Class {

	@NotBlank(message = "{class.clid.not.blank}")
	private String clid;

	@NotBlank(message = "{class.clname.not.blank}")
	private String clname;

	public String getClid() {
		return clid;
	}

	public void setClid(String clid) {
		this.clid = clid == null ? null : clid.trim();
	}

	public String getClname() {
		return clname;
	}

	public void setClname(String clname) {
		this.clname = clname == null ? null : clname.trim();
	}

}