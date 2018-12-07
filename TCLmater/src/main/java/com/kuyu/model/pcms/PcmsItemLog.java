package com.kuyu.model.pcms;

import java.util.Date;

public class PcmsItemLog {
    private Integer tlid;

    private Integer itid;

    private Integer status;

    private String note;

    private Date createTime;

    public Integer getTlid() {
        return tlid;
    }

    public void setTlid(Integer tlid) {
        this.tlid = tlid;
    }

    public Integer getItid() {
        return itid;
    }

    public void setItid(Integer itid) {
        this.itid = itid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}