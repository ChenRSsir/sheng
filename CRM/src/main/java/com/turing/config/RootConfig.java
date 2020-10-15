package com.turing.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.github.pagehelper.PageInterceptor;

/**
 * 副配置文件
 * @author Administrator
 *
 */
@Configuration//空的配置
@ComponentScan(basePackages="com.turing",excludeFilters={@ComponentScan.Filter
(type=FilterType.ANNOTATION,value={EnableWebMvc.class,Controller.class})})
@MapperScan("com.turing.mapper")//扫描所有mapper
@ImportResource("classpath:spring-transaction.xml")//事务处理
public class RootConfig {

	//配置DataSource
	@Bean
	public DataSource dataSource(){
		BasicDataSource dataSource=new BasicDataSource();
		//四要素
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/crm");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		//其他设置
        dataSource.setInitialSize(5);//初始化连接数量
        dataSource.setMaxIdle(10);//最大空闲连接数
        dataSource.setMinIdle(2);//最小空闲连接数
        dataSource.setMaxTotal(100);//最大连接数
        dataSource.setMaxWaitMillis(3000);//最大等待时间  3秒钟
        return dataSource;
	}
	
	//配置
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
		//设置数据源
		sessionFactoryBean.setDataSource(dataSource);
		//设置分页拦截器
		PageInterceptor pageInterceptor=new PageInterceptor();
		//创建插件需要的参数集合
		Properties properties=new Properties();
		//配置数据库方言
		properties.setProperty("helperDialect", "mysql");
		//配置分页合理化数据
		properties.setProperty("reasonable", "true");
		
		pageInterceptor.setProperties(properties);
         //将拦截器设置到SqlSessionFactory
		sessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});
		return sessionFactoryBean.getObject();
		
	}
	
	//事务处理
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
	}
}
