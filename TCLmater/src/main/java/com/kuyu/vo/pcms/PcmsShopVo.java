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
}
