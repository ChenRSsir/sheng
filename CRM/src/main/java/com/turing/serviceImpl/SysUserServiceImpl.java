package com.turing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.entity.SysUser;
import com.turing.entity.SysUserExample;
import com.turing.mapper.SysUserMapper;
import com.turing.service.SysUserService;
/**
 * 用户实现类
 * @author Administrator
 *
 */
@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserMapper userMapper;

	@Override
	public SysUser login(String name){
		SysUserExample example=new SysUserExample();
		example.createCriteria().andUserNameEqualTo(name);
		List<SysUser> list = userMapper.selectByExample(example);
		SysUser user=null;
		try{
		 user=list.get(0);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}

	@Override
	public List<SysUser> getUsers() {
		return userMapper.selectByExample(null);
	}

}
