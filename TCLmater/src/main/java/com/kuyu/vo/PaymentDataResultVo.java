package com.kuyu.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentDataResultVo {

	 /**
     * code码
     */
    @JsonProperty("RET_CODE")
    private String retCode;
    /**
     * 返回信息
     */
    @JsonProperty("RET_MSG")
    private String retMsg;
    @JsonProperty("RET_LIST")
    private List<PaymentDaVo> retList;
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public List<PaymentDaVo> getRetList() {
		return retList;
	}
	public void setRetList(List<PaymentDaVo> retList) {
		this.retList = retList;
	}
    
}
