package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.AMenuQx;
import com.meetsun.meetsun.vo.AMenuQxVo;

@Mapper
public interface AMenuQxDao {
	/**
	 * 获取菜单方法信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<AMenuQx> getAMenuQxList(AMenuQxVo vo);
	int getAMenuQxListTotal(AMenuQxVo vo);
	/**
	 * 添加菜单方法信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveAMenuQx(AMenuQxVo vo);
	/**
	 * 修改菜单方法信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateAMenuQx(AMenuQxVo vo);
	/**
	 * 删除菜单方法信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteAMenuQx(AMenuQxVo vo);
}
