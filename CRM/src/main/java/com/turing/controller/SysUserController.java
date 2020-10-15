package com.turing.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.turing.entity.SysUser;
import com.turing.service.SysUserService;

/**
 * ÓÃ»§¿ØÖÆÆ÷
 * @author Administrator
 *
 */
@Controller
public class SysUserController {

	@Autowired
	private SysUserService userService;
	//µÇÂ½
	@RequestMapping("/userLogin")
	public ModelAndView userLogin(HttpServletRequest request, HttpServletResponse response, String textfield,String textfield2) throws Exception{
		ModelAndView mv=new ModelAndView();
		PrintWriter out = response.getWriter();
		if(textfield==null){
			 out.print("<script>alert('ÇëÏÈµÇÂ½£¡');</script>");
			mv.setViewName("/login");
		}else{
		SysUser user2 = userService.login(textfield);
		if(user2==null){
			//ÕËºÅ²»¶Ô£¬µÇÂ½Ê§°Ü
		    out.print("<script>alert('µÇÂ½Ê§°Ü£¡ÕËºÅÊäÈë´íÎó¡£');</script>");
			mv.setViewName("login");
		}else{
			//ÃÜÂëÕıÈ·
			if(user2.getUserPassword().equals(textfield2)){
				if(user2.getUserFlag()==1){
				   //³É¹¦
					request.getSession().setAttribute("user", user2);
			       mv.setViewName("Main");
				}else{
				   //È¨ÏŞ²»¹»
					out.print("<script>alert('µÇÂ½Ê§°Ü£¡È¨ÏŞ²»×ã¡£');</script>");
					mv.setViewName("login");
				}
			}else{
				//ÃÜÂë´íÎó
				out.print("<script>alert('µÇÂ½Ê§°Ü£¡ÃÜÂëÊäÈë´íÎó¡£');</script>");
				mv.setViewName("login");
			}
		}
	}	
		return mv;
}
	/**
	 * ÍË³ö
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginOut")
	public ModelAndView loginOut(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		if(request.getSession().getAttribute("user")!=null){
			request.getSession().removeAttribute("user");
			mv.setViewName("login");
		}else{
			mv.setViewName("login");
		}
		return mv;
	}
}
