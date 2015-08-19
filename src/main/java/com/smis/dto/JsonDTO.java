package com.smis.dto;

public class JsonDTO {
	private boolean success;
	private String msg;
	private Object obj;

	public JsonDTO() {
		this.success = true;
	}

	public JsonDTO(String msg) {
		this.success = true;
		this.msg = msg;
	}

	public JsonDTO(String msg, Object obj) {
		this.success = true;
		this.msg = msg;
		this.obj = obj;
	}

	public JsonDTO(Object obj) {
		this.success = true;
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}