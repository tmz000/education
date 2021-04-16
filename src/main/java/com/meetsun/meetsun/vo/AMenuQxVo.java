package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class AMenuQxVo {
	private String sysId;
	private String roleId;
	private String menuId;
	private String method;
	private String createTime;
	private String updateTime;
	private Integer offset;
	private Integer pageNumber;
}
