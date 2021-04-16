package com.meetsun.meetsun.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.meetsun.meetsun.dao.AMenuQxDao;
import com.meetsun.meetsun.entity.AMenuQx;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.vo.AMenuQxVo;

public class BaseInterceptor {
	
	@Autowired
	private AMenuQxDao aMenuQxDao;
	
	/**
     * 权限列表查询
     * @param permissionDomainRepository
     * @param url
     * @return
     */
    public boolean isHavePermiss(MsUser user, String uri){
        //获取用户权限
        AMenuQxVo vo1 = new AMenuQxVo(); 
        vo1.setRoleId(user.getRoleId());
        List<AMenuQx> list = aMenuQxDao.getAMenuQxList(vo1);
        //对比查询权限
	   if(uri.endsWith("/noLock") || uri.endsWith("/index") || uri.endsWith("/home") || uri.endsWith("Detail") || uri.endsWith("detail") || uri.endsWith("/msVideoPlay") || uri.endsWith("/outLogin")) {
		   return true;
	   }else {
	        if(list != null && list.size() > 0) {
	        	String[] st = list.get(0).getMethod().split(",");
	        	for(String url : st) {
	        		if(url.trim().endsWith(uri)){
	        			return true;
	                }
	        	}
	        }
	   }
	  return false;
   }
}
