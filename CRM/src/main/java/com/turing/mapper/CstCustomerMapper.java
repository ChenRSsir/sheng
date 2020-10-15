package com.turing.mapper;

import com.turing.entity.CstCustomer;
import com.turing.entity.CstCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CstCustomerMapper {
    long countByExample(CstCustomerExample example);

    int deleteByExample(CstCustomerExample example);

    int deleteByPrimaryKey(String custNo);

    int insert(CstCustomer record);

    int insertSelective(CstCustomer record);

    List<CstCustomer> selectByExampleWithRowbounds(CstCustomerExample example, RowBounds rowBounds);

    List<CstCustomer> selectByExample(CstCustomerExample example);

    CstCustomer selectByPrimaryKey(String custNo);

    int updateByExampleSelective(@Param("record") CstCustomer record, @Param("example") CstCustomerExample example);

    int updateByExample(@Param("record") CstCustomer record, @Param("example") CstCustomerExample example);

    int updateByPrimaryKeySelective(CstCustomer record);

    int updateByPrimaryKey(CstCustomer record);
    //查询所有状态为正常的客户，用于判断
    List<CstCustomer> selectCus(@Param("custNo")String custNo,@Param("custName")String custName,@Param("custRegion")String custRegion,@Param("custManagerName")String custManagerName,@Param("custLevel")Integer custLevel);
    //客户分组查询
    List<CstCustomer> findCompose(@Param("i")Integer i);
}