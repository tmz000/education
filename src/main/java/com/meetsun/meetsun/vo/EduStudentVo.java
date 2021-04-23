package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class EduStudentVo {
	private String sysId;
	private String name;
	private String claId;
	private String courseId;
	private String status;
	private String comeDate;
	private String outDate;
	private String atSchool;
	private String atGrade;
	private String parent;
	private String mobile;
	private String createTime;
	private String updateTime;
	
	private String courseName;
	private Integer offset;
	private Integer pageNumber;
}
