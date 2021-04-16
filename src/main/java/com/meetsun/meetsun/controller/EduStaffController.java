package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.EduStaffService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.EduStaffVo;

@RestController
@RequestMapping("/eduStaff")
public class EduStaffController {
	@Autowired
	private EduStaffService eduStaffService;
	
	@RequestMapping({"/getEduStaffList"})
	public Result<Object> getEduStaffList(@RequestBody EduStaffVo vo,HttpServletRequest request) { 
		return eduStaffService.getEduStaffList(vo); 
	}
	
	@Log(operation = "新增" , remark="【员工管理】新增员工管理" , type = "0")
	@RequestMapping({"/saveEduStaff"})
	public Result<Object> saveEduStaff(@RequestBody EduStaffVo vo,HttpServletRequest request) {
		return eduStaffService.saveEduStaff(vo); 
	}
   
	@Log(operation = "修改" , remark="【员工管理】修改员工管理" , type = "0")
	@RequestMapping({"/updateEduStaff"})
	public Result<Object> updateEduStaff(@RequestBody EduStaffVo vo) { 
		return eduStaffService.updateEduStaff(vo); 
	}
   
	@Log(operation = "删除" , remark="【员工管理】删除员工管理" , type = "0")
	@RequestMapping({"/deleteEduStaff"})
	public Result<Object> deleteEduStaff(@RequestBody EduStaffVo vo) { 
		return eduStaffService.deleteEduStaff(vo); 
	}
}
