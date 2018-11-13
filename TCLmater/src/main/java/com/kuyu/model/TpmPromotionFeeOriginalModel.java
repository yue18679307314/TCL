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

import org.hibernate.validator.constraints.SafeHtml.Tag;

/**
 *
 *
 * @Author jt_L
 * @Date 2017-09-19
 * @Description 实体类:立项关联的临促费用表(子表项目1)
 */
@ApiModel("立项单临促费用(子表项目1)")
@TableName("tpm_promotion_fee_original")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TpmPromotionFeeOriginalModel extends Model<TpmPromotionFeeOriginalModel> {

	@TableId
	private String uuid;
    /**
     * 活动ID
     */
	@TableField("project_id")
	@ApiModelProperty(value = "活动ID",position = -50)
	private String projectId;
	/**
	 * 费用细类
	 */
	@TableField("fee_detail_type")
	@ApiModelProperty(value = "费用细类",position = -48)
	private String feeDetailType;
    /**
     * 门店/区域
     */
	@ApiModelProperty(value = "门店/区域",position = -47)
	private String district;
    /**
     * 现场负责人
     */
	@ApiModelProperty(value = "现场负责人",position = -46)
	private String manager;
    /**
     * 开始日期(YYYY-MM-DD HH(24)-mm-ss)
     */
	@TableField("start_time")
	@ApiModelProperty(value = "开始日期(YYYY-MM-DD",position = -45)
	private String startTime;
    /**
     * 结束日期(YYYY-MM-DD HH(24)-mm-ss)
     */
	@TableField("end_time")
	@ApiModelProperty(value = "结束日期(YYYY-MM-DD",position = -44)
	private String endTime;
    /**
     * 总天数
     */
	@TableField("total_day")
	@ApiModelProperty(value = "总天数",position = -43)
	private Integer totalDay;
    /**
     * 人数
     */
	@TableField("user_no")
	@ApiModelProperty(value = "人数",position = -42)
	private Integer userNo;
    /**
     * 工作时长
     */
	@TableField("work_hours")
	@ApiModelProperty(value = "工作时长",position = -41)
	private Integer workHours;
    /**
     * 人天单价
     */
	@TableField("unit_price")
	@ApiModelProperty(value = "人天单价",position = -40)
	private Double unitPrice;
    /**
     * 其它激励金额
     */
	@TableField("incentive_amount")
	@ApiModelProperty(value = "其它激励金额",position = -39)
	private Double incentiveAmount;
    /**
     * 结算方式
     */
	@TableField("settlement_method")
	@ApiModelProperty(value = "结算方式",position = -37)
	private String settlementMethod;
    /**
     * 结算周期
     */
	@TableField("settlement_cycle")
	@ApiModelProperty(value = "结算周期",position = -36)
	private String settlementCycle;
    /**
     * 临促费用总金额
     */
	@TableField("total_fee")
	@ApiModelProperty(value = "临促费用总金额",position = -35)
	private Double totalFee;

	@TableField(exist = false)
	@ApiModelProperty(value = "活动名称",position = -34)
	private String projectName;
	
	@TableField(exist = false)
	@ApiModelProperty(value = "现场",position = -34)
	private String managerName;
	
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
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

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getTotalDay() {
		return totalDay;
	}

	public void setTotalDay(Integer totalDay) {
		this.totalDay = totalDay;
	}

	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}

	public Integer getWorkHours() {
		return workHours;
	}

	public void setWorkHours(Integer workHours) {
		this.workHours = workHours;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getIncentiveAmount() {
		return incentiveAmount;
	}

	public void setIncentiveAmount(Double incentiveAmount) {
		this.incentiveAmount = incentiveAmount;
	}

	public String getSettlementMethod() {
		return settlementMethod;
	}

	public void setSettlementMethod(String settlementMethod) {
		this.settlementMethod = settlementMethod;
	}

	public String getSettlementCycle() {
		return settlementCycle;
	}

	public void setSettlementCycle(String settlementCycle) {
		this.settlementCycle = settlementCycle;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public String getFeeDetailType(){
		return feeDetailType;
	}

	public void setFeeDetailType(String feeDetailType){
		this.feeDetailType = feeDetailType;
	}

	public String getUuid(){
		return uuid;
	}

	public void setUuid(String uuid){
		this.uuid = uuid;
	}

	/**
	 * 主键值
	 */
	@Override
	protected Serializable pkVal() {
		return this.uuid;
	}
}
