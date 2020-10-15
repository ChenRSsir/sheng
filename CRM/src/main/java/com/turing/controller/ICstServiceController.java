package com.turing.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.entity.BaseDict;
import com.turing.entity.CstCustomer;
import com.turing.entity.CstService;
import com.turing.entity.SysUser;
import com.turing.service.BaseDictService;
import com.turing.service.CstCustomerService;
import com.turing.service.ICstService;
import com.turing.service.SysUserService;

/**
 * 客户服务控制层
 * @author Administrator
 *
 */
@Controller
public class ICstServiceController {
    //客户服务
	@Autowired
	private ICstService iCstService;
	//数据字典
	@Autowired
	private BaseDictService baseDictService;
	//客户
	@Autowired
	private CstCustomerService cstCustomerService;
	//用户
	@Autowired
	private SysUserService sysUserService;
	
	//新增
	@RequestMapping("/createCstService")
	public ModelAndView createCstService(){
		ModelAndView mv=new ModelAndView();
		//数据字典，服务类型
		List<BaseDict> item = baseDictService.findItem("2");
		mv.addObject("item", item);
		mv.setViewName("CustomerService/ServiceCreate");
		return mv;
	}
	
	@RequestMapping("/addCstService")
	@ResponseBody
	public String addCstService(CstService cs,HttpServletRequest r){
		//判断输入的客户是否是已有客户
		CstCustomer customer = cstCustomerService.findByName(cs.getSvrCustName());
		if(customer!=null){
			//客户编号
			cs.setSvrCustNo(customer.getCustNo());
		}
		//状态1 待分配
		cs.setSvrStatus("1");
		//创建人
		SysUser user =(SysUser)r.getSession().getAttribute("user");
		cs.setSvrCreateId(user.getUserId());
		cs.setSvrCreateBy(user.getUserName());
		//创建时间 当前时间
		cs.setSvrCreateDate(new Date());
		
		int i = iCstService.addCstService(cs);
			
		return i>0?"true":"false";
	}
	
	//分配 查询
	@RequestMapping("/findCs")
	public String findCs(){
		return "CustomerService/ServiceAllot";
	}
	//处理 查询
	@RequestMapping("/svrDeal")
	public String svrDeal(){
		return "CustomerService/ServiceDispose";
	}
	//反馈查询
	@RequestMapping("/Sresult")
	public String Sresult(){
		return "CustomerService/ServiceResult";
	}
	 
	//归档
	@RequestMapping("/archives")
	public String archives(){
		return "CustomerService/ServiceDetail";
	}
	
	//复杂查询
	@RequestMapping("/findCusService")
	@ResponseBody
	public Object[] findCusService(Integer pageNum,Integer pageSize, CstService cs,String bDate1, String bDate2,HttpServletRequest r) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		HttpSession session = r.getSession();
		Date date=null;
		if(cs.getSvrCustName()==null){
			cs.setSvrCustName("");
		}
		if(cs.getSvrTitle()==null){
			cs.setSvrTitle("");
		}
		if(cs.getSvrType()==null){
			cs.setSvrType("");
		}
		if(cs.getSvrStatus()==null){
			cs.setSvrStatus("");
		}
		if(bDate1!=null && bDate2!=null && bDate1!="" && bDate2!=""){
			cs.setSvrCreateDate(sdf.parse(bDate1));
			 date = sdf.parse(bDate2);
		}
		//获取用户
		List<SysUser> userlist = sysUserService.getUsers();
		
		//数据字典，服务类型
		List<BaseDict> itemlist = baseDictService.findItem("2");
		session.setAttribute("itemlist", itemlist);
		//分页插件
		PageHelper.startPage(pageNum, pageSize);
		List<CstService> cstService = iCstService.findCstService(cs, date);
		PageInfo<CstService> pageInfo=new PageInfo<>(cstService);
		return new Object[]{pageInfo,userlist};	
}
	//修改， 改变状态
	@RequestMapping("/updateCstService")
	@ResponseBody
	//is为变量，判断改变的是哪个
	public String updateCstService(CstService cstService,Integer is,HttpServletRequest r){
		//分配时间
		if(is==1){
			cstService.setSvrDueDate(new Date());
			cstService.setSvrStatus("2");
		}else if(is==2){
			//处理
			//当前用户
			SysUser user =(SysUser)r.getSession().getAttribute("user");
			cstService.setSvrDealId(user.getUserId());
			cstService.setSvrDealBy(user.getUserName());
			cstService.setSvrDealDate(new Date());
			//处理完
			cstService.setSvrStatus("3");
		}else if(is==3){
			if(cstService.getSvrSatisfy()>=3){
				//归档
				cstService.setSvrStatus("4");
			}else if(cstService.getSvrSatisfy()<3){
				//不满意，重新处理
				cstService.setSvrStatus("2");
			}
		}
		int i = iCstService.updaeCstService(cstService);
		return i>0?"true":"false";
	}
	
	//删除
	@RequestMapping("/delectCstService")
	@ResponseBody
	public String delectCstService(Integer csId){
		int i = iCstService.deleteCstService(csId);
		return i>0?"true":"false";
	}
	
	//处理服务 ，查询一条
	//is判断是哪条查询
	@RequestMapping("/SelSvrDealById")
	public ModelAndView SelSvrDealById(Integer csId,Integer is){
		ModelAndView mv=new ModelAndView();
		CstService cstService = iCstService.findcServiceById(csId);
		mv.addObject("cstService", cstService);
		if(is==1){
			//处理
			mv.setViewName("CustomerService/ServiceDisposeDialog");
		}else if(is==2){
			//反馈
			mv.setViewName("CustomerService/ServiceResultDialog");
		}else if(is==3){
			//归档
			mv.setViewName("CustomerService/ServiceDetailDialog");		
		}
		return mv;
	}
	
	
	//客户服务分析
	@RequestMapping("/findService")
	public ModelAndView findService(String dateTime){
		if(dateTime==null || dateTime==""){
			dateTime=null;
		}
		ModelAndView mv=new ModelAndView();
		List<CstService> service = iCstService.findService(dateTime);
		System.out.println(service.size());
		mv.addObject("service", service);
		mv.setViewName("Report/ServerReport");
		return mv;
	}
	
}