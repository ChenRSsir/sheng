package com.turing.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.turing.controller.LoginInterceptor;

/**
 * 配置文件
 * @author Administrator
 *
 */

@Configuration//空的配置文件
@ComponentScan("com.turing.controller")//扫描所有副控制器
@EnableWebMvc//开启Spring mvc 所有注解
public class WebConfig extends WebMvcConfigurerAdapter{

	//配置模板解析器
	@Bean
	public ITemplateResolver iTemplateResolver(ApplicationContext applicationContext){
		//模板解析器
		SpringResourceTemplateResolver templateResolver=new SpringResourceTemplateResolver();
		//设置上下文 IOC容器
		templateResolver.setApplicationContext(applicationContext);
		//设置前缀
		templateResolver.setPrefix("/");
		//设置后缀
		templateResolver.setSuffix(".html");
		//设置模板类型
		templateResolver.setTemplateMode(TemplateMode.HTML);
		//开发时为了调试方便，禁用页面缓存
		templateResolver.setCacheable(false);
        //设置编码
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}
	
	//Spring 模板引擎
	@Bean
	public ISpringTemplateEngine templateEngine(ITemplateResolver iTemplateResolver){
		//创建模板引擎
		SpringTemplateEngine templateEngine=new SpringTemplateEngine();
		//设置支持Spring的IE表达式
		templateEngine.setEnableSpringELCompiler(true);
		//设置模板引擎的解析器
		templateEngine.setTemplateResolver(iTemplateResolver);
		
		return templateEngine;
	}
	
	//视图解析器
	@Bean
	public ViewResolver viewResolver(ISpringTemplateEngine templateEngine){
		//创建ThymeleafView的视图解析器
		ThymeleafViewResolver viewResolver=new ThymeleafViewResolver();
		//设置模板
		viewResolver.setTemplateEngine(templateEngine);
		//设置编码 
		viewResolver.setCharacterEncoding("UTF-8");
		
		return viewResolver;
	}
	
	//重写方法
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
        //开启静态资源访问
		configurer.enable();
	}
	
	//注册拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		InterceptorRegistration interceptor = registry.addInterceptor(new LoginInterceptor());
		//拦截的路径
		interceptor.addPathPatterns("/**");
		//不拦截的路径，多个用逗号隔开
		interceptor.excludePathPatterns("/userLogin");
	}
}
