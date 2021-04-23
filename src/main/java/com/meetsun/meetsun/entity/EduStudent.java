package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class EduStudent {
	private String sysId;
	private String name;
	private String courseId;
	private String status;
	private String claId;
	private String comeDate;
	private String outDate;
	private String atSchool;
	private String atGrade;
	private String parent;
	private String mobile;
	private String createTime;
	private String updateTime;
	
	private String claName;
	private String courseName;
	private Integer offset;
	private Integer pageNumber;
}
