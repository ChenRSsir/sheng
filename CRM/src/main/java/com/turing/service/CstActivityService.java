package com.turing.service;

import java.util.List;

import com.turing.entity.CstActivity;

/**
 * 活动接口类
 * @author Administrator
 *
 */
public interface CstActivityService {

	/**
	 * 根据客户id获取活动集合
	 * @param custNo
	 * @return
	 */
	public List<CstActivity> findActList(String custNo);
	
	/**
	 * 通过id获取活动
	 * @param actId
	 * @return
	 */
	public CstActivity findActById(Integer actId);
	
	/**
	 * 新增活动
	 * @param activity
	 * @return
	 */
	public int addAct(CstActivity activity);
	
	/**
	 * 修改活动
	 * @param activity
	 * @return
	 */
	public int updateAct(CstActivity activity);
	
	/**
	 * 通过id删除活动
	 * @param actId
	 * @return
	 */
	public int deleteAct(Integer actId);
	
}
