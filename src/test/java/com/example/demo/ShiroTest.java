package com.example.demo;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.shiro.ShiroConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroTest {
	
	@Autowired
	ShiroConfig shiroConfig;
	
	@Test
	public void Test() {
		// 搭建securitymanager环境
		DefaultSecurityManager defaultsecuritymanager = shiroConfig.securityManager();
		//主题提交认证请求
		SecurityUtils.setSecurityManager(defaultsecuritymanager);
		Subject subject = SecurityUtils.getSubject();
		//用户登录认证
		UsernamePasswordToken token = new UsernamePasswordToken("111","111");
		subject.login(token);
		/*
		 * System.out.println(subject.isAuthenticated()); subject.checkRole("role");
		 */
	}
}





