package com.kuyu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentDaVo{
	@JsonProperty("FSSC_BILL")
	private String fsscBill;
	@JsonProperty("PAYMENT_ID")
	private String paymentId;
	@JsonProperty("RESULT_CODE")
	private String resultCode;
	@JsonProperty("RESULT_MSG")
	private String resultMsg;
	public String getFsscBill() {
		return fsscBill;
	}
	public void setFsscBill(String fsscBill) {
		this.fsscBill = fsscBill;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
}
	