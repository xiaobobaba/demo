package com.example.demo.entity.login;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WXEntity {
	
	private String openid; 		 //用户id
	private String sex; 		 //用户性别
	private String nickname; 	 //用户姓名
	private String province; 	 //用户个人资料填写的省份
	private String city; 		 //普通用户个人资料填写的城市
	private String country; 	 //国家，如中国为CN
	private String headimgurl; 	 //用户头像
}
