package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.EduClassesService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.EduClassesVo;

@RestController
@RequestMapping("/eduClasses")
public class EduClassesController {
	@Autowired
	private EduClassesService eduClassesService;
	
	@RequestMapping({"/getEduClassesList"})
	public Result<Object> getEduClassesList(@RequestBody EduClassesVo vo,HttpServletRequest request) { 
		return eduClassesService.getEduClassesList(vo); 
	}
	
	@Log(operation = "新增" , remark="【班级】新增班级" , type = "0")
	@RequestMapping({"/saveEduClasses"})
	public Result<Object> saveEduClasses(@RequestBody EduClassesVo vo,HttpServletRequest request) {
		return eduClassesService.saveEduClasses(vo); 
	}
   
	@Log(operation = "修改" , remark="【班级】修改班级" , type = "0")
	@RequestMapping({"/updateEduClasses"})
	public Result<Object> updateEduClasses(@RequestBody EduClassesVo vo) { 
		return eduClassesService.updateEduClasses(vo); 
	}
   
	@Log(operation = "删除" , remark="【班级】删除班级" , type = "0")
	@RequestMapping({"/deleteEduClasses"})
	public Result<Object> deleteEduClasses(@RequestBody EduClassesVo vo) { 
		return eduClassesService.deleteEduClasses(vo); 
	}
}
