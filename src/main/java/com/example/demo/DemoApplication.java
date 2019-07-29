package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan("com.example.demo")
@MapperScan("com.example.demo.dao")
@SpringBootApplication
//@EnableEurekaClient
public class DemoApplication {

	@Bean
	public  RestTemplate getRestTemplate() {
			return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
