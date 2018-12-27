package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pc on 2018/12/26
 */
public class PcmsSettlementVo {

    @ApiModelProperty("付款单id")
    private Integer pmid;
    @ApiModelProperty("供应商编码")
    private String vendor_id;
    @ApiModelProperty("供应商名称")
    private String vendor_name;


    public Integer getPmid() {
        return pmid;
    }

    public void setPmid(Integer pmid) {
        this.pmid = pmid;
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
}
