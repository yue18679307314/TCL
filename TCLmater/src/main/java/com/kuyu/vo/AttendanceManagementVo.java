package com.kuyu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("PC考勤管理返回类")
public class AttendanceManagementVo {
	@ApiModelProperty("结算单uuid")
	private String uuid;
	@ApiModelProperty("活动uuid")
	private String activity_uuid;
	@ApiModelProperty("日期")
	private String activity_time;
	@ApiModelProperty("工作时间")
	private String work_hours;
	@ApiModelProperty("实际工作时间")
	private String valid_work_hours;
	@ApiModelProperty("工作开始时间")
	private String work_start_time;
	@ApiModelProperty("工作结束时间")
	private String work_end_time;
	@ApiModelProperty("调整工时状态")
	private String hours_state;
	@ApiModelProperty("审核时间")
	private String check_time;
	@ApiModelProperty("是否是该活动的负责人")
	private Boolean isManager = false;
	
	@ApiModelProperty("临促姓名")
	private String name;
	@ApiModelProperty("活动名称")
	private String activity_name;
	@ApiModelProperty("预算部门")
	private String dept;
	@ApiModelProperty("计划工作时间")
	private String planWorkHours;
	@ApiModelProperty("活动负责人姓名")
	private String person_name;
	@ApiModelProperty("活动负责人")
	private String manager;
	@ApiModelProperty("立项申请人")
	private String request_user;
	@ApiModelProperty("工作分钟")
	private Integer work_minute;
	
	@ApiModelProperty("实际工作分钟")
	private Integer valid_work_minute;
	
	@ApiModelProperty("临促编号")
	private String openid;
	
	@ApiModelProperty("签到图片")
	private String work_start_imgurl;

	@ApiModelProperty("签退图片")
	private String work_end_imgurl;

	@ApiModelProperty("签到地址")
	private String start_clock_address;
	@ApiModelProperty("签退地址")
	private String end_clock_address;
	@ApiModelProperty("立项申请单号")
	private String requestId;
	
	@ApiModelProperty(hidden = true, value = "生成结算管理表的序号")
	private String no;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getWork_start_imgurl() {
		return work_start_imgurl;
	}

	public void setWork_start_imgurl(String work_start_imgurl) {
		this.work_start_imgurl = work_start_imgurl;
	}

	public String getWork_end_imgurl() {
		return work_end_imgurl;
	}

	public void setWork_end_imgurl(String work_end_imgurl) {
		this.work_end_imgurl = work_end_imgurl;
	}

	public String getStart_clock_address() {
		return start_clock_address;
	}
	public void setStart_clock_address(String start_clock_address) {
		this.start_clock_address = start_clock_address;
	}
	public String getEnd_clock_address() {
		return end_clock_address;
	}
	public void setEnd_clock_address(String end_clock_address) {
		this.end_clock_address = end_clock_address;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getActivity_uuid() {
		return activity_uuid;
	}
	public void setActivity_uuid(String activity_uuid) {
		this.activity_uuid = activity_uuid;
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
	public Boolean getIsManager() {
		return isManager;
	}
	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getRequest_user() {
		return request_user;
	}
	public void setRequest_user(String request_user) {
		this.request_user = request_user;
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
	public String getWork_hours() {
		return work_hours;
	}
	public void setWork_hours(String work_hours) {
		this.work_hours = work_hours;
	}
	public String getValid_work_hours() {
		return valid_work_hours;
	}
	public void setValid_work_hours(String valid_work_hours) {
		this.valid_work_hours = valid_work_hours;
	}
	public String getWork_start_time() {
		return work_start_time;
	}
	public void setWork_start_time(String work_start_time) {
		this.work_start_time = work_start_time;
	}
	public String getWork_end_time() {
		return work_end_time;
	}
	public void setWork_end_time(String work_end_time) {
		this.work_end_time = work_end_time;
	}
	public String getHours_state() {
		return hours_state;
	}
	public void setHours_state(String hours_state) {
		this.hours_state = hours_state;
	}
	public String getCheck_time() {
		return check_time;
	}
	public void setCheck_time(String check_time) {
		this.check_time = check_time;
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
	public String getPlanWorkHours() {
		return planWorkHours;
	}
	public void setPlanWorkHours(String planWorkHours) {
		this.planWorkHours = planWorkHours;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
}
