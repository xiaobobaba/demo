package com.example.demo.controller.user;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.util.MathUtil;
import com.example.demo.util.ReturnParam;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping(value="/login")
public class UserLoginController {
	
	@Autowired
	UserService userService;
	@Value("${spring.servlet.multipart.location}")
	private String url;
	/**
	 * 
	*<p>Title: login</p> 
	*<p>Description:用户登录 </p> 
	* @date 2019年8月24日 
	* @author xiaobo 
	* @param user
	* @return
	 */
	@RequestMapping("/login")
	public String login(UserEntity user){
		log.info(user.getEmail());
		user.setPassword(MathUtil.getMD5(user.getEmail()+"#"+user.getPassword()));
		int a = userService.findUserLogin(user);
		return a+"";
	}
	/**
	 * 
	*<p>Title: login</p> 
	*<p>Description:用户登录 </p> 
	* @date 2019年8月24日 
	* @author xiaobo 
	* @param user
	* @return
	 */
	@RequestMapping("/userAdd")
	public String userAdd(@RequestBody UserEntity user){
		user.setPassword(MathUtil.getMD5(user.getEmail()+"#"+user.getPassword()));
		int a = userService.insertUser(user);
		return "success";
	}
	
    /**
     * 
    * @Title: upload  
    * @Description: TODO(文件上传)  
    * @param @param file
    * @param @return    文件
    * @return String    返回类型  
    * @date 2019年9月20日下午3:57:44  
    * @throws
     */
	@RequestMapping("/upload")
	public ReturnParam<String> upload(MultipartFile file) {
		ReturnParam<String> param = new ReturnParam<String>();
	 try {
        if (file.isEmpty()) {
        	param.setSuccess(false);
            return param;
        }
        
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String fileName =  sdf.format(date)+file.getOriginalFilename();
        File dest = new File(url + fileName);
            file.transferTo(dest);
            log.info("上传成功");
            param.setSuccess(true);
            param.setObj(fileName);
       } catch (IOException e) {
           log.error(e.toString(), e);
       }
	 	return param;
    }

}
