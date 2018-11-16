package com.kuyu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pc on 2018/11/15
 */
@ApiModel("供应商列表VO")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsSupplierListVo {

    /**供应商名称*/
    @ApiModelProperty("供应商名称")
    private String vendor_id;

    /**供应商名称*/
    @ApiModelProperty("供应商名称")
    private String vendor_name;

    /**地区*/
    @ApiModelProperty("地区")
    private String city;

    /**法人*/
    @ApiModelProperty("法人")
    private String legal_person;

    /**电话*/
    @ApiModelProperty("电话")
    private String mobile;

    /**账期*/
    @ApiModelProperty("账期")
    private Integer statement_date;

    /**业务来往公司*/
    @ApiModelProperty("业务来往公司")
    private String request_company;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public Integer getStatement_date() {
        return statement_date;
    }

    public void setStatement_date(Integer statement_date) {
        this.statement_date = statement_date;
    }

    public String getRequest_company() {
        return request_company;
    }

    public void setRequest_company(String request_company) {
        this.request_company = request_company;
    }


}
