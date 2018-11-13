package com.kuyu.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 *
 *
 * @Author jt_L
 * @Date 2017-09-19
 * @Description 实体类:立项关联的其它费用表(子表项目2)
 */
@ApiModel("立项活动其它费用(子表项目2)")
@TableName("tpm_other_fee_original")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TpmOtherFeeOriginalModel extends Model<TpmOtherFeeOriginalModel> {

    /**
     * 活动ID
     */
	@TableField("project_id")
	@ApiModelProperty(value = "活动ID",position = -50)
	private String projectId;
    /**
     * 费用明细
     */
	@TableField("fee_detail_type")
	@ApiModelProperty(value = "费用明细",position = -49)
	private String feeDetailType;
	/**
	 * 物料类别
	 */
	@TableField("material_category")
	@ApiModelProperty(value = "物料类别",position = -48)
	private String materialCategory;
    /**
     * 规格
     */
	@ApiModelProperty(value = "规格",position = -46)
	private String specifications;
    /**
     * 单位
     */
	@ApiModelProperty(value = "单位",position = -45)
	private String unit;
    /**
     * 数量
     */
	@ApiModelProperty(value = "数量",position = -44)
	private Double amount;
    /**
     * 单价
     */
	@TableField("unit_price")
	@ApiModelProperty(value = "单价",position = -43)
	private Double unitPrice;
    /**
     * 其它促销费用总金额
     */
	@TableField("other_total_fee")
	@ApiModelProperty(value = "其它促销费用总金额",position = -42)
	private Double otherTotalFee;
	
	@TableField("activity_vendor")
	@ApiModelProperty(value = "供应商",position = -40)
	private String activityVendor;
	
	@TableField(exist = false)
	@ApiModelProperty(value = "项目名",position = -41)
	private String projectName;


	public String getActivityVendor() {
		return activityVendor;
	}

	public void setActivityVendor(String activityVendor) {
		this.activityVendor = activityVendor;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getFeeDetailType() {
		return feeDetailType;
	}

	public void setFeeDetailType(String feeDetailType) {
		this.feeDetailType = feeDetailType;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getOtherTotalFee() {
		return otherTotalFee;
	}

	public void setOtherTotalFee(Double otherTotalFee) {
		this.otherTotalFee = otherTotalFee;
	}

	public String getMaterialCategory() {
		return materialCategory;
	}

	public void setMaterialCategory(String materialCategory) {
		this.materialCategory = materialCategory;
	}

	/**
	 * 主键值
	 */
	@Override
	protected Serializable pkVal() {
		return null;
	}
}
