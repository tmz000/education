package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.DateBillDao;
import com.meetsun.meetsun.dao.DateBillTypeDao;
import com.meetsun.meetsun.dao.EduStudentDao;
import com.meetsun.meetsun.entity.DateBill;
import com.meetsun.meetsun.entity.DateBillType;
import com.meetsun.meetsun.service.DateBillTypeService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.DateBillTypeVo;
import com.meetsun.meetsun.vo.DateBillVo;
import com.meetsun.meetsun.vo.EduStudentVo;

@Service
public class DateBillTypeServiceImpl implements DateBillTypeService{
	
	@Autowired
	private DateBillTypeDao dateBillTypeDao;
	@Autowired
	private DateBillDao dateBillDao;
	
	@Override
	public Result<Object> getDateBillTypeList(DateBillTypeVo vo) {
		List<DateBillType> list = dateBillTypeDao.getDateBillTypeList(vo);
		Result result = new Result();
		int total = dateBillTypeDao.getDateBillTypeListTotal(vo);
		result.setStatus("01");
		result.setRows(list);
		result.setTotal(total);
		return result;
	}

	@Override
	public Result<Object> saveDateBillType(DateBillTypeVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = dateBillTypeDao.saveDateBillType(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateDateBillType(DateBillTypeVo vo) {
		if(vo.getOldBillType() != null && vo.getOldBillType() != "") {
			DateBillVo vo1 = new DateBillVo();
			vo1.setBillType(vo.getOldBillType());
			List<DateBill> list = dateBillDao.getDateBillList(vo1);
			for(DateBill dd : list) {
				DateBillVo vo2 = new DateBillVo();
				vo2.setSysId(dd.getSysId());
				vo2.setBillType(vo.getType());
				dateBillDao.updateDateBill(vo2);
			}
		}
		int flag = dateBillTypeDao.updateDateBillType(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteDateBillType(DateBillTypeVo vo) {
		DateBillVo vo1 = new DateBillVo();
		vo1.setBillType(vo.getType());
		List<DateBill> list = dateBillDao.getDateBillList(vo1);
		if(list != null && list.size() > 0) {
			return Result.error("该类型在【日录账单】存在数据，请修改为其他类型，在进行此操作！");
		}
		int flag = dateBillTypeDao.deleteDateBillType(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
