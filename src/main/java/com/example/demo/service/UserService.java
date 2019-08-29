package com.example.demo.service;

import com.example.demo.entity.UserEntity;

/**
 * 
　 * <p>Title: UserService</p> 
　 * <p>Description:UserService </p> 
　 * @author xiaobo 
　 * @date 2019年8月24日
 */
public interface UserService {
	
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
