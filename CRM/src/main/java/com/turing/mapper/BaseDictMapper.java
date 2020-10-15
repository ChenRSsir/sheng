package com.turing.mapper;

import com.turing.entity.BaseDict;
import com.turing.entity.BaseDictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BaseDictMapper {
    long countByExample(BaseDictExample example);

    int deleteByExample(BaseDictExample example);

    int deleteByPrimaryKey(Integer dictId);

    int insert(BaseDict record);

    int insertSelective(BaseDict record);

    List<BaseDict> selectByExampleWithRowbounds(BaseDictExample example, RowBounds rowBounds);

    List<BaseDict> selectByExample(BaseDictExample example);

    BaseDict selectByPrimaryKey(Integer dictId);

    int updateByExampleSelective(@Param("record") BaseDict record, @Param("example") BaseDictExample example);

    int updateByExample(@Param("record") BaseDict record, @Param("example") BaseDictExample example);

    int updateByPrimaryKeySelective(BaseDict record);

    int updateByPrimaryKey(BaseDict record);
}