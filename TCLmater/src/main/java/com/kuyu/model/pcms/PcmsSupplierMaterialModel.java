package com.kuyu.model.pcms;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel("PcmsSupplierMaterialModel(供应商物料模型)")
@TableName("pcms_supplier_material")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsSupplierMaterialModel extends Model<PcmsSupplierMaterialModel> {
    @TableField(exist=false)
    private static final long serialVersionUID = 4999163694305048704L;
    /**id*/
    @TableId(type= IdType.AUTO)
    private Integer id;

    /**供应商id*/
    @ApiModelProperty("物料类别")
    @JsonProperty("category")
    private String category;

    @ApiModelProperty("物料规格")
    @JsonProperty("specifications")
    private String specifications;

    @ApiModelProperty("单位")
    @JsonProperty("unit")
    private String unit;

    @ApiModelProperty("参考价")
    @JsonProperty("comparisonPrice")
    private Double comparison_price;


    @ApiModelProperty("备注")
    @JsonProperty("note")
    private String note;

    @ApiModelProperty("版本号")
    @JsonProperty("version")
    private String version;

    /**供应商id*/
    @ApiModelProperty("供应商id")
    @JsonProperty("vendor_id")
    private String vendor_id;

    /**供应商名称*/
    @ApiModelProperty("供应商名称")
    @JsonProperty("vendor_name")
    private String vendor_name;

    /**公司代码*/
    @ApiModelProperty("公司代码")
    @JsonProperty("company")
    private String company;

    /**创建时间*/
    @ApiModelProperty("创建时间")
    @JsonProperty("create_time")
    private Date create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getComparison_price() {
        return comparison_price;
    }

    public void setComparison_price(Double comparison_price) {
        this.comparison_price = comparison_price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
