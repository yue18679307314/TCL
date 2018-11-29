package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsShowcase;
import com.kuyu.model.pcms.PcmsShowcaseExample;
import com.kuyu.vo.pcms.ShowcaseResult;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsShowcaseMapper {
    int countByExample(PcmsShowcaseExample example);

    int deleteByExample(PcmsShowcaseExample example);

    int deleteByPrimaryKey(Integer scid);

    int insert(PcmsShowcase record);

    int insertSelective(PcmsShowcase record);

    List<PcmsShowcase> selectByExample(PcmsShowcaseExample example);

    PcmsShowcase selectByPrimaryKey(Integer scid);

    int updateByExampleSelective(@Param("record") PcmsShowcase record, @Param("example") PcmsShowcaseExample example);

    int updateByExample(@Param("record") PcmsShowcase record, @Param("example") PcmsShowcaseExample example);

    int updateByPrimaryKeySelective(PcmsShowcase record);

    int updateByPrimaryKey(PcmsShowcase record);

	List<ShowcaseResult> selectByItid(Integer itid);
}