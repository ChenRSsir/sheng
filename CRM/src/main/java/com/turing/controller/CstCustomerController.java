package com.turing.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.entity.BaseDict;
import com.turing.entity.CstCustomer;
import com.turing.entity.CstLost;
import com.turing.service.BaseDictService;
import com.turing.service.CstCustomerService;
import com.turing.service.CstLostService;
import com.turing.service.OrdersService;

/**
 * 客户控制层
 * @author Administrator
 *没改变状态，要修理
 */
@Controller
public class CstCustomerController {
	//客户依赖
	@Autowired
	private CstCustomerService cstCustomerService;
    //数据字典依赖
	@Autowired
	private BaseDictService baseDictService;
	//订单
	@Autowired
	private OrdersService ordersService;
	//流失
	@Autowired
	private CstLostService cstLostService;
	//查询 
	@RequestMapping("/selCustomer")
	public String selCustomer(){
		return "Customer/CustomerPage";
	}
	
	@RequestMapping("/selectCus")
	@ResponseBody
	public Object selectCus(Integer pageNum,Integer pageSize,String custNo,String custName,String custRegion,String custManagerName,Integer custLevel,HttpServletRequest request) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		HttpSession session = request.getSession();
	    if(custNo==null){
	    	custNo="";
	    }
	    if(custName==null){
	    	custName="";
	    }
	    if(custRegion==null){
	    	custRegion="";
	    }
	    if(custManagerName==null){
	    	custManagerName="";
	    }
		//客户等级
		List<BaseDict> levelList = baseDictService.findItem("1");
		//地区
		List<BaseDict> regionList = baseDictService.findItem("3");
		//存起来
		session.setAttribute("levelList", levelList);
		session.setAttribute("regionList", regionList);
		//客户流失判断
		//查询所有状态为正常的客户
		List<CstCustomer> list = cstCustomerService.findCus();	
		String cust=null;
		//遍历集合查看客户订单情况
		for (CstCustomer c : list) {
		
			//查找客户的订单
			cust = ordersService.findOrderTime(c.getCustName()); 
			if(cust!=null){
				//修改客户状态
				c.setCustStatus("2");
				int i = cstCustomerService.updateCustomer(c);
				if(i>0){
					//新增流失客户
					CstLost cl=new CstLost();
					//客户信息
					cl.setLstCustNo(c.getCustNo());
					cl.setLstCustName(c.getCustName());
					//客户经理
					cl.setLstCustManagerId(c.getCustManagerId());
					cl.setLstCustManagerName(c.getCustManagerName());
					//上次下单时间
					String lstLastOrderDate = ordersService.findMaxOrderTime(c.getCustName());
					cl.setLstLastOrderDate(sdf.parse(lstLastOrderDate));
					//确定流失
					cl.setLstLostDate(new Date());
					//状态，警告
					cl.setLstStatus("1");
					//添加
					cstLostService.addCusLost(cl);
				}
			}
		}
		//调用分页插件
		PageHelper.startPage(pageNum, pageSize);
		//查询到的正常客户集合
		List<CstCustomer> list2 = cstCustomerService.findCustomer(custNo,custName,custRegion,custManagerName,custLevel);
		PageInfo<CstCustomer> pageInfo=new PageInfo<>(list2);
		return pageInfo;
	}
	//查询一条
	//Customer/CustomerEdit
	@RequestMapping("/findCusById/{custNo}")
	public ModelAndView findCusById(@PathVariable("custNo")String custNo){
		ModelAndView mv=new ModelAndView();
		CstCustomer customer = cstCustomerService.findCusById(custNo);
		mv.addObject("cus", customer);
		mv.setViewName("Customer/CustomerEdit");
		return mv;
	}
	
	//修改
	@RequestMapping("/updateCus")
	@ResponseBody
	public String updateCus(CstCustomer c){
		int i = cstCustomerService.updateCustomer(c);
		return i>0?"true":"false";
		
	}
	
	//删除操作
	@RequestMapping("/deleteCus")
	@ResponseBody
	public String deleteCus(String cusId){
		CstCustomer customer=new CstCustomer();
		customer.setCustNo(cusId);
		customer.setCustStatus("3");
		int i = cstCustomerService.deleteCustomer(customer);
		return i>0?"true":"false";
	}
	//客户构成
	@RequestMapping("/findCompose/{i}")
	public ModelAndView findCompose(@PathVariable("i")Integer i){
		ModelAndView mv=new ModelAndView();
		List<CstCustomer> list = cstCustomerService.findCompose(i);
		mv.addObject("list", list);
		mv.setViewName("Report/MakeReport");
		return mv;
	}
	
	//流失客户，系统自动判断超过6个月没下单的客户， 每周六凌晨两点扫描全库
	@Scheduled(cron="0 0 2 ? * SAT")
	public void findLostByTask() throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("findLostByTask 执行了啊");
		        //客户流失判断
				//查询所有状态为正常的客户
				List<CstCustomer> list = cstCustomerService.findCus();	
				String cust=null;
				//遍历集合查看客户订单情况
				for (CstCustomer c : list) {
				
					//查找客户的订单，获取超过6月没购物的客户名称
					cust = ordersService.findOrderTime(c.getCustName()); 
					if(cust!=null){
						//修改客户状态
						c.setCustStatus("2");
						int i = cstCustomerService.updateCustomer(c);
						if(i>0){
							//新增流失客户
							CstLost cl=new CstLost();
							//客户信息
							cl.setLstCustNo(c.getCustNo());
							cl.setLstCustName(c.getCustName());
							//客户经理
							cl.setLstCustManagerId(c.getCustManagerId());
							cl.setLstCustManagerName(c.getCustManagerName());
							//上次下单时间
							String lstLastOrderDate = ordersService.findMaxOrderTime(c.getCustName());
							cl.setLstLastOrderDate(sdf.parse(lstLastOrderDate));
							//确定流失
							cl.setLstLostDate(new Date());
							//状态，警告
							cl.setLstStatus("1");
							//添加
							cstLostService.addCusLost(cl);
						}
					}
				}
	}
}
