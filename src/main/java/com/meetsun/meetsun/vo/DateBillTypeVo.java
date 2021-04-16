package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class DateBillTypeVo {
	private String sysId;
	private String type;
	private String createTime;
	private String updateTime;
	
	private String oldBillType;
	private Integer offset;
	private Integer pageNumber;
}
