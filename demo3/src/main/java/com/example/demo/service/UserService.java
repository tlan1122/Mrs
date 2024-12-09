package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.entity.UserParams;
import com.example.demo.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import jakarta.annotation.Resource;

@Service
public class UserService {

	@Resource
	private UserDao Userdao;

	public List<User> getAll() {
		// TODO Auto-generated method stub
//		return userDao.getUser();
		
		//使用引入的包，它包含userDao.getUser();
		return Userdao.selectAll();
	}

	public PageInfo<User> findby(UserParams userparams) {
		// TODO Auto-generated method stub
		//分页查询,开启后自动查询
		PageHelper.startPage(userparams.getPageNum(),userparams.getPageSize());
		List<User> list =Userdao.findby(userparams);
		return PageInfo.of(list);//包装分页信息
	}

	public void add(User user) {
		User nouser = Userdao.findbyname(user.getName());
		if(nouser !=null) {
			//有这个用户，提示前台
			throw new CustomException("该用户名已经存在，无法添加");
			
		}
		
		//登录名不能为空
		if(user.getLoginname()==null || "".equals(user.getLoginname())) {
			throw new CustomException("登录名不能为空");
		}
		User nouser2 = Userdao.findbyloginname(user.getLoginname());
		if(nouser2 !=null) {
			//有这个用户，提示前台
			throw new CustomException("该登录名已经存在，无法添加");
			
		}
		
		if(user.getPassword()==null) {
			user.setPassword("123456");
		}
		Userdao.insertSelective(user);
		
	}

	public void update(User user) {
		
		// 获取数据库中当前的登录名
	    User existingUser = Userdao.findbyloginname(user.getLoginname());
	    // 检查existingUser是否为null以及user的loginname是否被修改
	    if (existingUser != null && (user.getId() == null || !user.getId().equals(existingUser.getId()))) {
	        throw new CustomException("该登录名已经存在，无法修改");
	    }
	    Userdao.updateByPrimaryKeySelective(user);
		
	}

	public void delete(Integer id) {
		Userdao.deleteByPrimaryKey(id);
	}

	public User login(User user) {
		//非空判断
		if(user.getLoginname()==null || "".equals(user.getLoginname())) {
			throw new CustomException("账号不能为空");
		}
		if(user.getPassword()==null || "".equals(user.getPassword())) {
			throw new CustomException("密码不能为空");
		}
				
		//查询是否正确		
		User userlogin = Userdao.findbynameandpassword(user.getLoginname(),user.getPassword());
			if(userlogin == null) {
			throw new CustomException("账号或密码错误");
		}
				
		
		return userlogin;
	}
}
