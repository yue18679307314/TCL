package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by pc on 2019/1/3
 */
public class DetailVo {
    @ApiModelProperty("时间")
    private List<DetailListVo> detailListVo;
    @ApiModelProperty("对账编码")
    private String reconciliation_id;
    @ApiModelProperty("月份")
    private String month;
    @ApiModelProperty("供应商名称")
    private String vendor_name;

    public List<DetailListVo> getDetailListVo() {
        return detailListVo;
    }

    public void setDetailListVo(List<DetailListVo> detailListVo) {
        this.detailListVo = detailListVo;
    }

    public String getReconciliation_id() {
        return reconciliation_id;
    }

    public void setReconciliation_id(String reconciliation_id) {
        this.reconciliation_id = reconciliation_id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }
}
