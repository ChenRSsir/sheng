package com.turing.service;

import java.util.List;

import com.turing.entity.SalChance;

/**
 * 销售机会业务类
 * @author Administrator
 *
 */
public interface SalChanceService{

	/**
	 * 查询未指派的销售机会
	 * @return
	 */
	public List<SalChance> findSalChanceByOne(String chcCustName,String chcLinkman,String chcTitle);
	
	/**
	 * 查询除了未指派的销售机会
	 * @return
	 */
	public List<SalChance> findSalChanceByNoOne(String chcCustName,String chcLinkman,String chcTitle,Integer chcStatus);
	
	/**
	 * 查询一条信息
	 * @param id
	 * @return
	 */
	public SalChance findSalChanceById(Integer id);
	
	/**
	 * 新增一条数据
	 * @param chance
	 * @return
	 */
	public int addSalChance(SalChance chance);
	
	/**
	 * 修改一条数据
	 * @param chance
	 * @return
	 */
	public int updateSalChance(SalChance chance);
	
	/**
	 * 根据id删除一条数据
	 * @param id
	 * @return
	 */
	public int deleteSalChance(Integer id);
}
