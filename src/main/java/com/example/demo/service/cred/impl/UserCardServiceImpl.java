package com.example.demo.service.cred.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.cred.UserCardDao;
import com.example.demo.entity.cred.UserCardEntity;
import com.example.demo.service.cred.UserCardService;
@Service
public class UserCardServiceImpl implements UserCardService {

	@Autowired
	UserCardDao userCardDao;
	/**
	 * 
	* @Title: findUserCard  
	* @Description: TODO(查询用户卡片)  
	* @param @param UserCard
	* @param @return    参数  
	* @return List<UserCardEntity>    返回类型  
	* @date 2019年9月25日下午9:05:29  
	* @throws
	 */
	@Override
	public List<UserCardEntity> findUserCard(UserCardEntity userCard) {
		return userCardDao.findUserCard(UserCard);
	}
	/**
	 * 
	* @Title: findUserCardById  
	* @Description: TODO(根据卡片ID或者用户ID查询卡片)  
	* @param @param UserCard
	* @param @return    参数  
	* @return List<UserCardEntity>    返回类型  
	* @date 2019年9月25日下午9:06:14  
	* @throws
	 */
	@Override
	public List<UserCardEntity> findUserCardById(UserCardEntity userCard) {
		return userCardDao.findUserCardById(UserCard);
	}

}
