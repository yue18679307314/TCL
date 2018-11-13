package com.kuyu.vo;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class TpmUserStatementVo{
    
	@ApiModelProperty("正常天数")
    private int countNormal;
	@ApiModelProperty("异常天数")
    private int countAbnormal;
	@ApiModelProperty("打卡记录")
    private List<TpmWorkAttendenceVo> records = new ArrayList<>();
	public int getCountNormal() {
		return countNormal;
	}
	public void setCountNormal(int countNormal) {
		this.countNormal = countNormal;
	}
	public int getCountAbnormal() {
		return countAbnormal;
	}
	public void setCountAbnormal(int countAbnormal) {
		this.countAbnormal = countAbnormal;
	}
	public List<TpmWorkAttendenceVo> getRecords() {
		return records;
	}
	public void setRecords(List<TpmWorkAttendenceVo> records) {
		this.records = records;
	}
   
    
}

