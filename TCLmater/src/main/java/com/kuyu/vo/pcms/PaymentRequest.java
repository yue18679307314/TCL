package com.kuyu.vo.pcms;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentRequest {

	@JsonProperty("FSSC_BILL")
	private String fsscBill;
	
	@JsonProperty("PAYMENT_LIST")
	private List<Payment> paymentList;
	
	@JsonProperty("FINANCIAL_LIST")
	private List<Financial> financialList;

	public String getFsscBill() {
		return fsscBill;
	}

	public void setFsscBill(String fsscBill) {
		this.fsscBill = fsscBill;
	}

	public List<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}

	public List<Financial> getFinancialList() {
		return financialList;
	}

	public void setFinancialList(List<Financial> financialList) {
		this.financialList = financialList;
	}
	
	
}
