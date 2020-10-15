package com.turing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.entity.CstCustomer;
import com.turing.entity.CstCustomerExample;
import com.turing.mapper.CstCustomerMapper;
import com.turing.service.CstCustomerService;
/**
 * 客户业务类
 * @author Administrator
 *
 */
@Service
public class CstCustomerServiceImpl implements CstCustomerService {
    @Autowired
	private CstCustomerMapper cstCustomerMapper;
	
	@Override
	public int addCustomer(CstCustomer customer) {
		return cstCustomerMapper.insert(customer);
	}

	@Override
	public int updateCustomer(CstCustomer customer) {
		return cstCustomerMapper.updateByPrimaryKeySelective(customer);
	}

	@Override
	public int deleteCustomer(CstCustomer customer) {
		return cstCustomerMapper.updateByPrimaryKeySelective(customer);
	}

	@Override
	public List<CstCustomer> findCustomer(String custNo,String custName,String custRegion,String custManagerName,Integer custLevel) {
		return cstCustomerMapper.selectCus("%"+custNo+"%","%"+custName+"%","%"+custRegion+"%","%"+custManagerName+"%",custLevel);
	}

	@Override
	public CstCustomer findCusById(String custNo) {
		return cstCustomerMapper.selectByPrimaryKey(custNo);
	}

	@Override
	public List<CstCustomer> findCus() {
		CstCustomerExample example=new CstCustomerExample();
		example.createCriteria().andCustStatusEqualTo("1");
		return cstCustomerMapper.selectByExample(example);
	}

	@Override
	public CstCustomer findByName(String cusName) {
		CstCustomerExample example=new CstCustomerExample();
		example.createCriteria().andCustNameEqualTo(cusName);
		List<CstCustomer> list = cstCustomerMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<CstCustomer> findCompose(Integer i) {
		return cstCustomerMapper.findCompose(i);
	}

}
