package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsPaymentCheck;
import com.kuyu.model.pcms.PcmsPaymentCheckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsPaymentCheckMapper {
    int countByExample(PcmsPaymentCheckExample example);

    int deleteByExample(PcmsPaymentCheckExample example);

    int insert(PcmsPaymentCheck record);

    int insertSelective(PcmsPaymentCheck record);

    List<PcmsPaymentCheck> selectByExampleWithBLOBs(PcmsPaymentCheckExample example);

    List<PcmsPaymentCheck> selectByExample(PcmsPaymentCheckExample example);

    int updateByExampleSelective(@Param("record") PcmsPaymentCheck record, @Param("example") PcmsPaymentCheckExample example);

    int updateByExampleWithBLOBs(@Param("record") PcmsPaymentCheck record, @Param("example") PcmsPaymentCheckExample example);

    int updateByExample(@Param("record") PcmsPaymentCheck record, @Param("example") PcmsPaymentCheckExample example);
}