package com.kuyu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("报销管理查询返回类")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RepaymentManagementReturnVo {
	@ApiModelProperty("序号")
	private String id;
	
	@ApiModelProperty("借款单号")
	private String loadBillUuid;
	
	@ApiModelProperty("立项单号")
	private String requestId;
	
	@ApiModelProperty("费用立项人")
	private String requestUser;
	
	@ApiModelProperty("结算方式")
	private String settlementMethod;
	
	@ApiModelProperty("考勤表单号")
	private String attendenceUuid;
	
	@ApiModelProperty("生成人员")
	private String operPersonName;
	
	@ApiModelProperty("生成时间")	
	private String createTime;
	
	@ApiModelProperty("活动名称")	
	private String activityName;
	
	@ApiModelProperty("临促姓名")	
	private String openName;
	
	@ApiModelProperty("支付账户名")
	private String accountName;
	
	@ApiModelProperty("联系电话") 
	private String mobile;
	
	@ApiModelProperty("考勤日期")
	private String activityTime;
	
	@ApiModelProperty("标准工时")
	private Integer workHours;
	
	@ApiModelProperty("实际工时")
	private String validWorkTime; 
	
	@ApiModelProperty("标准日薪")
	private Double unitPrice;
	
	@ApiModelProperty("实际日薪")
	private Double validPrice;
	
	@ApiModelProperty("奖金")
	private Double adjustMoney;
	
	@ApiModelProperty("结算总费用")
	private Double realSalary;
	
	@ApiModelProperty("奖励")
	private Double incentiveAmount;
	
	@ApiModelProperty("扣除")
	private Double reduceMoney;
	
	@ApiModelProperty("实际工作小时")
	private Integer validWorkHours;
	
	@ApiModelProperty("实际工作分钟")
	private Integer validWorkMinute;
	
	@ApiModelProperty("活动负责人编号")
	private String manager;
	
	@ApiModelProperty("活动负责人姓名")
	private String managerName;
	
	@ApiModelProperty("是否是活动负责人")
	private Boolean isManager = false;
	
	@ApiModelProperty("是否已导出到考勤表")
	private Boolean isAttendence = false;
	
	@ApiModelProperty("结算单单号")
	private String tusUuid;
	
	@ApiModelProperty("文件路径")
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAttendenceUuid() {
		return attendenceUuid;
	}

	public void setAttendenceUuid(String attendenceUuid) {
		this.attendenceUuid = attendenceUuid;
	}

	public Boolean getIsAttendence() {
		return isAttendence;
	}

	public void setIsAttendence(Boolean isAttendence) {
		this.isAttendence = isAttendence;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	public Integer getValidWorkHours() {
		return validWorkHours;
	}

	public void setValidWorkHours(Integer validWorkHours) {
		this.validWorkHours = validWorkHours;
	}

	public Integer getValidWorkMinute() {
		return validWorkMinute;
	}

	public void setValidWorkMinute(Integer validWorkMinute) {
		this.validWorkMinute = validWorkMinute;
	}

	public Double getIncentiveAmount() {
		return incentiveAmount;
	}

	public void setIncentiveAmount(Double incentiveAmount) {
		this.incentiveAmount = incentiveAmount;
	}

	public Double getReduceMoney() {
		return reduceMoney;
	}

	public void setReduceMoney(Double reduceMoney) {
		this.reduceMoney = reduceMoney;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoadBillUuid() {
		return loadBillUuid;
	}

	public void setLoadBillUuid(String loadBillUuid) {
		this.loadBillUuid = loadBillUuid;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(String requestUser) {
		this.requestUser = requestUser;
	}

	public String getSettlementMethod() {
		return settlementMethod;
	}

	public void setSettlementMethod(String settlementMethod) {
		this.settlementMethod = settlementMethod;
	}

	public String getTusUuid() {
		return tusUuid;
	}

	public void setTusUuid(String tusUuid) {
		this.tusUuid = tusUuid;
	}

	public String getOperPersonName() {
		return operPersonName;
	}

	public void setOperPersonName(String operPersonName) {
		this.operPersonName = operPersonName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getOpenName() {
		return openName;
	}

	public void setOpenName(String openName) {
		this.openName = openName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}

	public Integer getWorkHours() {
		return workHours;
	}

	public void setWorkHours(Integer workHours) {
		this.workHours = workHours;
	}

	public String getValidWorkTime() {
		return validWorkTime;
	}

	public void setValidWorkTime(String validWorkTime) {
		this.validWorkTime = validWorkTime;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getValidPrice() {
		return validPrice;
	}

	public void setValidPrice(Double validPrice) {
		this.validPrice = validPrice;
	}

	public Double getAdjustMoney() {
		return adjustMoney;
	}

	public void setAdjustMoney(Double adjustMoney) {
		this.adjustMoney = adjustMoney;
	}

	public Double getRealSalary() {
		return realSalary;
	}

	public void setRealSalary(Double realSalary) {
		this.realSalary = realSalary;
	}

	
}
