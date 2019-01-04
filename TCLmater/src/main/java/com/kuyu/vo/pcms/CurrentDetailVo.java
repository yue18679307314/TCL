package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by pc on 2019/1/4
 */
public class CurrentDetailVo {
    @ApiModelProperty("往来数据")
    private List<CurrentDetailModelVo> list;
    @ApiModelProperty("时间")
    private String date;
    @ApiModelProperty("供应商名称")
    private String vendor_name;
    @ApiModelProperty("期初余额")
    private String Initial_balance;

    public List<CurrentDetailModelVo> getList() {
        return list;
    }

    public void setList(List<CurrentDetailModelVo> list) {
        this.list = list;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getInitial_balance() {
        return Initial_balance;
    }

    public void setInitial_balance(String initial_balance) {
        Initial_balance = initial_balance;
    }
}
