package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class BePaidBillVo {
	private String sysId;
	private String claId;
	private String stuId;
	private String money;
	private String yuPay;
	private String houPay;
	private String billType;
	private String remark;
	private String status;
	private String payTime;
	private String isCall;
	private String createTime;
	private String updateTime;
	
	private String claName;
	private String stuName;
	private String userId;
	private Integer offset;
	private Integer pageNumber;
	private Float sum;
}
