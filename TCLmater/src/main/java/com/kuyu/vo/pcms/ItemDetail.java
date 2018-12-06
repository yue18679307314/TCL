package com.kuyu.vo.pcms;

import java.util.List;

public class ItemDetail {

	private Integer itid;
	
	private Integer itType; //订单类型
	
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
	
	private String SHOP_NAME; //门店名称
	
	private String SHOP_CRM_CODE;  //门店编码
	
	private String SHOP_CHANNEL; //渠道
	
	private String SHOP_ADDRESS;  //所在地
	
	private String SHOP_BRANCH; //所属分部
	
	private String SHOP_LEVEL;  //级别
	
	private String DETAIL_TASK; //门店任务
	
	private Integer status;  //状态

	private String context; //作废原因
	
	private List<MaterialResult> mrList;
	
	private List<OthertmResult> otList;
	
	private List<ShowcaseResult> scList;

	public String getSHOP_NAME() {
		return SHOP_NAME;
	}

	public void setSHOP_NAME(String sHOP_NAME) {
		SHOP_NAME = sHOP_NAME;
	}

	public String getSHOP_CRM_CODE() {
		return SHOP_CRM_CODE;
	}

	public void setSHOP_CRM_CODE(String sHOP_CRM_CODE) {
		SHOP_CRM_CODE = sHOP_CRM_CODE;
	}

	public String getSHOP_CHANNEL() {
		return SHOP_CHANNEL;
	}

	public void setSHOP_CHANNEL(String sHOP_CHANNEL) {
		SHOP_CHANNEL = sHOP_CHANNEL;
	}

	public String getSHOP_ADDRESS() {
		return SHOP_ADDRESS;
	}

	public void setSHOP_ADDRESS(String sHOP_ADDRESS) {
		SHOP_ADDRESS = sHOP_ADDRESS;
	}

	public String getSHOP_BRANCH() {
		return SHOP_BRANCH;
	}

	public void setSHOP_BRANCH(String sHOP_BRANCH) {
		SHOP_BRANCH = sHOP_BRANCH;
	}

	public String getSHOP_LEVEL() {
		return SHOP_LEVEL;
	}

	public void setSHOP_LEVEL(String sHOP_LEVEL) {
		SHOP_LEVEL = sHOP_LEVEL;
	}

	public String getDETAIL_TASK() {
		return DETAIL_TASK;
	}

	public void setDETAIL_TASK(String dETAIL_TASK) {
		DETAIL_TASK = dETAIL_TASK;
	}

	public Integer getItid() {
		return itid;
	}

	public void setItid(Integer itid) {
		this.itid = itid;
	}

	public Integer getItType() {
		return itType;
	}

	public void setItType(Integer itType) {
		this.itType = itType;
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

	public List<OthertmResult> getOtList() {
		return otList;
	}

	public void setOtList(List<OthertmResult> otList) {
		this.otList = otList;
	}

	public List<ShowcaseResult> getScList() {
		return scList;
	}

	public void setScList(List<ShowcaseResult> scList) {
		this.scList = scList;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
}
