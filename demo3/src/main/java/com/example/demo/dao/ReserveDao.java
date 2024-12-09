package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Keshi;
import com.example.demo.entity.Params;
import com.example.demo.entity.Reserve;

import tk.mybatis.mapper.common.Mapper;

//操作数据库表的实体层
@Repository
public interface ReserveDao extends Mapper<Reserve>{

	List<Reserve> findby(@Param("params") Params params);

	@Select("SELECT * FROM Reserve WHERE time = #{time} AND userId = #{userId} LIMIT 1")
	Reserve findbytimeAndUser(@Param("time") String time, @Param("userId") Integer userId);

	@Select("SELECT * FROM Reserve WHERE doctor = #{doctor} AND userId = #{userId} LIMIT 1")
	Reserve findbydoctorAndUser(@Param("doctor") String doctor, @Param("userId") Integer userId);
}
