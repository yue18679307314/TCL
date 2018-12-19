package com.kuyu.model.pcms;

import java.util.Date;

public class PcmsSettlementItem {
    private Integer setid;

    private Integer itid;

    private String sedetailId;

    private String sedetailMoney;

    private String sedetailMemo;

    private String taxType;

    private String taxNumber;

    private String taxRate;

    private Integer isLast;

    private Integer settlementId;

    private Date createTime;

    public Integer getSetid() {
        return setid;
    }

    public void setSetid(Integer setid) {
        this.setid = setid;
    }

    public Integer getItid() {
        return itid;
    }

    public void setItid(Integer itid) {
        this.itid = itid;
    }

    public String getSedetailId() {
        return sedetailId;
    }

    public void setSedetailId(String sedetailId) {
        this.sedetailId = sedetailId == null ? null : sedetailId.trim();
    }

    public String getSedetailMoney() {
        return sedetailMoney;
    }

    public void setSedetailMoney(String sedetailMoney) {
        this.sedetailMoney = sedetailMoney == null ? null : sedetailMoney.trim();
    }

    public String getSedetailMemo() {
        return sedetailMemo;
    }

    public void setSedetailMemo(String sedetailMemo) {
        this.sedetailMemo = sedetailMemo == null ? null : sedetailMemo.trim();
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType == null ? null : taxType.trim();
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber == null ? null : taxNumber.trim();
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate == null ? null : taxRate.trim();
    }

    public Integer getIsLast() {
        return isLast;
    }

    public void setIsLast(Integer isLast) {
        this.isLast = isLast;
    }

    public Integer getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(Integer settlementId) {
        this.settlementId = settlementId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}