package com.kuyu.vo.pcms;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuyu.model.TpmActivityOriginalModel;
import com.kuyu.model.pcms.PcmsShop;

public class PcmsProjectVo2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//申请单编号
	@JsonProperty("REQUEST_ID")
	private String requestId;
	
	//申请标题
	@JsonProperty("REQUEST_TITLE")
	private String requestTitle;
	
	//公司代码
	@JsonProperty("REQUEST_COMPANY_CODE")
	private String requestCompanyCode;
	
	//申请人部门
	@JsonProperty("REQUEST_DEPT")
	private String requestDept;
	
	//申请人工号
	@JsonProperty("REQUEST_USER")
	private String requestUser;
	
	//填单人
	@JsonProperty("CREATE_USER")
	private String createUser;
	
	//申请人电话
	@JsonProperty("REQUEST_TELPHONE")
	private String requestTelphone;
	
	//申请人邮箱
	@JsonProperty("REQUEST_EMAIL")
	private String requestEmail;
	
	//申请事由
	@JsonProperty("REQUEST_INFO")
	private String requestInfo;
	
	//申请事项开始日期
	@JsonProperty("REQUEST_CREATE_TIME")
	private String requestCreateTime;
	
	//申请事项结束日期
	@JsonProperty("REQUEST_END_TIME")
	private String requestEndTime;
	
	//单据创建日期
	@JsonProperty("CREATE_TIME")
	private String createTime;
	
	//币种
	@JsonProperty("CURRENCY")
	private String currency;
	
	//汇率
	@JsonProperty("EXCHANGE")
	private String exchange;
	
	//事项申请明细区
	@JsonProperty("DETAIL_LIST")
	private List<ProjectDetailVo> detailList;
	
	//门店信息
	@JsonProperty("SHOP_LIST")
	private List<PcmsShop> shopList;
	
	//广告物料-活动单
	private List<ActivityOriginalVo> activityList;
	
	//立项单类型 1门头展台  2物料
	private String type;
	
	//合计费用
	private String sumMoney;
		
	//合计剩余可结算金额
	private String sumSubclass;

	public String getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(String sumMoney) {
		this.sumMoney = sumMoney;
	}

	public String getSumSubclass() {
		return sumSubclass;
	}

	public void setSumSubclass(String sumSubclass) {
		this.sumSubclass = sumSubclass;
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

	public String getRequestCompanyCode() {
		return requestCompanyCode;
	}

	public void setRequestCompanyCode(String requestCompanyCode) {
		this.requestCompanyCode = requestCompanyCode;
	}

	public String getRequestDept() {
		return requestDept;
	}

	public void setRequestDept(String requestDept) {
		this.requestDept = requestDept;
	}

	public String getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(String requestUser) {
		this.requestUser = requestUser;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getRequestTelphone() {
		return requestTelphone;
	}

	public void setRequestTelphone(String requestTelphone) {
		this.requestTelphone = requestTelphone;
	}

	public String getRequestEmail() {
		return requestEmail;
	}

	public void setRequestEmail(String requestEmail) {
		this.requestEmail = requestEmail;
	}

	public String getRequestInfo() {
		return requestInfo;
	}

	public void setRequestInfo(String requestInfo) {
		this.requestInfo = requestInfo;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public List<ProjectDetailVo> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ProjectDetailVo> detailList) {
		this.detailList = detailList;
	}

	public List<PcmsShop> getShopList() {
		return shopList;
	}

	public void setShopList(List<PcmsShop> shopList) {
		this.shopList = shopList;
	}

	public List<ActivityOriginalVo> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<ActivityOriginalVo> activityList) {
		this.activityList = activityList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
}
