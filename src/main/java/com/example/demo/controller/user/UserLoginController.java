package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.util.MathUtil;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping(value="/login")
public class UserLoginController {
	
	@Autowired
	UserService userService;
	
	
	/**
	 * 
	*<p>Title: login</p> 
	*<p>Description:用户登录 </p> 
	* @date 2019年8月24日 
	* @author xiaobo 
	* @param user
	* @return
	 */
	@RequestMapping("/login")
	public String login(UserEntity user){
		log.info(user.getEmail());
		user.setPassword(MathUtil.getMD5(user.getEmail()+"#"+user.getPassword()));
		int a = userService.findUserLogin(user);
		return a+"";
	}
	/**
	 * 
	*<p>Title: login</p> 
	*<p>Description:用户登录 </p> 
	* @date 2019年8月24日 
	* @author xiaobo 
	* @param user
	* @return
	 */
	@RequestMapping("/userAdd")
	public String userAdd(UserEntity user){
		user.setPassword(MathUtil.getMD5(user.getEmail()+"#"+user.getPassword()));
		int a = userService.insertUser(user);
		return ""+a;
	}

}
