package com.kuyu.model.pcms;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PcmsOthertm {
    private Integer otid;

    private String requestId;

    private String vendorId;

    @JsonProperty("CHILDREN3_DETAIL")
    private String children3Detail;

    private String children3Vendor;

    private String children3Buildtime;

    private String children3Count;

    private String children3Price;

    private String children3Amount;

    private Date createTime;

    public Integer getOtid() {
        return otid;
    }

    public void setOtid(Integer otid) {
        this.otid = otid;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId == null ? null : vendorId.trim();
    }

    public String getChildren3Detail() {
        return children3Detail;
    }

    public void setChildren3Detail(String children3Detail) {
        this.children3Detail = children3Detail == null ? null : children3Detail.trim();
    }

    public String getChildren3Vendor() {
        return children3Vendor;
    }

    public void setChildren3Vendor(String children3Vendor) {
        this.children3Vendor = children3Vendor == null ? null : children3Vendor.trim();
    }

    public String getChildren3Buildtime() {
        return children3Buildtime;
    }

    public void setChildren3Buildtime(String children3Buildtime) {
        this.children3Buildtime = children3Buildtime == null ? null : children3Buildtime.trim();
    }

    public String getChildren3Count() {
        return children3Count;
    }

    public void setChildren3Count(String children3Count) {
        this.children3Count = children3Count == null ? null : children3Count.trim();
    }

    public String getChildren3Price() {
        return children3Price;
    }

    public void setChildren3Price(String children3Price) {
        this.children3Price = children3Price == null ? null : children3Price.trim();
    }

    public String getChildren3Amount() {
        return children3Amount;
    }

    public void setChildren3Amount(String children3Amount) {
        this.children3Amount = children3Amount == null ? null : children3Amount.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}