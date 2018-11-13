package com.kuyu.vo.borrow;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Author jt_L
 * @Date 2017/10/10
 * @Description 借款单Vo
 */
public class BorrowMoneyVo{

    /**
     * 日常费用申请单单号
     */
    @JSONField(name = "FSSC_BILL")
    private String fssc_bill;
    /**
     * 申请人
     */
    @JSONField(name = "REQUEST_USER")
    private String request_user;
    /**
     * 申请人部门
     */
    @JSONField(name = "REQUEST_DEPART")
    private String request_depart;
    /**
     * 预计还款日期(当月月末最后一天)
     */
    @JSONField(name = "REPAY_DATE")
    private String repay_date;
    /**
     * 预计还款日期(当月月末最后一天)
     */
    @JSONField(name = "REPAY_REL_USER")
    private String repay_rel_user;
    /**
     * 借款明细区信息
     */
    @JSONField(name = "DETAIL_LIST")
    private List<BorrowDetialVo> detail_list;
    /**
     * 付款区信息
     */
    @JSONField(name = "PAYMENT_LIST")
    private List<PaymentVo> payment_list;

    public String getFssc_bill(){
        return fssc_bill;
    }

    public void setFssc_bill(String fssc_bill){
        this.fssc_bill = fssc_bill;
    }

    public String getRequest_user(){
        return request_user;
    }

    public void setRequest_user(String request_user){
        this.request_user = request_user;
    }

    public String getRequest_depart(){
        return request_depart;
    }

    public void setRequest_depart(String request_depart){
        this.request_depart = request_depart;
    }

    public String getRepay_date(){
        return repay_date;
    }

    public void setRepay_date(String repay_date){
        this.repay_date = repay_date;
    }

    public List<BorrowDetialVo> getDetail_list(){
        return detail_list;
    }

    public void setDetail_list(List<BorrowDetialVo> detail_list){
        this.detail_list = detail_list;
    }

    public List<PaymentVo> getPayment_list(){
        return payment_list;
    }
    
    public void setPayment_list(List<PaymentVo> payment_list){
        this.payment_list = payment_list;
    }

	public String getRepay_rel_user() {
		return repay_rel_user;
	}

	public void setRepay_rel_user(String repay_rel_user) {
		this.repay_rel_user = repay_rel_user;
	}
    
}
