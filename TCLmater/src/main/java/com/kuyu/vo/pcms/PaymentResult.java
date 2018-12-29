package com.kuyu.vo.pcms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PaymentResult {

	private Integer pmid;//付款单ID
	
	private String fsscBill;//付款单号
	
	private String vendorId;//供应商ID
	
	private String vendorName; //供应商名称
	
	private String paymentType;//付款方式
	
	private String accountNumber;//银行账号
	
	private String accountName;//银行名称
	
	private String payeeName;//开户名称
	
	private String payAmount;  //应付金额
	
	private String alreadyAmount; //已付金额
	
	private String stopAmount; //终止金额
	
	private String failAmount; //失败金额
	
	private Integer status; //状态
	
	private String updateTime; //更新时间

	
	
	
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

	public Integer getPmid() {
		return pmid;
	}

	public void setPmid(Integer pmid) {
		this.pmid = pmid;
	}

	public String getFsscBill() {
		return fsscBill;
	}

	public void setFsscBill(String fsscBill) {
		this.fsscBill = fsscBill;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getAlreadyAmount() {
		return alreadyAmount;
	}

	public void setAlreadyAmount(String alreadyAmount) {
		this.alreadyAmount = alreadyAmount;
	}

	public String getStopAmount() {
		return stopAmount;
	}

	public void setStopAmount(String stopAmount) {
		this.stopAmount = stopAmount;
	}

	public String getFailAmount() {
		return failAmount;
	}

	public void setFailAmount(String failAmount) {
		this.failAmount = failAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
