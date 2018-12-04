package com.kuyu.model.pcms;

import java.util.Date;

public class PcmsShop {
    private Integer sid;

    private String requestId;

    private String shopCrmCode;

    private String shopName;

    private String shopLevel;

    private String shopBranch;

    private String shopCustomer;

    private String shopChannel;

    private String shopProvince;

    private String shopCity;

    private String shopCounty;

    private String detailTask;

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