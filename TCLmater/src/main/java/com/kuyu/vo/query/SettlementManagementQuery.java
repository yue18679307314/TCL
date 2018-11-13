package com.kuyu.vo.query;

import java.util.List;

import com.kuyu.common.BasePageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("PC结算管理查询类")
public class SettlementManagementQuery extends BasePageQuery{

	@ApiModelProperty("姓名")
	private String name;
	@ApiModelProperty("活动名称")
	private String activityName;
	@ApiModelProperty("考勤日期")
	private String attendanceTime;
	@ApiModelProperty("预算部门")
	private String dept;
	@ApiModelProperty("活动负责人")
	private String activityManager;
	@ApiModelProperty("活动负责人审核状态")
	private String managerCheckStatus;
	@ApiModelProperty("财务审核状态")
	private String financialCheckStatus;
	@ApiModelProperty("付款状态")
	private String payStatus;
	@ApiModelProperty("待办事项查询：1")
	private String toDoState;
	@ApiModelProperty(hidden = true,value ="员工编号")
	private String personCode;
	@ApiModelProperty(hidden = true,value ="类型，不需要传入")
	private Integer personType;
	@ApiModelProperty(hidden = true,value ="列表，不需要传入")
	private List<String> list;
	@ApiModelProperty("立项申请单号")
	private String requestId;
	@ApiModelProperty("借款单号")
	private String fsscLoanBillNo;
	@ApiModelProperty(hidden = true,value = "借款单表id列表，根据接口单号获得")
	private List<String> lbuList;
	@ApiModelProperty("为1导出所有，其他导出当前页")
	private String flag;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public List<String> getLbuList() {
		return lbuList;
	}
	public void setLbuList(List<String> lbuList) {
		this.lbuList = lbuList;
	}
	public String getFsscLoanBillNo() {
		return fsscLoanBillNo;
	}
	public void setFsscLoanBillNo(String fsscLoanBillNo) {
		this.fsscLoanBillNo = fsscLoanBillNo;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getActivityManager() {
		return activityManager;
	}
	public void setActivityManager(String activityManager) {
		this.activityManager = activityManager;
	}
	public String getManagerCheckStatus() {
		return managerCheckStatus;
	}
	public void setManagerCheckStatus(String managerCheckStatus) {
		this.managerCheckStatus = managerCheckStatus;
	}
	public String getFinancialCheckStatus() {
		return financialCheckStatus;
	}
	public void setFinancialCheckStatus(String financialCheckStatus) {
		this.financialCheckStatus = financialCheckStatus;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
}
