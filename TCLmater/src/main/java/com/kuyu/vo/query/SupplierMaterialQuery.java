package com.kuyu.vo.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuyu.common.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("物料列表vo")
public class SupplierMaterialQuery extends BasePageQuery {

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
    /**公司代码*/
    @ApiModelProperty("公司代码")
    @JsonProperty("company")
    private String company;

    @ApiModelProperty("版本号")
    @JsonProperty("version")
    private Integer version;

    private String ranges;

    @ApiModelProperty("供应商编码")
    private String vendor_id;

    private String url;

    private Integer state;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getRanges() {
        return ranges;
    }

    public void setRanges(String ranges) {
        this.ranges = ranges;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
