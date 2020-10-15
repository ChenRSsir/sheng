package com.turing.mapper;

import com.turing.entity.CstLost;
import com.turing.entity.CstLostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CstLostMapper {
    long countByExample(CstLostExample example);

    int deleteByExample(CstLostExample example);

    int deleteByPrimaryKey(Integer lstId);

    int insert(CstLost record);

    int insertSelective(CstLost record);

    List<CstLost> selectByExampleWithRowbounds(CstLostExample example, RowBounds rowBounds);

    List<CstLost> selectByExample(CstLostExample example);

    CstLost selectByPrimaryKey(Integer lstId);

    int updateByExampleSelective(@Param("record") CstLost record, @Param("example") CstLostExample example);

    int updateByExample(@Param("record") CstLost record, @Param("example") CstLostExample example);

    int updateByPrimaryKeySelective(CstLost record);

    int updateByPrimaryKey(CstLost record);
}