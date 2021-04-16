package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.EduStudentService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.EduStudentVo;

@RestController
@RequestMapping("/eduStudent")
public class EduStudentController {
	@Autowired
	private EduStudentService eduStudentService;
	
	@RequestMapping({"/getEduStudentList"})
	public Result<Object> getEduStudentList(@RequestBody EduStudentVo vo,HttpServletRequest request) { 
		return eduStudentService.getEduStudentList(vo); 
	}
	
	@Log(operation = "新增" , remark="【学生管理】新增学生管理" , type = "0")
	@RequestMapping({"/saveEduStudent"})
	public Result<Object> saveEduStudent(@RequestBody EduStudentVo vo,HttpServletRequest request) {
		return eduStudentService.saveEduStudent(vo); 
	}
   
	@Log(operation = "修改" , remark="【学生管理】修改学生管理" , type = "0")
	@RequestMapping({"/updateEduStudent"})
	public Result<Object> updateEduStudent(@RequestBody EduStudentVo vo) { 
		return eduStudentService.updateEduStudent(vo); 
	}
   
	@Log(operation = "删除" , remark="【学生管理】删除学生管理" , type = "0")
	@RequestMapping({"/deleteEduStudent"})
	public Result<Object> deleteEduStudent(@RequestBody EduStudentVo vo) { 
		return eduStudentService.deleteEduStudent(vo); 
	}
}
