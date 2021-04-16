package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.EduPointsTypeDao;
import com.meetsun.meetsun.entity.EduPointsType;
import com.meetsun.meetsun.service.EduPointsTypeService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.EduPointsTypeVo;

@Service
public class EduPointsTypeServiceImpl implements EduPointsTypeService{
	
	@Autowired
	private EduPointsTypeDao eduPointsTypeDao;
	
	@Override
	public Result<Object> getEduPointsTypeList(EduPointsTypeVo vo) {
		List<EduPointsType> list = eduPointsTypeDao.getEduPointsTypeList(vo);
		Result result = new Result();
		int total = eduPointsTypeDao.getEduPointsTypeListTotal(vo);
		result.setStatus("01");
		result.setRows(list);
		result.setTotal(total);
		return result;
	}

	@Override
	public Result<Object> saveEduPointsType(EduPointsTypeVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = eduPointsTypeDao.saveEduPointsType(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateEduPointsType(EduPointsTypeVo vo) {
		int flag = eduPointsTypeDao.updateEduPointsType(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteEduPointsType(EduPointsTypeVo vo) {
		int flag = eduPointsTypeDao.deleteEduPointsType(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
