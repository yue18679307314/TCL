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
 * @Description
 */
@ApiModel
@TableName("tpm_repayment_fee_detail")
public class TpmRepaymentFeeDetailModel extends Model<TpmRepaymentModel> {
    /**
     * 整个报销单编号，跟随主表
     */
    @TableField("request_id")
    @ApiModelProperty("报销单编号")
    private String requestId;
    /**
     * 财务共享导过来的费用明细区编号
     */
    @TableId
    @TableField("detail_id")
    @JsonProperty("DETAIL_ID")
    @ApiModelProperty("财务共享导过来的费用明细区编号")
    private String detailId;

    /**
     *费用明细区序号（费用明细可能有多个）
     */
    @TableField("request_no")
    @JsonProperty("DETAIL_SERIA")
    @ApiModelProperty("费用明细区序号")
    private String requestNo;

    /**
     *摘要
     */
    @TableField("summary")
    @ApiModelProperty("摘要")
    @JsonProperty("DETAIL_MEMO")
    private String summary;

    /**
     *事项申请单号
     */
    @TableField("project_request_no")
    @ApiModelProperty("事项申请单号")
    @JsonProperty("DETAIL_REQ_NO")
    private String projectRequestNo;
    /**
     *费用明细
     */
    @TableField("cost_category")
    @ApiModelProperty("费用细类")
    @JsonProperty("DETAIL_CATEGORY")
    private String costCategory;
    /**
     *核算科目
     */
    @TableField("accounting_subject")
    @ApiModelProperty("核算科目")
    @JsonProperty("DETAIL_ACCOUNT")
    private String accountingSubject;
    /**
     *预算部门
     */
    @TableField("cost_dept")
    @ApiModelProperty("预算部门")
    @JsonProperty("DETAIL_SUBJECT")
    private String costDept;
    /**
     *成本中心
     */
    @TableField("cost_center")
    @ApiModelProperty("成本中心")
    @JsonProperty("DETAIL_COSTCENTER")
    private String costCenter;
    /**
     *项目（WBS）
     */
    @TableField("wbs")
    @ApiModelProperty("项目（WBS）")
    @JsonProperty("DETAIL_WBS")
    private String wbs;
    /**
     *渠道/内部订单
     */
    @TableField("channel")
    @ApiModelProperty("渠道/内部订单")
    @JsonProperty("DETAIL_ORDER")
    private String channel;
    /**
     *发票类型
     */
    @TableField("invoice_type")
    @ApiModelProperty("发票类型")
    @JsonProperty("DETAIL_INVOICE_TYPE")
    private String invoiceType;
    /**
     *发票号
     */
    @TableField("invoice_no")
    @ApiModelProperty("发票号")
    @JsonProperty("DETAIL_INVOICE_NO")
    private String invoiceNo;
    /**
     *
     */
    @TableField("amount")
    @ApiModelProperty("原币金额")
    @JsonProperty("DETAIL_ORIGINAL")
    private Double amount;

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

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getProjectRequestNo() {
        return projectRequestNo;
    }

    public void setProjectRequestNo(String projectRequestNo) {
        this.projectRequestNo = projectRequestNo;
    }


    public String getCostCategory() {
        return costCategory;
    }

    public void setCostCategory(String costCategory) {
        this.costCategory = costCategory;
    }


    public String getAccountingSubject() {
        return accountingSubject;
    }

    public void setAccountingSubject(String accountingSubject) {
        this.accountingSubject = accountingSubject;
    }

    public String getCostDept() {
        return costDept;
    }

    public void setCostDept(String costDept) {
        this.costDept = costDept;
    }


    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }


    public String getWbs() {
        return wbs;
    }

    public void setWbs(String wbs) {
        this.wbs = wbs;
    }


    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }


    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    @Override
    protected Serializable pkVal() {
        return null;
    }


}
