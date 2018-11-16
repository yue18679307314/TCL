package com.kuyu.vo.pcms;

import java.util.List;

public class ItemDetail {

	private Integer itid;
	
	private String vendorName; //供应商名字
	
	private String requestId; //立项单单号
	
	private String requestTitle; //立项单标题
	
	private String detailAccount; //立项单的费用细类
	
	private String financeUserName;   //负责人
	
	private String requestCompanyCode;  //公司代码
	
	private String requestUserName; //申请人
	
	private String detailSubject; //预算部门
	
	private String requestDept;  //申请部门
	
	private String requestCreateTime; //申请开始时间
	
	private String requestEndTime;  //过期时间
	
	private Integer status;  //状态
	
	private List<MaterialResult> mrList;

	public Integer getItid() {
		return itid;
	}

	public void setItid(Integer itid) {
		this.itid = itid;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getRequestTitle() {
		return requestTitle;
	}

	public void setRequestTitle(String requestTitle) {
		this.requestTitle = requestTitle;
	}

	public String getDetailAccount() {
		return detailAccount;
	}

	public void setDetailAccount(String detailAccount) {
		this.detailAccount = detailAccount;
	}

	public String getFinanceUserName() {
		return financeUserName;
	}

	public void setFinanceUserName(String financeUserName) {
		this.financeUserName = financeUserName;
	}

	public String getRequestCompanyCode() {
		return requestCompanyCode;
	}

	public void setRequestCompanyCode(String requestCompanyCode) {
		this.requestCompanyCode = requestCompanyCode;
	}

	public String getRequestUserName() {
		return requestUserName;
	}

	public void setRequestUserName(String requestUserName) {
		this.requestUserName = requestUserName;
	}

	public String getDetailSubject() {
		return detailSubject;
	}

	public void setDetailSubject(String detailSubject) {
		this.detailSubject = detailSubject;
	}

	public String getRequestDept() {
		return requestDept;
	}

	public void setRequestDept(String requestDept) {
		this.requestDept = requestDept;
	}

	public String getRequestCreateTime() {
		return requestCreateTime;
	}

	public void setRequestCreateTime(String requestCreateTime) {
		this.requestCreateTime = requestCreateTime;
	}

	public String getRequestEndTime() {
		return requestEndTime;
	}

	public void setRequestEndTime(String requestEndTime) {
		this.requestEndTime = requestEndTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<MaterialResult> getMrList() {
		return mrList;
	}

	public void setMrList(List<MaterialResult> mrList) {
		this.mrList = mrList;
	}

	
}
