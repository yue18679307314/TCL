package com.kuyu.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by pc on 2018/12/27
 */
public class ReconciliationVo {

    private Integer id;

    @ApiModelProperty("供应商编码")
    private String vendor_id;

    @ApiModelProperty("供应商名称")
    private String vendor_name;

    @ApiModelProperty("对账月份")
    private String month;

    @ApiModelProperty("对账ID")
    private String reconciliation_id;

    @ApiModelProperty("对账状态")
    private Integer state;

    @ApiModelProperty("创建时间")
    private Date create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getReconciliation_id() {
        return reconciliation_id;
    }

    public void setReconciliation_id(String reconciliation_id) {
        this.reconciliation_id = reconciliation_id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
