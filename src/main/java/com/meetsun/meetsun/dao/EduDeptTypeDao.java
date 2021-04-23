package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.EduDeptType;
import com.meetsun.meetsun.vo.EduDeptTypeVo;

@Mapper
public interface EduDeptTypeDao {
	/**
	 * 获取部门职务
	 * @param paramMsUserVo
	 * @return
	 */
	List<EduDeptType> getEduDeptTypeList(EduDeptTypeVo vo);
	int getEduDeptTypeListTotal(EduDeptTypeVo vo);
	/**
	 * 添加部门职务
	 * @param paramMsUserVo
	 * @return
	 */
	int saveEduDeptType(EduDeptTypeVo vo);
	/**
	 * 修改部门职务
	 * @param paramMsUserVo
	 * @return
	 */
	int updateEduDeptType(EduDeptTypeVo vo);
	/**
	 * 删除部门职务
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteEduDeptType(EduDeptTypeVo vo);
}
