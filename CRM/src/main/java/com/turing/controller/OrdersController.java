package com.turing.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.entity.CstCustomer;
import com.turing.entity.Orders;
import com.turing.service.CstCustomerService;
import com.turing.service.OrdersService;

/**
 * 订单控制层
 * @author Administrator
 *
 */
@Controller
public class OrdersController {

	@Autowired
	private CstCustomerService cstCustomerService;
	@Autowired
	private OrdersService ordersService;
	//查询
	//Customer/OrderPage.html
	@RequestMapping("/findOrdByCus")
	public ModelAndView findOrdByCus(String custNo,Integer pageNum,Integer pageSize,HttpServletRequest request){
		if(pageNum==null){
			pageNum=1;
		}
		if(pageSize==null){
			pageSize=3;
		}
		ModelAndView mv=new ModelAndView();
		//获取当前客户
		CstCustomer customer = cstCustomerService.findCusById(custNo);
		//通过用户名称获取订单
		PageHelper.startPage(pageNum, pageSize);
		List<Orders> Orderlist = ordersService.findOrderByCus(customer.getCustName());
		PageInfo<Orders> pageInfo=new PageInfo<>(Orderlist);
		//将客户 和订单存起来
		mv.addObject("c", customer);
		mv.addObject("Orderlist", pageInfo);
		mv.setViewName("Customer/OrderPage");
		return mv;
	}
	 
	//查询订单贡献分析
	@RequestMapping("/findOrderTotal")
	public ModelAndView findOrderTotal(String customer,String dateTime){
		if(customer==null){
			customer="";
		}
		if(dateTime==null || dateTime==""){
			dateTime=null;
		}
		ModelAndView mv=new ModelAndView();
		List<Orders> total = ordersService.findOrderTotal(customer, dateTime);
		mv.addObject("total", total);
		mv.setViewName("Report/ContributeReport");
		return mv;
	}
}
