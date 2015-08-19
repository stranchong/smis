package com.smis.model;

import org.hibernate.validator.constraints.NotBlank;

public class StudentCourseKey {

	@NotBlank(message = "{student.sid.not.blank}")
	private String sid;

	@NotBlank(message = "{course.cid.not.blank}")
	private String cid;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid == null ? null : sid.trim();
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid == null ? null : cid.trim();
	}

}
