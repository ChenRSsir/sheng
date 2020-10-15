package com.turing.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.entity.CstCustomer;
import com.turing.entity.SalChance;
import com.turing.entity.SysUser;
import com.turing.service.CstCustomerService;
import com.turing.service.SalChanceService;
import com.turing.service.SysUserService;

/**
 * 销售机会控制层
 * @author Administrator
 *
 */
@Controller
public class SalChanceController {
	
	//销售机会
	@Autowired
	private SalChanceService salChanceService;
	//用户依赖注入
	@Autowired
	private SysUserService sysUserService;
	//客户依赖
	@Autowired
	private CstCustomerService cstCustomerService;

	//查询 销售机会
	@RequestMapping("/SalChance")
	public String SalChance(){
	  return "sale/SaleChance";	
	}
	//查询  客户开发
	@RequestMapping("/salManager")
	public String saleManager(){
		return "sale/saleManager";
	}
	
	// 查询
	@RequestMapping("/findSalChanceByOne")
	@ResponseBody
	public Object findSalChanceByOne(Integer pageNum,Integer pageSize,String chcCustName, String chcLinkman, String chcTitle,Integer chcStatus){
		if(chcCustName==null){
			chcCustName="";
		}
		if(chcLinkman==null){
			chcLinkman="";
		}
		if(chcTitle==null){
			chcTitle="";
		}
		PageHelper.startPage(pageNum, pageSize);
		List<SalChance> list=null;
		//判断是销售机会还是客户开发
		if(chcStatus==null){
			//销售机会
		    list = salChanceService.findSalChanceByOne(chcCustName, chcLinkman, chcTitle);
		}else{
		 if(chcStatus==0){
			//查询所有
			list=salChanceService.findSalChanceByNoOne(chcCustName, chcLinkman, chcTitle, chcStatus);
		 }else{
			//查询其他 2 3 4
			list=salChanceService.findSalChanceByNoOne(chcCustName, chcLinkman, chcTitle, chcStatus);
		      }
		}
		 PageInfo<SalChance> pageInfo=new PageInfo<>(list);
	     return pageInfo;
	}
	//新增
	@RequestMapping("/add")
	public String add(){
		return "sale/AddSale";
	}
	
	@RequestMapping("/addSale")
	@ResponseBody
	public String addSale(SalChance salChance,String CreateDate,HttpServletRequest request) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//赋予创建人id
		SysUser user =(SysUser)request.getSession().getAttribute("user");
		if(salChance.getChcCreateBy().equals(user.getUserName())){
			salChance.setChcCreateId(user.getUserId());
			salChance.setChcCreateDate(sdf.parse(CreateDate));
			//创建默认为1
			salChance.setChcStatus(1);
		}
		int i = salChanceService.addSalChance(salChance);
		
		return i>0?"true":"false";
	}
	
	/**
	 * 查询一条数据
	 * @param id 数据id
	 * @param i 发入的请求类型
	 * @return
	 */
	@RequestMapping("/findSaleById")
	public ModelAndView findSaleById(Integer id,Integer i,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		SalChance salChance = salChanceService.findSalChanceById(id);
		//获取用户
		List<SysUser> users = sysUserService.getUsers();
		mv.addObject("salChance", salChance);
		if(i==1){
			mv.setViewName("sale/AllotSale");
			mv.addObject("users", users);
		}else if(i==2){
		    mv.setViewName("sale/EditSale");
		}
		return mv;
	}
	
	//修改
	@RequestMapping("/updateSale")
	@ResponseBody
	public String updateSale(SalChance chance,String DueDate) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		int i=0;
        //判断是否有指派时间，有是指派表，没有是修改表
		if(DueDate!=null){
			//获取被指派人id
			SysUser user = sysUserService.login(chance.getChcDueTo());
			chance.setChcDueId(user.getUserId());
			chance.setChcDueDate(sdf.parse(DueDate));
			//修改状态为已指派
			chance.setChcStatus(2);
			i= salChanceService.updateSalChance(chance);
		}else{
			i= salChanceService.updateSalChance(chance);
		}
		 
		return i>0?"true":"false";
	}
	
	//删除
	@RequestMapping("/deleteSale")
	@ResponseBody
	public Integer deleteSale(Integer id,HttpServletRequest request){
		//获取当前用户信息
		SysUser user =(SysUser)request.getSession().getAttribute("user");
		Integer userId = user.getUserId();
		//获取这条记录的信息 取创建人
		SalChance salChance = salChanceService.findSalChanceById(id);
		//返回的信息
		int i=0;
		//判断是否是当前登陆用户
		if(salChance.getChcCreateId()==userId){
			i = salChanceService.deleteSalChance(id);
		}else{
			i=2;
		}	
		return i;
	}
	//修改状态
	@RequestMapping("/updateChcStatus")
	@ResponseBody
	public String updateChcStatus(SalChance chance){
		int i=0;
		//4为失败 3为成功新建客户
		if(chance.getChcStatus()==4){
		  i = salChanceService.updateSalChance(chance);
		}else if(chance.getChcStatus()==3){
		  i = salChanceService.updateSalChance(chance);
		  //新建客户
		  SalChance chance2 = salChanceService.findSalChanceById(chance.getChcId());
		  CstCustomer c=new CstCustomer();
		  //客户编号
		  c.setCustNo("KH10010"+chance2.getChcId());
		  //客户名称
		  c.setCustName(chance2.getChcCustName());
		  //客户经理id
		  c.setCustManagerId(chance2.getChcDueId());
		  //客户经理名字
		  c.setCustManagerName(chance2.getChcDueTo());
		  //客户电话
		  c.setCustTel(chance2.getChcTel());
		  //状态 默认正常
		  c.setCustStatus("1");
		  //时间，默认系统时间
		  c.setCustCreateDate(new Date());
		  //，默认为普通客户
		  c.setCustLevel(13);
		  //地址暂为空
		  c.setCustRegion("");
		  //调用添加方法
		  cstCustomerService.addCustomer(c);
		}
		return i>0?"true":"false";
	}
}
