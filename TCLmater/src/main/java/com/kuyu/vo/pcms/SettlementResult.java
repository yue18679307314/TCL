package com.kuyu.vo.pcms;

public class SettlementResult {

	private Integer setid;
	
	private String settNumber;
	
	private String itemNumber;
	
	private String sedetailMemo;
	
	private String taxType;
	
	private String invoiceNumber;
	
	private String isLast;
	
	private String sedetailMoney;
	
	
	//报销单
	private String fsscBill;
	
	private String sumMoney;
	
	private String bxStatus;
	
	private String updateTime;
	
	private String stopReson;
	
	public String getStopReson() {
		return stopReson;
	}

	public void setStopReson(String stopReson) {
		this.stopReson = stopReson;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getBxStatus() {
		return bxStatus;
	}

	public void setBxStatus(String bxStatus) {
		this.bxStatus = bxStatus;
	}

	public String getFsscBill() {
		return fsscBill;
	}

	public void setFsscBill(String fsscBill) {
		this.fsscBill = fsscBill;
	}

	public String getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(String sumMoney) {
		this.sumMoney = sumMoney;
	}

	public Integer getSetid() {
		return setid;
	}

	public void setSetid(Integer setid) {
		this.setid = setid;
	}

	public String getSedetailMoney() {
		return sedetailMoney;
	}

	public void setSedetailMoney(String sedetailMoney) {
		this.sedetailMoney = sedetailMoney;
	}

	public String getSettNumber() {
		return settNumber;
	}

	public void setSettNumber(String settNumber) {
		this.settNumber = settNumber;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getSedetailMemo() {
		return sedetailMemo;
	}

	public void setSedetailMemo(String sedetailMemo) {
		this.sedetailMemo = sedetailMemo;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getIsLast() {
		return isLast;
	}

	public void setIsLast(String isLast) {
		this.isLast = isLast;
	}
	
	
}
