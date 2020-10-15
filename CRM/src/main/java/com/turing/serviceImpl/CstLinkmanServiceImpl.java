package com.turing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.entity.CstLinkman;
import com.turing.entity.CstLinkmanExample;
import com.turing.mapper.CstLinkmanMapper;
import com.turing.service.CstLinkmanService;
/**
 * 联系人业务类
 * @author Administrator
 *
 */
@Service
public class CstLinkmanServiceImpl implements CstLinkmanService {

	@Autowired
	private CstLinkmanMapper cstLinkmanMapper;
	
	@Override
	public List<CstLinkman> findLinkManById(String custNo) {
		CstLinkmanExample example=new CstLinkmanExample();
		example.createCriteria().andLkmCustNoEqualTo(custNo);
		return cstLinkmanMapper.selectByExample(example);
	}

	@Override
	public int addLinkMan(CstLinkman linkman) {
		return cstLinkmanMapper.insert(linkman);
	}

	@Override
	public int updateLinkMan(CstLinkman linkman) {
		return cstLinkmanMapper.updateByPrimaryKeySelective(linkman);
	}

	@Override
	public int deleteLinkMan(Integer lid) {
		return cstLinkmanMapper.deleteByPrimaryKey(lid);
	}

	@Override
	public CstLinkman findlmById(Integer lid) {
		return cstLinkmanMapper.selectByPrimaryKey(lid);
	}

}
