package com.turing.service;

import java.util.List;

import com.turing.entity.OrdersLine;

/**
 * 订单详情接口
 * @author Administrator
 *
 */
public interface OrdersLineService {

	/**
	 * 通过订单id查询详情
	 * @param oddOrderId
	 * @return
	 */
	public List<OrdersLine> findOrderLineByOid(Integer oddOrderId);
	
	/**
	 * 通过订单id查询订单总价
	 * @param oddOrderId
	 * @return
	 */
	public Integer findOrderTotal(Integer oddOrderId);
}
