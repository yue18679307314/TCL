package com.kuyu.model.pcms;

public class PcmsSupplierUser {
    private Integer suid;

    private String spid;

    private String openid;

    public Integer getSuid() {
        return suid;
    }

    public void setSuid(Integer suid) {
        this.suid = suid;
    }

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid == null ? null : spid.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }
}