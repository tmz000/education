package com.meetsun.meetsun.common;
 
import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meetsun.meetsun.dao.AMenuDao;
import com.meetsun.meetsun.dao.ARoleMenuDao;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.AMenu;
import com.meetsun.meetsun.entity.ARoleMenu;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.vo.AMenuVo;
import com.meetsun.meetsun.vo.ARoleMenuVo;
import com.meetsun.meetsun.vo.MsUserVo;
 
@Component
@WebFilter(filterName = "MyFilter", urlPatterns = {"/*"})
public class MyFilter implements Filter{
	String[] includeUrls = Common.getIncludeUrls();
	
	@Autowired
	private ARoleMenuDao aRoleMenuDao;
	@Autowired
	private AMenuDao aMenuDao;
	@Autowired
	private MsUserDao msUserDao;
	
	public void init(FilterConfig filterConfig) throws ServletException {}
 
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		String uri = request.getRequestURI();
		boolean needFilter = isNeedFilter(uri);
		if (!needFilter) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}else {
			String param = request.getQueryString();
			if(param == null) {
				response.sendRedirect(request.getContextPath() + "/");
				return;
			}else {
				String token = Common.getParam(param,"token");
				if(token != null && !token.equals("null")) {
					MsUserVo mvo =new MsUserVo();
					mvo.setSysId(token);
					MsUser user = msUserDao.getMsUser(mvo).get(0);
					if (null != user) {
						if(uri.endsWith(".html")) {
							response.sendRedirect(request.getContextPath() + "/error500");
							return;
						}else {
							if(isHaveUrl(uri,user)) {
								filterChain.doFilter((ServletRequest)request, (ServletResponse)response);
								return;
							}else {
								response.sendRedirect(request.getContextPath() + "/noLock?token="+token);
								return;
							}
						}
					} else {
						response.sendRedirect(request.getContextPath() + "/");
						return;
					} 
				}else {
					response.sendRedirect(request.getContextPath() + "/");
					return;
				}
			}
		}
	}
   
   public void destroy() {}
   
   public boolean isNeedFilter(String uri) {
	   for (String includeUrl : this.includeUrls) {
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
   
   public boolean isHaveUrl(String uri,MsUser user) {
	   String str[] = uri.split("/");
	   if(str.length > 2) {
		   return true;
	   }else {
		   if(uri.endsWith("/noLock") || uri.endsWith("/index") || uri.endsWith("/home") || uri.endsWith("Detail") || uri.endsWith("detail") || uri.endsWith("/msVideoPlay") || uri.endsWith("/outLogin")) {
			   return true;
		   }else {
			   ARoleMenuVo vo = new ARoleMenuVo();
			   vo.setRoleId(user.getRoleId());
			   List<ARoleMenu> list = aRoleMenuDao.getARoleMenuList(vo);
			   int flag=0;
			   if(list != null && list.size() > 0) {
				   for(String menuId:list.get(0).getMenuId().split(",")) {
					   AMenuVo avo = new AMenuVo();
					   avo.setSysId(menuId.trim());
					   List<AMenu> list1 = aMenuDao.getAMenuList(avo);
					   if(list.size() > 0) {
						   if(uri.equals(list1.get(0).getMenuLink())) {
							   flag=1;
							   break;
						   }
					   }
				   }
				   if(flag==1) {
					   return true;
				   }else {
					   return false;
				   }
			   }
		   }
		   return false;
	   }
   }
}

