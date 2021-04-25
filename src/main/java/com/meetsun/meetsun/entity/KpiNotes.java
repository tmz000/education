package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class KpiNotes {
	private String sysId;
	private String kpiType;
	private String kpi;
	private String points;
	private String deptPoints;
	private String adCheck;
	private String reason;
	private String deptId;
	private String deptTypeId;
	private String staffId;
	private String deptSign;
	private String adSign;
	private String countPoints;
	private String addTime;
	private String andTime;
	private String createTime;
	private String updateTime;
	private String serialNum;
	
	private String staffName;
	private String deptName;
	private String deptTypeName;
	private Integer offset;
	private Integer pageNumber;
}
