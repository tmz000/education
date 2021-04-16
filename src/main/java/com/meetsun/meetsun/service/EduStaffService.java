package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.EduStaffVo;

public interface EduStaffService {
	Result<Object> getEduStaffList(EduStaffVo vo);
	Result<Object> saveEduStaff(EduStaffVo vo);
	Result<Object> updateEduStaff(EduStaffVo vo);
	Result<Object> deleteEduStaff(EduStaffVo vo);
}
