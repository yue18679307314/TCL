package com.kuyu.vo.pcms;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pc on 2018/11/26
 */
public class PcmsSupplierModelVo {

    /**供应商名称*/
    @ApiModelProperty("供应商名称")
    @JsonProperty("vendor_name")
    private String vendor_name;

    /**法人*/
    @ApiModelProperty("法人")
    @JsonProperty("legal_person")
    private String legal_person;

    /**电话*/
    @ApiModelProperty("电话")
    @JsonProperty("mobile")
    private String mobile;

    /**公司代码*/
    @ApiModelProperty("公司代码")
    @JsonProperty("company")
    private String company;

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getLegal_person() {
        return legal_person;
    }

    public void setLegal_person(String legal_person) {
        this.legal_person = legal_person;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
