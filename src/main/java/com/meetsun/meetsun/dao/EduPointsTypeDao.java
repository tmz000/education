package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.EduPointsType;
import com.meetsun.meetsun.vo.EduPointsTypeVo;

@Mapper
public interface EduPointsTypeDao {
	/**
	 * 获取积分类型信息
	 * @param vo
	 * @return
	 */
	List<EduPointsType> getEduPointsTypeList(EduPointsTypeVo vo);
	int getEduPointsTypeListTotal(EduPointsTypeVo vo);
	/**
	 * 添加积分类型信息
	 * @param vo
	 * @return
	 */
	int saveEduPointsType(EduPointsTypeVo vo);
	/**
	 * 修改积分类型信息
	 * @param vo
	 * @return
	 */
	int updateEduPointsType(EduPointsTypeVo vo);
	/**
	 * 删除积分类型信息
	 * @param vo
	 * @return
	 */
	int deleteEduPointsType(EduPointsTypeVo vo);
}
