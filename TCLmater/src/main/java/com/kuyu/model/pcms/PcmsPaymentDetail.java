package com.kuyu.model.pcms;

import java.util.Date;

public class PcmsPaymentDetail {
    private Integer pmdid;

    private String fsscBill;

    private String financialNum;

    private String financialMoney;

    private String financialStatus;

    private Date createTime;

    public Integer getPmdid() {
        return pmdid;
    }

    public void setPmdid(Integer pmdid) {
        this.pmdid = pmdid;
    }

    public String getFsscBill() {
        return fsscBill;
    }

    public void setFsscBill(String fsscBill) {
        this.fsscBill = fsscBill == null ? null : fsscBill.trim();
    }

    public String getFinancialNum() {
        return financialNum;
    }

    public void setFinancialNum(String financialNum) {
        this.financialNum = financialNum == null ? null : financialNum.trim();
    }

    public String getFinancialMoney() {
        return financialMoney;
    }

    public void setFinancialMoney(String financialMoney) {
        this.financialMoney = financialMoney == null ? null : financialMoney.trim();
    }

    public String getFinancialStatus() {
        return financialStatus;
    }

    public void setFinancialStatus(String financialStatus) {
        this.financialStatus = financialStatus == null ? null : financialStatus.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}