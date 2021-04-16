package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class EduClassesVo {
	private String sysId;
	private String claName;
	private String createTime;
	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
}
