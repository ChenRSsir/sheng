package com.turing.mapper;

import com.turing.entity.SalChance;
import com.turing.entity.SalChanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SalChanceMapper {
    long countByExample(SalChanceExample example);

    int deleteByExample(SalChanceExample example);

    int deleteByPrimaryKey(Integer chcId);

    int insert(SalChance record);

    int insertSelective(SalChance record);

    List<SalChance> selectByExampleWithRowbounds(SalChanceExample example, RowBounds rowBounds);

    List<SalChance> selectByExample(SalChanceExample example);

    SalChance selectByPrimaryKey(Integer chcId);

    int updateByExampleSelective(@Param("record") SalChance record, @Param("example") SalChanceExample example);

    int updateByExample(@Param("record") SalChance record, @Param("example") SalChanceExample example);

    int updateByPrimaryKeySelective(SalChance record);

    int updateByPrimaryKey(SalChance record);
}