package com.kuyu.vo.pcms;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SettlementDetailResult {
	
	private Integer seid;  //结算单id
	
	private String settlementNumber; //结算单号

	private String applyTitle; //申请标题
	
	private String applyNote; //申请说明
	
	private String requestUser; //申请用户编码
	
	private String requestUserName; //申请人
	
	private String openingAccount; //开户银行账号
	
	private String openingBank; //开户银行名称
	
	private String vendorId; //供应商编码
	
	private String vendorName; //供应商名称
	
	private Integer type; //付款方式
	
	private String companyCode; //公司编码
	
	private String applyMoney; //付款金额
	
	private List<SettlementItemResult> itemList; //结算详细
	
	
	

	public String getSettlementNumber() {
		return settlementNumber;
	}

	public void setSettlementNumber(String settlementNumber) {
		this.settlementNumber = settlementNumber;
	}

	public Integer getSeid() {
		return seid;
	}

	public void setSeid(Integer seid) {
		this.seid = seid;
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

	public String getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(String requestUser) {
		this.requestUser = requestUser;
	}

	public String getRequestUserName() {
		return requestUserName;
	}

	public void setRequestUserName(String requestUserName) {
		this.requestUserName = requestUserName;
	}

	public String getOpeningAccount() {
		return openingAccount;
	}

	public void setOpeningAccount(String openingAccount) {
		this.openingAccount = openingAccount;
	}

	public String getOpeningBank() {
		return openingBank;
	}

	public void setOpeningBank(String openingBank) {
		this.openingBank = openingBank;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(String applyMoney) {
		this.applyMoney = applyMoney;
	}

	public List<SettlementItemResult> getItemList() {
		return itemList;
	}

	public void setItemList(List<SettlementItemResult> itemList) {
		this.itemList = itemList;
	}
	
	
}
