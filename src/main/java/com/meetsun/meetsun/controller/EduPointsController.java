package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.EduPointsService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.EduPointsVo;

@RestController
@RequestMapping("/eduPoints")
public class EduPointsController {
	@Autowired
	private EduPointsService eduPointsService;
	
	@RequestMapping({"/getEduPointsList"})
	public Result<Object> getEduPointsList(@RequestBody EduPointsVo vo,HttpServletRequest request) { 
		return eduPointsService.getEduPointsList(vo); 
	}
	
	@Log(operation = "新增" , remark="【积分录入】新增积分录入" , type = "0")
	@RequestMapping({"/saveEduPoints"})
	public Result<Object> saveEduPoints(@RequestBody EduPointsVo vo,HttpServletRequest request) {
		return eduPointsService.saveEduPoints(vo); 
	}
   
	@Log(operation = "修改" , remark="【积分录入】修改积分录入" , type = "0")
	@RequestMapping({"/updateEduPoints"})
	public Result<Object> updateEduPoints(@RequestBody EduPointsVo vo) { 
		return eduPointsService.updateEduPoints(vo); 
	}
   
	@Log(operation = "删除" , remark="【积分录入】删除积分录入" , type = "0")
	@RequestMapping({"/deleteEduPoints"})
	public Result<Object> deleteEduPoints(@RequestBody EduPointsVo vo) { 
		return eduPointsService.deleteEduPoints(vo); 
	}
	
	//周期积分查询
	@RequestMapping({"/getEduPointsListByDate"})
	public Result<Object> getEduPointsListByDate(@RequestBody EduPointsVo vo,HttpServletRequest request) { 
		return eduPointsService.getEduPointsListByDate(vo); 
	}
}
