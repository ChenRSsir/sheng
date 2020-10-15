package com.turing.service;

import java.util.List;

import com.turing.entity.Orders;

/**
 * 订单接口类
 * @author Administrator
 *
 */
public interface OrdersService {

	/**
	 * 通过客户查询订单
	 * @param cusName
	 * @return
	 */
	public List<Orders> findOrderByCus(String cusName);
	
	/**
	 * 通过id查询订单
	 * @param oid
	 * @return
	 */
	public Orders findOrderById(Integer oid);
	/**
	 * 通过客户名称查询订单近期情况
	 * @param odrCustomer
	 * @return
	 */
	public String findOrderTime(String odrCustomer);
	
	/**
	 * 查询客户上次下单时间
	 * @param odrCustomer
	 * @return
	 */
	public String findMaxOrderTime(String odrCustomer);
	
	/**
	 * 查询订单总价
	 * @param customer
	 * @param dateTime
	 * @return
	 */
	public List<Orders> findOrderTotal(String customer,String dateTime);
}
