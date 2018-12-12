package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsProjectDeatil;
import com.kuyu.model.pcms.PcmsProjectDeatilExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsProjectDeatilMapper {
    int countByExample(PcmsProjectDeatilExample example);

    int deleteByExample(PcmsProjectDeatilExample example);

    int insert(PcmsProjectDeatil record);

    int insertSelective(PcmsProjectDeatil record);

    List<PcmsProjectDeatil> selectByExample(PcmsProjectDeatilExample example);

    int updateByExampleSelective(@Param("record") PcmsProjectDeatil record, @Param("example") PcmsProjectDeatilExample example);

    int updateByExample(@Param("record") PcmsProjectDeatil record, @Param("example") PcmsProjectDeatilExample example);
}