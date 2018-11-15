package com.kuyu.model.pcms;

import java.util.Date;

public class PcmsBill {
    private Integer blid;

    private Integer uuid;

    private String itid;

    private String spid;

    private Integer mrid;

    private Integer number;

    private Double price;

    private Integer statementDate;

    private Integer status;

    private Date updateTime;

    private Date createTime;

    public Integer getBlid() {
        return blid;
    }

    public void setBlid(Integer blid) {
        this.blid = blid;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getItid() {
        return itid;
    }

    public void setItid(String itid) {
        this.itid = itid == null ? null : itid.trim();
    }

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid == null ? null : spid.trim();
    }

    public Integer getMrid() {
        return mrid;
    }

    public void setMrid(Integer mrid) {
        this.mrid = mrid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(Integer statementDate) {
        this.statementDate = statementDate;
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