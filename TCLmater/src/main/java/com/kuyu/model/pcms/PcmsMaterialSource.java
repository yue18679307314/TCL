package com.kuyu.model.pcms;

import java.util.Date;

public class PcmsMaterialSource {
    private Integer mrid;

    private String resuestId;

    private Integer mrtype;

    private Integer mrcode;

    private String vendorId;

    private String mrname;

    private String cost;

    private String specifications;

    private String unit;

    private Double unitPrice;

    private Integer number;

    private Integer minRange;

    private Integer maxRange;

    private String rangePrice;

    private Double comparisonPrice;

    private Integer status;

    private String note;

    private Date updateTime;

    private Date createTime;

    public Integer getMrid() {
        return mrid;
    }

    public void setMrid(Integer mrid) {
        this.mrid = mrid;
    }

    public String getResuestId() {
        return resuestId;
    }

    public void setResuestId(String resuestId) {
        this.resuestId = resuestId == null ? null : resuestId.trim();
    }

    public Integer getMrtype() {
        return mrtype;
    }

    public void setMrtype(Integer mrtype) {
        this.mrtype = mrtype;
    }

    public Integer getMrcode() {
        return mrcode;
    }

    public void setMrcode(Integer mrcode) {
        this.mrcode = mrcode;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId == null ? null : vendorId.trim();
    }

    public String getMrname() {
        return mrname;
    }

    public void setMrname(String mrname) {
        this.mrname = mrname == null ? null : mrname.trim();
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost == null ? null : cost.trim();
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getMinRange() {
        return minRange;
    }

    public void setMinRange(Integer minRange) {
        this.minRange = minRange;
    }

    public Integer getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(Integer maxRange) {
        this.maxRange = maxRange;
    }

    public String getRangePrice() {
        return rangePrice;
    }

    public void setRangePrice(String rangePrice) {
        this.rangePrice = rangePrice == null ? null : rangePrice.trim();
    }

    public Double getComparisonPrice() {
        return comparisonPrice;
    }

    public void setComparisonPrice(Double comparisonPrice) {
        this.comparisonPrice = comparisonPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}