package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class EduDeptTypeVo {
	private String sysId;
	private String name;
	private String deptId;
	private String createTime;
	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
	private Integer count;
}
