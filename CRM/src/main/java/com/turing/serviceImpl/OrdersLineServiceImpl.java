package com.turing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.entity.OrdersLine;
import com.turing.mapper.OrdersLineMapper;
import com.turing.service.OrdersLineService;
/**
 * 订单详情业务类
 * @author Administrator
 *
 */
@Service
public class OrdersLineServiceImpl implements OrdersLineService {
    @Autowired
	private OrdersLineMapper ordersLineMapper;
	
	@Override
	public List<OrdersLine> findOrderLineByOid(Integer oddOrderId) {
		return ordersLineMapper.findOrderByOid(oddOrderId);
	}

	@Override
	public Integer findOrderTotal(Integer oddOrderId) {
		return ordersLineMapper.findOrderTotal(oddOrderId);
	}

	
}
