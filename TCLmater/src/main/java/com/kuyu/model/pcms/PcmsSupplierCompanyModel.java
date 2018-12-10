package com.kuyu.model.pcms;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Created by pc on 2018/11/20
 */
@ApiModel("PcmsSupplierCompanyModel(供应商模型)")
@TableName("pcms_supplier_company")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsSupplierCompanyModel extends Model<PcmsSupplierCompanyModel> implements Serializable{

    private static final long serialVersionUID = 7913074281875854565L;
    @TableId(type= IdType.AUTO)
    private Integer id;

    @TableField("vendor_id")
    private String vendor_id;

    @TableField("company")
    private String company;

    @TableField("create_time")
    private String create_time;

    private Integer statement_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Integer getStatement_date() {
        return statement_date;
    }

    public void setStatement_date(Integer statement_date) {
        this.statement_date = statement_date;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
