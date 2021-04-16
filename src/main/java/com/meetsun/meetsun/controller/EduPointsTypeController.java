package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.EduPointsTypeService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.EduPointsTypeVo;

@RestController
@RequestMapping("/eduPointsType")
public class EduPointsTypeController {
	@Autowired
	private EduPointsTypeService eduPointsTypeService;
	
	@RequestMapping({"/getEduPointsTypeList"})
	public Result<Object> getEduPointsTypeList(@RequestBody EduPointsTypeVo vo,HttpServletRequest request) { 
		return eduPointsTypeService.getEduPointsTypeList(vo); 
	}
	
	@Log(operation = "新增" , remark="【积分类型】新增积分类型" , type = "0")
	@RequestMapping({"/saveEduPointsType"})
	public Result<Object> saveEduPointsType(@RequestBody EduPointsTypeVo vo,HttpServletRequest request) {
		return eduPointsTypeService.saveEduPointsType(vo); 
	}
   
	@Log(operation = "修改" , remark="【积分类型】修改积分类型" , type = "0")
	@RequestMapping({"/updateEduPointsType"})
	public Result<Object> updateEduPointsType(@RequestBody EduPointsTypeVo vo) { 
		return eduPointsTypeService.updateEduPointsType(vo); 
	}
   
	@Log(operation = "删除" , remark="【积分类型】删除积分类型" , type = "0")
	@RequestMapping({"/deleteEduPointsType"})
	public Result<Object> deleteEduPointsType(@RequestBody EduPointsTypeVo vo) { 
		return eduPointsTypeService.deleteEduPointsType(vo); 
	}
}
