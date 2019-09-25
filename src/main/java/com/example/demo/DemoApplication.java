package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.client.RestTemplate;

@ComponentScan("com.example.demo")
@MapperScan("com.example.demo.dao")
@SpringBootApplication
@ImportResource("classpath:transaction.xml")
//@EnableEurekaClient
public class DemoApplication extends SpringBootServletInitializer{

	@Bean
	public  RestTemplate getRestTemplate() {
			return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	//指明启动类
	return builder.sources(DemoApplication.class);
	}
}
