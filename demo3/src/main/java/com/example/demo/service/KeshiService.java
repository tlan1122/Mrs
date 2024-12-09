package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.dao.KeshiDao;
import com.example.demo.entity.Keshi;
import com.example.demo.entity.Params;
import com.example.demo.exception.CustomException;

import jakarta.annotation.Resource;
import com.github.pagehelper.*;
@Service
public class KeshiService {

	@Resource
	private KeshiDao KeshiDao;

	public List<Keshi> getAll() {
		// TODO Auto-generated method stub
//		return userDao.getUser();
		
		//使用引入的包，它包含userDao.getUser();
		return KeshiDao.selectAll();
	}

	public PageInfo<Keshi> findby(Params params) {
		// TODO Auto-generated method stub
		//分页查询,开启后自动查询
		PageHelper.startPage(params.getPageNum(),params.getPageSize());
		List<Keshi> list =KeshiDao.findby(params);
		return PageInfo.of(list);//包装分页信息
	}

	public void add(Keshi keshi) {
		System.out.println(keshi);
			//不允许同名
			Keshi noKeshi=KeshiDao.findbyname(keshi.getKeshiname());
			if(noKeshi !=null) {
				//有这个用户，提示前台
				throw new CustomException("该科室已经存在，无法添加");
				
			}
			KeshiDao.insertSelective(keshi);
	
	}

	public void update(Keshi keshi) {
		KeshiDao.updateByPrimaryKeySelective(keshi);
		
	}

	public void delete(Integer id) {
		KeshiDao.deleteByPrimaryKey(id);
	}


	public Keshi findByById(Integer id) {
		// TODO Auto-generated method stub
		return KeshiDao.selectByPrimaryKey(id);
	}

}

