package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.EduStudent;
import com.meetsun.meetsun.vo.EduStudentVo;

@Mapper
public interface EduStudentDao {
	/**
	 * 获取学生
	 * @param paramMsUserVo
	 * @return
	 */
	List<EduStudent> getEduStudentList(EduStudentVo vo);
	int getEduStudentListTotal(EduStudentVo vo);
	/**
	 * 添加学生
	 * @param paramMsUserVo
	 * @return
	 */
	int saveEduStudent(EduStudentVo vo);
	/**
	 * 修改学生
	 * @param paramMsUserVo
	 * @return
	 */
	int updateEduStudent(EduStudentVo vo);
	/**
	 * 删除学生
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteEduStudent(EduStudentVo vo);
}
