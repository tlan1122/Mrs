package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDao;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Params;
import com.example.demo.exception.CustomException;

import jakarta.annotation.Resource;
import com.github.pagehelper.*;
@Service
public class AdminService {

	@Resource
	private AdminDao AdminDao;

	public List<Admin> getAll() {
		// TODO Auto-generated method stub
//		return userDao.getUser();
		
		//使用引入的包，它包含userDao.getUser();
		return AdminDao.selectAll();
	}

	public PageInfo<Admin> findby(Params params) {
		// TODO Auto-generated method stub
		//分页查询,开启后自动查询
		PageHelper.startPage(params.getPageNum(),params.getPageSize());
		List<Admin> list =AdminDao.findby(params);
		return PageInfo.of(list);//包装分页信息
	}

	public void add(Admin admin) {
		System.out.println(admin);
		if("123456".equals(admin.getAdminpassword())) {
			//不允许同名
			Admin noadmin=AdminDao.findbyname(admin.getName());
			if(noadmin !=null) {
				//有这个用户，提示前台
				throw new CustomException("该用户名已经存在，无法添加");
				
			}
			
			if(admin.getLoginname()==null || "".equals(admin.getLoginname())) {
				throw new CustomException("登录名不能为空");
			}
			Admin noadmin2=AdminDao.findbyloginname(admin.getLoginname());
			if(noadmin2 !=null) {
				//有这个用户，提示前台
				throw new CustomException("该登录名已经存在，无法添加");
				
			}
			if(admin.getPassword()==null) {
				admin.setPassword("123456");
			}
			AdminDao.insertSelective(admin);
		}else {
			throw new CustomException("请输入正确的管理人员密码");
		}
	
	}

	public void update(Admin admin) {
		AdminDao.updateByPrimaryKeySelective(admin);
		
	}

	public void delete(Integer id) {
		AdminDao.deleteByPrimaryKey(id);
	}

	public Admin login(Admin admin) {
		//非空判断
		if(admin.getLoginname()==null || "".equals(admin.getLoginname())) {
			throw new CustomException("账号不能为空");
		}
		if(admin.getPassword()==null || "".equals(admin.getPassword())) {
			throw new CustomException("密码不能为空");
		}
		
		//查询是否正确
		Admin adminlogin = AdminDao.findbynameandpassword(admin.getLoginname(),admin.getPassword());
		if(adminlogin == null) {
			throw new CustomException("账号或密码错误");
		}
		return adminlogin;
	}

	public Admin findByById(Integer id) {
		// TODO Auto-generated method stub
		return AdminDao.selectByPrimaryKey(id);
	}
}
