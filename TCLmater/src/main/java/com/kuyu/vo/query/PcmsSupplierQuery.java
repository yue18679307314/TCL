package com.kuyu.vo.query;

import com.kuyu.common.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pc on 2018/11/15
 */
@ApiModel("供应商查询vo")
public class PcmsSupplierQuery extends BasePageQuery {
    /**公司代码*/
    @ApiModelProperty("公司代码")
    private String person_code;

    /**供应商名称*/
    @ApiModelProperty("供应商名称")
    private String vendor_name;

    /**地区*/
    @ApiModelProperty("地区")
    private String city;

    /**业务来往公司*/
    @ApiModelProperty("业务来往公司")
    private String request_company;

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

    public String getRequest_company() {
        return request_company;
    }

    public void setRequest_company(String request_company) {
        this.request_company = request_company;
    }

    public String getPerson_code() {
        return person_code;
    }

    public void setPerson_code(String person_code) {
        this.person_code = person_code;
    }
}
