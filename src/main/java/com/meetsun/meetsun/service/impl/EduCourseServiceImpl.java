package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.EduCourseDao;
import com.meetsun.meetsun.dao.EduStudentDao;
import com.meetsun.meetsun.entity.EduCourse;
import com.meetsun.meetsun.service.EduCourseService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.EduCourseVo;
import com.meetsun.meetsun.vo.EduStudentVo;

@Service
public class EduCourseServiceImpl implements EduCourseService{
	
	@Autowired
	private EduCourseDao eduCourseDao;
	@Autowired
	private EduStudentDao eduStudentDao;
	
	@Override
	public Result<Object> getEduCourseList(EduCourseVo vo) {
		Result result = new Result();
		List<EduCourse> list = eduCourseDao.getEduCourseList(vo);
		for(EduCourse et : list) {
			EduStudentVo es = new EduStudentVo();
			es.setClaId(et.getSysId());
			et.setCount(eduStudentDao.getEduStudentList(es).size());
 		}
		int total = eduCourseDao.getEduCourseListTotal(vo);
		result.setStatus("01");
		result.setRows(list);
		result.setTotal(total);
		return result;
	}

	@Override
	public Result<Object> saveEduCourse(EduCourseVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = eduCourseDao.saveEduCourse(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateEduCourse(EduCourseVo vo) {
		int flag = eduCourseDao.updateEduCourse(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteEduCourse(EduCourseVo vo) {
		int flag = eduCourseDao.deleteEduCourse(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
