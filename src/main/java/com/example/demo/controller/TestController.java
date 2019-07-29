package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.City;

@RestController
@RequestMapping(value="/test")
public class TestController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping(value="/hello/{id}")
	public String hello(@PathVariable int id) {
		return "hello world" + id ;
	}
	
	@GetMapping(value="/test/{id}")
	public String test(@PathVariable int id) {
		String str = restTemplate.getForObject("http://127.0.0.1:9090/hhh/hello/" + id, String.class);
		return str;
	}

}
