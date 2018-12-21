package com.kuyu.vo.pcms;

import java.util.List;

public class SettlementRequest {
	
	
	private String applyTitle; //申请标题
	
	private String applyNote; //申请说明
	
	private Integer type; //付款方式
	
	private String requestUser; //申请用户编码
	
	private String dept; //申请部门编码
	
	private String companyCode; //公司编码
	
	private String vendorId; //供应商编码
	
	private String taxNumber; //税号
	
	private String applyMoney; //付款金额
	
	private List<SettlementDetailRequest> itemList; //结算详细
	
	public String getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(String applyMoney) {
		this.applyMoney = applyMoney;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getApplyTitle() {
		return applyTitle;
	}

	public void setApplyTitle(String applyTitle) {
		this.applyTitle = applyTitle;
	}

	public String getApplyNote() {
		return applyNote;
	}

	public void setApplyNote(String applyNote) {
		this.applyNote = applyNote;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<SettlementDetailRequest> getItemList() {
		return itemList;
	}

	public void setItemList(List<SettlementDetailRequest> itemList) {
		this.itemList = itemList;
	}

	public String getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(String requestUser) {
		this.requestUser = requestUser;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	
}
