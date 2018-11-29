package com.kuyu.vo.pcms;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by pc on 2018/11/29
 */
public class SupplierMaterialVo implements Serializable {


    private String openid;

    @ApiModelProperty("物料类别")
    @JsonProperty("category")
    private String category;

    @ApiModelProperty("物料规格")
    @JsonProperty("specifications")
    private String specifications;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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
}
