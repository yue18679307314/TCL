package com.kuyu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author tang_zhen
 * @Date 2018/01/15
 * @Description 省市数据VO
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel("省市数据VO")
public class ProvinceVo {

    /**
     * 活动uuid
     */
    @ApiModelProperty(value = "省名",position = 1)
    private String provinceName;

    /**
     * 活动uuid
     */
    @ApiModelProperty(value = "省市Id",position = 2)
    private String provinceId;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
}
