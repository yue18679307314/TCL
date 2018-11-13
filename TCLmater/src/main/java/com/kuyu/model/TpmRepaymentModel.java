package com.kuyu.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;


/**
 * @Author tang_zhen
 * @Date 2017/9/29
 * @Description
 */

@ApiModel("报销单信息Model")
//@JsonIgnoreProperties(value = {"password"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@TableName("tpm_repayment_main")
public class TpmRepaymentModel extends Model<TpmRepaymentModel> {

    /**
     * 报销单编号
     */
    @TableId
    @ApiModelProperty("报销单编号")
    @JsonProperty("REQUEST_ID")
    @TableField("request_id")
    private String requestId;
    /**
     * 申请标题
     */
    @ApiModelProperty("申请标题")
    @JsonProperty("REQUEST_TITLE")
    @TableField("request_title")
    private String requestTitle;

    /**
     * 申请人
     */
    @JsonProperty("REQUEST_USER")
    @ApiModelProperty("申请人")
    @TableField("request_user")
    private String requestUser;
    /**
     * 申请时间与日期
     */
    @JsonProperty("REQUEST_CREATE_TIME")
    @ApiModelProperty("申请时间与日期")
    @TableField("request_create_time")
    private String requestCreateTime;
    /**
     * 币种
     */
    @JsonProperty("REQUEST_ CURRENCY")
    @ApiModelProperty("币种")
    @TableField("currency")
    private String currency;
    /**
     * 事项说明
     */
    @JsonProperty("REQUEST_MEMO")
    @ApiModelProperty("事项说明")
    @TableField("note")
    private String note;
    /**
     * 支付信息列表
     */
    @JsonProperty("STERILIZATION_LIST")
    @ApiModelProperty("冲销区list")
    @TableField(exist = false)
    private List<TpmRepaymentPayInfoModel> tpmRepaymentPayInfoModelList;
    /**
     * 支付细节列表
     */
    @JsonProperty("DETAIL_FEE_LIST")
    @ApiModelProperty("支付细节列表")
    @TableField(exist = false)
    private List<TpmRepaymentFeeDetailModel> tpmRepaymentFeeDetailModelList;


    public List<TpmRepaymentPayInfoModel> getTpmRepaymentPayInfoModelList() {
        return tpmRepaymentPayInfoModelList;
    }

    public void setTpmRepaymentPayInfoModelList(List<TpmRepaymentPayInfoModel> tpmRepaymentPayInfoModelList) {
        this.tpmRepaymentPayInfoModelList = tpmRepaymentPayInfoModelList;
    }

    public List<TpmRepaymentFeeDetailModel> getTpmRepaymentFeeDetailModelList() {
        return tpmRepaymentFeeDetailModelList;
    }

    public void setTpmRepaymentFeeDetailModelList(List<TpmRepaymentFeeDetailModel> tpmRepaymentFeeDetailModelList) {
        this.tpmRepaymentFeeDetailModelList = tpmRepaymentFeeDetailModelList;
    }



    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


    public String getRequestTitle() {
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
    }


    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser;
    }


    public String getRequestCreateTime() {
        return requestCreateTime;
    }

    public void setRequestCreateTime(String requestCreateTime) {
        this.requestCreateTime = requestCreateTime;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }


}
