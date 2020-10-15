package com.turing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.entity.BaseDict;
import com.turing.entity.BaseDictExample;
import com.turing.mapper.BaseDictMapper;
import com.turing.service.BaseDictService;
/**
 * 数据字典实现类
 * @author Administrator
 *
 */
@Service
public class BaseDictServiceImpl implements BaseDictService {

	@Autowired
	private BaseDictMapper baseDictMapper;
	
	@Override
	public List<BaseDict> findBaseDict() {
		List<BaseDict> list = baseDictMapper.selectByExample(null);
		return list;
	}

	@Override
	public List<BaseDict> findBaseDictByWhere(BaseDict baseDict) {
		BaseDictExample example=new BaseDictExample();
		example.createCriteria().andDictTypeLike("%"+baseDict.getDictType()+"%").andDictItemLike("%"+baseDict.getDictItem()+"%").andDictValueLike("%"+baseDict.getDictValue()+"%");
		List<BaseDict> list = baseDictMapper.selectByExample(example);
		return list;
	}

	@Override
	public BaseDict findBaseDictById(Integer id) {
		// TODO Auto-generated method stub
		return  baseDictMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insert(BaseDict baseDict) {
		// TODO Auto-generated method stub
		return baseDictMapper.insert(baseDict);
	}

	@Override
	public int update(BaseDict baseDict) {
		// TODO Auto-generated method stub
		return baseDictMapper.updateByPrimaryKeySelective(baseDict);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return baseDictMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<BaseDict> findItem(String dictValue) {
		BaseDictExample example=new BaseDictExample();
		example.createCriteria().andDictValueEqualTo(dictValue);
		return baseDictMapper.selectByExample(example);
	}

}
