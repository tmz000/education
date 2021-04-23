package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.EduDeptTypeService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.EduDeptTypeVo;

@RestController
@RequestMapping("/eduDeptType")
public class EduDeptTypeController {
	@Autowired
	private EduDeptTypeService eduDeptTypeService;
	
	@RequestMapping({"/getEduDeptTypeList"})
	public Result<Object> getEduDeptTypeList(@RequestBody EduDeptTypeVo vo,HttpServletRequest request) { 
		return eduDeptTypeService.getEduDeptTypeList(vo); 
	}
	
	@Log(operation = "新增" , remark="【部门职务】新增部门职务" , type = "0")
	@RequestMapping({"/saveEduDeptType"})
	public Result<Object> saveEduDeptType(@RequestBody EduDeptTypeVo vo,HttpServletRequest request) {
		return eduDeptTypeService.saveEduDeptType(vo); 
	}
   
	@Log(operation = "修改" , remark="【部门职务】修改部门职务" , type = "0")
	@RequestMapping({"/updateEduDeptType"})
	public Result<Object> updateEduDeptType(@RequestBody EduDeptTypeVo vo) { 
		return eduDeptTypeService.updateEduDeptType(vo); 
	}
   
	@Log(operation = "删除" , remark="【部门职务】删除部门职务" , type = "0")
	@RequestMapping({"/deleteEduDeptType"})
	public Result<Object> deleteEduDeptType(@RequestBody EduDeptTypeVo vo) { 
		return eduDeptTypeService.deleteEduDeptType(vo); 
	}
}
