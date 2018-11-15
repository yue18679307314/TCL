package com.kuyu.model;

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
    @JsonProperty("spid")
    private String spid;

    /**供应商名称*/
    @ApiModelProperty("供应商名称")
    @JsonProperty("spname")
    private String spname;


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

    /**供应商类型*/
    @ApiModelProperty("供应商类型")
    @JsonProperty("type")
    private String type;


    /**创建时间*/
    @ApiModelProperty("创建时间")
    @JsonProperty("create_time")
    private Date create_time;

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getSpname() {
        return spname;
    }

    public void setSpname(String spname) {
        this.spname = spname;
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

    @Override
    protected Serializable pkVal() {
        return this.spid;
    }
}
