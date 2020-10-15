package com.turing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.turing.entity.Orders;
import com.turing.entity.OrdersLine;
import com.turing.service.OrdersLineService;
import com.turing.service.OrdersService;

/**
 * 订单详情控制层
 * @author Administrator
 *
 */
@Controller
public class OrdersLineController {

	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrdersLineService ordersLineService;
	//查询 Customer/OrderDetail
	@RequestMapping("/findOrderByOid/{odrId}")
	public ModelAndView findOrderByOid(@PathVariable("odrId")Integer ordid){
		ModelAndView mv=new ModelAndView();
		//通过id查询一条订单
		Orders orders = ordersService.findOrderById(ordid);
		//通过id查询订单详情
		List<OrdersLine> ordList = ordersLineService.findOrderLineByOid(ordid); 
		//通过id查询订单总价
		Integer total=ordersLineService.findOrderTotal(ordid);
		mv.addObject("total", total);
		mv.addObject("orders", orders);
		mv.addObject("ordList", ordList);
		mv.setViewName("Customer/OrderDetail");
		return mv;
	}
}
