package com.example.demo.service.cred.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.cred.CardCommentDao;
import com.example.demo.entity.cred.CardCommentEntity;
import com.example.demo.entity.cred.UserCardEntity;
import com.example.demo.service.cred.CardCommentService;


@Service
public class CardCommentServiceImpl implements CardCommentService {
	
	@Autowired
	private CardCommentDao cardCommentDao;
	
	/**
	 * 
	* @Title: selectUserCard  
	* @Description: TODO(查询卡片弹幕)  
	* @param @param userCard
	* @param @return    参数  
	* @return List<CardCommentEntity>    返回类型  
	* @date 2019年9月25日下午8:54:27  
	* @throws
	 */
	@Override
	public List<CardCommentEntity> selectCardComment(CardCommentEntity cardComment) {
		return cardCommentDao.selectCardComment(cardComment);
	}

}
