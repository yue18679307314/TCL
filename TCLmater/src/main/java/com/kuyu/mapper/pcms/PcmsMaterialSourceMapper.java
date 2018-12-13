package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsMaterialSource;
import com.kuyu.model.pcms.PcmsMaterialSourceExample;
import com.kuyu.vo.pcms.MaterialResult;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsMaterialSourceMapper {
    int countByExample(PcmsMaterialSourceExample example);

    int deleteByExample(PcmsMaterialSourceExample example);

    int deleteByPrimaryKey(Integer mrid);

    int insert(PcmsMaterialSource record);

    int insertSelective(PcmsMaterialSource record);

    List<PcmsMaterialSource> selectByExample(PcmsMaterialSourceExample example);

    PcmsMaterialSource selectByPrimaryKey(Integer mrid);

    int updateByExampleSelective(@Param("record") PcmsMaterialSource record, @Param("example") PcmsMaterialSourceExample example);

    int updateByExample(@Param("record") PcmsMaterialSource record, @Param("example") PcmsMaterialSourceExample example);

    int updateByPrimaryKeySelective(PcmsMaterialSource record);

    int updateByPrimaryKey(PcmsMaterialSource record);

	List<MaterialResult> selectByItid(Integer itid);
}