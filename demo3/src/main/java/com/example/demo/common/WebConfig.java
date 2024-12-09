package com.example.demo.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		//指定controller统一接口前缀，就是增加路由前缀
		configurer.addPathPrefix("/ikun", clazz -> clazz.isAnnotationPresent(RestController.class));
	}
	
	

}
