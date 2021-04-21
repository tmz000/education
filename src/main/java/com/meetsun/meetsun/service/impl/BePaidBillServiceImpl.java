package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.BePaidBillDao;
import com.meetsun.meetsun.dao.DateBillDao;
import com.meetsun.meetsun.dao.DateBillTypeDao;
import com.meetsun.meetsun.dao.EduClassesDao;
import com.meetsun.meetsun.dao.EduStudentDao;
import com.meetsun.meetsun.entity.BePaidBill;
import com.meetsun.meetsun.entity.DateBillType;
import com.meetsun.meetsun.entity.EduClasses;
import com.meetsun.meetsun.entity.EduStudent;
import com.meetsun.meetsun.service.BePaidBillService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.BePaidBillVo;
import com.meetsun.meetsun.vo.DateBillTypeVo;
import com.meetsun.meetsun.vo.DateBillVo;
import com.meetsun.meetsun.vo.EduClassesVo;
import com.meetsun.meetsun.vo.EduStudentVo;

@Service
public class BePaidBillServiceImpl implements BePaidBillService{
	
	@Autowired
	private BePaidBillDao bePaidBillDao;
	@Autowired
	private DateBillTypeDao dateBillTypeDao;
	@Autowired
	private DateBillDao dateBillDao;
	@Autowired
	private EduClassesDao eduClassesDao;
	@Autowired
	private EduStudentDao eduStudentDao;
	
	@Override
	public Result<Object> getBePaidBillList(BePaidBillVo vo) {
		Result result = new Result();
		List<BePaidBill> list = bePaidBillDao.getBePaidBillList(vo);
		List<BePaidBill> list1 = new ArrayList<BePaidBill>();
		String sum = bePaidBillDao.getBePaidBillListSum(vo);
		for(BePaidBill b : list) {
			DateBillTypeVo dvo = new DateBillTypeVo();
			dvo.setSysId(b.getBillType());
			List<DateBillType> li = dateBillTypeDao.getDateBillTypeList(dvo);
			if(li != null && li.size() > 0) {
				b.setType(li.get(0).getType());
			}
			
			EduClassesVo evo = new EduClassesVo();
			evo.setSysId(b.getClaId());
			List<EduClasses> lie = eduClassesDao.getEduClassesList(evo);
			if(lie != null && lie.size() > 0) {
				b.setClaName(lie.get(0).getClaName());
			}
			
			EduStudentVo ev = new EduStudentVo();
			ev.setSysId(b.getStuId());
			List<EduStudent> lide = eduStudentDao.getEduStudentList(ev);
			if(lide != null && lide.size() > 0) {
				b.setStuName(lide.get(0).getName());
			}
			b.setSum(sum);
			list1.add(b);
		}
		int total = bePaidBillDao.getBePaidBillListTotal(vo);
		result.setStatus("01");
		result.setRows(list1);
		result.setTotal(total);
		return result;
	}

	@Override
	public Result<Object> saveBePaidBill(BePaidBillVo vo) {
		vo.setSysId(Tools.getUUID());
//		if(vo.getYuPay().equals("") || vo.getYuPay() == null) {
//			vo.setYuPay("0");
//		}
//		if(vo.getHouPay().equals("") || vo.getHouPay() == null) {
//			vo.setHouPay("0");
//		}
		int flag = bePaidBillDao.saveBePaidBill(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateBePaidBill(BePaidBillVo vo) {
//		if(vo.getYuPay().equals("") || vo.getYuPay() == null) {
//			vo.setYuPay("0");
//		}
//		if(vo.getHouPay().equals("") || vo.getHouPay() == null) {
//			vo.setHouPay("0");
//		}
//		String sum = String.valueOf(Double.valueOf(vo.getYuPay())+Double.valueOf(vo.getHouPay()));
//		String money = String.valueOf(Double.valueOf(vo.getMoney()));
//		if(sum.equals(money)) {
			int flag = bePaidBillDao.updateBePaidBill(vo);
	     	if (flag > 0) {
	     		if(vo.getStatus().equals("1")) {
     				DateBillVo dvo = new DateBillVo();
     				dvo.setSysId(Tools.getUUID());
     				dvo.setBillMoney(vo.getMoney());
     				dvo.setBillDate(vo.getPayTime());
     				dvo.setStatus("1");
     				dvo.setUserId(vo.getUserId());
     				dvo.setBillRemark(vo.getStuName()+vo.getRemark());
     				
     				DateBillTypeVo ddvo = new DateBillTypeVo();
     				ddvo.setSysId(vo.getBillType());
     				List<DateBillType> li = dateBillTypeDao.getDateBillTypeList(ddvo);
     				dvo.setBillType(li.get(0).getType().trim());
     				dateBillDao.saveDateBill(dvo);
	     		}
	     		return Result.success("成功");
	     	}
//     	}else {
//			return Result.error("预付费和后付费总金额与待缴费的金额不符！");
//		}
     	return Result.error("失败");
	}

	@Override
	public Result<Object> deleteBePaidBill(BePaidBillVo vo) {
		int flag = bePaidBillDao.deleteBePaidBill(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
