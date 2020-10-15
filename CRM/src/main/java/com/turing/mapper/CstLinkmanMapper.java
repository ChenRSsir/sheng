package com.turing.mapper;

import com.turing.entity.CstLinkman;
import com.turing.entity.CstLinkmanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CstLinkmanMapper {
    long countByExample(CstLinkmanExample example);

    int deleteByExample(CstLinkmanExample example);

    int deleteByPrimaryKey(Integer lkmId);

    int insert(CstLinkman record);

    int insertSelective(CstLinkman record);

    List<CstLinkman> selectByExampleWithRowbounds(CstLinkmanExample example, RowBounds rowBounds);

    List<CstLinkman> selectByExample(CstLinkmanExample example);

    CstLinkman selectByPrimaryKey(Integer lkmId);

    int updateByExampleSelective(@Param("record") CstLinkman record, @Param("example") CstLinkmanExample example);

    int updateByExample(@Param("record") CstLinkman record, @Param("example") CstLinkmanExample example);

    int updateByPrimaryKeySelective(CstLinkman record);

    int updateByPrimaryKey(CstLinkman record);
}