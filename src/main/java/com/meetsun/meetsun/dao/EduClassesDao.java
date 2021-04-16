package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.EduClasses;
import com.meetsun.meetsun.vo.EduClassesVo;

@Mapper
public interface EduClassesDao {
	/**
	 * 获取班级
	 * @param paramMsUserVo
	 * @return
	 */
	List<EduClasses> getEduClassesList(EduClassesVo vo);
	int getEduClassesListTotal(EduClassesVo vo);
	/**
	 * 添加班级
	 * @param paramMsUserVo
	 * @return
	 */
	int saveEduClasses(EduClassesVo vo);
	/**
	 * 修改班级
	 * @param paramMsUserVo
	 * @return
	 */
	int updateEduClasses(EduClassesVo vo);
	/**
	 * 删除班级
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteEduClasses(EduClassesVo vo);
}
