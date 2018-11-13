package com.kuyu.vo;

import java.util.List;

import com.kuyu.common.BasePageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("导出银行信息查询类")
public class BankInfoVo extends BasePageQuery{
	
	@ApiModelProperty("立项单号")
	private String requestId;
	
	@ApiModelProperty("活动名称")
	private String activityName;
	
	@ApiModelProperty("时间")
	private String activityTime;
	
	@ApiModelProperty("活动负责人")
	private String managerName;
	
	@ApiModelProperty(value = "类型",hidden = true)
	private Integer personType;
	
	@ApiModelProperty(value = "人员编号",hidden = true)
	private String personCode;
	
	@ApiModelProperty(hidden = true)
	private List<String> list;
	
	@ApiModelProperty("openid列表")
	private List<String> openidList;
	
	@ApiModelProperty("临促姓名")
	private String openName;
	
	public String getOpenName() {
		return openName;
	}

	public void setOpenName(String openName) {
		this.openName = openName;
	}

	public List<String> getOpenidList() {
		return openidList;
	}

	public void setOpenidList(List<String> openidList) {
		this.openidList = openidList;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Integer getPersonType() {
		return personType;
	}

	public void setPersonType(Integer personType) {
		this.personType = personType;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
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
	
}
