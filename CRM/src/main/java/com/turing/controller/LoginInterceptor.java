package com.turing.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
/**
 * 登陆拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor{
    /**
     * 做拦截的方法
     */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle");
		//获取session
		Object loginUser = request.getSession().getAttribute("user");
		if(loginUser==null){
			//返回登陆页
			response.sendRedirect("/login.html");
			return false;//拦截
		}else{
			return true;//放行
		}
	}
}
