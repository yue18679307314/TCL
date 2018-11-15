package com.kuyu.model.pcms;

import java.util.Date;

public class PcmsItem {
    private Integer itid;

    private String spid;

    private String requestId;

    private String itUuid;

    private String title;

    private Integer itType;

    private Integer status;

    private Date updateTime;

    private Date createTime;

    public Integer getItid() {
        return itid;
    }

    public void setItid(Integer itid) {
        this.itid = itid;
    }

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid == null ? null : spid.trim();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    public String getItUuid() {
        return itUuid;
    }

    public void setItUuid(String itUuid) {
        this.itUuid = itUuid == null ? null : itUuid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getItType() {
        return itType;
    }

    public void setItType(Integer itType) {
        this.itType = itType;
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