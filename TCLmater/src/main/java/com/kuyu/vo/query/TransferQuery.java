package com.kuyu.vo.query;

import com.kuyu.common.BasePageQuery;
import io.swagger.annotations.ApiModel;

/**
 * Created by pc on 2018/12/10
 */
@ApiModel("转办查询类")
public class TransferQuery extends BasePageQuery {

    private String vendor_name;

    private String request_user_name;

    private String person_name;

    private String employeenumber;

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getRequest_user_name() {
        return request_user_name;
    }

    public void setRequest_user_name(String request_user_name) {
        this.request_user_name = request_user_name;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(String employeenumber) {
        this.employeenumber = employeenumber;
    }
}
