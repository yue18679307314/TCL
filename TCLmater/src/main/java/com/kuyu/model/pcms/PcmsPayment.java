package com.kuyu.model.pcms;

import java.util.Date;

public class PcmsPayment {
    private Integer pmid;

    private Integer type;

    private String fsscBill;

    private String vendorId;

    private String accountNumber;

    private String accountName;

    private String payeeName;

    private String paymentType;

    private String recommentDate;

    private String payAmount;

    private String payStandard;

    private String billExpireDate;

    private String bankAccountNumber;

    private Integer status;

    private Date createTime;

    public Integer getPmid() {
        return pmid;
    }

    public void setPmid(Integer pmid) {
        this.pmid = pmid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFsscBill() {
        return fsscBill;
    }

    public void setFsscBill(String fsscBill) {
        this.fsscBill = fsscBill == null ? null : fsscBill.trim();
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId == null ? null : vendorId.trim();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber == null ? null : accountNumber.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public String getRecommentDate() {
        return recommentDate;
    }

    public void setRecommentDate(String recommentDate) {
        this.recommentDate = recommentDate == null ? null : recommentDate.trim();
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount == null ? null : payAmount.trim();
    }

    public String getPayStandard() {
        return payStandard;
    }

    public void setPayStandard(String payStandard) {
        this.payStandard = payStandard == null ? null : payStandard.trim();
    }

    public String getBillExpireDate() {
        return billExpireDate;
    }

    public void setBillExpireDate(String billExpireDate) {
        this.billExpireDate = billExpireDate == null ? null : billExpireDate.trim();
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber == null ? null : bankAccountNumber.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}