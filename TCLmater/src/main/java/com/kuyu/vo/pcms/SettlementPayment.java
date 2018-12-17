package com.kuyu.vo.pcms;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SettlementPayment {

	@JsonProperty("PAYMENT_VENDORCODE")
	private String paymentVendorcode;
	
	@JsonProperty("ACCOUNT_NAME")
	private String accountName;
	
	@JsonProperty("ACCOUNT_VALUE")
	private String accountValue;
	
	@JsonProperty("PAYMENT_CURRENCY")
	private String paymentCurrency;

	public String getPaymentVendorcode() {
		return paymentVendorcode;
	}

	public void setPaymentVendorcode(String paymentVendorcode) {
		this.paymentVendorcode = paymentVendorcode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountValue() {
		return accountValue;
	}

	public void setAccountValue(String accountValue) {
		this.accountValue = accountValue;
	}

	public String getPaymentCurrency() {
		return paymentCurrency;
	}

	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}
	
	
}
