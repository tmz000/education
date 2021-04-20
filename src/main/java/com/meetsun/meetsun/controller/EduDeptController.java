package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.EduDeptService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.EduDeptVo;

@RestController
@RequestMapping("/eduDept")
public class EduDeptController {
	@Autowired
	private EduDeptService eduDeptService;
	
	@RequestMapping({"/getEduDeptList"})
	public Result<Object> getEduDeptList(@RequestBody EduDeptVo vo,HttpServletRequest request) { 
		return eduDeptService.getEduDeptList(vo); 
	}
	
	@Log(operation = "新增" , remark="【部门】新增部门" , type = "0")
	@RequestMapping({"/saveEduDept"})
	public Result<Object> saveEduDept(@RequestBody EduDeptVo vo,HttpServletRequest request) {
		return eduDeptService.saveEduDept(vo); 
	}
   
	@Log(operation = "修改" , remark="【部门】修改部门" , type = "0")
	@RequestMapping({"/updateEduDept"})
	public Result<Object> updateEduDept(@RequestBody EduDeptVo vo) { 
		return eduDeptService.updateEduDept(vo); 
	}
   
	@Log(operation = "删除" , remark="【部门】删除部门" , type = "0")
	@RequestMapping({"/deleteEduDept"})
	public Result<Object> deleteEduDept(@RequestBody EduDeptVo vo) { 
		return eduDeptService.deleteEduDept(vo); 
	}
}
