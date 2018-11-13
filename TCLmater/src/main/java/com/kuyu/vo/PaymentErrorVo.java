package com.kuyu.vo;

/**
 * @Author jt_L
 * @Date 2017/10/30
 * @Description 结算支付异常VO
 */
public class PaymentErrorVo{

    /**
     * 活动uuid
     */
    private String activity_uuid;
    /**
     * 财务员工号
     */
    private String personCode;
    /**
     * 支付异常数量
     */
    private int errorCount;

    public String getActivity_uuid(){
        return activity_uuid;
    }

    public void setActivity_uuid(String activity_uuid){
        this.activity_uuid = activity_uuid;
    }

    public int getErrorCount(){
        return errorCount;
    }

    public void setErrorCount(int errorCount){
        this.errorCount = errorCount;
    }

    public String getPersonCode(){
        return personCode;
    }

    public void setPersonCode(String personCode){
        this.personCode = personCode;
    }
}
