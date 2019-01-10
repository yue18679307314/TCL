package com.kuyu.mapper.pcms;

import com.kuyu.model.pcms.PcmsPayment;
import com.kuyu.model.pcms.PcmsPaymentExample;
import com.kuyu.vo.pcms.PaymentResult;

import java.util.HashMap;
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

	PaymentResult getDetailMoney(String fsscBill);

	List<PaymentResult> getPaymentList(HashMap<String, Object> param);

	PcmsPayment selectByFsscBill(String fsscBill);

	Integer getPaymentListCount(HashMap<String, Object> param);
}