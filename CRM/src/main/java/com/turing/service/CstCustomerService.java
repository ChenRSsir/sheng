package com.turing.service;

import java.util.List;

import com.turing.entity.CstCustomer;

/**
 * 客户接口
 * @author Administrator
 *
 */
public interface CstCustomerService {

	/**
	 * 新增一条客户
	 * @param customer
	 * @return
	 */
	public int addCustomer(CstCustomer customer);
	
	/**
	 * 修改一条客户
	 * @param customer
	 * @return
	 */
	public int updateCustomer(CstCustomer customer);
	
	/**
	 * 删除一条客户,修改客户状态，没有真正删除
	 * @param customer
	 * @return
	 */
	public int deleteCustomer(CstCustomer customer);
	
	/**
	 * 通过名称获取客户
	 * @param cusName
	 * @return
	 */
	public CstCustomer findByName(String cusName);
	
	/**
	 * 通过条件查询数据
	 * @param customer
	 * @return
	 */
	public List<CstCustomer> findCustomer(String custNo,String custName,String custRegion,String custManagerName,Integer custLevel);

	/**
	 * 查询一条数据
	 * @param custNo
	 * @return
	 */
    public CstCustomer findCusById(String custNo);
    
    /**
     * 查询所有状态为正常的客户，用于判断
     * @return
     */
    public List<CstCustomer> findCus();
    
    /**
     * 客户分组查询
     * @param i
     * @return
     */
    public List<CstCustomer> findCompose(Integer i);
}
