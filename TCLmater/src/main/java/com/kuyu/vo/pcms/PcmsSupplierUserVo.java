package com.kuyu.vo.pcms;

import com.baomidou.mybatisplus.annotations.TableField;

public class PcmsSupplierUserVo {

    @TableField("openid")
    private String openid;
    @TableField("create_time")
    private String create_time;
    @TableField("openName")
    private String openName;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getOpenName() {
        return openName;
    }

    public void setOpenName(String openName) {
        this.openName = openName;
    }
}
