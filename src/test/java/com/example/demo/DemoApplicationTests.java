package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.login.UserEntity;
import com.example.demo.util.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private RedisUtil redis;
	@Test
	public void contextLoads() {
		UserEntity user = (UserEntity)redis.get("user");
		System.out.println(user.toString());
		redis.expire("user", 60);
		System.out.println(redis.getExpire("user"));
	}

}
