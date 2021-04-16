package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.BePaidBillVo;

public interface BePaidBillService {
	Result<Object> getBePaidBillList(BePaidBillVo vo);
	Result<Object> saveBePaidBill(BePaidBillVo vo);
	Result<Object> updateBePaidBill(BePaidBillVo vo);
	Result<Object> deleteBePaidBill(BePaidBillVo vo);
}
