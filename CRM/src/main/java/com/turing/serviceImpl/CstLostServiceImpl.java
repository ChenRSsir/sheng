package com.turing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.entity.CstLost;
import com.turing.entity.CstLostExample;
import com.turing.entity.CstLostExample.Criteria;
import com.turing.mapper.CstLostMapper;
import com.turing.service.CstLostService;
/**
 * 客户流失业务类
 * @author Administrator
 *
 */
@Service
public class CstLostServiceImpl implements CstLostService {
    @Autowired
	private CstLostMapper cstLostMapper;
	
	@Override
	public int addCusLost(CstLost cstLost) {
		return cstLostMapper.insert(cstLost);
	}

	@Override
	public int updateCusLost(CstLost cstLost) {
		return cstLostMapper.updateByPrimaryKeySelective(cstLost);
	}

	@Override
	public List<CstLost> findCusLost(String lstCustName, String lstCustManagerName, String lstStatus) {
		CstLostExample example=new CstLostExample();
		Criteria criteria = example.createCriteria();
		criteria.andLstCustNameLike("%"+lstCustName+"%").andLstCustManagerNameLike("%"+lstCustManagerName+"%");
		if(lstStatus!=""){
			criteria.andLstStatusEqualTo(lstStatus);
		}
		return cstLostMapper.selectByExample(example);
	}

	@Override
	public CstLost findClostById(Integer lid) {
		return cstLostMapper.selectByPrimaryKey(lid);
	}

	@Override
	public List<CstLost> findLost(String lstCustName, String lstCustManagerName) {
		CstLostExample example=new CstLostExample();
		example.createCriteria().andLstCustNameLike("%"+lstCustName+"%").andLstCustManagerNameLike("%"+lstCustManagerName+"%").andLstStatusEqualTo("3");
		return cstLostMapper.selectByExample(example);
	}

}
