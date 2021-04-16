package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.BePaidBill;
import com.meetsun.meetsun.entity.EduPoints;
import com.meetsun.meetsun.vo.BePaidBillVo;
import com.meetsun.meetsun.vo.EduPointsVo;

@Mapper
public interface EduPointsDao {
	/**
	 * 获取积分录入
	 * @param paramMsUserVo
	 * @return
	 */
	List<EduPoints> getEduPointsList(EduPointsVo vo);
	int getEduPointsListTotal(EduPointsVo vo);
	/**
	 * 添加积分录入
	 * @param paramMsUserVo
	 * @return
	 */
	int saveEduPoints(EduPointsVo vo);
	/**
	 * 修改积分录入
	 * @param paramMsUserVo
	 * @return
	 */
	int updateEduPoints(EduPointsVo vo);
	/**
	 * 删除积分录入
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteEduPoints(EduPointsVo vo);
	
	//周期积分查询
	List<EduPoints> getEduPointsListByDate(EduPointsVo vo);
}
