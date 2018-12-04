package com.kuyu.model.pcms;

import java.util.Date;

public class PcmsOthertmSource {
    private Integer otid;

    private String requestId;

    private String vendorId;

    private String mrname3;

    private String cost;

    private String unit;

    private String note;

    private String comparisonPrice;

    private String specifications3;

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

    public String getMrname3() {
        return mrname3;
    }

    public void setMrname3(String mrname3) {
        this.mrname3 = mrname3 == null ? null : mrname3.trim();
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost == null ? null : cost.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getComparisonPrice() {
        return comparisonPrice;
    }

    public void setComparisonPrice(String comparisonPrice) {
        this.comparisonPrice = comparisonPrice == null ? null : comparisonPrice.trim();
    }

    public String getSpecifications3() {
        return specifications3;
    }

    public void setSpecifications3(String specifications3) {
        this.specifications3 = specifications3 == null ? null : specifications3.trim();
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