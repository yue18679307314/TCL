package com.kuyu.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel("PC结算审核查询类")
public class SettlementManagementCheckQuery {
	@ApiModelProperty("结算单uuid列表")
	private List<String> uuidList = new ArrayList<>();
	@ApiModelProperty("调整工资备注")
	private String adjust_money_note;
	@ApiModelProperty("财务审核备注")
	private String reason;
	@ApiModelProperty("审核状态：0未通过审核(现场负责人审核) 1 已生成结算单； 2 现场负责人已确认申请单； 4 财务审核通过；5 财务审核不通过")
	private String checkStatus;
	@ApiModelProperty("审核类型：true财务审核，false现场负责人审核")
	private Boolean isFinacial;
	@ApiModelProperty("实际工资")
	private Double real_salary;
	
	@ApiModelProperty("调整报酬")
	private Double adjust_money;

	@ApiModelProperty("调整工资原因")
	private String adjust_money_reason;

	@ApiModelProperty("奖励或者扣除，1奖励，0扣除")
	private String isIncentiveOrReduce;
	
	@ApiModelProperty("是否是单个审核，1是，其他是批量")
	private Integer singleCheck;
	
	@ApiModelProperty("财务审核时间")
	private String checkTime;
	
	@ApiModelProperty("结算审核时间时间")
	private String statement_check_time;
	
	
	public String getStatement_check_time() {
		return statement_check_time;
	}
	public void setStatement_check_time(String statement_check_time) {
		this.statement_check_time = statement_check_time;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public Integer getSingleCheck() {
		return singleCheck;
	}
	public void setSingleCheck(Integer singleCheck) {
		this.singleCheck = singleCheck;
	}
	public Double getAdjust_money() {
		return adjust_money;
	}
	public void setAdjust_money(Double adjust_money) {
		this.adjust_money = adjust_money;
	}
	public String getAdjust_money_reason() {
		return adjust_money_reason;
	}
	public void setAdjust_money_reason(String adjust_money_reason) {
		this.adjust_money_reason = adjust_money_reason;
	}
	public String getIsIncentiveOrReduce() {
		return isIncentiveOrReduce;
	}
	public void setIsIncentiveOrReduce(String isIncentiveOrReduce) {
		this.isIncentiveOrReduce = isIncentiveOrReduce;
	}
	
	
	public Double getReal_salary() {
		return real_salary;
	}
	public void setReal_salary(Double real_salary) {
		this.real_salary = real_salary;
	}
	public Boolean getIsFinacial() {
		return isFinacial;
	}
	public void setIsFinacial(Boolean isFinacial) {
		this.isFinacial = isFinacial;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public List<String> getUuidList() {
		return uuidList;
	}
	public void setUuidList(List<String> uuidList) {
		this.uuidList = uuidList;
	}
	
	public String getAdjust_money_note() {
		return adjust_money_note;
	}
	public void setAdjust_money_note(String adjust_money_note) {
		this.adjust_money_note = adjust_money_note;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	
}
