package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.EduDeptDao;
import com.meetsun.meetsun.dao.EduDeptTypeDao;
import com.meetsun.meetsun.dao.EduStaffDao;
import com.meetsun.meetsun.entity.EduDept;
import com.meetsun.meetsun.entity.EduDeptType;
import com.meetsun.meetsun.service.EduDeptTypeService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.EduDeptTypeVo;
import com.meetsun.meetsun.vo.EduDeptVo;
import com.meetsun.meetsun.vo.EduStaffVo;

@Service
public class EduDeptTypeServiceImpl implements EduDeptTypeService{
	
	@Autowired
	private EduDeptTypeDao eduDeptTypeDao;
	@Autowired
	private EduDeptDao eduDeptDao;
	@Autowired
	private EduStaffDao eduStaffDao;
	
	@Override
	public Result<Object> getEduDeptTypeList(EduDeptTypeVo vo) {
		Result result = new Result();
		List<EduDeptType> list = eduDeptTypeDao.getEduDeptTypeList(vo);
		for(EduDeptType ef:list) {
			EduDeptVo ev = new EduDeptVo();
			ev.setSysId(ef.getDeptId());
			List<EduDept> li = eduDeptDao.getEduDeptList(ev);
			if(li.size() > 0) {
				ef.setDeptName(li.get(0).getName());
			}
			EduStaffVo es = new EduStaffVo();
			es.setDeptTypeId(ef.getSysId());
			es.setStatus("0");
			ef.setCount(eduStaffDao.getEduStaffList(es).size());
		}
		int total = eduDeptTypeDao.getEduDeptTypeListTotal(vo);
		result.setStatus("01");
		result.setRows(list);
		result.setTotal(total);
		return result;
	}

	@Override
	public Result<Object> saveEduDeptType(EduDeptTypeVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = eduDeptTypeDao.saveEduDeptType(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateEduDeptType(EduDeptTypeVo vo) {
		int flag = eduDeptTypeDao.updateEduDeptType(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteEduDeptType(EduDeptTypeVo vo) {
		int flag = eduDeptTypeDao.deleteEduDeptType(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
