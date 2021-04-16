package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.AMenuQxService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.AMenuQxVo;

@RestController
@RequestMapping("/aMenuQx")
public class AMenuQxController {
	@Autowired
	private AMenuQxService aMenuQxService;
	
	@RequestMapping({"/getAMenuQxList"})
	public Result<Object> getAMenuQxList(@RequestBody AMenuQxVo vo,HttpServletRequest request) { 
		return aMenuQxService.getAMenuQxList(vo); 
	}
	
	@Log(operation = "新增" , remark="【菜单管理】新增菜单" , type = "0")
	@RequestMapping({"/saveAMenuQx"})
	public Result<Object> saveAMenuQx(@RequestBody AMenuQxVo vo,HttpServletRequest request) {
		return aMenuQxService.saveAMenuQx(vo); 
	}
   
	@Log(operation = "修改" , remark="【菜单管理】修改菜单" , type = "0")
	@RequestMapping({"/updateAMenuQx"})
	public Result<Object> updateAMenuQx(@RequestBody AMenuQxVo vo) { 
		return aMenuQxService.updateAMenuQx(vo); 
	}
   
	@Log(operation = "删除" , remark="【菜单管理】删除菜单" , type = "0")
	@RequestMapping({"/deleteAMenuQx"})
	public Result<Object> deleteAMenuQx(@RequestBody AMenuQxVo vo) { 
		return aMenuQxService.deleteAMenuQx(vo); 
	}
}
