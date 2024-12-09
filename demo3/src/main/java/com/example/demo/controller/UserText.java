package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.Result;
import com.example.demo.common.UserResult;
import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.entity.UserParams;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;

import jakarta.annotation.Resource;

@CrossOrigin//解决前后台跨域问题
@RestController
@RequestMapping("/user")
public class UserText {
	@Resource
	private UserService userService;
	
	@PostMapping
	public UserResult addsu(@RequestBody User user) {
		if(user.getId()==null) {
			userService.add(user);//新增数据
		}else {
			userService.update(user);//修改数据
		}
		return UserResult.success();
		
	}
	
	@PostMapping("/login")
	public UserResult login(@RequestBody User user) {
		User loginuser =userService.login(user);
		return UserResult.success(loginuser);
		
	}
	
	@PostMapping("/register")
	public UserResult register(@RequestBody User user) {
		userService.add(user);
		return UserResult.success();
		
	}
	
	@GetMapping
	public UserResult getAll() {
		List<User> list =userService.getAll();
		return UserResult.success(list);
	}
	
	@GetMapping("/search")
	public UserResult findby(UserParams userparams) {
		PageInfo<User> list =userService.findby(userparams);
		return UserResult.success(list);
	}
	
	@DeleteMapping("/{id}")
	public UserResult delete(@PathVariable Integer id) {
		userService.delete(id);
		return UserResult.success();
	}
}
