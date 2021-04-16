package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class EduPoints {
	private String sysId;
	private String teaId;
	private String claId;
	private String points;
	private String remark;
	private String addTime;
	private String createTime;
	private String updateTime;
	private String type;
	private String pointsMake;
	private String pointsTypeId;
	

	private String teaName;
	private String claName;
	private Integer offset;
	private Integer pageNumber;
	private String andTime;
	private String cycleTime;
}
