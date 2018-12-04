package com.kuyu.vo.pcms;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by pc on 2018/12/4
 */
public class SupplierMaterialResultVo implements Serializable {

    @ApiModelProperty("物料类别")
    @JsonProperty("category")
    private String category;

    @ApiModelProperty("供应商ID")
    @JsonProperty("vendor_id")
    private String vendor_id;

    @ApiModelProperty("公司 编码ID")
    @JsonProperty("company")
    private String company;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
