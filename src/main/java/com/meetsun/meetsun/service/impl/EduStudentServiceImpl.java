package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.EduClassesDao;
import com.meetsun.meetsun.dao.EduCourseDao;
import com.meetsun.meetsun.dao.EduStudentDao;
import com.meetsun.meetsun.entity.EduClasses;
import com.meetsun.meetsun.entity.EduCourse;
import com.meetsun.meetsun.entity.EduStudent;
import com.meetsun.meetsun.service.EduStudentService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.EduClassesVo;
import com.meetsun.meetsun.vo.EduCourseVo;
import com.meetsun.meetsun.vo.EduStudentVo;

@Service
public class EduStudentServiceImpl implements EduStudentService{
	
	@Autowired
	private EduStudentDao eduStudentDao;
	@Autowired
	private EduClassesDao eduClassesDao;
	@Autowired
	private EduCourseDao eduCourseDao;
	
	@Override
	public Result<Object> getEduStudentList(EduStudentVo vo) {
		List<EduStudent> list = eduStudentDao.getEduStudentList(vo);
		List<EduStudent> list1 = new ArrayList<EduStudent>();
		for(EduStudent et:list) {
			EduClassesVo evo = new EduClassesVo();
			evo.setSysId(et.getClaId());
			List<EduClasses> li = eduClassesDao.getEduClassesList(evo);
			if(li != null && li.size() > 0) {
				et.setClaName(li.get(0).getClaName());
			}
			List<String> strli = new ArrayList<String>();
			if(et.getCourseId() != null && et.getCourseId() != "") {
				for(String str : et.getCourseId().split(",")) {
					EduCourseVo evvo = new EduCourseVo();
					evvo.setSysId(str);
					List<EduCourse> ecli = eduCourseDao.getEduCourseList(evvo);
					strli.add(ecli.get(0).getName());
				}
				et.setCourseName(strli.toString().substring(1,strli.toString().length()-1).replaceAll(" +",""));
			}else {
				et.setCourseName(null);
			}
			list1.add(et);
		}
		Result result = new Result();
		int total = eduStudentDao.getEduStudentListTotal(vo);
		result.setStatus("01");
		result.setRows(list1);
		result.setTotal(total);
		return result;
	}

	@Override
	public Result<Object> saveEduStudent(EduStudentVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = eduStudentDao.saveEduStudent(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateEduStudent(EduStudentVo vo) {
		List<String> strli = new ArrayList<String>();
		if(vo.getCourseId() != null && vo.getCourseId() != "") {
			for(String str : vo.getCourseId().split(",")) {
				EduCourseVo evo = new EduCourseVo();
				evo.setName(str);
				List<EduCourse> li = eduCourseDao.getEduCourseList(evo);
				strli.add(li.get(0).getSysId());
			}
			vo.setCourseId(strli.toString().substring(1,strli.toString().length()-1).replaceAll(" +",""));
		}
		int flag = eduStudentDao.updateEduStudent(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteEduStudent(EduStudentVo vo) {
		int flag = eduStudentDao.deleteEduStudent(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
