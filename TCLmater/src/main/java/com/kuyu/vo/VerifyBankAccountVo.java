package com.kuyu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author tang_zhen
 * @Date 2017/10/21
 * @Description
 */
@ApiModel("接收财务共享验证银行账号的Vo")
public class VerifyBankAccountVo {

    /**
     * 共享申请单单据号
     */
    @ApiModelProperty("共享申请单单据号")
    @JsonProperty("REQ_FSSC_BILL")
    private String req_fssc_bill;
    /**
     * 单据处理结果,9999：成功；0000：失败。
     */
    @ApiModelProperty("单据处理结果,9999：成功；0000：失败。")
    @JsonProperty("BILL_DEAL_CODE")
    private String bill_deal_code;

    /**
     * 单据处理意见
     */
    @ApiModelProperty("单据处理意见")
    @JsonProperty("BILL_DEAL_MAG")
   private String  bill_deal_mag;
    /**
     * 审核信息
     */
    //@ApiModelProperty("")
    private String  bank_account_verify;

    public String getReq_fssc_bill() {
        return req_fssc_bill;
    }

    public void setReq_fssc_bill(String req_fssc_bill) {
        this.req_fssc_bill = req_fssc_bill;
    }

    public String getBill_deal_code() {
        return bill_deal_code;
    }

    public void setBill_deal_code(String bill_deal_code) {
        this.bill_deal_code = bill_deal_code;
    }

    public String getBill_deal_mag() {
        return bill_deal_mag;
    }

    public void setBill_deal_mag(String bill_deal_mag) {
        this.bill_deal_mag = bill_deal_mag;
    }

    public String getBank_account_verify() {
        return bank_account_verify;
    }

    public void setBank_account_verify(String bank_account_verify) {
        this.bank_account_verify = bank_account_verify;
    }
}
