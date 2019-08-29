package com.example.demo.dao;

import com.example.demo.entity.UserEntity;

/**
 * 
　 * <p>Title: UserDao</p> 
　 * <p>Description: userDAO</p> 
　 * @author xiaobo 
　 * @date 2019年8月24日
 */
public interface UserDao {
	/**
	 * 
	*<p>Title: findUserLogin</p> 
	*<p>Description:用户登录 </p> 
	* @date 2019年8月24日 
	* @author xiaobo 
	* @param user
	* @return
	 */
	int findUserLogin(UserEntity user);
	
	/**
	 * 
	*<p>Title: findUserLogin</p> 
	*<p>Description:用户注册 </p> 
	* @date 2019年8月24日 
	* @author xiaobo 
	* @param user
	* @return
	 */
	int insertUser(UserEntity user);

}
