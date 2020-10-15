package com.turing.service;

import java.util.List;

import com.turing.entity.SalPlan;

/**
 * 销售计划业务类
 * @author Administrator
 *
 */
public interface SalPlanMapperService {

	/**
	 * 通过销售机会id查询计划集合
	 * @return
	 */
	public List<SalPlan> findPlanList(Integer saleChanceId);
	
	/**
	 * 添加一条计划
	 * @param plan
	 * @return
	 */
	public int addPlan(SalPlan plan);
	
	/**
	 * 修改一条计划
	 * @param plan
	 * @return
	 */
	public int updatePlan(SalPlan plan);
	
	/**
	 * 删除一条计划
	 * @param planId
	 * @return
	 */
	public int deletePlan(Integer plaId);
}
