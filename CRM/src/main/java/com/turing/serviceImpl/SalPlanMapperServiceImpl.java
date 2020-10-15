package com.turing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.entity.SalPlan;
import com.turing.entity.SalPlanExample;
import com.turing.mapper.SalPlanMapper;
import com.turing.service.SalPlanMapperService;
/**
 * 销售计划业务类
 * @author Administrator
 *
 */
@Service
public class SalPlanMapperServiceImpl implements SalPlanMapperService {
    @Autowired
	private SalPlanMapper salPlanMapper;
	
	@Override
	public List<SalPlan> findPlanList(Integer saleChanceId) {
		SalPlanExample example=new SalPlanExample();
		example.createCriteria().andPlaChcIdEqualTo(saleChanceId);
		return salPlanMapper.selectByExample(example);
	}

	@Override
	public int addPlan(SalPlan plan) {
		return salPlanMapper.insert(plan);
	}

	@Override
	public int updatePlan(SalPlan plan) {
		return salPlanMapper.updateByPrimaryKeySelective(plan);
	}

	@Override
	public int deletePlan(Integer plaId) {
		return salPlanMapper.deleteByPrimaryKey(plaId);
	}

}
