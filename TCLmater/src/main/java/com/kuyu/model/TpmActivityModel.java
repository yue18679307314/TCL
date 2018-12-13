package com.kuyu.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * @Description 实体类:活动列表
 */
@ApiModel("活动列表")
@TableName("tpm_activity")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TpmActivityModel extends Model<TpmActivityModel> {

    /**
     * 活动唯一标示
     */
    @TableId("activity_uuid")
	@ApiModelProperty(value = "活动唯一标示",position = -50)
	private String activityUuid;
	/**
	 * 立项申请单编号
	 * TpmProjectModel
	 */
	@TableField("activity_name")
	@ApiModelProperty(value = "活动名称",position = -50)
	private String projectName;
    /**
     * 立项申请单编号
	 * TpmProjectModel
     */
	@TableField("request_id")
	@ApiModelProperty(value = "立项申请单编号",position = -49)
	private String requestId;
    /**
     * 立项申请单活动id
	 * TpmActivityOriginalModel
     */
	@TableField("project_id")
	@ApiModelProperty(value = "立项申请单活动id",position = -48)
	private String projectId;
	/**
     * 供应商id
	 * TpmActivityOriginalModel
     */
	@TableField("vendor_id")
	@ApiModelProperty(value = "供应商id",position = -48)
	private String vendorId;
    /**
     * 申请日期
	 * TpmProjectModel
     */
	@TableField("request_create_time")
	@ApiModelProperty(value = "申请日期",position = -46)
	private String requestCreateTime;
    /**
     * 预算部门
	 * TpmActivityOriginalModel
     */
	@ApiModelProperty(value = "预算部门",position = -45)
	private String dept;
    /**
     * 成本中心
	 * TpmActivityOriginalModel
     */
	@TableField("cost_center")
	@ApiModelProperty(value = "成本中心",position = -44)
	private String costCenter;
    /**
     * 项目（WBS）
	 * TpmActivityOriginalModel
     */
	@ApiModelProperty(value = "项目（WBS）",position = -43)
	private String wbs;
    /**
     * 所在城市
	 * TpmPromotionFeeOriginalModel
     */
	@ApiModelProperty(value = "所在城市",position = -42)
	private String city;
    /**
     * 门店/区域
	 * TpmPromotionFeeOriginalModel
     */
	@ApiModelProperty(value = "门店/区域",position = -41)
	private String district;
    /**
     * 现场负责人
	 * TpmPromotionFeeOriginalModel
     */
	@ApiModelProperty(value = "现场负责人",position = -40)
	private String manager;
    /**
     * 开始日期(YYYY-MM-DD)
	 * TpmPromotionFeeOriginalModel
     */
	@TableField("start_time")
	@ApiModelProperty(value = "开始日期",position = -39)
	private String startTime;
    /**
     * 结束日期(YYYY-MM-DD)
	 * TpmPromotionFeeOriginalModel
     */
	@TableField("end_time")
	@ApiModelProperty(value = "结束日期",position = -38)
	private String endTime;
    /**
     * 总天数
	 * TpmPromotionFeeOriginalModel
     */
	@TableField("total_day")
	@ApiModelProperty(value = "总天数",position = -37)
	private Integer totalDay;
    /**
     * 人数
	 * TpmPromotionFeeOriginalModel
     */
	@TableField("user_no")
	@ApiModelProperty(value = "人数",position = -36)
	private Integer userNo;
    /**
     * 工作时长
	 * TpmPromotionFeeOriginalModel
     */
	@TableField("work_hours")
	@ApiModelProperty(value = "工作时长",position = -35)
	private Integer workHours;
    /**
     * 人天单价
	 * TpmPromotionFeeOriginalModel
     */
	@TableField("unit_price")
	@ApiModelProperty(value = "人天单价",position = -34)
	private Double unitPrice;
    /**
     * 其它激励金额
	 * TpmPromotionFeeOriginalModel
     */
	@TableField("incentive_amount")
	@ApiModelProperty(value = "其它激励金额",position = -33)
	private Double incentiveAmount;
    /**
     * 结算方式
	 * TpmPromotionFeeOriginalModel
     */
	@TableField("settlement_method")
	@ApiModelProperty(value = "结算方式",position = -31)
	private String settlementMethod;
    /**
     * 结算周期
	 * TpmPromotionFeeOriginalModel
     */
	@TableField("settlement_cycle")
	@ApiModelProperty(value = "结算周期",position = -30)
	private String settlementCycle;
    /**
     * 费用总金额
	 * TpmPromotionFeeOriginalModel
     */
	@TableField("total_fee")
	@ApiModelProperty(value = "费用总金额",position = -29)
	private Double totalFee;
    /**
     * 生成时间(YYYY-MM-DD HH:mm:ss)
	 * TpmPromotionFeeOriginalModel start_time
     */
	@TableField("create_time")
	@ApiModelProperty(value = "生成时间",position = -28)
	private String createTime;
	
	@TableField(exist = false)
	@ApiModelProperty(value = "预算部门名称",position = -27)
	private String deptName;


	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getActivityUuid() {
		return activityUuid;
	}

	public void setActivityUuid(String activityUuid) {
		this.activityUuid = activityUuid;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getRequestCreateTime() {
		return requestCreateTime;
	}

	public void setRequestCreateTime(String requestCreateTime) {
		this.requestCreateTime = requestCreateTime;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * 主键值
	 */
	@Override
	protected Serializable pkVal() {
		return this.activityUuid;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	
	
	
}


