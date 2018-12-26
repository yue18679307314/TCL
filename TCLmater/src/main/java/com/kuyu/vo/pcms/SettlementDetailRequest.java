package com.kuyu.vo.pcms;

public class SettlementDetailRequest {

	private Integer itid;
	
	private Integer invoiceType;
	
	private String detailMoney;
	
	private String detailMemo;
	
	private String  taxRate; 
	
	private String  taxAmount;
	
	private Integer	isLast;
	
	private String invoiceNumber;//发票号
	
	private String image;

	public Integer getItid() {
		return itid;
	}

	public void setItid(Integer itid) {
		this.itid = itid;
	}

	public Integer getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getDetailMoney() {
		return detailMoney;
	}

	public void setDetailMoney(String detailMoney) {
		this.detailMoney = detailMoney;
	}

	public String getDetailMemo() {
		return detailMemo;
	}

	public void setDetailMemo(String detailMemo) {
		this.detailMemo = detailMemo;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Integer getIsLast() {
		return isLast;
	}

	public void setIsLast(Integer isLast) {
		this.isLast = isLast;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	
	
	
}
