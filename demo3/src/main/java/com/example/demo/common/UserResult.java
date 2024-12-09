package com.example.demo.common;

public class UserResult {
	private static final String SUCCESS ="0";
	private static final String ERROR ="-1";
	
	private String code;//返回是否成功
	private String msg;//后台的报错信息
	private Object data;//任何数据类型
	
	public static UserResult success() {
		UserResult Userresult =new UserResult();
		Userresult.setCode(SUCCESS);
		return Userresult;
	}
	
	public static UserResult success(Object data) {
		UserResult Userresult =new UserResult();
		Userresult.setCode(SUCCESS);
		Userresult.setData(data);
		return Userresult;
	}
	
	public static UserResult error(String msg) {
		UserResult Userresult =new UserResult();
		Userresult.setCode(ERROR);
		Userresult.setMsg(msg);
		return Userresult;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
