package com.turing.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.turing.entity.SalChance;
import com.turing.entity.SalPlan;
import com.turing.service.SalChanceService;
import com.turing.service.SalPlanMapperService;

/**
 * 销售计划控制层
 * @author Administrator
 *
 */
@Controller
public class SalPlanMapperController {

	@Autowired
	private SalChanceService salChanceService;
	@Autowired
	private SalPlanMapperService salPlanMapperService;
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 
	 * @param salMgerId 销售机会id
	 * @param i 请求参数，用来判断是哪个查询
	 * @return
	 */
	@RequestMapping("/findPlanById")
	public ModelAndView findPlanById(Integer salMgerId,Integer i){
		ModelAndView mv=new ModelAndView();
		//查询销售机会
		SalChance salChance = salChanceService.findSalChanceById(salMgerId);
		//查询销售计划
		List<SalPlan> planList = salPlanMapperService.findPlanList(salMgerId);
		mv.addObject("salChance", salChance);
		mv.addObject("planList", planList);
		//查看信息 sale/LookPlay.html
		//制定计划 sale/SetPlay.html
	    //执行计划 sale/ExecPlay.html
		if(i==1){
		mv.setViewName("sale/LookPlay");
		}else if(i==2){
			mv.setViewName("sale/SetPlay");
		}else if(i==3){
			mv.setViewName("sale/ExecPlay");
		}
		return mv;
	}
	
	//添加
	@RequestMapping("/addPlan")
	@ResponseBody
	public String addPlan(SalPlan plan,String pDate) throws Exception{
		plan.setPlaDate(sdf.parse(pDate));
		int i = salPlanMapperService.addPlan(plan);
		return i>0?"true":"false";
	}
	
	//修改
	@RequestMapping("/updatePlan")
	@ResponseBody
	public String updatePlan(SalPlan plan){
		int i = salPlanMapperService.updatePlan(plan);
		return i>0?"true":"false";
	}
	
	//删除
	@RequestMapping("/deletePlan")
	@ResponseBody
	public String deletePlan(Integer plaId){
		int i = salPlanMapperService.deletePlan(plaId);
		return i>0?"true":"false";
	}
}
