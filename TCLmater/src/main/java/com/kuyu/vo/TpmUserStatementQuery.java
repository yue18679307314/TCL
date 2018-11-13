package com.kuyu.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class TpmUserStatementQuery implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* 活动id */
	@ApiModelProperty("活动id")
	private String activity_uuid;

	/* 参加日期:YYYY-MM-DD */
	@ApiModelProperty("参加日期:YYYY-MM-DD")
	private String activity_time;

	@ApiModelProperty("临促openid")
	private String openid;

	@ApiModelProperty("参加的状态:0未通过；1 已生成结算单； 2 现场负责人已确认申请单； 4 财务审核通过；5 财务审核不通过")
	private Integer request_state;

	@ApiModelProperty("原因")
	private String reason;
	@ApiModelProperty("调整工时报酬")
	private Double adjust_money;

	@ApiModelProperty("调整工时报酬事由")
	private String adjust_money_reason;

	@ApiModelProperty(hidden = true,value = "实际报酬,计算得到，不需要传入")
	private Double real_salary;
	
	@ApiModelProperty("奖励或者扣除，true奖励，false扣除")
	private Boolean isIncentiveOrReduce;

	@ApiModelProperty("有效工作时长")
    private Integer valid_work_hours;
	
	@ApiModelProperty("扣减工时原因")
	private String reduce_hours_reason;
	
	@ApiModelProperty("修改工时状态:1通过, 2不通过")
	private Integer hours_state;
	
	@ApiModelProperty("分钟")
	private Integer work_minute;
	
	public Integer getWork_minute() {
		return work_minute;
	}

	public void setWork_minute(Integer work_minute) {
		this.work_minute = work_minute;
	}

	public Integer getValid_work_hours() {
		return valid_work_hours;
	}

	public void setValid_work_hours(Integer valid_work_hours) {
		this.valid_work_hours = valid_work_hours;
	}

	public String getReduce_hours_reason() {
		return reduce_hours_reason;
	}

	public void setReduce_hours_reason(String reduce_hours_reason) {
		this.reduce_hours_reason = reduce_hours_reason;
	}

	public Integer getHours_state() {
		return hours_state;
	}

	public void setHours_state(Integer hours_state) {
		this.hours_state = hours_state;
	}

	public String getActivity_uuid() {
		return activity_uuid;
	}

	public void setActivity_uuid(String activity_uuid) {
		this.activity_uuid = activity_uuid;
	}

	public String getActivity_time() {
		return activity_time;
	}

	public void setActivity_time(String activity_time) {
		this.activity_time = activity_time;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getRequest_state() {
		return request_state;
	}

	public void setRequest_state(Integer request_state) {
		this.request_state = request_state;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public Double getReal_salary() {
		return real_salary;
	}

	public void setReal_salary(Double real_salary) {
		this.real_salary = real_salary;
	}

	public Boolean getIsIncentiveOrReduce() {
		return isIncentiveOrReduce;
	}

	public void setIsIncentiveOrReduce(Boolean isIncentiveOrReduce) {
		this.isIncentiveOrReduce = isIncentiveOrReduce;
	} 


}
