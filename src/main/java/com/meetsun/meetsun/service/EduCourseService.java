package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.EduCourseVo;

public interface EduCourseService {
	Result<Object> getEduCourseList(EduCourseVo vo);
	Result<Object> saveEduCourse(EduCourseVo vo);
	Result<Object> updateEduCourse(EduCourseVo vo);
	Result<Object> deleteEduCourse(EduCourseVo vo);
}
