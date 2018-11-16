package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsItemRelation;
import com.kuyu.model.pcms.PcmsItemRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsItemRelationMapper {
    int countByExample(PcmsItemRelationExample example);

    int deleteByExample(PcmsItemRelationExample example);

    int deleteByPrimaryKey(Integer irid);

    int insert(PcmsItemRelation record);

    int insertSelective(PcmsItemRelation record);

    List<PcmsItemRelation> selectByExample(PcmsItemRelationExample example);

    PcmsItemRelation selectByPrimaryKey(Integer irid);

    int updateByExampleSelective(@Param("record") PcmsItemRelation record, @Param("example") PcmsItemRelationExample example);

    int updateByExample(@Param("record") PcmsItemRelation record, @Param("example") PcmsItemRelationExample example);

    int updateByPrimaryKeySelective(PcmsItemRelation record);

    int updateByPrimaryKey(PcmsItemRelation record);
}