package com.smis.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class StudentCourse extends StudentCourseKey {

	@NotNull(message = "{student.course.score.not.null}")
	@Range(min = 0, max = 100, message = "{student.course.score.between.0.and.100}")
	private Integer score;

	/** 内连接的外表的字段 */
	private String cname;
	private String clname;
	private String sname;
	private Double credit;
	private Integer creditHours;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getClname() {
		return clname;
	}

	public void setClname(String clname) {
		this.clname = clname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	public Integer getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(Integer creditHours) {
		this.creditHours = creditHours;
	}

}