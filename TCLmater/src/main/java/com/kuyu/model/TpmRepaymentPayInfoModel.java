package com.kuyu.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author tang_zhen
 * @Date 2017/9/29
 * @Description  本Model对应数据库中的tpm_repayment_pay_info
 * 也是数据库更改后的冲销部分的信息
 */
@ApiModel
@TableName( "tpm_repayment_pay_info")
public class TpmRepaymentPayInfoModel  extends Model<TpmRepaymentPayInfoModel> {
    /**
     *报销单编号（跟随主表）
     */
    @TableField("request_id")
    @ApiModelProperty("报销单编号")
    private String requestId;
    /**
     * 财务共享导过来的冲销区编号
     */
    @TableId
    @TableField("detail_id")
    @JsonProperty("REVERSAL_ID")
    @ApiModelProperty("财务共享导过来的冲销区编号")
    private String detailId;
    /**
     * 冲销区序号（冲销区可能有多个）
     */
    @TableField("repayment_no")
    @ApiModelProperty("冲销区序号")
    @JsonProperty("REVERSAL_SERIA")
    private String repaymentNo;
    /**
     *借款单/预付款编号
     */
    @TableField("loan_bill_no")
    @ApiModelProperty("借款单/预付款编号")
    @JsonProperty("REVERSAL_REQ_NO")
    private String loanBillNo;
    /**
     *借款（预付款）金额
     */
    @TableField("loan_amount")
    @ApiModelProperty("借款（预付款）金额")
    @JsonProperty("REVERSAL_REQ_AMOUNT")
    private Double loanAmount;
    /**
     *供应商
     */
    @TableField("supplier")
    @ApiModelProperty("供应商")
    @JsonProperty("REVERSAL_VENDOR")
    private String supplier;
    /**
     *可用金额
     */
    @TableField("available_amount")
    @ApiModelProperty("可用金额")
    @JsonProperty("REVERSAL_AVALIABLE")
    private Double availableAmount;
    /**
     *本次还款原币金额
     */
    @TableField("original_currency_amount")
    @ApiModelProperty("本次还款原币金额")
    @JsonProperty("REVERSAL_CUR_ORIGINAL")
    private Double originalCurrencyAmount;
    /**
     *还款本币金额
     */
    @TableField("repayment_amount")
    @ApiModelProperty("还款本币金额")
    @JsonProperty("REVERSAL_ORIGINAL")
    private Double repaymentAmount;

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


    public String getRepaymentNo() {
        return repaymentNo;
    }

    public void setRepaymentNo(String repaymentNo) {
        this.repaymentNo = repaymentNo;
    }

    public String getLoanBillNo() {
        return loanBillNo;
    }

    public void setLoanBillNo(String loanBillNo) {
        this.loanBillNo = loanBillNo;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Double getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Double availableAmount) {
        this.availableAmount = availableAmount;
    }

    public Double getOriginalCurrencyAmount() {
        return originalCurrencyAmount;
    }

    public void setOriginalCurrencyAmount(Double originalCurrencyAmount) {
        this.originalCurrencyAmount = originalCurrencyAmount;
    }

    public Double getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(Double repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    @Override
    protected Serializable pkVal()
    {
        return null;
    }
}

