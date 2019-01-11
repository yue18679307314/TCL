package com.kuyu.model.pcms;

/**
 * Created by pc on 2019/1/11
 */

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("PcmsProfitCenterModel(期初余额初始化模型)")
@TableName("pcms_profit_center")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsProfitCenterModel extends Model<PcmsProfitCenterModel> {

    @TableId(type= IdType.AUTO)
    private Integer id;

    @ApiModelProperty("公司编码:")
    private String company;

    @ApiModelProperty("挂账公司名称:")
    private String company_name;

    @ApiModelProperty("挂账公司代码")
    private String companyId;

    @ApiModelProperty("共享系统公司组织编码:")
    private String org_code;

    @ApiModelProperty("利润中心编码:")
    private String profit_center_code;

    @ApiModelProperty("长名称:")
    private String long_name;

    @ApiModelProperty("名称:")
    private String name;

    @ApiModelProperty("备注:")
    private String rematk;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getProfit_center_code() {
        return profit_center_code;
    }

    public void setProfit_center_code(String profit_center_code) {
        this.profit_center_code = profit_center_code;
    }

    public String getLong_name() {
        return long_name;
    }

    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRematk() {
        return rematk;
    }

    public void setRematk(String rematk) {
        this.rematk = rematk;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
