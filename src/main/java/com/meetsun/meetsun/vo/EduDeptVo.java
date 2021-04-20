package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class EduDeptVo {
	private String sysId;
	private String name;
	private String createTime;
	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
	private Integer count;
}
