package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class EduPointsType {
	private String sysId;
	private String remark;
	private String type;
	private String pointsMake;
	private String points;
	private String status;
	private String createTime;
	private String updateTime;
	private Integer offset;
	private Integer pageNumber;
}
