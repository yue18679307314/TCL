package com.kuyu.model.pcms;

import java.util.Date;

public class PcmsShowcase {
    private Integer scid;

    private String resuestId;

    private String vendorId;

    private String children1Task;

    private String children1LastBuildtime;

    private String children1Type;

    private String children1Reason;

    private String children1Area;

    private String children1Linear;

    private String children1Buildtime;

    private String children1Vendor;

    private String children1Inner;

    private String children1Outer;

    private String children1Amount;

    private Date createTime;

    public Integer getScid() {
        return scid;
    }

    public void setScid(Integer scid) {
        this.scid = scid;
    }

    public String getResuestId() {
        return resuestId;
    }

    public void setResuestId(String resuestId) {
        this.resuestId = resuestId == null ? null : resuestId.trim();
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId == null ? null : vendorId.trim();
    }

    public String getChildren1Task() {
        return children1Task;
    }

    public void setChildren1Task(String children1Task) {
        this.children1Task = children1Task == null ? null : children1Task.trim();
    }

    public String getChildren1LastBuildtime() {
        return children1LastBuildtime;
    }

    public void setChildren1LastBuildtime(String children1LastBuildtime) {
        this.children1LastBuildtime = children1LastBuildtime == null ? null : children1LastBuildtime.trim();
    }

    public String getChildren1Type() {
        return children1Type;
    }

    public void setChildren1Type(String children1Type) {
        this.children1Type = children1Type == null ? null : children1Type.trim();
    }

    public String getChildren1Reason() {
        return children1Reason;
    }

    public void setChildren1Reason(String children1Reason) {
        this.children1Reason = children1Reason == null ? null : children1Reason.trim();
    }

    public String getChildren1Area() {
        return children1Area;
    }

    public void setChildren1Area(String children1Area) {
        this.children1Area = children1Area == null ? null : children1Area.trim();
    }

    public String getChildren1Linear() {
        return children1Linear;
    }

    public void setChildren1Linear(String children1Linear) {
        this.children1Linear = children1Linear == null ? null : children1Linear.trim();
    }

    public String getChildren1Buildtime() {
        return children1Buildtime;
    }

    public void setChildren1Buildtime(String children1Buildtime) {
        this.children1Buildtime = children1Buildtime == null ? null : children1Buildtime.trim();
    }

    public String getChildren1Vendor() {
        return children1Vendor;
    }

    public void setChildren1Vendor(String children1Vendor) {
        this.children1Vendor = children1Vendor == null ? null : children1Vendor.trim();
    }

    public String getChildren1Inner() {
        return children1Inner;
    }

    public void setChildren1Inner(String children1Inner) {
        this.children1Inner = children1Inner == null ? null : children1Inner.trim();
    }

    public String getChildren1Outer() {
        return children1Outer;
    }

    public void setChildren1Outer(String children1Outer) {
        this.children1Outer = children1Outer == null ? null : children1Outer.trim();
    }

    public String getChildren1Amount() {
        return children1Amount;
    }

    public void setChildren1Amount(String children1Amount) {
        this.children1Amount = children1Amount == null ? null : children1Amount.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}