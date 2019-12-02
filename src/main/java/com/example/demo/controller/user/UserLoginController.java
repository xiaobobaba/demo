package com.example.demo.controller.user;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.login.EmaillogEntity;
import com.example.demo.entity.login.UserEntity;
import com.example.demo.entity.login.WXEntity;
import com.example.demo.service.login.EmailLogService;
import com.example.demo.service.login.UserService;
import com.example.demo.util.HttpURLConectionGET;
import com.example.demo.util.MathUtil;
import com.example.demo.util.RedisUtil;
import com.example.demo.util.ReturnParam;
import com.example.demo.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping(value="/login")
public class UserLoginController {
	
	/**
	 * 用户操作
	 */
	@Autowired
	UserService userService;
	/**
	 * 邮箱操作
	 */
	@Autowired
	private EmailLogService emailLogService;
	
	@Value("${publicvariable.WX_APP_ID}")
	private String wxAppid;
	
	@Value("${publicvariable.WX_APP_KEY}")
	private String wxAppiKye;
	
	@Value("${spring.servlet.multipart.location}")
	private String url;
	//发送邮箱
	@Autowired
    private JavaMailSender jms; //自动注入的Bean
	
	@Autowired
	private RedisUtil redis;

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
	public ReturnParam<String> login(@RequestBody UserEntity user){
		ReturnParam<String> param = new ReturnParam<String>();
		System.out.println("------------------------------");
		try {
			user.setPassword(MathUtil.getMD5(user.getEmail() + "#" + user.getPassword()));
			UserEntity userLogin = userService.findUserLogin(user);
			if (userLogin != null) {
				param.setSuccess(true);
				param.setIsTan("登录成功!");
				redis.set("user", userLogin);
			} else {
				param.setIsTan("用户名密码不正确!");
				param.setSuccess(false);
			} 
		} catch (Exception e) {
			param.setSuccess(false);
			param.setIsTan("操作失败");
			log.info("不好了报错了："+e);
			return param;
		}
		return param;
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
	public ReturnParam<String> Adduser(@RequestBody UserEntity user){
		ReturnParam<String> param = new ReturnParam<String>();
		EmaillogEntity email = new EmaillogEntity();
		try {
			log.info(user.toString());
			if(StringUtil.isNull(user.getEmail())) {
				param.setIsTan("邮箱不能为空!");
				param.setSuccess(false);
				return param;
			}
			if(StringUtil.isNull(user.getPassword())) {
				param.setIsTan("密码不能为空!");
				param.setSuccess(false);
				return param;
			}
			if(StringUtil.isNull(user.getYzm())) {
				param.setIsTan("邮箱验证有误!");
				param.setSuccess(false);
				return param;
			}
			email.setEmailUser(user.getEmail());
			email.setYzm(user.getYzm());
			String yzm = emailLogService.findEmaillogYZM(email);
			if(!email.getYzm().equals(yzm)) {
				param.setIsTan("邮箱验证码错误!");
				param.setSuccess(false);
				return param;
			}
			user.setPassword(MathUtil.getMD5(user.getEmail() + "#" + user.getPassword()));
			UserEntity userLogin = userService.findUserLogin(user);
			if (userLogin != null) {
				param.setIsTan("用户已存在!");
				param.setSuccess(false);
				return param;
			}
			int userCount = userService.insertUser(user);
			if (userCount > 0) {
				param.setSuccess(true);
				param.setIsTan("注册成功!");
			} else {
				param.setSuccess(false);
			} 
		} catch (Exception e) {
			log.info("不好了报错了："+e);
			param.setSuccess(false);
			return param;
		}
		return param;
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
	public ReturnParam<String> upload(@RequestBody MultipartFile file) {
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
            param.setIsTan("上传成功");
            param.setSuccess(true);
            param.setObj(fileName);
       } catch (IOException e) {
           log.error(e.toString(), e);
           param.setSuccess(false);
           return param;
       }
	 	return param;
    }
	@RequestMapping("/wxLogin")
    public ModelAndView wxcallback(String code,HttpServletResponse response){
		   ModelAndView mv = new ModelAndView();
		try {
			JSONObject accessToken = JSONObject.parseObject(HttpURLConectionGET.httpRequest("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+wxAppid+"&secret="+wxAppiKye+"&code="+code+"&grant_type=authorization_code", "GET", null));
			WXEntity wx = JSONObject.parseObject(HttpURLConectionGET.httpRequest("https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken.getString("access_token")+"&openid="+accessToken.getString("openid")+"&lang=zh_CN", "GET", null),WXEntity.class);
			//WXEntity wx = userInfo.getObject(userInfo.toJSONString(), WXEntity.class);
			if(wx == null) {
				mv.setViewName("/user/login.html");
				return mv;
			}
			UserEntity user = new UserEntity();
			user.setOpenId(wx.getOpenid());
			UserEntity userLogin = userService.findUserLogin(user);
			if (userLogin == null) {
				user.setCity(wx.getCity());
				user.setProvince(wx.getProvince());
				user.setCountry(wx.getCountry());
				user.setOpenId(wx.getOpenid());
				user.setTouXiang(wx.getHeadimgurl());
				user.setName(wx.getNickname());
				user.setSex(wx.getSex());
				redis.set("user", user);
				userService.insertWXUser(user);
			}
			//创建Cookie
		    Cookie cookie = new Cookie("userName", wx.getNickname());
		    //设置Cookie的最大生命周期,否则浏览器关闭后Cookie即失效
		    cookie.setMaxAge(Integer.MAX_VALUE);
		    //将Cookie加到response中
		    response.addCookie(cookie);
	        mv.setViewName("/xiaobo/index.html");
		} catch (Exception e) {
			log.info("不好了报错了："+e);
			 mv.setViewName("/user/login.html");
			 return mv;
		}
		return mv;
     }
	@RequestMapping("/wxYanZheng")
	public ReturnParam<String> wxYanZheng(@RequestBody EmaillogEntity email) {
		ReturnParam<String> param = new ReturnParam<String>();
		 try {
			if(StringUtil.isNull(email.getEmailUser())) {
				param.setSuccess(false);
				param.setIsTan("验证邮箱不能为空");
				return param;
			}
			//建立邮件消息
			SimpleMailMessage mainMessage = new SimpleMailMessage();
			//发送者
			mainMessage.setFrom("18273288578@163.com");
			//接收者
			mainMessage.setTo(email.getEmailUser());
			//发送的标题
			mainMessage.setSubject("登录验证码");
			//发送的内容
			int yzm = (int) (1 + Math.random() * (10000 - 1 + 1));
			email.setYzm(yzm+"");
			mainMessage.setText("这是验证码，看仔细点哦\n验证码：" + yzm + "\n此验证码只有五分钟内有效");
			jms.send(mainMessage);
			emailLogService.addEmailYZM(email);
		} catch (Exception e) {
			log.info("不好了报错了："+e);
			param.setSuccess(false);
			param.setIsTan("发送失败，请核对邮箱");
			return param;
		}
		 param.setSuccess(true);
		 param.setIsTan("发送成功");
		return param;
	}
}
