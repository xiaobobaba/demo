package com.example.demo.entity.cred;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
/**
 * 用户卡片表
 * 
 * @author xiaobo
 * @version 1.0.0 2019-09-25
 */
@Getter
@Setter
public class UserCardEntity {

    /** 卡片ID */
    private Long cardId;

    /** 用户ID */
    private Long userId;

    /** 卡片标题 */
    private String cardTitle;

    /** 音乐地址 */
    private String cardMusic;

    /** 视频地址 */
    private String cardFilm;

    /** 卡片内容 */
    private String cardContent;

    /** 卡片图片 */
    private String cardImg;

    /** cardImg2 */
    private String cardImg2;

    /** cardImg3 */
    private String cardImg3;

    /** cardImg4 */
    private String cardImg4;

    /** cardImg5 */
    private String cardImg5;

    /** cardImg6 */
    private String cardImg6;

    /** cardImg7 */
    private String cardImg7;

    /** cardImg8 */
    private String cardImg8;

    /** cardImg9 */
    private String cardImg9;

    /** 点赞数量 */
    private Long goodCount;

    /** 创建时间 */
    private Date createTime;
    
    /** 用户姓名 */
    private String name;
    
    /** 用户头像 */
    private String touXiang;
    
    /** 最新评论 */
    private String commentContent;
    
    /** 开始条数*/
    private int startIndex;
    
    /** 结束条数*/
    private int index;
}