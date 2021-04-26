package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.EduDeptDao;
import com.meetsun.meetsun.dao.EduDeptTypeDao;
import com.meetsun.meetsun.dao.EduStaffDao;
import com.meetsun.meetsun.dao.KpiNotesDao;
import com.meetsun.meetsun.entity.EduDept;
import com.meetsun.meetsun.entity.EduStaff;
import com.meetsun.meetsun.entity.KpiNotes;
import com.meetsun.meetsun.service.KpiNotesService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.EduDeptTypeVo;
import com.meetsun.meetsun.vo.EduDeptVo;
import com.meetsun.meetsun.vo.EduStaffVo;
import com.meetsun.meetsun.vo.KpiNotesVo;

@Service
public class KpiNotesServiceImpl implements KpiNotesService{
	
	@Autowired
	private KpiNotesDao kpiNotesDao;
	@Autowired
	private EduStaffDao eduStaffDao;
	@Autowired
	private EduDeptDao eduDeptDao;
	@Autowired
	private EduDeptTypeDao eduDeptTypeDao;
	
	@Override
	public Result<Object> getKpiNotesList(KpiNotesVo vo) {
		List<KpiNotes> list = kpiNotesDao.getKpiNotesList(vo);
		for(KpiNotes kp : list) {
			if(kp.getStaffId() != null && kp.getStaffId() != "") {
				EduStaffVo evo = new EduStaffVo();
				evo.setSysId(kp.getStaffId());
				kp.setStaffName(eduStaffDao.getEduStaffList(evo).get(0).getName());
				EduDeptVo edvo = new EduDeptVo();
				edvo.setSysId(kp.getDeptId());
				kp.setDeptName(eduDeptDao.getEduDeptList(edvo).get(0).getName());
				if(kp.getDeptTypeId() != null) {
					EduDeptTypeVo edtvo = new EduDeptTypeVo();
					edvo.setSysId(kp.getDeptTypeId());
					kp.setDeptTypeName(eduDeptTypeDao.getEduDeptTypeList(edtvo).get(0).getName());
				}
			}
		}
		Result result = new Result();
		int total = kpiNotesDao.getKpiNotesListTotal(vo);
		result.setStatus("01");
		result.setRows(list);
		result.setTotal(total);
		return result;
	}
	//kpi详情
	@Override
	public Result<Object> getKpiNotesDetailList(KpiNotesVo vo) {
		List<KpiNotes> list = kpiNotesDao.getKpiNotesDetailList(vo);
		for(KpiNotes kp : list) {
			if(kp.getStaffId() != null && kp.getStaffId() != "") {
				EduStaffVo evo = new EduStaffVo();
				evo.setSysId(kp.getStaffId());
				kp.setStaffName(eduStaffDao.getEduStaffList(evo).get(0).getName());
				EduDeptVo edvo = new EduDeptVo();
				edvo.setSysId(kp.getDeptId());
				kp.setDeptName(eduDeptDao.getEduDeptList(edvo).get(0).getName());
				if(kp.getDeptTypeId() != null) {
					EduDeptTypeVo edtvo = new EduDeptTypeVo();
					edvo.setSysId(kp.getDeptTypeId());
					kp.setDeptTypeName(eduDeptTypeDao.getEduDeptTypeList(edtvo).get(0).getName());
				}
			}
		}
		return Result.success(list);
	}

	@Override
	public Result<Object> saveKpiNotes(KpiNotes vo) {
		vo.setSysId(Tools.getUUID());
		int flag = kpiNotesDao.saveKpiNotes(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateKpiNotes(KpiNotesVo vo) {
		int flag = kpiNotesDao.updateKpiNotes(vo);
//		List<KpiNotes> list = kpiNotesDao.getKpiNotesDetailList(vo);
//		KpiNotes kp = new KpiNotes();
//		KpiNotesVo vo1 = new KpiNotesVo();
//		for(KpiNotes k : list) {
//			vo1.setKpi("合计");
//			vo1.setSerialNum(k.getSerialNum());
//			kp = kpiNotesDao.getKpiNotesDetailList(vo1).get(0);
//		}
//		if(kp != null) {
//			KpiNotesVo vo2 = new KpiNotesVo();
//			vo2.setKpi("合计");
//			vo2.setSerialNum(kp.getSerialNum());
//			if(vo.getDeptPoints() != null && vo.getDeptPoints() != "") {
//				vo2.setDeptPoints(String.valueOf(Integer.valueOf(kp.getDeptPoints()) + Integer.valueOf(vo.getDeptPoints())));
//			}
//			if(vo.getAdCheck() != null && vo.getAdCheck() != "") {
//				vo2.setAdCheck(String.valueOf(Integer.valueOf(kp.getAdCheck()) + Integer.valueOf(vo.getAdCheck())));
//			}
//			kpiNotesDao.updateKpiNotes(vo2);
//		}
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteKpiNotes(KpiNotesVo vo) {
		int flag = kpiNotesDao.deleteKpiNotes(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
	//kpi生成
	@Override
	public Result<Object> getKpiByStaffId(KpiNotesVo vo) {
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
		List<KpiNotes> listc = kpiNotesDao.getKpiNotesList(vo);
		
		String deptId = "";
		String deptTypeId = "";
		String deptName = "";
		String staffName = "";
		String serialNum = Tools.getUUID();
		Integer deptPointsSum = 0;
		Integer adCheckSum = 0;
		if(vo.getStaffId() != null && vo.getStaffId() != "") {
			EduStaffVo evo = new EduStaffVo();
			evo.setSysId(vo.getStaffId());
			List<EduStaff> evli = eduStaffDao.getEduStaffList(evo);
			deptId = evli.get(0).getDeptId();
			deptTypeId = evli.get(0).getDeptTypeId();
			staffName = evli.get(0).getName();
			EduDeptVo edvo = new EduDeptVo();
			edvo.setSysId(deptId);
			deptName = eduDeptDao.getEduDeptList(edvo).get(0).getName();
		}
		List<KpiNotes> list = kpiNotesDao.getKpiByStaffId(vo);
		for(KpiNotes kp : list) {
			if(kp.getAdCheck() == null || kp.getAdCheck() == "") {
				kp.setAdCheck("");
			}
			if(kp.getDeptPoints() == null || kp.getDeptPoints() == "") {
				kp.setDeptPoints("");
			}
			if(vo.getStaffId() != null) {
				kp.setStaffId(vo.getStaffId());
			}
			if(kp.getReason() == null || kp.getReason() == "") {
				kp.setReason("");
			}
			kp.setDeptId(deptId);
			kp.setDeptName(deptName);
			kp.setAddTime(vo.getAddTime());
			kp.setAndTime(vo.getAndTime());
			kp.setStaffId(vo.getStaffId());
			kp.setStaffName(staffName);
			kp.setSerialNum(serialNum);
			kp.setSysId(Tools.getUUID());
			if(kp.getDeptPoints() != null && kp.getDeptPoints() != "") {
				kp.setAdCheck(kp.getDeptPoints());
				deptPointsSum = deptPointsSum + Integer.valueOf(kp.getDeptPoints());
				adCheckSum = adCheckSum + Integer.valueOf(kp.getAdCheck());
			}
		}
		//增加合计
		KpiNotes kp = new KpiNotes();
		kp.setDeptId(deptId);
		kp.setDeptName(deptName);
		kp.setAddTime(vo.getAddTime());
		kp.setAndTime(vo.getAndTime());
		kp.setStaffId(vo.getStaffId());
		kp.setStaffName(staffName);
		kp.setSerialNum(serialNum);
		kp.setSysId(Tools.getUUID());
		kp.setKpiType(list.get(list.size()-1).getKpiType());
		kp.setKpi("合计");
		kp.setPoints("100");
		kp.setDeptPoints(String.valueOf(100+deptPointsSum));
		kp.setAdCheck(String.valueOf(100+adCheckSum));
		kp.setReason("");
		list.add(kp);
		//增加底部两行数据
		KpiNotes kp1 = new KpiNotes();
		kp1.setDeptId(deptId);
		kp1.setDeptName(deptName);
		kp1.setAddTime(vo.getAddTime());
		kp1.setAndTime(vo.getAndTime());
		kp1.setStaffId(vo.getStaffId());
		kp1.setStaffName(staffName);
		kp1.setSerialNum(serialNum);
		kp1.setSysId(Tools.getUUID());
		kp1.setKpiType("部门");
		kp1.setKpi(deptName);
		kp1.setPoints("员工");
		kp1.setDeptPoints(staffName);
		kp1.setAdCheck("考核时间");
		kp1.setReason(vo.getAddTime()+"&nbsp;&nbsp;至&nbsp;&nbsp;"+vo.getAndTime());
		list.add(kp1);
		//获取该员工部门领导名字
		EduStaffVo evo = new EduStaffVo();
		evo.setDeptId(deptId);
		evo.setIsLead("0");
		List<EduStaff> evli = eduStaffDao.getEduStaffList(evo);
		KpiNotes kp2 = new KpiNotes();
		//获取行政部门领导名字
		String deptId2 = null;
		EduDeptVo edvo = new EduDeptVo();
		edvo.setName("行政部");
		List<EduDept> listEdp = eduDeptDao.getEduDeptList(edvo);
		if(listEdp != null && listEdp.size() > 0) {
			deptId2 = listEdp.get(0).getSysId();
		}
		List<EduStaff> evli2 = new ArrayList<EduStaff>();
		if(deptId2 != null) {
			EduStaffVo evo2 = new EduStaffVo();
			evo2.setDeptId(deptId2);
			evo2.setIsLead("0");
			evli2 = eduStaffDao.getEduStaffList(evo2);
		}
		kp2.setDeptId(deptId);
		kp2.setDeptName(deptName);
		kp2.setAddTime(vo.getAddTime());
		kp2.setAndTime(vo.getAndTime());
		kp2.setStaffId(vo.getStaffId());
		kp2.setStaffName(staffName);
		kp2.setSerialNum(serialNum);
		kp2.setSysId(Tools.getUUID());
		kp2.setKpiType("部门签字");
		if(evli != null && evli.size() > 0) {
			kp2.setKpi(evli.get(0).getName());
		}else {
			kp2.setKpi("");
		}
		kp2.setPoints("");
		kp2.setDeptPoints("行政签字");
		if(evli2 != null && evli2.size() > 0) {
			kp2.setAdCheck(evli2.get(0).getName());
		}else {
			kp2.setAdCheck("");
		}
		kp2.setReason("");
		list.add(kp2);
		
		if(vo.getStaffId() != null && vo.getStaffId() != "") {
			if(listc != null && listc.size() > 0) {
				KpiNotesVo kvo = new KpiNotesVo();
				kvo.setStaffId(vo.getStaffId());
				kvo.setSerialNum(listc.get(0).getSerialNum());
				deleteKpiNotes(kvo);
			}
			for(KpiNotes k : list) {
				k.setDeptTypeId(deptTypeId);
				kpiNotesDao.saveKpiNotes(k);
			}
		}
		return Result.success(list);
	}
	
	@Override
	public Result<Object> saveKpiNotesBatch(KpiNotesVo vo) {
		List<EduStaff> list = eduStaffDao.getEduStaffList(new EduStaffVo());
		for(EduStaff es : list) {
			vo.setStaffId(es.getSysId());
			this.getKpiByStaffId(vo);
		}
		return Result.success("success");
	}
}
