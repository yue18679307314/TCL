package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsPayment;
import com.kuyu.model.pcms.PcmsPaymentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcmsPaymentMapper {
    int countByExample(PcmsPaymentExample example);

    int deleteByExample(PcmsPaymentExample example);

    int deleteByPrimaryKey(Integer pmid);

    int insert(PcmsPayment record);

    int insertSelective(PcmsPayment record);

    List<PcmsPayment> selectByExample(PcmsPaymentExample example);

    PcmsPayment selectByPrimaryKey(Integer pmid);

    int updateByExampleSelective(@Param("record") PcmsPayment record, @Param("example") PcmsPaymentExample example);

    int updateByExample(@Param("record") PcmsPayment record, @Param("example") PcmsPaymentExample example);

    int updateByPrimaryKeySelective(PcmsPayment record);

    int updateByPrimaryKey(PcmsPayment record);
}