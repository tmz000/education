package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.EduStudentVo;

public interface EduStudentService {
	Result<Object> getEduStudentList(EduStudentVo vo);
	Result<Object> saveEduStudent(EduStudentVo vo);
	Result<Object> updateEduStudent(EduStudentVo vo);
	Result<Object> deleteEduStudent(EduStudentVo vo);
}
