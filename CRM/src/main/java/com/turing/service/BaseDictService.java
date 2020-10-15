package com.turing.service;

import java.util.List;

import com.turing.entity.BaseDict;

/**
 * 数据字典业务类
 * @author Administrator
 *
 */
public interface BaseDictService {

	/**
	 * 查询所有数据字典
	 * @return 数据集合
	 */
	public List<BaseDict> findBaseDict();
	
	/**
	 * 根据条件查询数据字典
	 * @param baseDict
	 * @return
	 */
	public List<BaseDict> findBaseDictByWhere(BaseDict baseDict);
	
	/**
	 * 查询一条数据
	 * @param id
	 * @return 一个对象
	 */
	public BaseDict findBaseDictById(Integer id);
	
	/**
	 * 添加一条数据
	 * @param baseDict
	 * @return 受影响的行数
	 */
	public int insert(BaseDict baseDict);
	
	/**
	 * 修改一条数据
	 * @param baseDict
	 * @return 受影响的行数
	 */
	public int update(BaseDict baseDict);
	
	/**
	 * 删除一条数据
	 * @param id
	 * @return 受影响的行数
	 */
	public int delete(Integer id);
	
	/**
	 * 通过值获取条目
	 * @param dictValue
	 * @return
	 */
	public List<BaseDict> findItem(String dictValue);
}
