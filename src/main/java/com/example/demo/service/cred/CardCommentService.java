package com.example.demo.service.cred;

import java.util.List;

import com.example.demo.entity.cred.CardCommentEntity;

public interface CardCommentService {
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
	public List<CardCommentEntity> selectCardComment(CardCommentEntity cardComment);
}
