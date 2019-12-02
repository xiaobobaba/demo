package com.example.demo.controller.cred;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.cred.CardCommentEntity;
import com.example.demo.service.cred.CardCommentService;
import com.example.demo.util.ReturnParam;
import com.example.demo.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/cardComment")
public class CardCommentController {
	
	@Autowired
	CardCommentService cardCommentService;
	
	@RequestMapping(value="/findCardComment")
	public ReturnParam<CardCommentEntity> findCardComment(@RequestBody CardCommentEntity userCard){
		ReturnParam<CardCommentEntity> param = new ReturnParam<CardCommentEntity>();
		try {
			if(StringUtil.isNull(userCard.getCardId()+"")) {
				param.setIsTan("卡片ID为空!");
				param.setSuccess(false);
				return param;
			}
			param.setList(cardCommentService.selectCardComment(userCard));
			param.setSuccess(true);
		} catch (Exception e) {
			param.setSuccess(false);
			param.setIsTan("操作失败");
			log.info("不好了报错了："+e);
		}
		return param;
	}
	
	

}
