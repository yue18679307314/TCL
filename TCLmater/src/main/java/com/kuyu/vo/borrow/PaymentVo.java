package com.kuyu.vo.borrow;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Author jt_L
 * @Date 2017/10/10 0010
 * @Description
 */
public class PaymentVo{

    /**
     * 开户银行名称
     */
    @JSONField(name = "ACCOUNT_BANK_NAME")
    private String account_bank_name;
    /**
     * 收款方名称
     */
    @JSONField(name = "ACCOUNT_NAME")
    private String account_name;
    /**
     * 收款方银行账号
     */
    @JSONField(name = "ACCOUNT_VALUE")
    private String account_value;
    /**
     * 建议支付日期(YYYY-MM-DD)(发送订单当天)
     */
    @JSONField(name = "PAYMENT_DATE")
    private String payment_date;
    /**
     * 付款金额
     */
    @JSONField(name = "PAYMENT_CURRENCY")
    private String payment_currency;

    public String getAccount_bank_name(){
        return account_bank_name;
    }

    public void setAccount_bank_name(String account_bank_name){
        this.account_bank_name = account_bank_name;
    }

    public String getAccount_name(){
        return account_name;
    }

    public void setAccount_name(String account_name){
        this.account_name = account_name;
    }

    public String getAccount_value(){
        return account_value;
    }

    public void setAccount_value(String account_value){
        this.account_value = account_value;
    }

    public String getPayment_date(){
        return payment_date;
    }

    public void setPayment_date(String payment_date){
        this.payment_date = payment_date;
    }

    public String getPayment_currency(){
        return payment_currency;
    }

    public void setPayment_currency(String payment_currency){
        this.payment_currency = payment_currency;
    }
}
