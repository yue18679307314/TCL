package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsOthertmSource;
import com.kuyu.model.pcms.PcmsOthertmSourceExample;
import com.kuyu.vo.pcms.OthertmResult;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsOthertmSourceMapper {
    int countByExample(PcmsOthertmSourceExample example);

    int deleteByExample(PcmsOthertmSourceExample example);

    int insert(PcmsOthertmSource record);

    int insertSelective(PcmsOthertmSource record);

    List<PcmsOthertmSource> selectByExample(PcmsOthertmSourceExample example);

    int updateByExampleSelective(@Param("record") PcmsOthertmSource record, @Param("example") PcmsOthertmSourceExample example);

    int updateByExample(@Param("record") PcmsOthertmSource record, @Param("example") PcmsOthertmSourceExample example);

	List<OthertmResult> selectByItid(Integer itid);
}