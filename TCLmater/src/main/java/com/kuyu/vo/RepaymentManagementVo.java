package com.kuyu.vo;

import java.util.List;

import com.kuyu.common.BasePageQuery;
import com.kuyu.model.TpmAttendenceModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("报销管理查询类")
public class RepaymentManagementVo extends BasePageQuery{

	@ApiModelProperty("结算方式")
	private String settlementMethod;
	
	@ApiModelProperty("考勤表是否生成:1全部，2已生成，3未生成")
	private String isAttendance;
	
	@ApiModelProperty("考勤日期")
	private String activityTime;
	
	@ApiModelProperty("立项单编号")
	private String requestId;
	
	@ApiModelProperty("活动负责人")
	private String managerName;
	
	@ApiModelProperty("活动名称")
	private String activityName;
	
	@ApiModelProperty(hidden = true,value ="列表，不需要传入")
	private List<String> list;
	
	@ApiModelProperty(hidden = true,value ="员工编号")
	private String personCode;
	
	@ApiModelProperty(hidden = true,value ="类型，不需要传入")
	private Integer personType;
	
	@ApiModelProperty("导出列表")
	private List<TpmAttendenceModel> tpmAttendenceModelList;
	
	@ApiModelProperty("考勤表单号")
	private String attendanceUuid;
	
	@ApiModelProperty("生成人员")
	private String personName;
	
	@ApiModelProperty("生成时间")
	private String createTime;
	
	@ApiModelProperty("开始时间")
	private String startTime;
	
	@ApiModelProperty("结束时间时间")
	private String endTime;
	
	@ApiModelProperty("临促姓名")
	private String openName;
	
	
	public String getOpenName() {
		return openName;
	}

	public void setOpenName(String openName) {
		this.openName = openName;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getAttendanceUuid() {
		return attendanceUuid;
	}

	public void setAttendanceUuid(String attendanceUuid) {
		this.attendanceUuid = attendanceUuid;
	}

	
	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public List<TpmAttendenceModel> getTpmAttendenceModelList() {
		return tpmAttendenceModelList;
	}

	public void setTpmAttendenceModelList(List<TpmAttendenceModel> tpmAttendenceModelList) {
		this.tpmAttendenceModelList = tpmAttendenceModelList;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
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

	public String getSettlementMethod() {
		return settlementMethod;
	}

	public void setSettlementMethod(String settlementMethod) {
		this.settlementMethod = settlementMethod;
	}

	public String getIsAttendance() {
		return isAttendance;
	}

	public void setIsAttendance(String isAttendance) {
		this.isAttendance = isAttendance;
	}

	public String getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}


	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	
}
