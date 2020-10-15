package com.turing.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.turing.entity.BaseDict;
import com.turing.service.BaseDictService;

/**
 * 数据字典控制器
 * @author Administrator
 *
 */
@Controller
public class BaseDictController {
	
	@Autowired
	private BaseDictService baseDictService;

	//查询
	@RequestMapping("/findBaseDict")
	public ModelAndView findBaseDict(){
		ModelAndView mv=new ModelAndView();
	    //调用方法
		List<BaseDict> list = baseDictService.findBaseDict();
		mv.addObject("list", list);
		mv.setViewName("Manager/DirectionData");
		return mv;
	}
	
	//模糊查询
	@RequestMapping("/findBaseDictByWhere")
	public ModelAndView findBaseDictByWhere(BaseDict baseDict){
		ModelAndView mv=new ModelAndView();
		List<BaseDict> list = baseDictService.findBaseDictByWhere(baseDict);
		mv.addObject("list", list);
		mv.setViewName("Manager/DirectionData");
		return mv;
	}
	
	//删除
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id")String id){
		ModelAndView mv=new ModelAndView();
		int i = baseDictService.delete(Integer.parseInt(id));
		if(i>0){
			mv = findBaseDict();
		}
		return mv;
		
	}
	
	//查询一条数据
	@RequestMapping("/findBaseDictById/{id}")
	public ModelAndView findBaseDictById(@PathVariable("id")String id){
		ModelAndView mv=new ModelAndView();
		BaseDict baseDict = baseDictService.findBaseDictById(Integer.parseInt(id));
		mv.addObject("baseDict", baseDict);
		mv.setViewName("Manager/DataEdit");
		return mv;	
	}
	
	//修改
	@RequestMapping("/update")
	public ModelAndView update(BaseDict baseDict) throws Exception{
		ModelAndView mv=new ModelAndView();
		int i = baseDictService.update(baseDict);	
		if(i>0){
			mv = findBaseDict();
		}
		return mv;
	}
	
	//新增
	@RequestMapping("/dataAdd")
	public ModelAndView dataAdd(BaseDict baseDict){
		ModelAndView mv=new ModelAndView();
		int i = baseDictService.insert(baseDict);
		if(i>0){
			mv = findBaseDict();
		}
		return mv;
	}
}
