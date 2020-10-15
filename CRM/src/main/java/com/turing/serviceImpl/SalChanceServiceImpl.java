package com.turing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.entity.SalChance;
import com.turing.entity.SalChanceExample;
import com.turing.entity.SalChanceExample.Criteria;
import com.turing.mapper.SalChanceMapper;
import com.turing.service.SalChanceService;
/**
 * 销售机会实现类
 * @author Administrator
 *
 */
@Service
public class SalChanceServiceImpl implements SalChanceService {
	
    @Autowired
	private SalChanceMapper salChanceMapper;
	
	@Override
	public List<SalChance> findSalChanceByOne(String chcCustName, String chcLinkman, String chcTitle) {
        SalChanceExample example=new SalChanceExample();
        example.createCriteria().andChcCustNameLike("%"+chcCustName+"%").andChcLinkmanLike("%"+chcLinkman+"%").andChcTitleLike("%"+chcTitle+"%").andChcStatusEqualTo(1);
		
		return salChanceMapper.selectByExample(example);
	}

	@Override
	public List<SalChance> findSalChanceByNoOne(String chcCustName, String chcLinkman, String chcTitle,
			Integer chcStatus) {

		SalChanceExample example=new SalChanceExample();
        Criteria criteria = example.createCriteria();
        criteria.andChcCustNameLike("%"+chcCustName+"%").andChcLinkmanLike("%"+chcLinkman+"%").andChcTitleLike("%"+chcTitle+"%").andChcStatusNotEqualTo(1);
		if(chcStatus!=0){
			criteria.andChcStatusEqualTo(chcStatus);			
		}
		return salChanceMapper.selectByExample(example);
	}

	@Override
	public SalChance findSalChanceById(Integer id) {
		return salChanceMapper.selectByPrimaryKey(id);
	}

	@Override
	public int addSalChance(SalChance chance) {
		return salChanceMapper.insert(chance);
	}

	@Override
	public int updateSalChance(SalChance chance) {
		return salChanceMapper.updateByPrimaryKeySelective(chance);
	}

	@Override
	public int deleteSalChance(Integer id) {
		return salChanceMapper.deleteByPrimaryKey(id);
	}

}
