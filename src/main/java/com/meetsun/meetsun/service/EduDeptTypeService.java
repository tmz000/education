package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.EduDeptTypeVo;

public interface EduDeptTypeService {
	Result<Object> getEduDeptTypeList(EduDeptTypeVo vo);
	Result<Object> saveEduDeptType(EduDeptTypeVo vo);
	Result<Object> updateEduDeptType(EduDeptTypeVo vo);
	Result<Object> deleteEduDeptType(EduDeptTypeVo vo);
}
