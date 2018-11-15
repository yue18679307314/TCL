package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsMaterial;
import com.kuyu.model.pcms.PcmsMaterialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsMaterialMapper {
    int countByExample(PcmsMaterialExample example);

    int deleteByExample(PcmsMaterialExample example);

    int deleteByPrimaryKey(Integer mrid);

    int insert(PcmsMaterial record);

    int insertSelective(PcmsMaterial record);

    List<PcmsMaterial> selectByExample(PcmsMaterialExample example);

    PcmsMaterial selectByPrimaryKey(Integer mrid);

    int updateByExampleSelective(@Param("record") PcmsMaterial record, @Param("example") PcmsMaterialExample example);

    int updateByExample(@Param("record") PcmsMaterial record, @Param("example") PcmsMaterialExample example);

    int updateByPrimaryKeySelective(PcmsMaterial record);

    int updateByPrimaryKey(PcmsMaterial record);
}