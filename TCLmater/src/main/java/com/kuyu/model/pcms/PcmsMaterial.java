package com.kuyu.model.pcms;

import java.util.Date;

public class PcmsMaterial {
    private Integer mrid;

    private Integer mrcode;

    private String spid;

    private String mrname;

    private String cost;

    private String specifications;

    private String unit;

    private Integer minRange;

    private Integer maxRange;

    private String range;

    private Double comparisonPrice;

    private Integer status;

    private Date updateTime;

    private Date createTime;

    public Integer getMrid() {
        return mrid;
    }

    public void setMrid(Integer mrid) {
        this.mrid = mrid;
    }

    public Integer getMrcode() {
        return mrcode;
    }

    public void setMrcode(Integer mrcode) {
        this.mrcode = mrcode;
    }

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid == null ? null : spid.trim();
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

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range == null ? null : range.trim();
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