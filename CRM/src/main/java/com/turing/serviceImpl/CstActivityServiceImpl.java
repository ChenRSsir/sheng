package com.turing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.entity.CstActivity;
import com.turing.entity.CstActivityExample;
import com.turing.mapper.CstActivityMapper;
import com.turing.service.CstActivityService;
/**
 * 活动业务类
 * @author Administrator
 *
 */
@Service
public class CstActivityServiceImpl implements CstActivityService {

	@Autowired
	private CstActivityMapper cstActivityMapper;
	
	@Override
	public List<CstActivity> findActList(String custNo) {
		CstActivityExample example=new CstActivityExample();
		example.createCriteria().andAtvCustNoEqualTo(custNo);
		return cstActivityMapper.selectByExample(example);
	}

	@Override
	public CstActivity findActById(Integer actId) {
		return cstActivityMapper.selectByPrimaryKey(actId);
	}

	@Override
	public int addAct(CstActivity activity) {
		return cstActivityMapper.insert(activity);
	}

	@Override
	public int updateAct(CstActivity activity) {
		return cstActivityMapper.updateByPrimaryKeySelective(activity);
	}

	@Override
	public int deleteAct(Integer actId) {
		return cstActivityMapper.deleteByPrimaryKey(actId);
	}

}
