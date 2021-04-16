package com.meetsun.meetsun.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.meetsun.meetsun.until.Common;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	//将拦截器注入容器
	@Autowired
	private QxInterceptor qxInterceptor;
    /**
     * 配置拦截器
     *
     * @param registry
     * @author lvfang
     */
    public void addInterceptors(InterceptorRegistry registry) {
    	String[] includeUrls = Common.getIncludeUrls();
    	
    	List<String> list = new ArrayList<String>();
    	for(String url : includeUrls) {
    		list.add(url);
        }
    	//这里参数是一个实现了HandlerInterceptor接口的拦截器
        registry.addInterceptor(qxInterceptor)
                .addPathPatterns("/**")//需要拦截的请求
                .excludePathPatterns(list);//不拦截的请求
   }
}
