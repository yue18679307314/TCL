package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pc on 2019/1/4
 */
public class PcmsReconciliationVo {

    @ApiModelProperty("本期增加金额")
    private String increase_amount;

    @ApiModelProperty("已付金额")
    private String pay_amount;

    @ApiModelProperty("供应商名称")
    private String vendor_name;

    @ApiModelProperty("对账月份")
    private String month;

    @ApiModelProperty("对账ID")
    private String pcms_reconciliation_id;

    public String getIncrease_amount() {
        return increase_amount;
    }

    public void setIncrease_amount(String increase_amount) {
        this.increase_amount = increase_amount;
    }

    public String getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(String pay_amount) {
        this.pay_amount = pay_amount;
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

    public String getPcms_reconciliation_id() {
        return pcms_reconciliation_id;
    }

    public void setPcms_reconciliation_id(String pcms_reconciliation_id) {
        this.pcms_reconciliation_id = pcms_reconciliation_id;
    }
}
