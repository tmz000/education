package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class DateBill {
	private String sysId;
	private String userId;
	private String billDate;
	private String billRemark;
	private String billMoney;
	private String billType;
	private String status;
	private String createTime;
	private String updateTime;
	
	private String month;
	private String count;
	private String sum;
	private String countSum;
	private String income;
	private String pay;
	private String lastMonth;
	private String lastYear;
	private String realName;
	private Integer offset;
	private Integer pageNumber;
}
