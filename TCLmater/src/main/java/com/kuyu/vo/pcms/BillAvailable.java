package com.kuyu.vo.pcms;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BillAvailable {

	@JsonProperty("BILL_DETAIL_ID")
	private String billDetailId;
	
	@JsonProperty("AVAILABLE_MONEY")
	private String availableMoney;

	public String getBillDetailId() {
		return billDetailId;
	}

	public void setBillDetailId(String billDetailId) {
		this.billDetailId = billDetailId;
	}

	public String getAvailableMoney() {
		return availableMoney;
	}

	public void setAvailableMoney(String availableMoney) {
		this.availableMoney = availableMoney;
	}
	
	
}
