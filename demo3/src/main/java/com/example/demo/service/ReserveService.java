package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DoctorDao;
import com.example.demo.dao.ReserveDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Keshi;
import com.example.demo.entity.Params;
import com.example.demo.entity.Reserve;
import com.example.demo.exception.CustomException;

import jakarta.annotation.Resource;
import com.github.pagehelper.*;

import cn.hutool.core.util.ObjectUtil;
@Service
public class ReserveService {

	@Resource
	private ReserveDao ReserveDao;
	
	@Resource
    private DoctorService doctorService;

	public List<Reserve> getAll() {
		// TODO Auto-generated method stub
//		return userDao.getUser();
		
		//使用引入的包，它包含userDao.getUser();
		return ReserveDao.selectAll();
	}
	
	public void add(Reserve Reserve) {
		// 检查医生ID是否为空
        if (Reserve.getDoctorId() == null) {
            throw new CustomException("预约请求中缺少医生ID");
        }
        if (Reserve.getStatus() == null) {
            Reserve.setStatus("未就诊");
        }

        // 根据医生ID查询医生信息
        Doctor doctor = doctorService.findByById(Reserve.getDoctorId());

        // 检查医生是否存在以及位置剩余数是否大于0
        if (doctor == null || doctor.getNum() <= 0) {
            throw new CustomException("医生的位置已满或医生不存在");
        }

        // 检查是否已经存在相同时间的预约
        Reserve existingReserve = ReserveDao.findbytimeAndUser(Reserve.getTime(), Reserve.getUserId());
        Reserve existingReserve2 = ReserveDao.findbydoctorAndUser(Reserve.getDoctor(), Reserve.getUserId());
        if (existingReserve != null || existingReserve2 != null) {
            throw new CustomException("您这个时间已经预约，请勿重复预约");
        }

        // 如果医生有剩余位置且没有重复预约，则减少医生的位置剩余数并添加预约
        doctor.setNum(doctor.getNum() - 1);
        doctorService.update(doctor); // 更新医生信息
        ReserveDao.insertSelective(Reserve); // 添加预约

        System.out.println(Reserve);
	}

	public PageInfo<Reserve> findby(Params params) {
		// TODO Auto-generated method stub
		//分页查询,开启后自动查询
		PageHelper.startPage(params.getPageNum(),params.getPageSize());
		List<Reserve> list =ReserveDao.findby(params);
		return PageInfo.of(list);//包装分页信息
		
	}

	public void update(Reserve Reserve) {
		ReserveDao.updateByPrimaryKeySelective(Reserve);
		
	}

	public void delete(Integer id) {
		ReserveDao.deleteByPrimaryKey(id);
	}


	public Reserve findByById(Integer id) {
		// TODO Auto-generated method stub
		return ReserveDao.selectByPrimaryKey(id);
	}

	public void addyy(Reserve reserve) {
		// TODO Auto-generated method stub
		
	}

}

