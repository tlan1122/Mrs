package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.demo.dao")
public class Demo3Application {
//http://localhost:8080/.....
	public static void main(String[] args) {
		SpringApplication.run(Demo3Application.class, args);
	}

}
