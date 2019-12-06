package com.example.demo.entity.user;

import lombok.Data;

@Data
public class UserRolePrivilege {
	
	 /** 权限类型 */
    private String roleType;

    /** 角色类型名称 */
    private String roleTypeName;

    /** 角色名称 */
    private String privilegeName;

    /** 权限代码 */
    private String privilegeCode;

    /** 父节点 */
    private String parentId;

    /** 跳转链接 */
    private String getUrl;

    /** 状态 */
    private String status;
	
	
}
