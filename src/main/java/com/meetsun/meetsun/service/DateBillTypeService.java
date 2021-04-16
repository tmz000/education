package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.DateBillTypeVo;

public interface DateBillTypeService {
	Result<Object> getDateBillTypeList(DateBillTypeVo vo);
	Result<Object> saveDateBillType(DateBillTypeVo vo);
	Result<Object> updateDateBillType(DateBillTypeVo vo);
	Result<Object> deleteDateBillType(DateBillTypeVo vo);
}
