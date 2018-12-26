package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsSettlementItem;
import com.kuyu.model.pcms.PcmsSettlementItemExample;
import com.kuyu.vo.pcms.SettlementResult;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsSettlementItemMapper {
    int countByExample(PcmsSettlementItemExample example);

    int deleteByExample(PcmsSettlementItemExample example);

    int deleteByPrimaryKey(Integer setid);

    int insert(PcmsSettlementItem record);

    int insertSelective(PcmsSettlementItem record);

    List<PcmsSettlementItem> selectByExample(PcmsSettlementItemExample example);

    PcmsSettlementItem selectByPrimaryKey(Integer setid);

    int updateByExampleSelective(@Param("record") PcmsSettlementItem record, @Param("example") PcmsSettlementItemExample example);

    int updateByExample(@Param("record") PcmsSettlementItem record, @Param("example") PcmsSettlementItemExample example);

    int updateByPrimaryKeySelective(PcmsSettlementItem record);

    int updateByPrimaryKey(PcmsSettlementItem record);

	List<SettlementResult> selectByItid(Integer itid);
}