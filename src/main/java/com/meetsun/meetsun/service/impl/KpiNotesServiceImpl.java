package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.KpiNotesDao;
import com.meetsun.meetsun.entity.KpiNotes;
import com.meetsun.meetsun.service.KpiNotesService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.KpiNotesVo;

@Service
public class KpiNotesServiceImpl implements KpiNotesService{
	
	@Autowired
	private KpiNotesDao kpiNotesDao;
	
	@Override
	public Result<Object> getKpiNotesList(KpiNotesVo vo) {
		List<KpiNotes> list = kpiNotesDao.getKpiNotesList(vo);
		Result result = new Result();
		int total = kpiNotesDao.getKpiNotesListTotal(vo);
		result.setStatus("01");
		result.setRows(list);
		result.setTotal(total);
		return result;
	}

	@Override
	public Result<Object> saveKpiNotes(KpiNotesVo vo) {
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
}
