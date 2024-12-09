package com.example.demo.exception;

//自定义异常
public class CustomException extends RuntimeException {
	
	private String msg;
	
	public CustomException(String msg) {
		this.msg=msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
