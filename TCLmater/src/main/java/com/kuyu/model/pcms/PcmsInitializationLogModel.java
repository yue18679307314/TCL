package com.kuyu.model.pcms;

/**
 * Created by pc on 2019/1/10
 */

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel("PcmsInitializationLogModel(期初余额初始化模型)")
@TableName("pcms_initialization_log")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsInitializationLogModel extends Model<PcmsInitializationLogModel> {

    @TableId(type= IdType.AUTO)
    private Integer id;

    @ApiModelProperty("期初余额:")
    private String initial_balance;

    @ApiModelProperty("公司编码:")
    private String company;

    @ApiModelProperty("供应商编码:")
    private String vendor_id;

    @ApiModelProperty("时间")
    private Date create_time;

    @ApiModelProperty("供应商名称:")
    private String vendor_name;

    @ApiModelProperty("科目名称:")
    private String subject_name;


    @ApiModelProperty("科目:")
    private String subject;

    @ApiModelProperty("公司名称:")
    private String company_name;

    @ApiModelProperty("挂账所属分公司:")
    private String accounting_company;

    @ApiModelProperty("共享系统公司组织编码:")
    private String org_code;

    @ApiModelProperty("利润中心")
    private String profit_center;

    @ApiModelProperty("挂账所属分公司代码")
    private String companyId;

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

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getAccounting_company() {
        return accounting_company;
    }

    public void setAccounting_company(String accounting_company) {
        this.accounting_company = accounting_company;
    }

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getProfit_center() {
        return profit_center;
    }

    public void setProfit_center(String profit_center) {
        this.profit_center = profit_center;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
