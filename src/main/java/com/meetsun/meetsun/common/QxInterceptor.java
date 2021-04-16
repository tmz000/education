package com.meetsun.meetsun.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.vo.MsUserVo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class QxInterceptor extends BaseInterceptor implements HandlerInterceptor {
	@Autowired
	private MsUserDao msUserDao;
	
	String[] includeUrls = Common.getIncludeUrls();
	
	@Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		
		//获取token用户
		String token = Common.getParam(request.getQueryString(),"token");
		MsUserVo mvo =new MsUserVo();
		mvo.setSysId(token);
		MsUser us = msUserDao.getMsUser(mvo).get(0);
		
		//请求资源
        String uri = request.getRequestURI();  //  /user/deleteUser
        String str[] = uri.split("/");
        boolean needFilter = isNeedFilter(uri);
        if(!needFilter) {
        	return true;
        }else if(uri.contains("/get")) {
        	return true;
        }else {
        	if(str.length == 2) {
        		return true;
        	}else {
        		boolean pstate = this.isHavePermiss(us,uri);
        		if(!pstate){
        			log.info("账号【{}】暂无该操作权限:{}",us.getUserName(),uri);
        			return false;
        		}
        		return true;
        	}
        }
	}
	
	@Override
    public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
    
    public boolean isNeedFilter(String uri) {
    	for (String includeUrl : includeUrls) {
    		if (includeUrl.equals(uri))
    			return false; 
    		if (uri.endsWith(includeUrl)) {
    			return false;
    		}
    		if(uri.contains("/pmValidate/")) {
    			return false;
    		}
    	} 
    	return true;
    }
}
