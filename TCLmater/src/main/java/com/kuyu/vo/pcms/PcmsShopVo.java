package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pc on 2018/11/22
 * 门店信息VO
 */
public class PcmsShopVo {

    @ApiModelProperty("门店名称")
    private String shop_name;

    @ApiModelProperty("所在省")
    private String shop_province;

    @ApiModelProperty("所在市")
    private String shop_city;

    @ApiModelProperty("所在县")
    private String shop_county;

    @ApiModelProperty("crm门店编码")
    private String shop_crm_code;

    @ApiModelProperty("门店级别")
    private String shop_level;

    @ApiModelProperty("所属分部")
    private String shop_branch;

    @ApiModelProperty("渠道")
    private String shop_channel;

    @ApiModelProperty("门店销售任务")
    private String detail_task;


    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_province() {
        return shop_province;
    }

    public void setShop_province(String shop_province) {
        this.shop_province = shop_province;
    }

    public String getShop_city() {
        return shop_city;
    }

    public void setShop_city(String shop_city) {
        this.shop_city = shop_city;
    }

    public String getShop_county() {
        return shop_county;
    }

    public void setShop_county(String shop_county) {
        this.shop_county = shop_county;
    }

    public String getShop_crm_code() {
        return shop_crm_code;
    }

    public void setShop_crm_code(String shop_crm_code) {
        this.shop_crm_code = shop_crm_code;
    }

    public String getShop_level() {
        return shop_level;
    }

    public void setShop_level(String shop_level) {
        this.shop_level = shop_level;
    }

    public String getShop_branch() {
        return shop_branch;
    }

    public void setShop_branch(String shop_branch) {
        this.shop_branch = shop_branch;
    }

    public String getShop_channel() {
        return shop_channel;
    }

    public void setShop_channel(String shop_channel) {
        this.shop_channel = shop_channel;
    }

    public String getDetail_task() {
        return detail_task;
    }

    public void setDetail_task(String detail_task) {
        this.detail_task = detail_task;
    }
}
