package com.example.demo.entity.login;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * EMAILLOG
 * 
 * @author xiaobo
 * @version 1.0.0 2019-09-23
 */
@Getter
@Setter
public class EmaillogEntity {
    /** emailLogId */
    private Long emailLogId;

    /** 接收者 */
    private String emailUser;

    /** 验证码值 */
    private String yzm;

    /** 创建时间 */
    private Date createTime;
}