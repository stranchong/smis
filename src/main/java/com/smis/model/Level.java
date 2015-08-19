package com.smis.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Level {

	private Long lid;

	@NotBlank(message = "{level.lname.not.blank}")
	private String lname;

	@NotNull(message = "level.level.not.null")
	private Integer rank;

	public Long getLid() {
		return lid;
	}

	public void setLid(Long lid) {
		this.lid = lid;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname == null ? null : lname.trim();
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

}