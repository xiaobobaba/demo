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
	
	/**
	 * 
	* @Title: findUserCardCount  
	* @Description: TODO(查询卡片总数)  
	* @param @return    参数  
	* @return int    返回类型  
	* @date 2019年9月26日下午1:38:27  
	* @throws
	 */
	public int findUserCardCount();
	
	/**
	 * 
	* @Title: 添加卡片
	* @Description: TODO(添加卡片)  
	* @param @return    参数  
	* @return int    返回类型  
	* @date 2019年10月16日上午10:01:25  
	* @throws
	 */
	public int insertUsercard(UserCardEntity UserCard);

}
