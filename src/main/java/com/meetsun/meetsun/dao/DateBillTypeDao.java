package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.DateBillType;
import com.meetsun.meetsun.vo.DateBillTypeVo;

@Mapper
public interface DateBillTypeDao {
	/**
	 * 获取日录账单类型信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<DateBillType> getDateBillTypeList(DateBillTypeVo vo);
	int getDateBillTypeListTotal(DateBillTypeVo vo);
	/**
	 * 添加日录账单类型信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveDateBillType(DateBillTypeVo vo);
	/**
	 * 修改日录账单类型信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateDateBillType(DateBillTypeVo vo);
	/**
	 * 删除日录账单类型信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteDateBillType(DateBillTypeVo vo);
}
