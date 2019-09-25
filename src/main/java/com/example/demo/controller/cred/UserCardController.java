package com.example.demo.controller.cred;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.cred.UserCardEntity;
import com.example.demo.service.cred.UserCardService;
import com.example.demo.util.ReturnParam;
import com.example.demo.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/userCard")
public class UserCardController {
	

	@Autowired
	UserCardService userCardService;
	
	@RequestMapping(value="/findUserCardById")
	public ReturnParam<UserCardEntity> findUserCardById(@RequestBody UserCardEntity userCard){
		ReturnParam<UserCardEntity> param = new ReturnParam<UserCardEntity>();
		try {
			if(StringUtil.isNull(userCard.getCardId()+"") && StringUtil.isNull(userCard.getUserId()+"")) {
				param.setIsTan("卡片ID或用户ID为空!");
				param.setSuccess(false);
				return param;
			}
			param.setList(userCardService.findUserCardById(userCard));
		} catch (Exception e) {
			param.setSuccess(false);
			param.setIsTan("操作失败");
			log.info("不好了报错了："+e);
		}
		return param;
	}
	
	@RequestMapping(value="/findUserCard")
	public ReturnParam<UserCardEntity> findUserCard(@RequestBody UserCardEntity userCard){
		ReturnParam<UserCardEntity> param = new ReturnParam<UserCardEntity>();
		try {
			if(StringUtil.isNull(userCard.getCardId()+"") && StringUtil.isNull(userCard.getUserId()+"")) {
				param.setIsTan("卡片ID或用户ID为空!");
				param.setSuccess(false);
				return param;
			}
			param.setList(userCardService.findUserCard(userCard));
		} catch (Exception e) {
			param.setSuccess(false);
			param.setIsTan("操作失败");
			log.info("不好了报错了："+e);
		}
		return param;
	}

}
