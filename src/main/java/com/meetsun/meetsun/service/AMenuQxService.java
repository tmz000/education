package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.AMenuQxVo;

public interface AMenuQxService {
	Result<Object> getAMenuQxList(AMenuQxVo vo);
	Result<Object> saveAMenuQx(AMenuQxVo vo);
	Result<Object> updateAMenuQx(AMenuQxVo vo);
	Result<Object> deleteAMenuQx(AMenuQxVo vo);
}
