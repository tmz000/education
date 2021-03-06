package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.EduClassesDao;
import com.meetsun.meetsun.dao.EduStudentDao;
import com.meetsun.meetsun.entity.EduClasses;
import com.meetsun.meetsun.entity.EduStudent;
import com.meetsun.meetsun.service.EduClassesService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.EduClassesVo;
import com.meetsun.meetsun.vo.EduStudentVo;

@Service
public class EduClassesServiceImpl implements EduClassesService{
	
	@Autowired
	private EduClassesDao eduClassesDao;
	@Autowired
	private EduStudentDao eduStudentDao;
	
	@Override
	public Result<Object> getEduClassesList(EduClassesVo vo) {
		Result result = new Result();
		List<EduClasses> list = eduClassesDao.getEduClassesList(vo);
		for(EduClasses et : list) {
			EduStudentVo es = new EduStudentVo();
			es.setClaId(et.getSysId());
			es.setStatus("0");
			et.setCount(eduStudentDao.getEduStudentList(es).size());
 		}
		int total = eduClassesDao.getEduClassesListTotal(vo);
		result.setStatus("01");
		result.setRows(list);
		result.setTotal(total);
		return result;
	}

	@Override
	public Result<Object> saveEduClasses(EduClassesVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = eduClassesDao.saveEduClasses(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateEduClasses(EduClassesVo vo) {
		int flag = eduClassesDao.updateEduClasses(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteEduClasses(EduClassesVo vo) {
		int flag = eduClassesDao.deleteEduClasses(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
