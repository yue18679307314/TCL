package com.kuyu.vo.project;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author jt_L
 * @Date 2017/10/23
 * @Description
 */
@ApiModel("立项单活动元数据复制")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ActivityOriginalModelVo{

    /**
     * 活动id
     */
    @JsonProperty("ACTIVITY_ID")
    @ApiModelProperty(value = "活动id",position = -50)
    private String projectId;
    /**
     * 活动名称
     */
    @JsonProperty("ACTIVITY_NAME")
    @ApiModelProperty(value = "活动名称",position = -49)
    private String projectName;
    /**
     * 费用细类
     */
    @JsonProperty("ACTIVITY_CATEGORY")
    @ApiModelProperty(value = "费用细类",position = -48)
    private String feeDetailType;
    /**
     * 预算部门
     */
    @JsonProperty("ACTIVITY_SUBJECT")
    @ApiModelProperty(value = "预算部门",position = -47)
    private String dept;

    /**
     * 预算部门
     */
    @ApiModelProperty(value = "预算部门名称",position = -46)
    private String deptName;
    /**
     * 所在城市
     */
    @JsonProperty("ACTIVITY_CITY")
    @ApiModelProperty(value = "所在城市",position = -45)
    private String city;
    /**
     * 成本中心
     */
    @JsonProperty("ACTIVITY_COSTCENTER")
    @ApiModelProperty(value = "成本中心",position = -44)
    private String costCenter;
    /**
     * 项目（WBS）
     */
    @JsonProperty("ACTIVITY_WSB")
    @ApiModelProperty(value = "项目（WBS）",position = -43)
    private String wbs;
    /**
     * 渠道/内部订单
     */
    @JsonProperty("ACTIVITY_ORDER")
    @ApiModelProperty(value = "渠道/内部订单",position = -42)
    private String orderNo;
    /**
     * 促销费用总金额
     */
    @JsonProperty("ACTIVITY_ORIGINAL")
    @ApiModelProperty(value = "促销费用总金额",position = -39)
    private Double totalFee;
    /**
     * 预计零售量
     */
    @JsonProperty("ACTIVITY_PREDICT_NUM")
    @ApiModelProperty(value = "预计零售量",position = -38)
    private Integer projectedSalesNo;
    /**
     * 预计零售额
     */
    @JsonProperty("ACTIVITY_PREDICT_AMOUNT")
    @ApiModelProperty(value = "预计零售额",position = -37)
    private Double projectedSalesMoney;
    /**
     * 预计费用率
     */
    @JsonProperty("ACTIVITY_PREDICT_RATE")
    @ApiModelProperty(value = "预计费用率",position = -36)
    private Double estimatedCostRate;
    /**
     * 备注（申请人填写）
     */
    @JsonProperty("ACTIVITY_MEMO")
    @ApiModelProperty(value = "备注（申请人填写）",position = -35)
    private String note;
    
    /**
     * 供应商
     */
    @JsonProperty("ACTIVITY_VENDOR")
    @ApiModelProperty(value = "供应商",position = -34)
    private String vendorId;

    public String getProjectId(){
        return projectId;
    }

    public void setProjectId(String projectId){
        this.projectId = projectId;
    }

    public String getProjectName(){
        return projectName;
    }

    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    public String getFeeDetailType(){
        return feeDetailType;
    }

    public void setFeeDetailType(String feeDetailType){
        this.feeDetailType = feeDetailType;
    }

    public String getDept(){
        return dept;
    }

    public void setDept(String dept){
        this.dept = dept;
    }

    public String getDeptName(){
        return deptName;
    }

    public void setDeptName(String deptName){
        this.deptName = deptName;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getCostCenter(){
        return costCenter;
    }

    public void setCostCenter(String costCenter){
        this.costCenter = costCenter;
    }

    public String getWbs(){
        return wbs;
    }

    public void setWbs(String wbs){
        this.wbs = wbs;
    }

    public String getOrderNo(){
        return orderNo;
    }

    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }

    public Double getTotalFee(){
        return totalFee;
    }

    public void setTotalFee(Double totalFee){
        this.totalFee = totalFee;
    }

    public Integer getProjectedSalesNo(){
        return projectedSalesNo;
    }

    public void setProjectedSalesNo(Integer projectedSalesNo){
        this.projectedSalesNo = projectedSalesNo;
    }

    public Double getProjectedSalesMoney(){
        return projectedSalesMoney;
    }

    public void setProjectedSalesMoney(Double projectedSalesMoney){
        this.projectedSalesMoney = projectedSalesMoney;
    }

    public Double getEstimatedCostRate(){
        return estimatedCostRate;
    }

    public void setEstimatedCostRate(Double estimatedCostRate){
        this.estimatedCostRate = estimatedCostRate;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note = note;
    }

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
    
    
}
