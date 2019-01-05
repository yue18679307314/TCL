package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pc on 2019/1/5
 */
public class PendingMaterialDetailVo {
    @ApiModelProperty("物料类别")
    private String category;
    @ApiModelProperty("物料规格")
    private String specifications;
    @ApiModelProperty("立项单ID")
    private Integer itid;
    @ApiModelProperty("总价")
    private Double all_price;
    @ApiModelProperty("数量")
    private Integer number;
    @ApiModelProperty("价格")
    private Double comparison_price;
    @ApiModelProperty("原始立项单ID")
    private String request_id;

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

    public Integer getItid() {
        return itid;
    }

    public void setItid(Integer itid) {
        this.itid = itid;
    }

    public Double getAll_price() {
        return all_price;
    }

    public void setAll_price(Double all_price) {
        this.all_price = all_price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getComparison_price() {
        return comparison_price;
    }

    public void setComparison_price(Double comparison_price) {
        this.comparison_price = comparison_price;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }
}
