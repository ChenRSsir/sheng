package com.turing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.entity.Storage;
import com.turing.mapper.StorageMapper;
import com.turing.service.StorageService;
/**
 * 库存业务实现类
 * @author Administrator
 *
 */
@Service
public class StorageServiceImpl implements StorageService {
	
	@Autowired
	private StorageMapper storageMapper;

	@Override
	public List<Storage> selectProStorage(String prodName, String stkWarehouse) {
		
		return storageMapper.selectByWhere("%"+prodName+"%","%"+stkWarehouse+"%");
	}

}
