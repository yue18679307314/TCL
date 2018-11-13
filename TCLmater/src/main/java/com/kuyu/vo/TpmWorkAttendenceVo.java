package com.kuyu.vo;

import java.util.ArrayList;
import java.util.List;

import com.kuyu.model.TpmWorkAttendenceDetailModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("打卡记录")
public class TpmWorkAttendenceVo {

	@ApiModelProperty("活动日期")
	private String activityTime;

	@ApiModelProperty("签到时间")
	private String workStartTime;

	@ApiModelProperty("签退时间")
	private String workEndTime;

	@ApiModelProperty("签到地址")
	private String workStartAddress;

	@ApiModelProperty("签退地址")
	private String workEndAddress;

	@ApiModelProperty("签到图片url")
	private String workStartUrl;

	@ApiModelProperty("签退图片url")
	private String workEndUrl;

	@ApiModelProperty("工作时长")
	private Integer workHours;

	@ApiModelProperty("工作分钟")
	private Integer workMinutes;
	
	@ApiModelProperty("计划工作时长")
	private Integer planWorkHours;

	public Integer getPlanWorkHours() {
		return planWorkHours;
	}

	public void setPlanWorkHours(Integer planWorkHours) {
		this.planWorkHours = planWorkHours;
	}

	public Integer getWorkMinutes() {
		return workMinutes;
	}

	public void setWorkMinutes(Integer workMinutes) {
		this.workMinutes = workMinutes;
	}

	// @ApiModelProperty("签到记录列表")
	// private List<TpmWorkAttendenceDetailModel> detailList = new ArrayList<>();
	public Integer getWorkHours() {
		return workHours;
	}

	public void setWorkHours(Integer workHours) {
		this.workHours = workHours;
	}

	public String getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}

	public String getWorkStartTime() {
		return workStartTime;
	}

	public void setWorkStartTime(String workStartTime) {
		this.workStartTime = workStartTime;
	}

	public String getWorkEndTime() {
		return workEndTime;
	}

	public void setWorkEndTime(String workEndTime) {
		this.workEndTime = workEndTime;
	}

	public String getWorkStartAddress() {
		return workStartAddress;
	}

	public void setWorkStartAddress(String workStartAddress) {
		this.workStartAddress = workStartAddress;
	}

	public String getWorkEndAddress() {
		return workEndAddress;
	}

	public void setWorkEndAddress(String workEndAddress) {
		this.workEndAddress = workEndAddress;
	}

	public String getWorkStartUrl() {
		return workStartUrl;
	}

	public void setWorkStartUrl(String workStartUrl) {
		this.workStartUrl = workStartUrl;
	}

	public String getWorkEndUrl() {
		return workEndUrl;
	}

	public void setWorkEndUrl(String workEndUrl) {
		this.workEndUrl = workEndUrl;
	}

}
