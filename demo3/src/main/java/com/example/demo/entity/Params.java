package com.example.demo.entity;

public class Params {

	private String name;
	private String phone;
	private String keshiname;
	private String keshimiaoshu;
	
	private Integer pageNum;
	private Integer pageSize;
	
	
	

	
	public String getKeshiname() {
		return keshiname;
	}
	public void setKeshiname(String keshiname) {
		this.keshiname = keshiname;
	}
	public String getKeshimiaoshu() {
		return keshimiaoshu;
	}
	public void setKeshimiaoshu(String keshimiaoshu) {
		this.keshimiaoshu = keshimiaoshu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
