package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsShowcaseSource;
import com.kuyu.model.pcms.PcmsShowcaseSourceExample;
import com.kuyu.vo.pcms.ShowcaseResult;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsShowcaseSourceMapper {
    int countByExample(PcmsShowcaseSourceExample example);

    int deleteByExample(PcmsShowcaseSourceExample example);

    int deleteByPrimaryKey(Integer scid);

    int insert(PcmsShowcaseSource record);

    int insertSelective(PcmsShowcaseSource record);

    List<PcmsShowcaseSource> selectByExample(PcmsShowcaseSourceExample example);

    PcmsShowcaseSource selectByPrimaryKey(Integer scid);

    int updateByExampleSelective(@Param("record") PcmsShowcaseSource record, @Param("example") PcmsShowcaseSourceExample example);

    int updateByExample(@Param("record") PcmsShowcaseSource record, @Param("example") PcmsShowcaseSourceExample example);

    int updateByPrimaryKeySelective(PcmsShowcaseSource record);

    int updateByPrimaryKey(PcmsShowcaseSource record);

	List<ShowcaseResult> selectByItid(Integer itid);
}