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
import com.example.demo.entity.Admin;
import com.example.demo.entity.Params;
import com.example.demo.service.AdminService;
import com.github.pagehelper.PageInfo;

import jakarta.annotation.Resource;


@CrossOrigin//解决前后台跨域问题
@RestController
@RequestMapping("/admin")
public class AdminText {
	
	@Resource
	private AdminService adminService;
	
	@PostMapping("/login")
	public Result login(@RequestBody Admin admin) {
		Admin loginAdmin =adminService.login(admin);
		return Result.success(loginAdmin);
		
	}
	
	@PostMapping("/register")
	public Result register(@RequestBody Admin admin) {
		adminService.add(admin);
		return Result.success();
		
	}
	
	@PostMapping
	public Result addsu(@RequestBody Admin admin) {
		if(admin.getId()==null) {
			adminService.add(admin);//新增数据
		}else {
			adminService.update(admin);//修改数据
		}
		return Result.success();
		
	}
	
	@GetMapping
	public Result getAll() {
		List<Admin> list =adminService.getAll();
		return Result.success(list);
	}
	
	@GetMapping("/search")
	public Result findby(Params params) {
		PageInfo<Admin> list =adminService.findby(params);
		return Result.success(list);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Integer id) {
		adminService.delete(id);
		return Result.success();
		
	}
	
	
}