package com.turing.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.entity.CstService;
import com.turing.entity.CstServiceExample;
import com.turing.entity.CstServiceExample.Criteria;
import com.turing.mapper.CstServiceMapper;
import com.turing.service.ICstService;
/**
 * 客户服务业务类
 * @author Administrator
 *
 */
@Service
public class ICstServiceImpl implements ICstService {
    @Autowired
	private CstServiceMapper cstServiceMapper;
	
	@Override
	public int addCstService(CstService cstService) {
		return cstServiceMapper.insert(cstService);
	}

	@Override
	public int updaeCstService(CstService cstService) {
		return cstServiceMapper.updateByPrimaryKeySelective(cstService);
	}

	@Override
	public int deleteCstService(Integer csId) {
		return cstServiceMapper.deleteByPrimaryKey(csId);
	}

	@Override
	public CstService findcServiceById(Integer csId) {
		return cstServiceMapper.selectByPrimaryKey(csId);
	}

	@Override
	public List<CstService> findCstService(CstService cs,Date bDate) {
		CstServiceExample example=new CstServiceExample();
		Criteria criteria = example.createCriteria();
		criteria.andSvrCustNameLike("%"+cs.getSvrCustName()+"%").andSvrTitleLike("%"+cs.getSvrTitle()+"%");
		if(cs.getSvrType()!=""){
			criteria.andSvrTypeEqualTo(cs.getSvrType());
		}
		if(cs.getSvrStatus()!=""){
			criteria.andSvrStatusEqualTo(cs.getSvrStatus());
		}
		if(cs.getSvrCreateDate()!=null && bDate!=null){
			criteria.andSvrCreateDateBetween(cs.getSvrCreateDate(), bDate);
		}
		
		return cstServiceMapper.selectByExample(example);
	}

	@Override
	public List<CstService> findService(String dateTime) {
		return cstServiceMapper.findService(dateTime);
	}

}
