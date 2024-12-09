package com.example.demo.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.common.Result;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@CrossOrigin//解决前后台跨域问题
@RestController
@RequestMapping("/files")
public class FileController {
	
	private static final String filePath =System.getProperty("user.dir")+"/file/";
	
	//上传文件
	@PostMapping("/upload")
	public Result upload(MultipartFile file) {
		synchronized (FileController.class) {
			//获取当前时间戳
			String flag = System.currentTimeMillis()+"";
			//获取文件名
			String fileName=file.getOriginalFilename();
			try {
				//如果没有文件夹会创建一个
				if(!FileUtil.isDirectory(filePath)) {
					FileUtil.mkdir(filePath);
				}
				//文件存储形式：时间-文件名
				FileUtil.writeBytes(file.getBytes(), filePath+flag+"-"+fileName);
				System.out.println(fileName+"--上传成功");
				Thread.sleep(1L);
			}catch(Exception e){
				System.out.println(fileName+"--文件上传失败");
			}
			return Result.success(flag);
		}
	}
	
	//获取文件
	@GetMapping("/{flag}")
	public void avatarPath(@PathVariable String flag, HttpServletResponse response) {
		if(!FileUtil.isDirectory(filePath)) {
			FileUtil.mkdir(filePath);
		}
		OutputStream os;
		List<String> fileNames =FileUtil.listFileNames(filePath);
		String avatar =fileNames.stream().filter(name->name.contains(flag)).findAny().orElse("");
		try {
			if(StrUtil.isAllNotEmpty(avatar)) {
				response.addHeader("Content-Disposition","attachment;filename="+URLEncoder.encode(avatar,"UTF-8"));
				response.setContentType("application/octet-stream");
				byte[] bytes = FileUtil.readBytes(filePath+avatar);
				os = response.getOutputStream();
				os.write(bytes);
				os.flush();
				os.close();
			}
		}catch (Exception e) {
		        System.out.println("文件下载失败");
		}
	}
	
	

}
