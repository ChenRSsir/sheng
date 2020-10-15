package com.turing.service;

import java.util.List;

import com.turing.entity.CstLinkman;

/**
 * 联系人接口
 * @author Administrator
 *
 */
public interface CstLinkmanService {

	/**
	 * 根据客户ID查询联系人
	 * @return
	 */
	public List<CstLinkman> findLinkManById(String custNo);
	/**
	 * 新增一个联系人
	 * @param linkman
	 * @return
	 */
	public int addLinkMan(CstLinkman linkman);
	/**
	 * 修改联系人
	 * @param linkman
	 * @return
	 */
	public int updateLinkMan(CstLinkman linkman);
	/**
	 * 根据id删除联系人
	 * @param lid
	 * @return
	 */
	public int deleteLinkMan(Integer lid);
	
	/**
	 * 通过id查询一条信息
	 * @param lid
	 * @return
	 */
	public CstLinkman findlmById(Integer lid);
}
