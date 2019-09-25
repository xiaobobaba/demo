package com.example.demo.dao.login;

import com.example.demo.entity.login.EmaillogEntity;

public interface EmailLogDao {
	
	/**
	 * 
	* @Title: findEmaillogYZM  
	* @Description: TODO(查询验证码)  
	* @param @param email
	* @param @return    参数  
	* @return String    返回类型  
	* @date 2019年9月23日下午3:29:46  
	* @throws
	 */
	public String findEmaillogYZM(EmaillogEntity email);
	
	/**
	 * 
	* @Title: addEmailYZM  
	* @Description: TODO(新增验证码)  
	* @param @param email    参数  
	* @return void    返回类型  
	* @date 2019年9月23日下午3:30:25  
	* @throws
	 */
	public int addEmailYZM(EmaillogEntity email);
	
}
