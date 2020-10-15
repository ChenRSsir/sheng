package com.turing.mapper;

import com.turing.entity.SalPlan;
import com.turing.entity.SalPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SalPlanMapper {
    long countByExample(SalPlanExample example);

    int deleteByExample(SalPlanExample example);

    int deleteByPrimaryKey(Integer plaId);

    int insert(SalPlan record);

    int insertSelective(SalPlan record);

    List<SalPlan> selectByExampleWithRowbounds(SalPlanExample example, RowBounds rowBounds);

    List<SalPlan> selectByExample(SalPlanExample example);

    SalPlan selectByPrimaryKey(Integer plaId);

    int updateByExampleSelective(@Param("record") SalPlan record, @Param("example") SalPlanExample example);

    int updateByExample(@Param("record") SalPlan record, @Param("example") SalPlanExample example);

    int updateByPrimaryKeySelective(SalPlan record);

    int updateByPrimaryKey(SalPlan record);
}