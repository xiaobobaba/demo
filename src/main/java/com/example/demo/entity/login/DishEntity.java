package com.example.demo.entity.login;


import java.util.Date;

import lombok.Data;

@Data
public class DishEntity {
	/** id */
	private Long id;
	
	/** 微信id*/
	private String dishName;

	/** 用户名 */
	private Date updateTime;
}
