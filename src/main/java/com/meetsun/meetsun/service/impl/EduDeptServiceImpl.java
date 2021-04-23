package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.EduDeptDao;
import com.meetsun.meetsun.dao.EduStaffDao;
import com.meetsun.meetsun.entity.EduDept;
import com.meetsun.meetsun.service.EduDeptService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.EduDeptVo;
import com.meetsun.meetsun.vo.EduStaffVo;

@Service
public class EduDeptServiceImpl implements EduDeptService{
	
	@Autowired
	private EduDeptDao eduDeptDao;
	@Autowired
	private EduStaffDao eduStaffDao;
	
	@Override
	public Result<Object> getEduDeptList(EduDeptVo vo) {
		Result result = new Result();
		List<EduDept> list = eduDeptDao.getEduDeptList(vo);
		for(EduDept et : list) {
			EduStaffVo es = new EduStaffVo();
			es.setDeptId(et.getSysId());
			es.setStatus("0");
			et.setCount(eduStaffDao.getEduStaffList(es).size());
 		}
		int total = eduDeptDao.getEduDeptListTotal(vo);
		result.setStatus("01");
		result.setRows(list);
		result.setTotal(total);
		return result;
	}

	@Override
	public Result<Object> saveEduDept(EduDeptVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = eduDeptDao.saveEduDept(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateEduDept(EduDeptVo vo) {
		int flag = eduDeptDao.updateEduDept(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteEduDept(EduDeptVo vo) {
		int flag = eduDeptDao.deleteEduDept(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
