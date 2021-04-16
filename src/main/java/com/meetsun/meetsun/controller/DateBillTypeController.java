package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.DateBillTypeService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.DateBillTypeVo;

@RestController
@RequestMapping("/dateBillType")
public class DateBillTypeController {
	@Autowired
	private DateBillTypeService DateBillTypeService;
	
	@RequestMapping({"/getDateBillTypeList"})
	public Result<Object> getDateBillTypeList(@RequestBody DateBillTypeVo vo,HttpServletRequest request) { 
		return DateBillTypeService.getDateBillTypeList(vo); 
	}
	
	@Log(operation = "新增" , remark="【日录账单类型】新增日录账单类型" , type = "0")
	@RequestMapping({"/saveDateBillType"})
	public Result<Object> saveDateBillType(@RequestBody DateBillTypeVo vo,HttpServletRequest request) {
		return DateBillTypeService.saveDateBillType(vo); 
	}
   
	@Log(operation = "修改" , remark="【日录账单类型】修改日录账单类型" , type = "0")
	@RequestMapping({"/updateDateBillType"})
	public Result<Object> updateDateBillType(@RequestBody DateBillTypeVo vo) { 
		return DateBillTypeService.updateDateBillType(vo); 
	}
   
	@Log(operation = "删除" , remark="【日录账单类型】删除日录账单类型" , type = "0")
	@RequestMapping({"/deleteDateBillType"})
	public Result<Object> deleteDateBillType(@RequestBody DateBillTypeVo vo) { 
		return DateBillTypeService.deleteDateBillType(vo); 
	}
}
