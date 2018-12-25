package com.kuyu.model.pcms;

import java.util.Date;

public class PcmsSettlement {
    private Integer seid;

    private String settNumber;

    private String applicationTitle;

    private String companyCode;

    private String requestUser;

    private String dept;

    private String applicationNotes;

    private Integer payType;

    private String taxNumber;

    private String sumMoney;

    private Integer status;

    private String fccsBill;

    private Date updateTime;

    private Date createTime;

    public Integer getSeid() {
        return seid;
    }

    public void setSeid(Integer seid) {
        this.seid = seid;
    }

    public String getSettNumber() {
        return settNumber;
    }

    public void setSettNumber(String settNumber) {
        this.settNumber = settNumber == null ? null : settNumber.trim();
    }

    public String getApplicationTitle() {
        return applicationTitle;
    }

    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle = applicationTitle == null ? null : applicationTitle.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser == null ? null : requestUser.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    public String getApplicationNotes() {
        return applicationNotes;
    }

    public void setApplicationNotes(String applicationNotes) {
        this.applicationNotes = applicationNotes == null ? null : applicationNotes.trim();
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber == null ? null : taxNumber.trim();
    }

    public String getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(String sumMoney) {
        this.sumMoney = sumMoney == null ? null : sumMoney.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFccsBill() {
        return fccsBill;
    }

    public void setFccsBill(String fccsBill) {
        this.fccsBill = fccsBill == null ? null : fccsBill.trim();
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