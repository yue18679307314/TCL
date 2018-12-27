package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsPaymentDetail;
import com.kuyu.model.pcms.PcmsPaymentDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsPaymentDetailMapper {
    int countByExample(PcmsPaymentDetailExample example);

    int deleteByExample(PcmsPaymentDetailExample example);

    int insert(PcmsPaymentDetail record);

    int insertSelective(PcmsPaymentDetail record);

    List<PcmsPaymentDetail> selectByExample(PcmsPaymentDetailExample example);

    int updateByExampleSelective(@Param("record") PcmsPaymentDetail record, @Param("example") PcmsPaymentDetailExample example);

    int updateByExample(@Param("record") PcmsPaymentDetail record, @Param("example") PcmsPaymentDetailExample example);
}