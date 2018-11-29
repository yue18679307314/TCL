package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsOthertm;
import com.kuyu.model.pcms.PcmsOthertmExample;
import com.kuyu.vo.pcms.OthertmResult;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsOthertmMapper {
    int countByExample(PcmsOthertmExample example);

    int deleteByExample(PcmsOthertmExample example);

    int deleteByPrimaryKey(Integer otid);

    int insert(PcmsOthertm record);

    int insertSelective(PcmsOthertm record);

    List<PcmsOthertm> selectByExample(PcmsOthertmExample example);

    PcmsOthertm selectByPrimaryKey(Integer otid);

    int updateByExampleSelective(@Param("record") PcmsOthertm record, @Param("example") PcmsOthertmExample example);

    int updateByExample(@Param("record") PcmsOthertm record, @Param("example") PcmsOthertmExample example);

    int updateByPrimaryKeySelective(PcmsOthertm record);

    int updateByPrimaryKey(PcmsOthertm record);

	List<OthertmResult> selectByItid(Integer itid);
}