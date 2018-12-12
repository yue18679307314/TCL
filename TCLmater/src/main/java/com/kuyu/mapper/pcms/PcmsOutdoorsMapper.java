package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsOutdoors;
import com.kuyu.model.pcms.PcmsOutdoorsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsOutdoorsMapper {
    int countByExample(PcmsOutdoorsExample example);

    int deleteByExample(PcmsOutdoorsExample example);

    int insert(PcmsOutdoors record);

    int insertSelective(PcmsOutdoors record);

    List<PcmsOutdoors> selectByExample(PcmsOutdoorsExample example);

    int updateByExampleSelective(@Param("record") PcmsOutdoors record, @Param("example") PcmsOutdoorsExample example);

    int updateByExample(@Param("record") PcmsOutdoors record, @Param("example") PcmsOutdoorsExample example);
}