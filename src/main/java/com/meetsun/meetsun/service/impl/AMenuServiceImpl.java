package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.AMenuDao;
import com.meetsun.meetsun.dao.AParentMenuDao;
import com.meetsun.meetsun.dao.ARoleMenuDao;
import com.meetsun.meetsun.entity.AMenu;
import com.meetsun.meetsun.entity.AParentMenu;
import com.meetsun.meetsun.service.AMenuService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.AMenuVo;
import com.meetsun.meetsun.vo.AParentMenuVo;
import com.meetsun.meetsun.vo.ARoleMenuVo;

@Service
public class AMenuServiceImpl implements AMenuService{
	
	@Autowired
	private AMenuDao aMenuDao;
	@Autowired
	private ARoleMenuDao aRoleMenuDao;
	@Autowired
	private AParentMenuDao aParentMenuDao;
	
	@Override
	public Result<Object> getAMenuList(AMenuVo vo) {
		List<AMenu> list = aMenuDao.getAMenuList(vo);
		List<AMenu> list1 = new ArrayList<AMenu>();
		if(list != null && list.size() > 0) {
			for(AMenu am : list) {
				if(am.getParentId() != null) {
					AParentMenuVo av = new AParentMenuVo();
					av.setSysId(am.getParentId());
					List<AParentMenu> pl = aParentMenuDao.getAParentMenuList(av);
					if(pl != null && pl.size() == 1) {
						am.setParentMenuName(pl.get(0).getMenuName());
					}
				}
				list1.add(am);
			}
		}
		Result result = new Result();
		int total = aMenuDao.getAMenuListTotal(vo);
		result.setStatus("01");
		result.setRows(list1);
		result.setTotal(total);
		return result;
	}

	@Override
	public Result<Object> saveAMenu(AMenuVo vo) {
		List<AMenu> list = aMenuDao.getAMenuList(vo);
		if(list != null && list.size() > 0) {
			return Result.error("已存在该菜单名称！");
		}
		vo.setSysId(Tools.getUUID());
		int flag = aMenuDao.saveAMenu(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateAMenu(AMenuVo vo) {
		int flag = aMenuDao.updateAMenu(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteAMenu(AMenuVo vo) {
		ARoleMenuVo avo = new ARoleMenuVo();
		avo.setMenuId(vo.getSysId());
		int flag = aMenuDao.deleteAMenu(vo);
		if (flag > 0) {
			aRoleMenuDao.deleteARoleMenu(avo);
			return Result.success("success");
		}
		return Result.error("error");
	}
}
