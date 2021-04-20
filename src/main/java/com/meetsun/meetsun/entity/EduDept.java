package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class EduDept {
	private String sysId;
	private String name;
	private String createTime;
	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
	private Integer count;
}
