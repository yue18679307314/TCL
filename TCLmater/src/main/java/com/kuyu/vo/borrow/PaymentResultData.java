package com.kuyu.vo.borrow;

import com.alibaba.fastjson.annotation.JSONPOJOBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.swing.*;

/**
 * @Author jt_L
 * @Date 2017/10/25
 * @Description 付款结果数据VO
 */
public class PaymentResultData{


    /**
     * 共享借款单单据号
     */
    @JsonProperty("FSSC_BILL")
    private String fssc_bill;

    /**
     * 借款单付款区ID(结算id)
     */
    @JsonProperty("PAYMENT_ID")
    private String payment_id;


    /**
     * 付款结果编码
     */
    @JsonProperty("RESULT_CODE")
    private String result_code;

    /**
     * 付款结果信息
     */
    @JsonProperty("RESULT_MSG")
    private String result_msg;

    public String getFssc_bill(){
        return fssc_bill;
    }

    public void setFssc_bill(String fssc_bill){
        this.fssc_bill = fssc_bill;
    }

    public String getPayment_id(){
        return payment_id;
    }

    public void setPayment_id(String payment_id){
        this.payment_id = payment_id;
    }

    public String getResult_code(){
        return result_code;
    }

    public void setResult_code(String result_code){
        this.result_code = result_code;
    }

    public String getResult_msg(){
        return result_msg;
    }

    public void setResult_msg(String result_msg){
        this.result_msg = result_msg;
    }

}
