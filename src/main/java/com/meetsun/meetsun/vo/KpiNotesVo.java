package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class KpiNotesVo {
	private String sysId;
	private String kpiType;
	private String kpi;
	private String points;
	private String deptPoints;
	private String adCheck;
	private String reason;
	private String deptId;
	private String staffId;
	private String deptSign;
	private String adSign;
	private String countPoints;
	private String addTime;
	private String andTime;
	private String createTime;
	private String updateTime;
	private String serialNum;
	
	private Integer offset;
	private Integer pageNumber;
}
