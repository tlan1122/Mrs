package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Keshi;
import com.example.demo.entity.Params;

import tk.mybatis.mapper.common.Mapper;

//操作数据库表的实体层
@Repository
public interface KeshiDao extends Mapper<Keshi>{

	List<Keshi> findby(@Param("params") Params params);

	@Select("select * from keshi where keshiname=#{keshiname} limit 1")
	Keshi findbyname(@Param("keshiname") String keshiname);
}
