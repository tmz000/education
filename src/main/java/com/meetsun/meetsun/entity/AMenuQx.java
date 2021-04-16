package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class AMenuQx {
	private String sysId;
	private String roleId;
	private String menuId;
	private String method;
	private String createTime;
	private String updateTime;
	
	private AMenu aMenu;
	private ARole aRole;
	private String roleName;
	private Integer offset;
	private Integer pageNumber;
}
