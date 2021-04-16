package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class EduClasses {
	private String sysId;
	private String claName;
	private String createTime;
	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
}
