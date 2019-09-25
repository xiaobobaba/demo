package com.example.demo.dao.login;

import com.example.demo.entity.login.UserEntity;

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
	
	/**
	 * 
	* @Title: insertWXUser  
	* @Description: TODO(新增微信用户)  
	* @param @param user
	* @param @return    参数  
	* @return int    返回类型  
	* @date 2019年9月23日下午12:34:14  
	* @throws
	 */
	int insertWXUser(UserEntity user);

}
