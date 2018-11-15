package com.kuyu.model.pcms;

import java.util.Date;

public class PcmsMaterialBill {
    private Integer mbid;

    private Integer blid;

    private String spid;

    private Integer status;

    private Date updateTime;

    private Date createTime;

    public Integer getMbid() {
        return mbid;
    }

    public void setMbid(Integer mbid) {
        this.mbid = mbid;
    }

    public Integer getBlid() {
        return blid;
    }

    public void setBlid(Integer blid) {
        this.blid = blid;
    }

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid == null ? null : spid.trim();
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