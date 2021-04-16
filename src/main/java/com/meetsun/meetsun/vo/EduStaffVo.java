package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class EduStaffVo {
	private String sysId;
	private String name;
	private String type;
	private String status;
	private String comeDate;
	private String outDate;
	private String createTime;
	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
}
