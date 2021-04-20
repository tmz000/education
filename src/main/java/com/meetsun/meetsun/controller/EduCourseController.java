package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.EduCourseService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.EduCourseVo;

@RestController
@RequestMapping("/eduCourse")
public class EduCourseController {
	@Autowired
	private EduCourseService eduCourseService;
	
	@RequestMapping({"/getEduCourseList"})
	public Result<Object> getEduCourseList(@RequestBody EduCourseVo vo,HttpServletRequest request) { 
		return eduCourseService.getEduCourseList(vo); 
	}
	
	@Log(operation = "新增" , remark="【课程】新增课程" , type = "0")
	@RequestMapping({"/saveEduCourse"})
	public Result<Object> saveEduCourse(@RequestBody EduCourseVo vo,HttpServletRequest request) {
		return eduCourseService.saveEduCourse(vo); 
	}
   
	@Log(operation = "修改" , remark="【课程】修改课程" , type = "0")
	@RequestMapping({"/updateEduCourse"})
	public Result<Object> updateEduCourse(@RequestBody EduCourseVo vo) { 
		return eduCourseService.updateEduCourse(vo); 
	}
   
	@Log(operation = "删除" , remark="【课程】删除课程" , type = "0")
	@RequestMapping({"/deleteEduCourse"})
	public Result<Object> deleteEduCourse(@RequestBody EduCourseVo vo) { 
		return eduCourseService.deleteEduCourse(vo); 
	}
}
