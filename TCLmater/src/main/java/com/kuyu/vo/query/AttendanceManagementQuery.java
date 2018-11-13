package com.kuyu.vo.query;

import java.util.List;

import com.kuyu.common.BasePageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("PC考勤管理查询类")
public class AttendanceManagementQuery extends BasePageQuery{
	@ApiModelProperty("姓名")
	private String name;
	@ApiModelProperty("活动名称")
	private String activityName;
	@ApiModelProperty("考勤日期")
	private String attendanceTime;
	@ApiModelProperty("预算部门")
	private String dept;
	@ApiModelProperty("考勤状态:1:正常，2：异常")
	private String attendanceStatus;
	@ApiModelProperty("活动负责人")
	private String activityManager;
	@ApiModelProperty("审核状态:修改工时的状态:0未审核； 1通过； 2不通过")
	private String checkStatus;
	@ApiModelProperty(hidden = true,value = "员工编号")
	private String personCode;
	@ApiModelProperty(hidden = true,value ="类型，不需要传入")
	private Integer personType;
	@ApiModelProperty(hidden = true,value ="列表，不需要传入")
	private List<String> list;
	@ApiModelProperty("立项申请单号")
	private String requestId;
	@ApiModelProperty("是否下载全部：1全部，其他下载当前页")
	private String flag;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	@ApiModelProperty("待办事项查询：1")
	private String toDoState;
	public String getToDoState() {
		return toDoState;
	}
	public void setToDoState(String toDoState) {
		this.toDoState = toDoState;
	}
	public String getPersonCode() {
		return personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPersonType() {
		return personType;
	}
	public void setPersonType(Integer personType) {
		this.personType = personType;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getAttendanceTime() {
		return attendanceTime;
	}
	public void setAttendanceTime(String attendanceTime) {
		this.attendanceTime = attendanceTime;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getAttendanceStatus() {
		return attendanceStatus;
	}
	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	public String getActivityManager() {
		return activityManager;
	}
	public void setActivityManager(String activityManager) {
		this.activityManager = activityManager;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	
}
