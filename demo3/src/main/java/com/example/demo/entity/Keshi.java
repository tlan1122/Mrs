package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "keshi")
public class Keshi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//自增的主键
	private Integer id;
	
	@Column(name = "Keshiname")
	private String Keshiname;
	
	@Column(name = "keshimiaoshu")
	private String keshimiaoshu;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeshiname() {
		return Keshiname;
	}

	public void setKeshiname(String keshiname) {
		Keshiname = keshiname;
	}

	public String getKeshimiaoshu() {
		return keshimiaoshu;
	}

	public void setKeshimiaoshu(String keshimiaoshu) {
		this.keshimiaoshu = keshimiaoshu;
	}

	
}
