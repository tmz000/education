package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class EduDeptType {
	private String sysId;
	private String name;
	private String deptId;
	private String createTime;
	private String updateTime;
	
	private String deptName;
	private Integer offset;
	private Integer pageNumber;
	private Integer count;
}
