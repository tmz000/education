package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.EduStaff;
import com.meetsun.meetsun.vo.EduStaffVo;

@Mapper
public interface EduStaffDao {
	/**
	 * 获取员工
	 * @param paramMsUserVo
	 * @return
	 */
	List<EduStaff> getEduStaffList(EduStaffVo vo);
	int getEduStaffListTotal(EduStaffVo vo);
	/**
	 * 添加员工
	 * @param paramMsUserVo
	 * @return
	 */
	int saveEduStaff(EduStaffVo vo);
	/**
	 * 修改员工
	 * @param paramMsUserVo
	 * @return
	 */
	int updateEduStaff(EduStaffVo vo);
	/**
	 * 删除员工
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteEduStaff(EduStaffVo vo);
}
