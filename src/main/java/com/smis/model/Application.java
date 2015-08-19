package com.smis.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class Application {

	private Long aid;

	@NotBlank(message = "{application.sid.not.blank}")
	private String sid;

	@JSONField(format = "yyyy-MM-dd")
	private Date createDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JSONField(format = "yyyy-MM-dd")
	private Date startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JSONField(format = "yyyy-MM-dd")
	private Date overDate;

	private Integer countDay;

	@NotBlank(message = "{application.leaveReason.not.blank}")
	private String leaveReason;

	@NotBlank(message = "{application.leavePlace.not.blank}")
	private String leavePlace;

	private String confirmTid;

	private Integer confirmMark = 3;

	private String confirmName;

	private String confirmReply;

	@JSONField(format = "yyyy-MM-dd")
	private Date confirmDate;

	/** 学生选择的审核教师tid */
	@NotBlank(message = "{application.tid.not.blank}")
	private String tid;

	/** 关联表属性，返回给客户端的 */
	private String sname;
	private String clname;

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid == null ? null : sid.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public Integer getCountDay() {
		return countDay;
	}

	public void setCountDay(Integer countDay) {
		this.countDay = countDay;
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
		this.confirmTid = confirmTid == null ? null : confirmTid.trim();
	}

	public String getConfirmName() {
		return confirmName;
	}

	public void setConfirmName(String confirmName) {
		this.confirmName = confirmName == null ? null : confirmName.trim();
	}

	public String getConfirmReply() {
		return confirmReply;
	}

	public void setConfirmReply(String confirmReply) {
		this.confirmReply = confirmReply == null ? null : confirmReply.trim();
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getClname() {
		return clname;
	}

	public void setClname(String clname) {
		this.clname = clname;
	}

}