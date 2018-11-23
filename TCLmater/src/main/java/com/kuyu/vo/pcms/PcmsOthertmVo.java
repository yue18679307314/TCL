package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pc on 2018/11/22
 * 其他终端信息
 */
public class PcmsOthertmVo {

    @ApiModelProperty("物料名称")
    private String mrname3;
    @ApiModelProperty("物料规格")
    private String specifications3;
    @ApiModelProperty("数量")
    private Integer children3_count;
    @ApiModelProperty("单价")
    private Double children3_price;
    @ApiModelProperty("总费用")
    private Double children3_amount;

    public String getMrname3() {
        return mrname3;
    }

    public void setMrname3(String mrname3) {
        this.mrname3 = mrname3;
    }

    public String getSpecifications3() {
        return specifications3;
    }

    public void setSpecifications3(String specifications3) {
        this.specifications3 = specifications3;
    }

    public Integer getChildren3_count() {
        return children3_count;
    }

    public void setChildren3_count(Integer children3_count) {
        this.children3_count = children3_count;
    }

    public Double getChildren3_price() {
        return children3_price;
    }

    public void setChildren3_price(Double children3_price) {
        this.children3_price = children3_price;
    }

    public Double getChildren3_amount() {
        return children3_amount;
    }

    public void setChildren3_amount(Double children3_amount) {
        this.children3_amount = children3_amount;
    }
}
