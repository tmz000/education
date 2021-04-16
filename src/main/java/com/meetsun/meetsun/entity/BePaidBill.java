package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class BePaidBill {
	private String sysId;
	private String stuId;
	private String claId;
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
	private String type;
	private Integer offset;
	private Integer pageNumber;
	private String sum;
}
