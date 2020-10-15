package com.turing.service;

import java.util.List;

import com.turing.entity.SysUser;

/**
 * 用户业务类
 * @author Administrator
 *
 */
public interface SysUserService {

	/**
	 * 登陆
	 * @return 查询到的用户
	 */
	public SysUser login(String name);
	/**
	 * 查询除了自己以外的用户
	 * @param name 当前用户
	 * @return
	 */
	public List<SysUser> getUsers();
}
