package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.AMenuQxDao;
import com.meetsun.meetsun.dao.ARoleDao;
import com.meetsun.meetsun.entity.AMenuQx;
import com.meetsun.meetsun.entity.ARole;
import com.meetsun.meetsun.service.AMenuQxService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.AMenuQxVo;
import com.meetsun.meetsun.vo.ARoleVo;

@Service
public class AMenuQxServiceImpl implements AMenuQxService{
	
	@Autowired
	private AMenuQxDao aMenuQxDao;
	@Autowired
	private ARoleDao aRoleDao;
	
	@Override
	public Result<Object> getAMenuQxList(AMenuQxVo vo) {
		List<AMenuQx> list = aMenuQxDao.getAMenuQxList(vo);
		for(AMenuQx aq : list) {
			ARoleVo avo = new ARoleVo();
			avo.setSysId(aq.getRoleId());
			List<ARole> al = aRoleDao.getARoleList(avo);
			aq.setRoleName(al.get(0).getRoleName());
		}
		Result result = new Result();
		int total = aMenuQxDao.getAMenuQxListTotal(vo);
		result.setStatus("01");
		result.setRows(list);
		result.setTotal(total);
		return result;
	}

	@Override
	public Result<Object> saveAMenuQx(AMenuQxVo vo) {
		List<AMenuQx> list = aMenuQxDao.getAMenuQxList(vo);
		if(list != null && list.size() > 0) {
			return Result.error("已存在该角色权限，请重新选择角色！");
		}
		vo.setSysId(Tools.getUUID());
		int flag = aMenuQxDao.saveAMenuQx(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateAMenuQx(AMenuQxVo vo) {
		int flag = aMenuQxDao.updateAMenuQx(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteAMenuQx(AMenuQxVo vo) {
		int flag = aMenuQxDao.deleteAMenuQx(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
