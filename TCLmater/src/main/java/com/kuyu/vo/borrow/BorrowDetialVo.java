package com.kuyu.vo.borrow;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author jt_L
 * @Date 2017/10/10
 * @Description
 */
public class BorrowDetialVo{
    /**
     * 申请单明细区id
     */
    @JSONField(name = "DETAIL_ID")
    private String detail_Id;
    /**
     * 实际报销金额
     */
    @JSONField(name = "NATURAL_CURRENCY")
    private String natural_currency;
    /**
     * 借款申请描述
     */
    @JSONField(name = "DETAIL_MEMO")
    private String detail_memo;

    public String getDetail_Id(){
        return detail_Id;
    }

    public void setDetail_Id(String detail_Id){
        this.detail_Id = detail_Id;
    }

    public String getNatural_currency(){
        return natural_currency;
    }

    public void setNatural_currency(String natural_currency){
        this.natural_currency = natural_currency;
    }

    public String getDetail_memo(){
        return detail_memo;
    }

    public void setDetail_memo(String detail_memo){
        this.detail_memo = detail_memo;
    }
}
