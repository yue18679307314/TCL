package com.kuyu.vo.borrow;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Author jt_L
 * @Date 2017/11/8
 * @Description
 */
public class PaymentBillResultVo{

    /**
     * code码
     */
    @JsonProperty("RET_CODE")
    private String retCode;
    /**
     * 返回信息
     */
    @JsonProperty("RET_MSG")
    private String retMsg;
    /**
     * 借款单单号
     */
    @JsonProperty("PAYMENT_BILL_NO")
    private String paymentBillNo;
    /**
     * 付款区信息
     */
    @JsonProperty("PAYMENT_BILL_LIST")
    private List<PaymentBillVo> payment_bill_list;

    public String getRetCode(){
        return retCode;
    }

    public void setRetCode(String retCode){
        this.retCode = retCode;
    }

    public String getRetMsg(){
        return retMsg;
    }

    public void setRetMsg(String retMsg){
        this.retMsg = retMsg;
    }

    public String getPaymentBillNo(){
        return paymentBillNo;
    }

    public void setPaymentBillNo(String paymentBillNo){
        this.paymentBillNo = paymentBillNo;
    }

    public List<PaymentBillVo> getPayment_bill_list(){
        return payment_bill_list;
    }

    public void setPayment_bill_list(List<PaymentBillVo> payment_bill_list){
        this.payment_bill_list = payment_bill_list;
    }
}
