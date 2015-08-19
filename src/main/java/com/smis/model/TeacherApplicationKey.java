package com.smis.model;

public class TeacherApplicationKey {
	private String tid;

	private Long aid;

	public TeacherApplicationKey() {
	}

	public TeacherApplicationKey(Long aid) {
		this.aid = aid;
	}

	public TeacherApplicationKey(String tid) {
		this.tid = tid;
	}

	public TeacherApplicationKey(String tid, Long aid) {
		this.tid = tid;
		this.aid = aid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid == null ? null : tid.trim();
	}

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}
}