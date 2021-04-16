package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.DateBillDao;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.DateBill;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.DateBillService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.DateBillVo;
import com.meetsun.meetsun.vo.MsUserVo;

@Service
public class DateBillServiceImpl implements DateBillService{
	
	@Autowired
	private DateBillDao dateBillDao;
	@Autowired
	private MsUserDao msUserDao;
	
	@Override
	public Result<Object> getDateBillList(DateBillVo vo) {
		List<DateBill> list = dateBillDao.getDateBillList(vo);
		for(DateBill db : list) {
			MsUserVo mvo =new MsUserVo();
			mvo.setSysId(db.getUserId());
			MsUser msUser = msUserDao.getMsUser(mvo).get(0);
			db.setRealName(msUser.getRealName());

		}
		Result result = new Result();
		int total = dateBillDao.getDateBillListTotal(vo);
		result.setStatus("01");
		result.setRows(list);
		result.setTotal(total);
		return result;
	} 
	
	@Override
	public Result<Object> getDateBillListByType(DateBillVo vo) {
		List<DateBill> list = dateBillDao.getDateBillListByType(vo);
		List<DateBill> list1 = new ArrayList<DateBill>();
		Double countSum = 0.0;
		if(list != null && list.size() > 0) {
			for(DateBill db : list) {
				countSum = countSum + Double.valueOf(db.getSum());
			}
		}
		if(list != null && list.size() > 0) {
			for(DateBill db : list) {
				db.setCountSum(String.valueOf(String.format("%.2f", countSum)));
				list1.add(db);
			}
		}
		return Result.success(list1);
	}
	
	@Override
	public Result<Object> getDateBillListByYear(DateBillVo vo) {
		if(vo.getBillDate() == null || vo.getBillDate() == "") {
			vo.setBillDate(Tools.getYear()+"-12-01");
		}
		List<DateBill> list = dateBillDao.getDateBillListByYear(vo);
		List<DateBill> li = new ArrayList<DateBill>();
		Double countSum = 0.0;
		if(list != null && list.size() > 0) {
			for(DateBill db : list) {
				if(db.getSum() == null) {
					db.setSum("0");
				}
				countSum = countSum + Double.valueOf(db.getSum());
			}
		}
		for(DateBill db:list) {
			db.setCountSum(String.valueOf(countSum));
			li.add(db);
		}
		return Result.success(li);
	}
	
	
	//报表
	@Override
	public Result<Object> getDateBillListByAllYear(DateBillVo vo) {
		if(vo.getBillDate() == null || vo.getBillDate() == "") {
			vo.setBillDate(Tools.getYear());
		}
		List<DateBill> list = dateBillDao.getDateBillListByAllYear(vo);
		List<DateBill> li = new ArrayList<DateBill>();
		Double countSum = 0.0;
		if(list != null && list.size() > 0) {
			for(DateBill db : list) {
				if(db.getSum() == null) {
					db.setSum("0");
				}
				li.add(db);
			}
		}
		return Result.success(li);
	}
	@Override
	public Result<Object> getDateBillListByAllMonth(DateBillVo vo) {
		if(vo.getBillDate() == null || vo.getBillDate() == "") {
			vo.setBillDate(Tools.getYear()+"-01-01");
		}
		List<DateBill> list = dateBillDao.getDateBillListByAllMonth(vo);
		List<DateBill> li = new ArrayList<DateBill>();
		if(list != null && list.size() > 0) {
			for(DateBill db : list) {
				if(db.getSum() == null) {
					db.setSum("0");
				}
				li.add(db);
			}
		}
		return Result.success(li);
	}
	@Override
	public Result<Object> getDateBillListByAllBillType(DateBillVo vo) {
		if(vo.getBillDate().equals("当天")) {
			vo.setBillDate(Tools.get10DateTimes());
		}
		vo.setStatus("0");
		List<DateBill> list = dateBillDao.getDateBillListByAllBillType(vo);
		DateBillVo vo1 = new DateBillVo();
		vo1.setBillDate(vo.getBillDate());
		vo1.setStatus("1");
		List<DateBill> list1 = dateBillDao.getDateBillListByAllBillType(vo1);
		List<DateBill> li = new ArrayList<DateBill>();
		for(DateBill db : list) {
			db.setIncome("0");
			db.setPay(db.getSum());
			li.add(db);
		}
		for(DateBill db : list1) {
			db.setPay("0");
			db.setIncome(db.getSum());
			li.add(db);
		}
		Set<String> set = new HashSet<String>();
		for (DateBill ls : li) {
			if (set.contains(ls.getBillType())) {
				System.out.println("重复值：" + ls);
			}
			set.add(ls.getBillType());
		}
		for (int i = 0; i < li.size() - 1; i++) {
			for (int j = li.size() - 1; j > i; j--) {
				if (li.get(j).getBillType().equals(li.get(i).getBillType())) {
					DateBill d = new DateBill();
					d = li.get(i);
					if(d.getIncome().equals("0") && !d.getPay().equals("0")) {
						d.setIncome(li.get(j).getSum());
					}else {
						d.setPay(li.get(i).getSum());
					}
					li.remove(j);//删除重复元素
					li.remove(i);//删除重复元素
					li.add(d);
				}
			}
		}
		return Result.success(li);
	}
	@Override
	public Result<Object> getDateBillListByAllBillMonth(DateBillVo vo) {
		List<DateBill> li = new ArrayList<DateBill>();
		
		//获取当年总收入
		vo.setBillDate(Tools.getYear());
		vo.setStatus("1");
		List<DateBill> listIncome = dateBillDao.getDateBillListByAllBillMonth(vo);
		//获取当月总收入
		DateBillVo vo1 = new DateBillVo();
		vo1.setBillDate(Tools.get10DateTimes().substring(0,7));
		vo1.setStatus("1");
		List<DateBill> listIncome1 = dateBillDao.getDateBillListByAllBillMonth(vo1);
		
		//获取上年总收入
		DateBillVo vol = new DateBillVo();
		vol.setBillDate(String.valueOf(Integer.valueOf(Tools.getYear())-1));
		vol.setStatus("1");
		List<DateBill> listIncomeUp = dateBillDao.getDateBillListByAllBillMonth(vol);
		//获取上月总收入
		DateBillVo vol1 = new DateBillVo();
		vol1.setBillDate(Tools.getLastMonth());
		vol1.setStatus("1");
		List<DateBill> listIncomeUp1 = dateBillDao.getDateBillListByAllBillMonth(vol1);
		
		String yearSumNow = null; 
		String yearSumNow1 = null; 
		String monthSumNow = null; 
		String monthSumNow1 = null; 
		yearSumNow = returnNum(listIncome);
		monthSumNow = returnNum(listIncome1);
		yearSumNow1 = returnNum(listIncomeUp);
		monthSumNow1 = returnNum(listIncomeUp1);
		
		DateBill monthDb = new DateBill();
		monthDb.setStatus("1");
		monthDb.setSum(monthSumNow);
		if(monthSumNow1.equals("0")) {
			monthDb.setLastMonth("100");
		}else {
			monthDb.setLastMonth(String.valueOf(Common.getTwoDouble(((Double.valueOf(monthSumNow)-Double.valueOf(monthSumNow1))/Double.valueOf(monthSumNow1))*100)));
		}
		if(yearSumNow1.equals("0")) {
			monthDb.setLastYear("100");
		}else {
			monthDb.setLastYear(String.valueOf(Common.getTwoDouble(((Double.valueOf(yearSumNow)-Double.valueOf(yearSumNow1))/Double.valueOf(yearSumNow1))*100)));
		}
		li.add(monthDb);
		
		
		//获取当年总支出
		DateBillVo pvo = new DateBillVo();
		pvo.setBillDate(Tools.getYear());
		pvo.setStatus("0");
		List<DateBill> listPay = dateBillDao.getDateBillListByAllBillMonth(pvo);
		//获取当月总支出
		DateBillVo pvo1 = new DateBillVo();
		pvo1.setBillDate(Tools.get10DateTimes().substring(0,7));
		pvo1.setStatus("0");
		List<DateBill> listPay1 = dateBillDao.getDateBillListByAllBillMonth(pvo1);
		
		//获取上年总支出
		DateBillVo payvo = new DateBillVo();
		payvo.setBillDate(String.valueOf(Integer.valueOf(Tools.getYear())-1));
		payvo.setStatus("0");
		List<DateBill> listPayUp = dateBillDao.getDateBillListByAllBillMonth(payvo);
		//获取上月总支出
		DateBillVo payvo1 = new DateBillVo();
		payvo1.setBillDate(Tools.getLastMonth());
		payvo1.setStatus("0");
		List<DateBill> listPayUp1 = dateBillDao.getDateBillListByAllBillMonth(payvo1);
		
		String yearSumPay = null; 
		String yearSumPay1 = null; 
		String monthSumPay = null; 
		String monthSumPay1 = null; 
		yearSumPay = returnNum(listPay);
		monthSumPay = returnNum(listPay1);
		yearSumPay1 = returnNum(listPayUp);
		monthSumPay1 = returnNum(listPayUp1);
			
		DateBill monthDb1 = new DateBill();
		monthDb1.setStatus("0");
		monthDb1.setSum(monthSumPay);
		if(monthSumPay1.equals("0")) {
			monthDb1.setLastMonth("100");
		}else {
			monthDb1.setLastMonth(String.valueOf(Common.getTwoDouble(((Double.valueOf(monthSumPay)-Double.valueOf(monthSumPay1))/Double.valueOf(monthSumPay1))*100)));
		}
		if(yearSumPay1.equals("0")) {
			monthDb1.setLastYear("100");
		}else {
			monthDb1.setLastYear(String.valueOf(Common.getTwoDouble(((Double.valueOf(yearSumPay)-Double.valueOf(yearSumPay1))/Double.valueOf(yearSumPay1))*100)));
		}
		li.add(monthDb1);
		
		return Result.success(li);
	}
	
	@Override
	public Result<Object> saveDateBill(DateBillVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = dateBillDao.saveDateBill(vo);
		if (flag > 0) {
			return Result.success("成功");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateDateBill(DateBillVo vo) {
		int flag = dateBillDao.updateDateBill(vo);
     	if (flag > 0) {
     		return Result.success("成功");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteDateBill(DateBillVo vo) {
		int flag = dateBillDao.deleteDateBill(vo);
		if (flag > 0) {
			return Result.success("成功");
		}
		return Result.error("error");
	}
	
	public String returnNum(List<DateBill> listIncome) {
		String yearSumNow = null;
		if(listIncome != null && listIncome.size() > 0) {
			if(listIncome.get(0).getSum() != null) {
				yearSumNow = listIncome.get(0).getSum();
			}else {
				yearSumNow = "0";
			}
		}else {
			yearSumNow = "0";
		}
		return yearSumNow;
	}
}
