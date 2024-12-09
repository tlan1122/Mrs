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

import com.example.demo.common.DoctorResult;
import com.example.demo.common.Result;
import com.example.demo.dao.DoctorDao;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.DoctorParams;
import com.example.demo.entity.Keshi;
import com.example.demo.entity.Params;
import com.example.demo.entity.Reserve;
import com.example.demo.service.DoctorService;
import com.example.demo.service.KeshiService;
import com.example.demo.service.ReserveService;
import com.github.pagehelper.PageInfo;

import jakarta.annotation.Resource;


@CrossOrigin//解决前后台跨域问题
@RestController
@RequestMapping("/reserve")
public class ReserveText {
	
	@Resource
	private ReserveService reserveService;
	
	@Resource
    private DoctorDao doctorDao;
	
	@PostMapping
	public Result addsu(@RequestBody Reserve reserve) {
		if(reserve.getId()==null) {
			reserveService.add(reserve);//新增数据
		}else {
			reserveService.update(reserve);//修改数据
		}
	    return Result.success();
	}
	
	
	@GetMapping
	public Result getAll() {
		List<Reserve> list =reserveService.getAll();
		return Result.success(list);
	}
	
	@GetMapping("/search")
	public Result findby(Params params) {
		PageInfo<Reserve> list =reserveService.findby(params);
		return Result.success(list);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Integer id) {
		reserveService.delete(id);
		return Result.success();
		
	}
	
	
}
