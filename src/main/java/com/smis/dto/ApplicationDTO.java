package com.smis.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 学生修改请假信息的DTO
 * 
 * @author acer
 * 
 */
public class ApplicationDTO {

	@NotNull(message = "{application.aid.not.null}")
	private Long aid;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date overDate;

	@NotBlank(message = "{application.leaveReason.not.blank}")
	private String leaveReason;

	@NotBlank(message = "{application.leavePlace.not.blank}")
	private String leavePlace;

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getOverDate() {
		return overDate;
	}

	public void setOverDate(Date overDate) {
		this.overDate = overDate;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason == null ? null : leaveReason.trim();
	}

	public String getLeavePlace() {
		return leavePlace;
	}

	public void setLeavePlace(String leavePlace) {
		this.leavePlace = leavePlace == null ? null : leavePlace.trim();
	}

}