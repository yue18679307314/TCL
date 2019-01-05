package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsSettlement;
import com.kuyu.model.pcms.PcmsSettlementExample;
import com.kuyu.vo.pcms.PcmsSettlementVo;
import com.kuyu.vo.pcms.SettlementItemResult;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsSettlementMapper {
    int countByExample(PcmsSettlementExample example);

    int deleteByExample(PcmsSettlementExample example);

    int deleteByPrimaryKey(Integer seid);

    int insert(PcmsSettlement record);

    int insertSelective(PcmsSettlement record);

    List<PcmsSettlement> selectByExample(PcmsSettlementExample example);

    PcmsSettlement selectByPrimaryKey(Integer seid);

    int updateByExampleSelective(@Param("record") PcmsSettlement record, @Param("example") PcmsSettlementExample example);

    int updateByExample(@Param("record") PcmsSettlement record, @Param("example") PcmsSettlementExample example);

    int updateByPrimaryKeySelective(PcmsSettlement record);

    int updateByPrimaryKey(PcmsSettlement record);

	PcmsSettlement selectByFsscBill(String fsscBill);

	List<SettlementItemResult> getSettlementItemResult(String fsscBill);

	List<PcmsSettlementVo> selectByTime();
}