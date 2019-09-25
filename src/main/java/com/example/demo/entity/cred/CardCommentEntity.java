package com.example.demo.entity.cred;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 卡片评论表
 * 
 * @author xiaobo
 * @version 1.0.0 2019-09-25
 */
@Getter
@Setter
public class CardCommentEntity {
    /** commentId */
    private Long commentId;

    /** 卡片ID */
    private Long cardId;

    /** 评论内容 */
    private String commentContent;

    /** createTime */
    private Date createTime;

    /** 哪个用户发送的ID */
    private Long userId;

    /** 评论用户名称 */
    private String userName;
}