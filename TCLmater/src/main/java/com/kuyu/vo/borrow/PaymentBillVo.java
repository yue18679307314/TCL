package com.kuyu.vo.borrow;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author jt_L
 * @Date 2017/11/8
 * @Description
 */
public class PaymentBillVo{

    /**
     * 收款方名称
     */
    @JsonProperty("ACCOUNT_NAME")
    private String accountName;
    /**
     * 收款方银行账号
     */
    @JsonProperty("ACCOUNT_VALUE")
    private String accountValue;
    /**
     * 付款金额
     */
    @JsonProperty("PAYMENT_CURRENCY")
    private String paymentCurrency;
    /**
     * 流水号
     */
    @JsonProperty("PAYMENT_ID")
    private String paymentId;

    public String getAccountName(){
        return accountName;
    }

    public void setAccountName(String accountName){
        this.accountName = accountName;
    }

    public String getAccountValue(){
        return accountValue;
    }

    public void setAccountValue(String accountValue){
        this.accountValue = accountValue;
    }

    public String getPaymentCurrency(){
        return paymentCurrency;
    }

    public void setPaymentCurrency(String paymentCurrency){
        this.paymentCurrency = paymentCurrency;
    }

    public String getPaymentId(){
        return paymentId;
    }

    public void setPaymentId(String paymentId){
        this.paymentId = paymentId;
    }
}
