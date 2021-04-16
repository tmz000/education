package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class DateBillType {
	private String sysId;
	private String type;
	private String createTime;
	private String updateTime;
	private Integer offset;
	private Integer pageNumber;
}
