package com.kuyu.vo.pcms;

import com.baomidou.mybatisplus.annotations.TableField;

public class PcmsSupplierUserVo {

    @TableField("create_time")
    private String create_time;
    @TableField("open_name")
    private String open_name;
    @TableField("img")
    private String img;

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getOpen_name() {
        return open_name;
    }

    public void setOpen_name(String open_name) {
        this.open_name = open_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
