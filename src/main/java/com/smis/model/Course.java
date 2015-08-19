package com.smis.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class Course {

	@NotBlank(message = "{course.cid.not.blank}")
	@Length(min = 6, max = 20, message = "{course.cid.length}")
	private String cid;

	@NotBlank(message = "{course.cname.not.blank}")
	private String cname;

	@NotNull(message = "{course.credit.not.null}")
	// @Pattern(regexp = "\\d*(\\.(5|0))?", message = "{course.credit.format}")
	// @DecimalMin(value = "0.5", message = "{course.credit.format}")
	private Double credit;

	@NotNull(message = "{course.credit.hours.not.null}")
	@Min(value = 1, message = "{course.credit.hours.format}")
	private Integer creditHours;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid == null ? null : cid.trim();
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname == null ? null : cname.trim();
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