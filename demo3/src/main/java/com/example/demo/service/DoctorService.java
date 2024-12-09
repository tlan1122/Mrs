package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.DoctorDao;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.DoctorParams;
import com.example.demo.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import jakarta.annotation.Resource;

@Service
public class DoctorService {

	@Resource
	private DoctorDao DoctorDao;

	public List<Doctor> getAll() {
		// TODO Auto-generated method stub
//		return userDao.getUser();
		
		//使用引入的包，它包含userDao.getUser();
		return DoctorDao.selectAll();
	}

	public PageInfo<Doctor> findby(DoctorParams doctorparams) {
		// TODO Auto-generated method stub
		//分页查询,开启后自动查询
		PageHelper.startPage(doctorparams.getPageNum(),doctorparams.getPageSize());
		List<Doctor> list =DoctorDao.findby(doctorparams);
		return PageInfo.of(list);//包装分页信息
	}

	public void add(Doctor doctor) {
		Doctor nodoctor =DoctorDao.findbyname(doctor.getName());
		if(nodoctor !=null) {
			//有这个用户，提示前台
			throw new CustomException("该用户名已经存在，无法添加");
			
		}
		
		if(doctor.getLoginname()==null || "".equals(doctor.getLoginname())) {
			throw new CustomException("登录名不能为空");
		}
		Doctor nodoctor2 =DoctorDao.findbyLoginname(doctor.getLoginname());
		if(nodoctor2 !=null) {
			//有这个用户，提示前台
			throw new CustomException("该登录名已经存在，无法添加");
			
		}
		
		if(doctor.getPassword()==null) {
			doctor.setPassword("123456");
		}
		DoctorDao.insertSelective(doctor);
		
	}

	public void update(Doctor doctor) {
		DoctorDao.updateByPrimaryKeySelective(doctor);
	}

	public void delete(Integer id) {
		DoctorDao.deleteByPrimaryKey(id);
	}

	public Doctor login(Doctor doctor) {
		//非空判断
				if(doctor.getLoginname()==null || "".equals(doctor.getLoginname())) {
					throw new CustomException("账号不能为空");
				}
				if(doctor.getPassword()==null || "".equals(doctor.getPassword())) {
					throw new CustomException("密码不能为空");
				}
				
				
				Doctor doctorlogin = DoctorDao.findbynameandpassword(doctor.getLoginname(),doctor.getPassword());
				if(doctorlogin == null) {
					throw new CustomException("账号或密码错误");
				}
				return doctorlogin;
	}
	

	public void updateNum(int id, int num) {
	    DoctorDao.updateNum(id, num);
	}
	public Doctor findByById(Integer id) {
		// TODO Auto-generated method stub
		return DoctorDao.selectByPrimaryKey(id);
	}

	public List<Doctor> findByKeshiname(String keshiname) {
        return DoctorDao.findByKeshiname(keshiname);
    }
	
}
