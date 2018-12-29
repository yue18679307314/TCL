package com.kuyu.vo.pcms;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemEndRequest {

	@JsonProperty("FSSC_BILL_DETAILID")
	private String fsscBillDetail;
	
	@JsonProperty("STATUS")
	private String status;
	
	@JsonProperty("FSSC_BILL")
	private String fsscBill;
	
	@JsonProperty("STOP_REASON")
	private String reason;

	public String getFsscBillDetail() {
		return fsscBillDetail;
	}

	public void setFsscBillDetail(String fsscBillDetail) {
		this.fsscBillDetail = fsscBillDetail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFsscBill() {
		return fsscBill;
	}

	public void setFsscBill(String fsscBill) {
		this.fsscBill = fsscBill;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	
	
}
