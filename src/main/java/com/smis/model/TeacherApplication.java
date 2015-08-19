package com.smis.model;

public class TeacherApplication extends TeacherApplicationKey {
	private Integer hasConfirmed;

	public TeacherApplication() {
		super();
	}

	public TeacherApplication(Long aid) {
		super(aid);
	}

	public TeacherApplication(String tid) {
		super(tid);
	}

	public TeacherApplication(String tid, Long aid, Integer hasConfirmed) {
		super(tid, aid);
		this.hasConfirmed = hasConfirmed;
	}

	public TeacherApplication(String tid, Long aid) {
		super(tid, aid);
		this.hasConfirmed = 0;
	}

	public Integer getHasConfirmed() {
		return hasConfirmed;
	}

	public void setHasConfirmed(Integer hasConfirmed) {
		this.hasConfirmed = hasConfirmed;
	}
}