package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.DateBillService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.DateBillVo;
import com.meetsun.meetsun.vo.MsUserVo;

@RestController
@RequestMapping("/dateBill")
public class DateBillController {
	@Autowired
	private DateBillService dateBillService;
	@Autowired
	private MsUserDao msUserDao;
	
	@RequestMapping({"/getDateBillList"})
	public Result<Object> getDateBillList(@RequestBody DateBillVo vo,HttpServletRequest request) { 
		return dateBillService.getDateBillList(vo); 
	}
	
	@RequestMapping({"/getDateBillListByType"})
	public Result<Object> getDateBillListByType(@RequestBody DateBillVo vo) { 
		return dateBillService.getDateBillListByType(vo); 
	}
	
	@RequestMapping({"/getDateBillListByYear"})
	public Result<Object> getDateBillListByYear(@RequestBody DateBillVo vo) { 
		return dateBillService.getDateBillListByYear(vo); 
	}
	
	@RequestMapping({"/getDateBillListByAllYear"})
	public Result<Object> getDateBillListByAllYear(@RequestBody DateBillVo vo) { 
		return dateBillService.getDateBillListByAllYear(vo); 
	}
	@RequestMapping({"/getDateBillListByAllMonth"})
	public Result<Object> getDateBillListByAllMonth(@RequestBody DateBillVo vo) { 
		return dateBillService.getDateBillListByAllMonth(vo); 
	}
	@RequestMapping({"/getDateBillListByAllBillType"})
	public Result<Object> getDateBillListByAllBillType(@RequestBody DateBillVo vo) { 
		return dateBillService.getDateBillListByAllBillType(vo); 
	}
	@RequestMapping({"/getDateBillListByAllBillMonth"})
	public Result<Object> getDateBillListByAllBillMonth(@RequestBody DateBillVo vo) { 
		return dateBillService.getDateBillListByAllBillMonth(vo); 
	}
	
	@Log(operation = "新增" , remark="【日志账单】新增日志账单" , type = "0")
	@RequestMapping({"/saveDateBill"})
	public Result<Object> saveDateBill(@RequestBody DateBillVo vo,HttpServletRequest request) {
		String token = Common.getParam(request.getQueryString(),"token");
		MsUserVo mvo =new MsUserVo();
		mvo.setSysId(token);
		MsUser msUser = msUserDao.getMsUser(mvo).get(0);
		vo.setUserId(msUser.getSysId());
		return dateBillService.saveDateBill(vo); 
	}
   
	@Log(operation = "修改" , remark="【日志账单】修改日志账单" , type = "0")
	@RequestMapping({"/updateDateBill"})
	public Result<Object> updateDateBill(@RequestBody DateBillVo vo,HttpServletRequest request) { 
		String token = Common.getParam(request.getQueryString(),"token");
		MsUserVo mvo =new MsUserVo();
		mvo.setSysId(token);
		MsUser msUser = msUserDao.getMsUser(mvo).get(0);
		vo.setUserId(msUser.getSysId());
		return dateBillService.updateDateBill(vo); 
	}
   
	@Log(operation = "删除" , remark="【日志账单】删除日志账单" , type = "0")
	@RequestMapping({"/deleteDateBill"})
	public Result<Object> deleteDateBill(@RequestBody DateBillVo vo) { 
		return dateBillService.deleteDateBill(vo); 
	}
}
