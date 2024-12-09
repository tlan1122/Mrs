package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Doctor;
import com.example.demo.entity.DoctorParams;

import tk.mybatis.mapper.common.Mapper;
@Repository
public interface DoctorDao extends Mapper<Doctor>{

	List<Doctor> findby(@Param("params") DoctorParams doctorparams);

	@Select("select * from doctor where name=#{name} limit 1")
	Doctor findbyname(@Param("name") String name);

	@Select("select * from doctor where loginname=#{loginname} limit 1")
	Doctor findbyLoginname(@Param("loginname")String loginname);

	@Select("select * from doctor where loginname=#{loginname} and password=#{password} limit 1")
	Doctor findbynameandpassword(@Param("loginname") String loginname,@Param("password") String password);
	
	List<Doctor> findByKeshiname(@Param("keshiname") String keshiname);
	
	int updateNum(@Param("id") int id, @Param("num") int num);
}
