package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsItemLog;
import com.kuyu.model.pcms.PcmsItemLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsItemLogMapper {
    int countByExample(PcmsItemLogExample example);

    int deleteByExample(PcmsItemLogExample example);

    int deleteByPrimaryKey(Integer tlid);

    int insert(PcmsItemLog record);

    int insertSelective(PcmsItemLog record);

    List<PcmsItemLog> selectByExample(PcmsItemLogExample example);

    PcmsItemLog selectByPrimaryKey(Integer tlid);

    int updateByExampleSelective(@Param("record") PcmsItemLog record, @Param("example") PcmsItemLogExample example);

    int updateByExample(@Param("record") PcmsItemLog record, @Param("example") PcmsItemLogExample example);

    int updateByPrimaryKeySelective(PcmsItemLog record);

    int updateByPrimaryKey(PcmsItemLog record);
}