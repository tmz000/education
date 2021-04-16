package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.EduPointsVo;

public interface EduPointsService {
	Result<Object> getEduPointsList(EduPointsVo vo);
	Result<Object> saveEduPoints(EduPointsVo vo);
	Result<Object> updateEduPoints(EduPointsVo vo);
	Result<Object> deleteEduPoints(EduPointsVo vo);
	Result<Object> getEduPointsListByDate(EduPointsVo vo);
}
