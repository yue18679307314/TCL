package com.kuyu.vo.pcms;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuyu.model.pcms.PcmsMaterial;
import com.kuyu.model.pcms.PcmsOthertm;
import com.kuyu.model.pcms.PcmsShowcase;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsProjectVo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("REQUEST_ID")
	private String requestId;
	
	@JsonProperty("REQUEST_TITLE")
	private String requestTitle;
	
	@JsonProperty("DETAIL_ACCOUNT")
	private String detailAccount;
	
	@JsonProperty("REQUEST_COMPANY_CODE")
	private String requestCompanyCode;
	
	@JsonProperty("DETAIL_SUBJECT")
	private String detailSubject;
	
	@JsonProperty("REQUEST_DEPT")
	private String requestDept;
	
	@JsonProperty("REQUEST_USER")
	private String REQUEST_USER;
	
	@JsonProperty("CREATE_USER")
	private String CREATE_USER;
	
	@JsonProperty("REQUEST_TELPHONE")
	private String REQUEST_TELPHONE;
	
	@JsonProperty("REQUEST_EMAIL")
	private String REQUEST_EMAIL;
	
	@JsonProperty("REQUEST_INFO")
	private String REQUEST_INFO;
	
	@JsonProperty("request_user_name")
	private String requestUserName; //申请人
	
	@JsonProperty("finance_user")
	private String finance_user;     //财务人工号
	
	@JsonProperty("finance_user_name")
	private String financeUserName; //财务人姓名
	
	@JsonProperty("REQUEST_CREATE_TIME")
	private String requestCreateTime;
	
	@JsonProperty("REQUEST_END_TIME")
	private String requestEndTime;
	
	@JsonProperty("TYPE")
	private String type;            //类型1表示展台展柜，2表示其他终端，3表示广告物料
	
	@JsonProperty("ACTIVITY_STORE")
	private String ACTIVITY_STORE;
	
	@JsonProperty("SHOP_CRM_CODE")
	private String shopCrmCode;
	
	@JsonProperty("SHOP_NAME")
	private String shopName;
	
	@JsonProperty("CREATE_TIME")
	private String CREATE_TIME;
	
	@JsonProperty("CURRENCY")
	private String CURRENCY;
	
	@JsonProperty("EXCHANGE")
	private String EXCHANGE;
	
	@JsonProperty("DETAIL_LIST")
	private String DETAIL_LIST;
	
	@JsonProperty("SHOP_LIST")
	private String SHOP_LIST;
	
	@JsonProperty("DETAIL_ID")
	private String DETAIL_ID;
	
	@JsonProperty("DETAIL_MEMO")
	private String DETAIL_MEMO;
	
	@JsonProperty("DETAIL_COST")
	private String DETAIL_COST;
	
	@JsonProperty("DETAIL_WBS")
	private String DETAIL_WBS;
	
	@JsonProperty("DETAIL_CHANNEL")
	private String DETAIL_CHANNEL;
	
	@JsonProperty("DETAIL_TASK")
	private String detailTask;
	
	@JsonProperty("DETAIL_AMOUNT")
	private String DETAIL_AMOUNT;
	
	@JsonProperty("DETAIL_STANDARD")
	private String DETAIL_STANDARD;
	
	@JsonProperty("DETAIL_VENDOR")
	private String DETAIL_VENDOR;

	@JsonProperty("ACTIVITY_CITY")
	private String ACTIVITY_CITY;
	
	@JsonProperty("SHOP_CITY")
	private String shopCity;
	
	@JsonProperty("SHOP_LEVEL")
	private String shopLevel;
	
	@JsonProperty("SHOP_BRANCH")
	private String shopBranch;
	
	@JsonProperty("SHOP_CHANNEL")
	private String shopChannel;
	
	@JsonProperty("SHOP_SALES_AMOUNT")
	private String shopSalesAmount;
	
	@JsonProperty("balance")
	private String balance;
	
	@JsonProperty("otherTerminalList")
    @ApiModelProperty("其他终端数据列表")
	List<PcmsOthertm> otherTerminalList;
	
	@JsonProperty("materialList")
    @ApiModelProperty("物料数据列表")
	List<PcmsMaterial> materialList;
	
	@JsonProperty("showcaseList")
    @ApiModelProperty("展柜展台")
	List<PcmsShowcase> showcaseList;

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

	public String getRequestCompanyCode() {
		return requestCompanyCode;
	}

	public void setRequestCompanyCode(String requestCompanyCode) {
		this.requestCompanyCode = requestCompanyCode;
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

	public String getREQUEST_USER() {
		return REQUEST_USER;
	}

	public void setREQUEST_USER(String rEQUEST_USER) {
		REQUEST_USER = rEQUEST_USER;
	}

	public String getCREATE_USER() {
		return CREATE_USER;
	}

	public void setCREATE_USER(String cREATE_USER) {
		CREATE_USER = cREATE_USER;
	}

	public String getREQUEST_TELPHONE() {
		return REQUEST_TELPHONE;
	}

	public void setREQUEST_TELPHONE(String rEQUEST_TELPHONE) {
		REQUEST_TELPHONE = rEQUEST_TELPHONE;
	}

	public String getREQUEST_EMAIL() {
		return REQUEST_EMAIL;
	}

	public void setREQUEST_EMAIL(String rEQUEST_EMAIL) {
		REQUEST_EMAIL = rEQUEST_EMAIL;
	}

	public String getREQUEST_INFO() {
		return REQUEST_INFO;
	}

	public void setREQUEST_INFO(String rEQUEST_INFO) {
		REQUEST_INFO = rEQUEST_INFO;
	}

	public String getRequestUserName() {
		return requestUserName;
	}

	public void setRequestUserName(String requestUserName) {
		this.requestUserName = requestUserName;
	}

	public String getFinance_user() {
		return finance_user;
	}

	public void setFinance_user(String finance_user) {
		this.finance_user = finance_user;
	}

	public String getFinanceUserName() {
		return financeUserName;
	}

	public void setFinanceUserName(String financeUserName) {
		this.financeUserName = financeUserName;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getACTIVITY_STORE() {
		return ACTIVITY_STORE;
	}

	public void setACTIVITY_STORE(String aCTIVITY_STORE) {
		ACTIVITY_STORE = aCTIVITY_STORE;
	}

	public String getShopCrmCode() {
		return shopCrmCode;
	}

	public void setShopCrmCode(String shopCrmCode) {
		this.shopCrmCode = shopCrmCode;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

	public String getCURRENCY() {
		return CURRENCY;
	}

	public void setCURRENCY(String cURRENCY) {
		CURRENCY = cURRENCY;
	}

	public String getEXCHANGE() {
		return EXCHANGE;
	}

	public void setEXCHANGE(String eXCHANGE) {
		EXCHANGE = eXCHANGE;
	}

	public String getDETAIL_LIST() {
		return DETAIL_LIST;
	}

	public void setDETAIL_LIST(String dETAIL_LIST) {
		DETAIL_LIST = dETAIL_LIST;
	}

	public String getSHOP_LIST() {
		return SHOP_LIST;
	}

	public void setSHOP_LIST(String sHOP_LIST) {
		SHOP_LIST = sHOP_LIST;
	}

	public String getDETAIL_ID() {
		return DETAIL_ID;
	}

	public void setDETAIL_ID(String dETAIL_ID) {
		DETAIL_ID = dETAIL_ID;
	}

	public String getDETAIL_MEMO() {
		return DETAIL_MEMO;
	}

	public void setDETAIL_MEMO(String dETAIL_MEMO) {
		DETAIL_MEMO = dETAIL_MEMO;
	}

	public String getDETAIL_COST() {
		return DETAIL_COST;
	}

	public void setDETAIL_COST(String dETAIL_COST) {
		DETAIL_COST = dETAIL_COST;
	}

	public String getDETAIL_WBS() {
		return DETAIL_WBS;
	}

	public void setDETAIL_WBS(String dETAIL_WBS) {
		DETAIL_WBS = dETAIL_WBS;
	}

	public String getDETAIL_CHANNEL() {
		return DETAIL_CHANNEL;
	}

	public void setDETAIL_CHANNEL(String dETAIL_CHANNEL) {
		DETAIL_CHANNEL = dETAIL_CHANNEL;
	}

	

	public String getDetailTask() {
		return detailTask;
	}

	public void setDetailTask(String detailTask) {
		this.detailTask = detailTask;
	}

	public String getDETAIL_AMOUNT() {
		return DETAIL_AMOUNT;
	}

	public void setDETAIL_AMOUNT(String dETAIL_AMOUNT) {
		DETAIL_AMOUNT = dETAIL_AMOUNT;
	}

	public String getDETAIL_STANDARD() {
		return DETAIL_STANDARD;
	}

	public void setDETAIL_STANDARD(String dETAIL_STANDARD) {
		DETAIL_STANDARD = dETAIL_STANDARD;
	}

	public String getDETAIL_VENDOR() {
		return DETAIL_VENDOR;
	}

	public void setDETAIL_VENDOR(String dETAIL_VENDOR) {
		DETAIL_VENDOR = dETAIL_VENDOR;
	}

	public String getACTIVITY_CITY() {
		return ACTIVITY_CITY;
	}

	public void setACTIVITY_CITY(String aCTIVITY_CITY) {
		ACTIVITY_CITY = aCTIVITY_CITY;
	}

	public String getShopLevel() {
		return shopLevel;
	}

	public void setShopLevel(String shopLevel) {
		this.shopLevel = shopLevel;
	}

	public String getShopBranch() {
		return shopBranch;
	}

	public void setShopBranch(String shopBranch) {
		this.shopBranch = shopBranch;
	}

	public String getShopChannel() {
		return shopChannel;
	}

	public void setShopChannel(String shopChannel) {
		this.shopChannel = shopChannel;
	}

	public String getShopSalesAmount() {
		return shopSalesAmount;
	}

	public void setShopSalesAmount(String shopSalesAmount) {
		this.shopSalesAmount = shopSalesAmount;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public List<PcmsOthertm> getOtherTerminalList() {
		return otherTerminalList;
	}

	public void setOtherTerminalList(List<PcmsOthertm> otherTerminalList) {
		this.otherTerminalList = otherTerminalList;
	}

	

	public List<PcmsMaterial> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<PcmsMaterial> materialList) {
		this.materialList = materialList;
	}

	public List<PcmsShowcase> getShowcaseList() {
		return showcaseList;
	}

	public void setShowcaseList(List<PcmsShowcase> showcaseList) {
		this.showcaseList = showcaseList;
	}

	public String getShopCity() {
		return shopCity;
	}

	public void setShopCity(String shopCity) {
		this.shopCity = shopCity;
	}

	
}
