package com.example.demo.common;

public class DoctorResult {
	private static final String SUCCESS ="0";
	private static final String ERROR ="-1";
	
	private String code;//返回是否成功
	private String msg;//后台的报错信息
	private Object data;//任何数据类型
	
	public static DoctorResult success() {
		DoctorResult Doctorresult =new DoctorResult();
		Doctorresult.setCode(SUCCESS);
		return Doctorresult;
	}
	
	public static DoctorResult success(Object data) {
		DoctorResult Doctorresult =new DoctorResult();
		Doctorresult.setCode(SUCCESS);
		Doctorresult.setData(data);
		return Doctorresult;
	}
	
	public static DoctorResult error(String msg) {
		DoctorResult Doctorresult =new DoctorResult();
		Doctorresult.setCode(ERROR);
		Doctorresult.setMsg(msg);
		return Doctorresult;
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
