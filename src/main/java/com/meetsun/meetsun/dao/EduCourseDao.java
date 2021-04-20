package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.EduCourse;
import com.meetsun.meetsun.vo.EduCourseVo;

@Mapper
public interface EduCourseDao {
	/**
	 * 获取班级
	 * @param paramMsUserVo
	 * @return
	 */
	List<EduCourse> getEduCourseList(EduCourseVo vo);
	int getEduCourseListTotal(EduCourseVo vo);
	/**
	 * 添加班级
	 * @param paramMsUserVo
	 * @return
	 */
	int saveEduCourse(EduCourseVo vo);
	/**
	 * 修改班级
	 * @param paramMsUserVo
	 * @return
	 */
	int updateEduCourse(EduCourseVo vo);
	/**
	 * 删除班级
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteEduCourse(EduCourseVo vo);
}
