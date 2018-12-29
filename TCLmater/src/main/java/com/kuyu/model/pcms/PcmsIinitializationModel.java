package com.kuyu.model.pcms;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by pc on 2018/12/28
 */
@ApiModel("PcmsIinitializationModel(期初余额初始化模型)")
@TableName("pcms_initialization")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsIinitializationModel extends Model<PcmsIinitializationModel> {

    @TableId(type= IdType.AUTO)
    private Integer id;

    @ApiModelProperty("期初余额:")
    private String initial_balance;

    @ApiModelProperty("余额:")
    private String balance;

    @ApiModelProperty("公司编码:")
    private String company;

    @ApiModelProperty("供应商编码:")
    private String vendor_id;

    @ApiModelProperty("状态:")
    private Integer type;

    @ApiModelProperty("月份:")
    private String month;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInitial_balance() {
        return initial_balance;
    }

    public void setInitial_balance(String initial_balance) {
        this.initial_balance = initial_balance;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
