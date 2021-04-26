package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class EduStaff {
	private String sysId;
	private String name;
	private String deptId;
	private String deptTypeId;
	private String status;
	private String comeDate;
	private String outDate;
	private String isLead;
	private String createTime;
	private String updateTime;
	
	private String deptName;
	private String deptTypeName;
	private Integer offset;
	private Integer pageNumber;
}
