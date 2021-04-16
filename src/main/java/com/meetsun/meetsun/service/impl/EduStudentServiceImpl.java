package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.EduClassesDao;
import com.meetsun.meetsun.dao.EduStudentDao;
import com.meetsun.meetsun.entity.EduClasses;
import com.meetsun.meetsun.entity.EduStaff;
import com.meetsun.meetsun.entity.EduStudent;
import com.meetsun.meetsun.service.EduStudentService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.EduClassesVo;
import com.meetsun.meetsun.vo.EduStudentVo;

@Service
public class EduStudentServiceImpl implements EduStudentService{
	
	@Autowired
	private EduStudentDao eduStudentDao;
	@Autowired
	private EduClassesDao eduClassesDao;
	
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
