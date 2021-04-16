package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.EduPointsTypeVo;

public interface EduPointsTypeService {
	Result<Object> getEduPointsTypeList(EduPointsTypeVo vo);
	Result<Object> saveEduPointsType(EduPointsTypeVo vo);
	Result<Object> updateEduPointsType(EduPointsTypeVo vo);
	Result<Object> deleteEduPointsType(EduPointsTypeVo vo);
}
