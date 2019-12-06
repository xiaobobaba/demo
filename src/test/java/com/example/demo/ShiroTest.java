package com.example.demo;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.shiro.MyShiroRealm;
import com.example.demo.shiro.ShiroConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroTest {
	
	@Autowired
	ShiroConfig shiroConfig;
	@Autowired
	private MyShiroRealm myShiroRealm;
	
	@Test
	public void Test() {
		// 搭建securitymanager环境
		DefaultSecurityManager defaultsecuritymanager = new DefaultSecurityManager();
		 HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
			matcher.setHashAlgorithmName("md5");
			matcher.setHashIterations(1);
			myShiroRealm.setCredentialsMatcher(matcher);
			defaultsecuritymanager.setRealm(myShiroRealm);
		//shiroConfig.securityManager();
		//主题提交认证请求
		SecurityUtils.setSecurityManager(defaultsecuritymanager);
		Subject subject = SecurityUtils.getSubject();
		//用户登录认证
		UsernamePasswordToken token = new UsernamePasswordToken("222","111");
		try {
			subject.login(token);
			System.out.println("------------------------"+subject.isAuthenticated());
			subject.checkRole("login");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		/*
		 * System.out.println(subject.isAuthenticated()); subject.checkRole("role");
		 */
	}
}





