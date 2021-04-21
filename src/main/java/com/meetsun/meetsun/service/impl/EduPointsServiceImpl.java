package com.meetsun.meetsun.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.EduClassesDao;
import com.meetsun.meetsun.dao.EduPointsDao;
import com.meetsun.meetsun.dao.EduPointsTypeDao;
import com.meetsun.meetsun.dao.EduStaffDao;
import com.meetsun.meetsun.entity.EduClasses;
import com.meetsun.meetsun.entity.EduPoints;
import com.meetsun.meetsun.entity.EduPointsType;
import com.meetsun.meetsun.entity.EduStaff;
import com.meetsun.meetsun.service.EduPointsService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.EduClassesVo;
import com.meetsun.meetsun.vo.EduPointsTypeVo;
import com.meetsun.meetsun.vo.EduPointsVo;
import com.meetsun.meetsun.vo.EduStaffVo;

@Service
public class EduPointsServiceImpl implements EduPointsService{
	
	@Autowired
	private EduPointsDao eduPointsDao;
	@Autowired
	private EduClassesDao eduClassesDao;
	@Autowired
	private EduStaffDao eduStaffDao;
	@Autowired
	private EduPointsTypeDao eduPointsTypeDao;
	
	@Override
	public Result<Object> getEduPointsList(EduPointsVo vo) {
		List<EduPoints> list = eduPointsDao.getEduPointsList(vo);
		List<EduPoints> list1 = new ArrayList<EduPoints>();
		for(EduPoints et:list) {
			EduClassesVo evo = new EduClassesVo();
			evo.setSysId(et.getClaId());
			List<EduClasses> li = eduClassesDao.getEduClassesList(evo);
			if(li != null && li.size() > 0) {
				et.setClaName(li.get(0).getClaName());
			}
			EduStaffVo stvo = new EduStaffVo();
			stvo.setSysId(et.getTeaId());
			List<EduStaff> lit = eduStaffDao.getEduStaffList(stvo);
			if(lit != null && lit.size() > 0) {
				et.setTeaName(lit.get(0).getName());
			}
			EduPointsTypeVo stpvo = new EduPointsTypeVo();
			stpvo.setSysId(et.getPointsTypeId());
			List<EduPointsType> litp = eduPointsTypeDao.getEduPointsTypeList(stpvo);
			if(litp != null && litp.size() > 0) {
				et.setPointsMake(litp.get(0).getPointsMake());
			}
			list1.add(et);
		}
		Result result = new Result();
		int total = eduPointsDao.getEduPointsListTotal(vo);
		result.setStatus("01");
		result.setRows(list1);
		result.setTotal(total);
		return result;
	}

	@Override
	public Result<Object> saveEduPoints(EduPointsVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = eduPointsDao.saveEduPoints(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateEduPoints(EduPointsVo vo) {
		int flag = eduPointsDao.updateEduPoints(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteEduPoints(EduPointsVo vo) {
		int flag = eduPointsDao.deleteEduPoints(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> getEduPointsListByDate(EduPointsVo vo) {
		if(vo.getAddTime() == "" || vo.getAddTime() == null) {
			String nowTime = Tools.get10DateTimes();
			if(Tools.getTrueTime(nowTime, Tools.getStarDate(16))) {
				vo.setAddTime(Tools.getStarDate(1));
				vo.setAndTime(Tools.getStarDate(15));
			}else {
				vo.setAddTime(Tools.getStarDate(16));
				vo.setAndTime(Tools.getEndDate());
			}
		}
		List<EduPoints> list = eduPointsDao.getEduPointsListByDate(vo);
		for(EduPoints et:list) {
			EduStaffVo stvo = new EduStaffVo();
			stvo.setSysId(et.getTeaId());
			List<EduStaff> lit = eduStaffDao.getEduStaffList(stvo);
			if(lit != null && lit.size() > 0) {
				et.setTeaName(lit.get(0).getName());
			}
			try {
				et.setCycleTime(Tools.getCycleTime(vo.getAddTime(), vo.getAndTime(),1));
			} catch (ParseException e) {
			}
			et.setAddTime(vo.getAddTime());
			et.setAndTime(vo.getAndTime());
		}
		return Result.success(list);
	}
}
