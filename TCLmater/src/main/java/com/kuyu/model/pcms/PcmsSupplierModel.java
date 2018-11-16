package com.kuyu.model.pcms;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zyl on 2018/11/14
 */
@ApiModel("PcmsSupplierModel(供应商模型)")
@TableName("pcms_supplier")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsSupplierModel extends Model<PcmsSupplierModel> {
    @TableField(exist=false)
    private static final long serialVersionUID = 4999163694304048704L;

    /**主键*/
    @TableId
    @ApiModelProperty("主键")
    @JsonProperty("vendor_id")
    private String vendor_id;

    /**供应商名称*/
    @ApiModelProperty("供应商名称")
    @JsonProperty("vendor_name")
    private String vendor_name;

    /**供应商名称*/
    @ApiModelProperty("供应商名称")
    @JsonProperty("statement_date")
    private String statement_date;


    /**供应商类型*/
    @ApiModelProperty("供应商类型")
    @JsonProperty("sptype")
    private String sptype;

    /**地区*/
    @ApiModelProperty("地区")
    @JsonProperty("city")
    private String city;

    /**法人*/
    @ApiModelProperty("法人")
    @JsonProperty("legal_person")
    private String legal_person;

    /**电话*/
    @ApiModelProperty("电话")
    @JsonProperty("mobile")
    private String mobile;

    /**联系人*/
    @ApiModelProperty("联系人")
    @JsonProperty("contacts")
    private String contacts;

    /**地址*/
    @ApiModelProperty("地址")
    @JsonProperty("address")
    private String address;

    /**税号*/
    @ApiModelProperty("税号")
    @JsonProperty("duty_paragrap")
    private String duty_paragrap;

    /**开户行*/
    @ApiModelProperty("开户行")
    @JsonProperty("opening_bank")
    private String opening_bank;

    /**开户账号*/
    @ApiModelProperty("开户账号")
    @JsonProperty("opening_account")
    private String opening_account;

    /**更新时间*/
    @ApiModelProperty("更新时间")
    @JsonProperty("update_time")
    private String update_time;

    /**公司代码*/
    @ApiModelProperty("公司代码")
    @JsonProperty("company")
    private String company;

    /**删除标识*/
    @ApiModelProperty("删除标识")
    @JsonProperty("del_flag")
    private String del_flag;

    /**公司代码删除标识*/
    @ApiModelProperty("公司代码删除标识")
    @JsonProperty("del_comp")
    private String del_comp;

    /**员工工号*/
    @ApiModelProperty("员工工号")
    @JsonProperty("person_id")
    private String person_id;

    /**创建时间*/
    @ApiModelProperty("创建时间")
    @JsonProperty("create_date")
    private Date create_date;

    /**创建时间*/
    @ApiModelProperty("创建时间")
    @JsonProperty("create_time")
    private Date create_time;

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
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


    public String getSptype() {
        return sptype;
    }

    public void setSptype(String sptype) {
        this.sptype = sptype;
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

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDuty_paragrap() {
        return duty_paragrap;
    }

    public void setDuty_paragrap(String duty_paragrap) {
        this.duty_paragrap = duty_paragrap;
    }

    public String getOpening_bank() {
        return opening_bank;
    }

    public void setOpening_bank(String opening_bank) {
        this.opening_bank = opening_bank;
    }

    public String getOpening_account() {
        return opening_account;
    }

    public void setOpening_account(String opening_account) {
        this.opening_account = opening_account;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
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

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getStatement_date() {
        return statement_date;
    }

    public void setStatement_date(String statement_date) {
        this.statement_date = statement_date;
    }

    @Override
    protected Serializable pkVal() {
        return this.vendor_id;
    }
}
