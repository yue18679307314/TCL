package com.kuyu.vo.pcms;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payment {

	@JsonProperty("VENDOR_CODE")
	private String vendorId;
	
	@JsonProperty("ACCOUNT_NUMBER")
	private String accountNumber;
	
	@JsonProperty("ACCOUNT_NAME")
	private String accountName;
	
	@JsonProperty("PAYEE_NAME")
	private String payeeName;
	
	@JsonProperty("PAYMENT_TYPE")
	private String paymentType;
	
	@JsonProperty("RECOMMENT_DATE")
	private String recommentDate;
	
	@JsonProperty("PAY_AMOUNT")
	private String payAmount;
	
	@JsonProperty("PAY_ STANDARD")
	private String payStandard;
	
	@JsonProperty("BILL_EXPIRE_DATE")
	private String billExpireDate;
	
	@JsonProperty("BANK_ACCOUNT_NUMBER")
	private String bankAccountNumber;

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getRecommentDate() {
		return recommentDate;
	}

	public void setRecommentDate(String recommentDate) {
		this.recommentDate = recommentDate;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayStandard() {
		return payStandard;
	}

	public void setPayStandard(String payStandard) {
		this.payStandard = payStandard;
	}

	public String getBillExpireDate() {
		return billExpireDate;
	}

	public void setBillExpireDate(String billExpireDate) {
		this.billExpireDate = billExpireDate;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	
	
}
