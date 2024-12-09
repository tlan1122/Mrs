package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserParams;

import tk.mybatis.mapper.common.Mapper;
@Repository
public interface UserDao extends Mapper<User>{

	List<User> findby(@Param("params") UserParams userparams);

	@Select("select * from user where name=#{name} limit 1")
	User findbyname(@Param("name") String name);

	@Select("select * from user where loginname=#{loginname} limit 1")
	User findbyloginname(@Param("loginname") String loginname);

	@Select("select * from user where loginname=#{loginname} and password=#{password} limit 1")
	User findbynameandpassword(@Param("loginname") String loginname,@Param("password") String password);
}

