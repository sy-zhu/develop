package com.develop.model;

public class RestBody {
	private int code;
	private String msg;
	private Object body;

	public RestBody() {
		super();
	}

	public RestBody(int code) {
		this.code = code;
	}

	public RestBody(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public RestBody(Object body) {
		this.code = 1;
		this.body = body;
	}

	public RestBody(int code, String msg, Object body) {
		super();
		this.code = code;
		this.msg = msg;
		this.body = body;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

}
