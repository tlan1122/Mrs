package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "doctor")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//自增的主键
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "loginname")
	private String loginname;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "sex")
	private String sex;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "posts")
	private String posts;
	
	@Column(name = "img")
	private String img;
	
	@Column(name = "typeId")
	private Integer typeId;
	
	@Transient//与数据库无关
	private String typeName;
	
	@Column(name = "num")
	private Integer num;


	@Column(name = "qian")
	private Integer qian;
	
	public Integer getNum() {
		return num;
	}

	
	public Integer getQian() {
		return qian;
	}


	public void setQian(Integer qian) {
		this.qian = qian;
	}

	public void setNum(Integer num) {
		this.num = num;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public Integer getTypeId() {
		return typeId;
	}


	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}

	@Transient
	private String token;

	
	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Integer getId() {
		return id;
	}

	
	public String getLoginname() {
		return loginname;
	}


	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPosts() {
		return posts;
	}

	public void setPosts(String posts) {
		this.posts = posts;
	}
}
