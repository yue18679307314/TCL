package com.kuyu.model.pcms;

public class PcmsPaymentDetail {
    private String fsscBill;

    private String financialNum;

    private String financialMoney;

    private String financialStatus;

    private String financialTime;

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

    public String getFinancialTime() {
        return financialTime;
    }

    public void setFinancialTime(String financialTime) {
        this.financialTime = financialTime == null ? null : financialTime.trim();
    }
}