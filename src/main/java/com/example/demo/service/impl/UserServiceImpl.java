package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Override
	public int findUserLogin(UserEntity user) {
		return userDao.findUserLogin(user);
	}
	@Override
	public int insertUser(UserEntity user) {
		return userDao.insertUser(user);
	}

}
