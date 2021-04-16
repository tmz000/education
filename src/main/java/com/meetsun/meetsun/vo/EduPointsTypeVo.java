package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class EduPointsTypeVo {
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
