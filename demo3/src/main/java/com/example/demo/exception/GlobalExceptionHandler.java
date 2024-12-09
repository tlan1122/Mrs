package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.common.Result;

import jakarta.servlet.http.HttpServletRequest;

//全局异常处理器
@ControllerAdvice(basePackages = "com.example.demo.controller")
public class GlobalExceptionHandler {
	private static final Logger log=LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	//非自定义异常
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result error(HttpServletRequest request,Exception e) {
		log.error("异常信息：",e);
		return Result.error("系统异常");//此处信息返回给前台
		
	}
	
	//自定义异常
	@ExceptionHandler(CustomException.class)
	@ResponseBody
	public Result customError(HttpServletRequest request,CustomException e) {
		return Result.error(e.getMsg());
		
	}
}
