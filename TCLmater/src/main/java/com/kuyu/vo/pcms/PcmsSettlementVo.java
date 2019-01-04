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
    @ApiModelProperty("付款单单号")
    private String fssc_bill;
    private String company;

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

    public String getFssc_bill() {
        return fssc_bill;
    }

    public void setFssc_bill(String fssc_bill) {
        this.fssc_bill = fssc_bill;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
