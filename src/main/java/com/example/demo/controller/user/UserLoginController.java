package com.example.demo.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/login")
public class UserLoginController {
	
	@RequestMapping("/login")
	public String login() throws Exception {
		return "hello World";
	}

}
