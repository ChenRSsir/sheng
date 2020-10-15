package com.turing.service;

import java.util.List;

import com.turing.entity.Product;

/**
 * 商品业务类
 * @author Administrator
 *
 */
public interface ProductService {

	/**
	 * 通过条件查询商品
	 * @param prodName 名称
	 * @param prodType 类型
	 * @param prodBatch 批次
	 * @return
	 */
	public List<Product> findPro(String prodName,String prodType,String prodBatch);
}
