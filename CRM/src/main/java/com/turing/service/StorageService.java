package com.turing.service;

import java.util.List;


import com.turing.entity.Storage;

/**
 * ø‚¥Ê“µŒÒ¿‡
 * @author Administrator
 *
 */
public interface StorageService {

   public List<Storage> selectProStorage(String prodName,String stkWarehouse);
}
