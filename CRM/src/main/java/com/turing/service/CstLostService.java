package com.turing.service;

import java.util.List;

import com.turing.entity.CstLost;

/**
 * 客户流失接口
 * @author Administrator
 *
 */
public interface CstLostService {

	/**
	 * 新增流失
	 * @param cstLost
	 * @return
	 */
	public int addCusLost(CstLost cstLost);
	
	/**
	 * 修改
	 * @param cstLost
	 * @return
	 */
	public int updateCusLost(CstLost cstLost);
	
	/**
	 * 条件查询
	 * @param lstCustName
	 * @param lstCustManagerName
	 * @param lstStatus
	 * @return
	 */
	public List<CstLost> findCusLost(String lstCustName,String lstCustManagerName,String lstStatus);

    /**
     * 通过id查询一条数据
     * @param lid
     * @return
     */
    public CstLost findClostById(Integer lid);
    
    /**
     * 流失信息分析
     * @param lstCustName
     * @param lstCustManagerName
     * @return
     */
    public List<CstLost> findLost(String lstCustName,String lstCustManagerName);
}
