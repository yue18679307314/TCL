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

/**
 *
 *
 * @Author jt_L
 * @Date 2017-09-19
 * @Description 实体类:立项关联的活动元数据
 */
@ApiModel("立项单活动元数据")
@TableName("tpm_activity_original")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TpmActivityOriginalModel extends Model<TpmActivityOriginalModel> {

    /**
     * 活动id
     */
	@TableId("project_id")
	@ApiModelProperty(value = "活动id",position = -50)
	private String projectId;
	
	@ApiModelProperty(value = "申请单id",position = -51)
	private String request_id;
	
    /**
     * 活动名称
     */
	@TableField("project_name")
	@ApiModelProperty(value = "活动名称",position = -49)
	private String projectName;
	/**
	 * 费用细类
	 */
	@TableField("fee_detail_type")
	@ApiModelProperty(value = "费用细类",position = -48)
	private String feeDetailType;
    /**
     * 预算部门
     */
	@ApiModelProperty(value = "预算部门",position = -47)
	private String dept;

	/**
	 * 预算部门
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "预算部门名称",position = -46)
	private String deptName;
	/**
	 * 所在城市
	 */
	@ApiModelProperty(value = "所在城市",position = -45)
	private String city;
    /**
     * 成本中心
     */
	@TableField("cost_center")
	@ApiModelProperty(value = "成本中心",position = -44)
	private String costCenter;
    /**
     * 项目（WBS）
     */
	@ApiModelProperty(value = "项目（WBS）",position = -43)
	private String wbs;
	/**
	 * 渠道/内部订单
	 */
	@TableField("order_no")
	@ApiModelProperty(value = "渠道/内部订单",position = -42)
	private String orderNo;
    /**
     * 促销费用总金额
     */
	@TableField("total_fee")
	@ApiModelProperty(value = "促销费用总金额",position = -39)
	private Double totalFee;
    /**
     * 预计零售量
     */
	@TableField("projected_sales_no")
	@ApiModelProperty(value = "预计零售量",position = -38)
	private Integer projectedSalesNo;
    /**
     * 预计零售额
     */
	@TableField("projected_sales_money")
	@ApiModelProperty(value = "预计零售额",position = -37)
	private Double projectedSalesMoney;
    /**
     * 预计费用率
     */
	@TableField("estimated_cost_rate")
	@ApiModelProperty(value = "预计费用率",position = -36)
	private Double estimatedCostRate;
    /**
     * 备注（申请人填写）
     */
	@ApiModelProperty(value = "备注（申请人填写）",position = -35)
	private String note;

	/**
     * 供应商id
     */
	@TableField("vendor_id")
	@ApiModelProperty(value = "供应商id",position = -34)
	private String vendorId;

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
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

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public Integer getProjectedSalesNo() {
		return projectedSalesNo;
	}

	public void setProjectedSalesNo(Integer projectedSalesNo) {
		this.projectedSalesNo = projectedSalesNo;
	}

	public Double getProjectedSalesMoney() {
		return projectedSalesMoney;
	}

	public void setProjectedSalesMoney(Double projectedSalesMoney) {
		this.projectedSalesMoney = projectedSalesMoney;
	}

	public Double getEstimatedCostRate() {
		return estimatedCostRate;
	}

	public void setEstimatedCostRate(Double estimatedCostRate) {
		this.estimatedCostRate = estimatedCostRate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCity(){
		return city;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getOrderNo(){
		return orderNo;
	}

	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}

	public String getFeeDetailType(){
		return feeDetailType;
	}

	public void setFeeDetailType(String feeDetailType){
		this.feeDetailType = feeDetailType;
	}

	public String getDeptName(){
		return deptName;
	}

	public void setDeptName(String deptName){
		this.deptName = deptName;
	}

	/**
	 * 主键值
	 */
	@Override
	protected Serializable pkVal() {
		return this.projectId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	
}
