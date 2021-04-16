package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.EduClassesVo;

public interface EduClassesService {
	Result<Object> getEduClassesList(EduClassesVo vo);
	Result<Object> saveEduClasses(EduClassesVo vo);
	Result<Object> updateEduClasses(EduClassesVo vo);
	Result<Object> deleteEduClasses(EduClassesVo vo);
}
