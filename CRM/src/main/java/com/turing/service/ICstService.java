package com.turing.service;

import java.util.Date;
import java.util.List;

import com.turing.entity.CstService;
/**
 * 客户服务接口
 * @author Administrator
 *
 */
public interface ICstService {

	/**
	 * 新增服务
	 * @param cstService
	 * @return
	 */
	public int addCstService(CstService cstService);
	
	/**
	 * 修改服务
	 * @param cstService
	 * @return
	 */
	public int updaeCstService(CstService cstService);
	
	/**
	 * 删除服务
	 * @param csId
	 * @return
	 */
	public int deleteCstService(Integer csId);
	
	/**
	 * 查询一条记录
	 * @param csId
	 * @return
	 */
	public CstService findcServiceById(Integer csId);
	

	/**
	 * 复杂查询
	 * @param cstService
	 * @return
	 */
	public List<CstService> findCstService(CstService cs,Date bDate);
	
	/**
	 * 客户服务分析
	 * @param dateTime
	 * @return
	 */
	public List<CstService> findService(String dateTime);
}
