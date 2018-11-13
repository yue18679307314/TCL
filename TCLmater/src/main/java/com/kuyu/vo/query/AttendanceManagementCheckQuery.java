package com.kuyu.vo.query;

import java.util.ArrayList;
import java.util.List;

import com.kuyu.util.DateUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("PC考勤审核查询类")
public class AttendanceManagementCheckQuery {
	
	@ApiModelProperty("结算单uuid列表")
	private List<String> uuidList = new ArrayList<>();
	@ApiModelProperty("调整时间备注")
	private String adjust_hours_note;
	@ApiModelProperty("审核状态：1通过，2不通过")
	private String checkStatus;
	@ApiModelProperty(hidden = true,value = "实际报酬,计算得到，不需要传入")
	private Double real_salary;
	@ApiModelProperty("有效工作时长")
    private Integer valid_work_hours;
	@ApiModelProperty(hidden = true,value = "审核时间,不需要传入")
	private String checkTime = DateUtils.currentTime();
	@ApiModelProperty(hidden = true,value = "活动负责人")
	private String manager;
	@ApiModelProperty("有效工作分钟")
	private Integer work_minute;
	@ApiModelProperty("调整工时原因")
	private String reduce_hours_reason;
	
	public String getReduce_hours_reason() {
		return reduce_hours_reason;
	}
	public void setReduce_hours_reason(String reduce_hours_reason) {
		this.reduce_hours_reason = reduce_hours_reason;
	}
	@ApiModelProperty("是否是单个审核，1是，其他是批量")
	private Integer singleCheck;
	
	public Integer getSingleCheck() {
		return singleCheck;
	}
	public void setSingleCheck(Integer singleCheck) {
		this.singleCheck = singleCheck;
	}
	public Integer getWork_minute() {
		return work_minute;
	}
	public void setWork_minute(Integer work_minute) {
		this.work_minute = work_minute;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public Double getReal_salary() {
		return real_salary;
	}
	public void setReal_salary(Double real_salary) {
		this.real_salary = real_salary;
	}
	public Integer getValid_work_hours() {
		return valid_work_hours;
	}
	public void setValid_work_hours(Integer valid_work_hours) {
		this.valid_work_hours = valid_work_hours;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public List<String> getUuidList() {
		return uuidList;
	}
	public String getAdjust_hours_note() {
		return adjust_hours_note;
	}
	public void setAdjust_hours_note(String adjust_hours_note) {
		this.adjust_hours_note = adjust_hours_note;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public void setUuidList(List<String> uuidList) {
		this.uuidList = uuidList;
	}
	
}
