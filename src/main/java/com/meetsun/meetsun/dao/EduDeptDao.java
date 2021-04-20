package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.EduDept;
import com.meetsun.meetsun.vo.EduDeptVo;

@Mapper
public interface EduDeptDao {
	/**
	 * 获取班级
	 * @param paramMsUserVo
	 * @return
	 */
	List<EduDept> getEduDeptList(EduDeptVo vo);
	int getEduDeptListTotal(EduDeptVo vo);
	/**
	 * 添加班级
	 * @param paramMsUserVo
	 * @return
	 */
	int saveEduDept(EduDeptVo vo);
	/**
	 * 修改班级
	 * @param paramMsUserVo
	 * @return
	 */
	int updateEduDept(EduDeptVo vo);
	/**
	 * 删除班级
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteEduDept(EduDeptVo vo);
}
