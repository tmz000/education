package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.EduDeptVo;

public interface EduDeptService {
	Result<Object> getEduDeptList(EduDeptVo vo);
	Result<Object> saveEduDept(EduDeptVo vo);
	Result<Object> updateEduDept(EduDeptVo vo);
	Result<Object> deleteEduDept(EduDeptVo vo);
}
