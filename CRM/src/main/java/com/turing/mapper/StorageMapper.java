package com.turing.mapper;

import com.turing.entity.Storage;
import com.turing.entity.StorageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface StorageMapper {
    long countByExample(StorageExample example);

    int deleteByExample(StorageExample example);

    int deleteByPrimaryKey(Integer stkId);

    int insert(Storage record);

    int insertSelective(Storage record);

    List<Storage> selectByExampleWithRowbounds(StorageExample example, RowBounds rowBounds);

    List<Storage> selectByExample(StorageExample example);

    Storage selectByPrimaryKey(Integer stkId);

    int updateByExampleSelective(@Param("record") Storage record, @Param("example") StorageExample example);

    int updateByExample(@Param("record") Storage record, @Param("example") StorageExample example);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);
    //Ä£ºý²éÑ¯
    List<Storage> selectByWhere(@Param("prodName") String prodName,@Param("stkWarehouse") String stkWarehouse);
}