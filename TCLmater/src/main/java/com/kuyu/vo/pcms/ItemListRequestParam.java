package com.kuyu.vo.pcms;

public class ItemListRequestParam {

	//供应商名字
	private String spName; 
	
	//原始立项ID
	private String requestId;
	
	//立项单状态  0:未接单 10:已接单制作中 20待验收 30待结算
	private Integer status;
	
	//申请人
	private String requestUser;
	
	//申请部门
	private String orgName;
	
	//开始时间
	private String startTime;
	
	//结束时间
	private String endTime;

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(String requestUser) {
		this.requestUser = requestUser;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
	
	
}
