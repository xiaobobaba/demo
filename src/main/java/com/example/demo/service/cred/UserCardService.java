package com.example.demo.service.cred;

import java.util.List;

import com.example.demo.entity.cred.UserCardEntity;

public interface UserCardService {
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
	public List<UserCardEntity> findUserCard(UserCardEntity userCard);
	
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
	public List<UserCardEntity> findUserCardById(UserCardEntity userCard);

}
