package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.KpiNotes;
import com.meetsun.meetsun.vo.KpiNotesVo;

@Mapper
public interface KpiNotesDao {
	/**
	 * 获取KPI考核信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<KpiNotes> getKpiNotesList(KpiNotesVo vo);
	int getKpiNotesListTotal(KpiNotesVo vo);
	/**
	 * 添加KPI考核信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveKpiNotes(KpiNotesVo vo);
	/**
	 * 修改KPI考核信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateKpiNotes(KpiNotesVo vo);
	/**
	 * 删除KPI考核信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteKpiNotes(KpiNotesVo vo);
}
