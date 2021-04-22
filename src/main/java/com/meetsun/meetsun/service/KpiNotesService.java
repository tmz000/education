package com.meetsun.meetsun.service;

import com.meetsun.meetsun.entity.KpiNotes;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.KpiNotesVo;

public interface KpiNotesService {
	Result<Object> getKpiNotesList(KpiNotesVo vo);
	Result<Object> saveKpiNotes(KpiNotes vo);
	Result<Object> updateKpiNotes(KpiNotesVo vo);
	Result<Object> deleteKpiNotes(KpiNotesVo vo);
	Result<Object> getKpiByStaffId(KpiNotesVo vo);
}
