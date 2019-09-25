package com.example.demo.service.login.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.login.EmailLogDao;
import com.example.demo.entity.login.EmaillogEntity;
import com.example.demo.service.login.EmailLogService;
/**
 * 
* @ClassName: EmailLogServiceImpl  
* @Description: TODO(邮箱验证码操作实现类)  
* @author xiaobo  
* @date 2019年9月23日下午3:32:28  
*
 */
@Service
public class EmailLogServiceImpl implements EmailLogService {

	@Autowired
	private EmailLogDao emailLogDao;
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
	@Override
	public String findEmaillogYZM(EmaillogEntity email) {
		return emailLogDao.findEmaillogYZM(email);
	}
	/**
	 * 
	* @Title: addEmailYZM  
	* @Description: TODO(新增验证码)  
	* @param @param email    参数  
	* @return void    返回类型  
	* @date 2019年9月23日下午3:30:25  
	* @throws
	 */
	@Override
	public int addEmailYZM(EmaillogEntity email) {
		return emailLogDao.addEmailYZM(email);
	}

}
