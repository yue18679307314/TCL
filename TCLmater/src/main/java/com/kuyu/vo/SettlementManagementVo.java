package com.kuyu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("PC结算单返回类")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SettlementManagementVo {
	@ApiModelProperty("结算单id")
	private String uuid;
	@ApiModelProperty("活动id")
	private String activity_uuid;
	@ApiModelProperty("活动时间")
	private String activity_time;
	@ApiModelProperty("实际工作时间")
	private String valid_work_hours;
	@ApiModelProperty("实际工资")
	private String real_salary;
	@ApiModelProperty("激励")
	private String incentive_amount;
	@ApiModelProperty("激励原因")
	private String incentive_reason;
	@ApiModelProperty("审核状态：0未通过审核(现场负责人审核) 1 已生成结算单； 2 现场负责人已确认申请单； 4 财务审核通过；5 财务审核不通过")
	private String request_state;
	@ApiModelProperty("付款状态：1 申请付款中；0失败；2 付款成功；")
	private String pay_state;
	@ApiModelProperty("临促姓名")
	private String name;
	@ApiModelProperty("活动名称")
	private String activity_name;
	@ApiModelProperty("预算部门")
	private String dept;
	@ApiModelProperty("活动现场负责人编号")
	private String manager;
	@ApiModelProperty("活动现场负责人姓名")
	private String managerName;
	@ApiModelProperty("预计结算日期")
	private String settlement_cycle;
	@ApiModelProperty("预计工资")
	private String unit_price;
	@ApiModelProperty("现场活动负责人审核工资备注")
	private String adjust_money_note;
	@ApiModelProperty("财务人员审核备注")
	private String reason;
	@ApiModelProperty("是否是活动负责人")
	private Boolean isManager = false;
	@ApiModelProperty("是否是财务")
	private Boolean isFinancial = false;
	@ApiModelProperty("工作小时")
	private Integer work_hours;
	@ApiModelProperty("工作分钟")
	private Integer work_minute;
	@ApiModelProperty("有效工作分钟")
	private Integer valid_work_minute;
	@ApiModelProperty("扣减工资")
	private Double reduce_money;
	@ApiModelProperty("扣减工资的原因")
	private String reduce_money_reason;
	@ApiModelProperty("调整工时原因")
	private String reduce_hours_reason;
	@ApiModelProperty("调整工时备注")
	private String adjust_hours_note;
	@ApiModelProperty("结算方式")
	private String settlementMethod;
	@ApiModelProperty("财务人员编号")
	private String financialPersonCode;
	@ApiModelProperty("财务人员姓名")
	private String financialPersonName;
	@ApiModelProperty("预算部门编号")
	private String orgCode;
	@ApiModelProperty("计划工作时间")
	private String planWorkHour;
	@ApiModelProperty("财务审核时间")
	private String financialCheckTime;
	@ApiModelProperty("生成考勤表人员")
	private String attendencePersonName;
	@ApiModelProperty("考勤表生成时间")
	private String attendenceTime;
	@ApiModelProperty("考勤审核时间")
	private String checkTime;
	@ApiModelProperty("结算审核时间")
	private String statementCheckTime;
	@ApiModelProperty("共享付款单流水号")
	private String paymentId;
	@ApiModelProperty("立项单号")
	private String requestId;
	@ApiModelProperty("借款单表id")
	private String loadBillUuid;
	@ApiModelProperty("借款单号")
	private String fsscLoanBillNo;
	@ApiModelProperty(hidden = true,value = "生成结算单表序号")
	private String no;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getFsscLoanBillNo() {
		return fsscLoanBillNo;
	}
	public void setFsscLoanBillNo(String fsscLoanBillNo) {
		this.fsscLoanBillNo = fsscLoanBillNo;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
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
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public String getStatementCheckTime() {
		return statementCheckTime;
	}
	public void setStatementCheckTime(String statementCheckTime) {
		this.statementCheckTime = statementCheckTime;
	}
	public String getAttendencePersonName() {
		return attendencePersonName;
	}
	public void setAttendencePersonName(String attendencePersonName) {
		this.attendencePersonName = attendencePersonName;
	}
	public String getAttendenceTime() {
		return attendenceTime;
	}
	public void setAttendenceTime(String attendenceTime) {
		this.attendenceTime = attendenceTime;
	}
	public String getPlanWorkHour() {
		return planWorkHour;
	}
	public void setPlanWorkHour(String planWorkHour) {
		this.planWorkHour = planWorkHour;
	}
	public String getFinancialCheckTime() {
		return financialCheckTime;
	}
	public void setFinancialCheckTime(String financialCheckTime) {
		this.financialCheckTime = financialCheckTime;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getFinancialPersonCode() {
		return financialPersonCode;
	}
	public void setFinancialPersonCode(String financialPersonCode) {
		this.financialPersonCode = financialPersonCode;
	}
	public String getFinancialPersonName() {
		return financialPersonName;
	}
	public void setFinancialPersonName(String financialPersonName) {
		this.financialPersonName = financialPersonName;
	}
	public String getSettlementMethod() {
		return settlementMethod;
	}
	public void setSettlementMethod(String settlementMethod) {
		this.settlementMethod = settlementMethod;
	}
	public String getActivity_uuid() {
		return activity_uuid;
	}
	public void setActivity_uuid(String activity_uuid) {
		this.activity_uuid = activity_uuid;
	}
	public String getAdjust_hours_note() {
		return adjust_hours_note;
	}
	public void setAdjust_hours_note(String adjust_hours_note) {
		this.adjust_hours_note = adjust_hours_note;
	}
	public String getReduce_hours_reason() {
		return reduce_hours_reason;
	}
	public void setReduce_hours_reason(String reduce_hours_reason) {
		this.reduce_hours_reason = reduce_hours_reason;
	}
	public Integer getWork_hours() {
		return work_hours;
	}
	public void setWork_hours(Integer work_hours) {
		this.work_hours = work_hours;
	}
	public Double getReduce_money() {
		return reduce_money;
	}
	public void setReduce_money(Double reduce_money) {
		this.reduce_money = reduce_money;
	}
	public String getReduce_money_reason() {
		return reduce_money_reason;
	}
	public void setReduce_money_reason(String reduce_money_reason) {
		this.reduce_money_reason = reduce_money_reason;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public Integer getValid_work_minute() {
		return valid_work_minute;
	}
	public void setValid_work_minute(Integer valid_work_minute) {
		this.valid_work_minute = valid_work_minute;
	}
	public Integer getWork_minute() {
		return work_minute;
	}
	public void setWork_minute(Integer work_minute) {
		this.work_minute = work_minute;
	}
	public Boolean getIsFinancial() {
		return isFinancial;
	}
	public void setIsFinancial(Boolean isFinancial) {
		this.isFinancial = isFinancial;
	}
	public Boolean getIsManager() {
		return isManager;
	}
	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}
	public String getAdjust_money_note() {
		return adjust_money_note;
	}
	public void setAdjust_money_note(String adjust_money_note) {
		this.adjust_money_note = adjust_money_note;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getActivity_time() {
		return activity_time;
	}
	public void setActivity_time(String activity_time) {
		this.activity_time = activity_time;
	}
	public String getValid_work_hours() {
		return valid_work_hours;
	}
	public void setValid_work_hours(String valid_work_hours) {
		this.valid_work_hours = valid_work_hours;
	}
	public String getReal_salary() {
		return real_salary;
	}
	public void setReal_salary(String real_salary) {
		this.real_salary = real_salary;
	}
	public String getIncentive_amount() {
		return incentive_amount;
	}
	public void setIncentive_amount(String incentive_amount) {
		this.incentive_amount = incentive_amount;
	}
	public String getIncentive_reason() {
		return incentive_reason;
	}
	public void setIncentive_reason(String incentive_reason) {
		this.incentive_reason = incentive_reason;
	}
	public String getRequest_state() {
		return request_state;
	}
	public void setRequest_state(String request_state) {
		this.request_state = request_state;
	}
	public String getPay_state() {
		return pay_state;
	}
	public void setPay_state(String pay_state) {
		this.pay_state = pay_state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getActivity_name() {
		return activity_name;
	}
	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getSettlement_cycle() {
		return settlement_cycle;
	}
	public void setSettlement_cycle(String settlement_cycle) {
		this.settlement_cycle = settlement_cycle;
	}
	public String getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}
	
	
}
