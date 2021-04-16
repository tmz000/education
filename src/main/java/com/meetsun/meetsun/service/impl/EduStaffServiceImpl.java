package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.EduStaffDao;
import com.meetsun.meetsun.entity.EduStaff;
import com.meetsun.meetsun.service.EduStaffService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.EduStaffVo;

@Service
public class EduStaffServiceImpl implements EduStaffService{
	
	@Autowired
	private EduStaffDao eduStaffDao;
	
	@Override
	public Result<Object> getEduStaffList(EduStaffVo vo) {
		Result result = new Result();
		List<EduStaff> list = eduStaffDao.getEduStaffList(vo);
		int total = eduStaffDao.getEduStaffListTotal(vo);
		result.setStatus("01");
		result.setRows(list);
		result.setTotal(total);
		return result;
	}

	@Override
	public Result<Object> saveEduStaff(EduStaffVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = eduStaffDao.saveEduStaff(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateEduStaff(EduStaffVo vo) {
		int flag = eduStaffDao.updateEduStaff(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteEduStaff(EduStaffVo vo) {
		int flag = eduStaffDao.deleteEduStaff(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
