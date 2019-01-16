package com.kuyu.model.pcms;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class PcmsShop implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer sid;

    private String requestId;

    @JsonProperty("SHOP_CRM_CODE")
    private String shopCrmCode;

    @JsonProperty("SHOP_NAME")
    private String shopName;

    @JsonProperty("SHOP_LEVEL")
    private String shopLevel;

    @JsonProperty("SHOP_BRANCH")
    private String shopBranch;

    @JsonProperty("SHOP_CUSTOMER")
    private String shopCustomer;

    @JsonProperty("SHOP_CHANNEL")
    private String shopChannel;

    @JsonProperty("SHOP_PROVINCE")
    private String shopProvince;

    @JsonProperty("SHOP_CITY")
    private String shopCity;

    @JsonProperty("SHOP_COUNTY")
    private String shopCounty;

    @JsonProperty("DETAIL_TASK")
    private String detailTask;

    @JsonProperty("SHOP_SALES_AMOUNT")
    private String shopSalesAmount;

    private Date createTime;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    public String getShopCrmCode() {
        return shopCrmCode;
    }

    public void setShopCrmCode(String shopCrmCode) {
        this.shopCrmCode = shopCrmCode == null ? null : shopCrmCode.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopLevel() {
        return shopLevel;
    }

    public void setShopLevel(String shopLevel) {
        this.shopLevel = shopLevel == null ? null : shopLevel.trim();
    }

    public String getShopBranch() {
        return shopBranch;
    }

    public void setShopBranch(String shopBranch) {
        this.shopBranch = shopBranch == null ? null : shopBranch.trim();
    }

    public String getShopCustomer() {
        return shopCustomer;
    }

    public void setShopCustomer(String shopCustomer) {
        this.shopCustomer = shopCustomer == null ? null : shopCustomer.trim();
    }

    public String getShopChannel() {
        return shopChannel;
    }

    public void setShopChannel(String shopChannel) {
        this.shopChannel = shopChannel == null ? null : shopChannel.trim();
    }

    public String getShopProvince() {
        return shopProvince;
    }

    public void setShopProvince(String shopProvince) {
        this.shopProvince = shopProvince == null ? null : shopProvince.trim();
    }

    public String getShopCity() {
        return shopCity;
    }

    public void setShopCity(String shopCity) {
        this.shopCity = shopCity == null ? null : shopCity.trim();
    }

    public String getShopCounty() {
        return shopCounty;
    }

    public void setShopCounty(String shopCounty) {
        this.shopCounty = shopCounty == null ? null : shopCounty.trim();
    }

    public String getDetailTask() {
        return detailTask;
    }

    public void setDetailTask(String detailTask) {
        this.detailTask = detailTask == null ? null : detailTask.trim();
    }

    public String getShopSalesAmount() {
        return shopSalesAmount;
    }

    public void setShopSalesAmount(String shopSalesAmount) {
        this.shopSalesAmount = shopSalesAmount == null ? null : shopSalesAmount.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}