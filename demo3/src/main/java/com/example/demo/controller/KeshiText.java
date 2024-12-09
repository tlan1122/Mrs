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
import com.example.demo.entity.Keshi;
import com.example.demo.entity.Params;

import com.example.demo.service.KeshiService;
import com.github.pagehelper.PageInfo;

import jakarta.annotation.Resource;


@CrossOrigin//解决前后台跨域问题
@RestController
@RequestMapping("/keshi")
public class KeshiText {
	
	@Resource
	private KeshiService keshiService;
	
	@PostMapping
	public Result addsu(@RequestBody Keshi keshi) {
		if(keshi.getId()==null) {
			keshiService.add(keshi);//新增数据
		}else {
			keshiService.update(keshi);//修改数据
		}
		return Result.success();
		
	}
	
	@GetMapping
	public Result getAll() {
		List<Keshi> list =keshiService.getAll();
		return Result.success(list);
	}
	
	@GetMapping("/search")
	public Result findby(Params params) {
		PageInfo<Keshi> list =keshiService.findby(params);
		return Result.success(list);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Integer id) {
		keshiService.delete(id);
		return Result.success();
		
	}
	
	
}
