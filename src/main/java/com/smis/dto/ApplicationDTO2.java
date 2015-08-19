package com.smis.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 中队长修改学生请假信息的DTO
 * 
 * @author acer
 * 
 */
public class ApplicationDTO2 {

	@NotNull(message = "{application.aid.not.null}")
	private Long aid;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@NotNull(message = "{application.countDay.not.null}")
	private Integer countDay;

	@NotNull(message = "{application.confirmMark.not.null}")
	private Integer confirmMark;

	@NotBlank(message = "{application.confirmTid.not.blank}")
	private String confirmTid;

	private String confirmReply;

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

	public Integer getCountDay() {
		return countDay;
	}

	public void setCountDay(Integer countDay) {
		this.countDay = countDay;
	}

	public Integer getConfirmMark() {
		return confirmMark;
	}

	public void setConfirmMark(Integer confirmMark) {
		this.confirmMark = confirmMark;
	}

	public String getConfirmTid() {
		return confirmTid;
	}

	public void setConfirmTid(String confirmTid) {
		this.confirmTid = confirmTid;
	}

	public String getConfirmReply() {
		return confirmReply;
	}

	public void setConfirmReply(String confirmReply) {
		this.confirmReply = confirmReply == null ? null : confirmReply.trim();
	}

}