package com.kuyu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("待办事项查询结果")
public class TpmToDoListVo {

	@ApiModelProperty("活动申请管理待办事项数")
	public Integer activityNo;
	@ApiModelProperty("考勤管理待办事项数")
	public Integer attendenceNo;
	@ApiModelProperty("结算管理待办事项数")
	public Integer settlementNo;
	public Integer getActivityNo() {
		return activityNo;
	}
	public void setActivityNo(Integer activityNo) {
		this.activityNo = activityNo;
	}
	public Integer getAttendenceNo() {
		return attendenceNo;
	}
	public void setAttendenceNo(Integer attendenceNo) {
		this.attendenceNo = attendenceNo;
	}
	public Integer getSettlementNo() {
		return settlementNo;
	}
	public void setSettlementNo(Integer settlementNo) {
		this.settlementNo = settlementNo;
	}

	
	
}
