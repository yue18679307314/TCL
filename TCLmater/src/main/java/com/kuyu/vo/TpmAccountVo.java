package com.kuyu.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author tang_zhen
 * @Date 2017/10/16
 * @Description
 */
@ApiModel("传入财务共享的银行卡信息")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TpmAccountVo {
    /**
     *户名
     * */
    @ApiModelProperty("户名")
    private String ACCOUNT_NAME;
    /**
     *开户省
     * */
    @ApiModelProperty("开户省")
    private String OPEN_PROVINCE;
    /**
     *开户市
     * */
    @ApiModelProperty("开户市")
    private String OPEN_CITY;
    /**
     *开户银行名
     * */
    @ApiModelProperty("开户银行名")
    private String ACCOUNT_BANK_NAME;
    /**
     *银行账号
     * */
    @ApiModelProperty("银行账号")
    private String ACCOUNT_VALUE;
    /**
     *开户分行网点
     * */
    @ApiModelProperty("开户分行网点")
    private String OPEN_BRANCH;

    @JSONField(name = "ACCOUNT_NAME")
    public String getACCOUNT_NAME() {
        return ACCOUNT_NAME;
    }

    public void setACCOUNT_NAME(String ACCOUNT_NAME) {
        this.ACCOUNT_NAME = ACCOUNT_NAME;
    }
    @JSONField(name = "OPEN_PROVINCE")
    public String getOPEN_PROVINCE() {
        return OPEN_PROVINCE;
    }

    public void setOPEN_PROVINCE(String OPEN_PROVINCE) {
        this.OPEN_PROVINCE = OPEN_PROVINCE;
    }
    @JSONField(name = "OPEN_CITY")
    public String getOPEN_CITY() {
        return OPEN_CITY;
    }

    public void setOPEN_CITY(String OPEN_CITY) {
        this.OPEN_CITY = OPEN_CITY;
    }
    @JSONField(name = "ACCOUNT_BANK_NAME")
    public String getACCOUNT_BANK_NAME() {
        return ACCOUNT_BANK_NAME;
    }

    public void setACCOUNT_BANK_NAME(String ACCOUNT_BANK_NAME) {
        this.ACCOUNT_BANK_NAME = ACCOUNT_BANK_NAME;
    }
    @JSONField(name = "ACCOUNT_VALUE")
    public String getACCOUNT_VALUE() {
        return ACCOUNT_VALUE;
    }

    public void setACCOUNT_VALUE(String ACCOUNT_VALUE) {
        this.ACCOUNT_VALUE = ACCOUNT_VALUE;
    }
    @JSONField(name = "OPEN_BRANCH")
    public String getOPEN_BRANCH() {
        return OPEN_BRANCH;
    }

    public void setOPEN_BRANCH(String OPEN_BRANCH) {
        this.OPEN_BRANCH = OPEN_BRANCH;
    }
}
