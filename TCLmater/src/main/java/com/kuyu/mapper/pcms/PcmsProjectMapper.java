package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsProject;
import com.kuyu.model.pcms.PcmsProjectExample;
import com.kuyu.vo.pcms.PcmsProjectVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsProjectMapper {
    int countByExample(PcmsProjectExample example);

    int deleteByExample(PcmsProjectExample example);

    int deleteByPrimaryKey(String requestId);

    int insert(PcmsProject record);

    int insertSelective(PcmsProject record);

    List<PcmsProject> selectByExampleWithBLOBs(PcmsProjectExample example);

    List<PcmsProject> selectByExample(PcmsProjectExample example);

    PcmsProject selectByPrimaryKey(String requestId);

    int updateByExampleSelective(@Param("record") PcmsProject record, @Param("example") PcmsProjectExample example);

    int updateByExampleWithBLOBs(@Param("record") PcmsProject record, @Param("example") PcmsProjectExample example);

    int updateByExample(@Param("record") PcmsProject record, @Param("example") PcmsProjectExample example);

    int updateByPrimaryKeySelective(PcmsProject record);

    int updateByPrimaryKeyWithBLOBs(PcmsProject record);

    int updateByPrimaryKey(PcmsProject record);

	PcmsProjectVo getProjectDeatil(String requestId);
}