package com.kuyu.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author tang_zhen
 * @Date 2017/10/16
 * @Description
 */
@ApiModel("传入财务共享的银行卡信息")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AccountVo {

    @ApiModelProperty("申请标题")
    private String MEMO;
    @ApiModelProperty("申请人")
    private  String REQUEST_USER;
    @ApiModelProperty("申请人部门")
    private  String REQUEST_DEPART;
    @ApiModelProperty("银行详细信息")
    private List<TpmAccountVo> DETAIL_LIST ;

    @JSONField(name = "MEMO")
    public String getMEMO() {
        return MEMO;
    }

    public void setMEMO(String MEMO) {
        this.MEMO = MEMO;
    }
    @JSONField(name = "REQUEST_USER")
    public String getREQUEST_USER() {
        return REQUEST_USER;
    }

    public void setREQUEST_USER(String REQUEST_USER) {
        this.REQUEST_USER = REQUEST_USER;
    }
    @JSONField(name = "REQUEST_DEPART")
    public String getREQUEST_DEPART() {
        return REQUEST_DEPART;
    }

    public void setREQUEST_DEPART(String REQUEST_DEPART) {
        this.REQUEST_DEPART = REQUEST_DEPART;
    }
    @JSONField(name = "DETAIL_LIST")
    public List<TpmAccountVo> getDETAIL_LIST() {
        return DETAIL_LIST;
    }

    public void setDETAIL_LIST(List<TpmAccountVo> DETAIL_LIST) {
        this.DETAIL_LIST = DETAIL_LIST;
    }
}
