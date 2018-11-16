package com.kuyu.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by pc on 2018/11/14
 */
@ApiModel("PcmsSupplierVo(供应商模型)")
public class PcmsSupplierVo implements Serializable {

    @TableField(exist=false)
    private static final long serialVersionUID = -9084664322127784665L;

    @ApiModelProperty("主键")
    private String vendor_id;

    /**供应商名称*/
    @ApiModelProperty("供应商名称")
    private String vendor_name;

    /**公司代码*/
    @ApiModelProperty("公司代码")
    private String company;

    /**删除标识*/
    @ApiModelProperty("删除标识")
    private String del_flag;

    /**公司代码删除标识*/
    @ApiModelProperty("公司代码删除标识")
    private String del_comp;

    /**员工工号*/
    @ApiModelProperty("员工工号")
    private String person_id;

    /**供应商类型*/
    @ApiModelProperty("供应商类型")
    private String type;

    /**同步回来的创建时间*/
    @ApiModelProperty("创建时间")
    private String create_date;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }

    public String getDel_comp() {
        return del_comp;
    }

    public void setDel_comp(String del_comp) {
        this.del_comp = del_comp;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
}
