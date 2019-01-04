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
	
	@JsonProperty("REQUEST_BILL_AVAILABLE")
	private List<BillAvailable> availableList;

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

	public List<BillAvailable> getAvailableList() {
		return availableList;
	}

	public void setAvailableList(List<BillAvailable> availableList) {
		this.availableList = availableList;
	}

	
	
	
}
