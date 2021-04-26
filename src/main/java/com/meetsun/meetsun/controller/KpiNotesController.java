package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.entity.KpiNotes;
import com.meetsun.meetsun.service.KpiNotesService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.KpiNotesVo;

@RestController
@RequestMapping("/kpiNotes")
public class KpiNotesController {
	@Autowired
	private KpiNotesService kpiNotesService;
	
	@RequestMapping({"/getKpiNotesList"})
	public Result<Object> getKpiNotesList(@RequestBody KpiNotesVo vo,HttpServletRequest request) { 
		return kpiNotesService.getKpiNotesList(vo); 
	}
	
	@RequestMapping({"/getKpiNotesDetailList"})
	public Result<Object> getKpiNotesDetailList(@RequestBody KpiNotesVo vo,HttpServletRequest request) { 
		return kpiNotesService.getKpiNotesDetailList(vo); 
	}
	
	@Log(operation = "新增" , remark="【KPI考核】新增KPI考核" , type = "0")
	@RequestMapping({"/saveKpiNotes"})
	public Result<Object> saveKpiNotes(@RequestBody KpiNotes vo,HttpServletRequest request) {
		return kpiNotesService.saveKpiNotes(vo); 
	}
	@Log(operation = "新增" , remark="【KPI考核】批量新增KPI考核" , type = "0")
	@RequestMapping({"/saveKpiNotesBatch"})
	public Result<Object> saveKpiNotesBatch(@RequestBody KpiNotesVo vo,HttpServletRequest request) {
		return kpiNotesService.saveKpiNotesBatch(vo); 
	}
   
	//@Log(operation = "修改" , remark="【KPI考核】修改KPI考核" , type = "0")
	@RequestMapping({"/updateKpiNotes"})
	public Result<Object> updateKpiNotes(@RequestBody KpiNotesVo vo) { 
		return kpiNotesService.updateKpiNotes(vo); 
	}
   
	@Log(operation = "删除" , remark="【KPI考核】删除KPI考核" , type = "0")
	@RequestMapping({"/deleteKpiNotes"})
	public Result<Object> deleteKpiNotes(@RequestBody KpiNotesVo vo) { 
		return kpiNotesService.deleteKpiNotes(vo); 
	}
	
	@RequestMapping({"/getKpiByStaffId"})
	public Result<Object> getKpiByStaffId(@RequestBody KpiNotesVo vo,HttpServletRequest request) { 
		return kpiNotesService.getKpiByStaffId(vo); 
	}
}
