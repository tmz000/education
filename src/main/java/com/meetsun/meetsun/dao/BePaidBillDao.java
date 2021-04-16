package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.BePaidBill;
import com.meetsun.meetsun.vo.BePaidBillVo;

@Mapper
public interface BePaidBillDao {
	/**
	 * 获取待缴费用信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<BePaidBill> getBePaidBillList(BePaidBillVo vo);
	int getBePaidBillListTotal(BePaidBillVo vo);
	String getBePaidBillListSum(BePaidBillVo vo);
	/**
	 * 添加待缴费用信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveBePaidBill(BePaidBillVo vo);
	/**
	 * 修改待缴费用信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateBePaidBill(BePaidBillVo vo);
	/**
	 * 删除待缴费用信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteBePaidBill(BePaidBillVo vo);
}
