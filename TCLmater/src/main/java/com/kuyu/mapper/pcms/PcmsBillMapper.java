package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsBill;
import com.kuyu.model.pcms.PcmsBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsBillMapper {
    int countByExample(PcmsBillExample example);

    int deleteByExample(PcmsBillExample example);

    int deleteByPrimaryKey(Integer blid);

    int insert(PcmsBill record);

    int insertSelective(PcmsBill record);

    List<PcmsBill> selectByExample(PcmsBillExample example);

    PcmsBill selectByPrimaryKey(Integer blid);

    int updateByExampleSelective(@Param("record") PcmsBill record, @Param("example") PcmsBillExample example);

    int updateByExample(@Param("record") PcmsBill record, @Param("example") PcmsBillExample example);

    int updateByPrimaryKeySelective(PcmsBill record);

    int updateByPrimaryKey(PcmsBill record);
}