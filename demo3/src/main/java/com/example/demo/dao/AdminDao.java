package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Params;

import tk.mybatis.mapper.common.Mapper;

//操作数据库表的实体层
@Repository
public interface AdminDao extends Mapper<Admin> {

	List<Admin> findby(@Param("params") Params params);

	@Select("select * from admin where name=#{name} limit 1")
	Admin findbyname(@Param("name") String name);

	@Select("select * from admin where loginname=#{loginname} limit 1")
	Admin findbyloginname(@Param("loginname") String loginname);

	@Select("select * from admin where loginname=#{loginname} and password=#{password} limit 1")
	Admin findbynameandpassword(@Param("loginname") String loginname,@Param("password") String password);


}
