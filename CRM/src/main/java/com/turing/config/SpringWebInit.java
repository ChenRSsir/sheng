package com.turing.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/***
 * 中心控制器配置DispathcherServlet
 * @author Administrator
 *
 */
public class SpringWebInit extends AbstractAnnotationConfigDispatcherServletInitializer{

	//全局配置项
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[]{RootConfig.class};
	}

	//配置Spring mvc xml
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[]{WebConfig.class};
	}

	//配置servletMapping
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[]{"/"};
	}
	
	//解决全站乱码问题
    @Override
    protected Filter[] getServletFilters() {
    	// TODO Auto-generated method stub
        //创建编码过滤器
    	CharacterEncodingFilter encodingFilter=new CharacterEncodingFilter();
    	//设置编码
    	encodingFilter.setEncoding("UTF-8");
    	encodingFilter.setForceEncoding(true);
    	return new Filter [] {encodingFilter};
    }
}
