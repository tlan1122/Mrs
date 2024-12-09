package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.DoctorResult;
import com.example.demo.common.Result;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.DoctorParams;
import com.example.demo.service.DoctorService;
import com.github.pagehelper.PageInfo;

import jakarta.annotation.Resource;

@CrossOrigin//解决前后台跨域问题
@RestController
@RequestMapping("/doctor")
public class DoctorText {
	@Resource
	private DoctorService doctorService;
	
	@PostMapping("/login")
	public DoctorResult login(@RequestBody Doctor doctor) {
		Doctor Doctorlogin =doctorService.login(doctor);
		return DoctorResult.success(Doctorlogin);
		
	}
	
	@PostMapping("/register")
	public DoctorResult register(@RequestBody Doctor doctor) {
		doctorService.add(doctor);
		return DoctorResult.success();
		
	}

	@PostMapping("/updateNum")
	public DoctorResult updateNum(@RequestBody Doctor doctor) {
	    doctorService.updateNum(doctor.getId(), doctor.getNum());
	    return DoctorResult.success();
	}
	
	@PostMapping
	public DoctorResult addsu(@RequestBody Doctor doctor) {
		if(doctor.getId()==null) {
			doctorService.add(doctor);//新增数据
		}else {
			doctorService.update(doctor);//修改数据
		}
		return DoctorResult.success();
		
	}
	
	@GetMapping
	public DoctorResult getAll() {
		List<Doctor> list =doctorService.getAll();
		return DoctorResult.success(list);
	}
	
	  @GetMapping("/findByKeshiname")
	    public Result findByKeshiname(@RequestParam String keshiname) {
	        List<Doctor> doctors = doctorService.findByKeshiname(keshiname);
	        return Result.success(doctors);
	    }
	
	@GetMapping("/search")
	public DoctorResult findby(DoctorParams doctorparams) {
		PageInfo<Doctor> list =doctorService.findby(doctorparams);
		return DoctorResult.success(list);
	}
	
	@DeleteMapping("/{id}")
	public DoctorResult delete(@PathVariable Integer id) {
		doctorService.delete(id);
		return DoctorResult.success();
		
	}

}
