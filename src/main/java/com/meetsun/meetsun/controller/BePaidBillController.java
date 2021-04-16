package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.BePaidBillService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.BePaidBillVo;
import com.meetsun.meetsun.vo.MsUserVo;

@RestController
@RequestMapping("/bePaidBill")
public class BePaidBillController {
	@Autowired
	private BePaidBillService bePaidBillService;
	@Autowired
	private MsUserDao msUserDao;
	
	@RequestMapping({"/getBePaidBillList"})
	public Result<Object> getBePaidBillList(@RequestBody BePaidBillVo vo,HttpServletRequest request) { 
		return bePaidBillService.getBePaidBillList(vo); 
	}
	
	@Log(operation = "新增" , remark="【待缴费用】新增待缴费用" , type = "0")
	@RequestMapping({"/saveBePaidBill"})
	public Result<Object> saveBePaidBill(@RequestBody BePaidBillVo vo) {
		return bePaidBillService.saveBePaidBill(vo); 
	}
   
	@Log(operation = "修改" , remark="【待缴费用】修改待缴费用" , type = "0")
	@RequestMapping({"/updateBePaidBill"})
	public Result<Object> updateBePaidBill(@RequestBody BePaidBillVo vo,HttpServletRequest request) { 
		String token = Common.getParam(request.getQueryString(),"token");
		MsUserVo mvo =new MsUserVo();
		mvo.setSysId(token);
		MsUser msUser = msUserDao.getMsUser(mvo).get(0);
		vo.setUserId(msUser.getSysId());
		return bePaidBillService.updateBePaidBill(vo); 
	}
   
	@Log(operation = "删除" , remark="【待缴费用】删除待缴费用" , type = "0")
	@RequestMapping({"/deleteBePaidBill"})
	public Result<Object> deleteBePaidBill(@RequestBody BePaidBillVo vo) { 
		return bePaidBillService.deleteBePaidBill(vo); 
	}
}
