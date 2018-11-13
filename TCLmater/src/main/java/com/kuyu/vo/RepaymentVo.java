package com.kuyu.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuyu.model.TpmRepaymentFeeDetailModel;
import com.kuyu.model.TpmRepaymentPayInfoModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author tang_zhen
 * @Date 2017/10/20
 * @Description
 */
@ApiModel("报销单的参数接收Vo")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RepaymentVo {

    /**
     * 报销单编号
     */
    @TableId
    @ApiModelProperty("报销单编号")
    private String requestId;
    /**
     * 申请标题
     */
    @ApiModelProperty("申请标题")
    private String requestTitle;
    /**
     * 申请人
     */
    @ApiModelProperty("申请人")
    private String requestUser;
    /**
     * 申请时间与日期
     */
    @ApiModelProperty("申请时间与日期")
    private String requestCreateTime;
    /**
     * 分页当前页
     */
    @ApiModelProperty("分页当前页")
    @TableField("request_title")
    private Integer current;
    /**
     * 分页大小
     */
    @ApiModelProperty("分页大小")
    private Integer size;
    /**
     * 登录人员的工号
     */
    @ApiModelProperty("登录人员的工号")
    private String personCode;


    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public Integer getCurrent() {
        if(current<1)
        {
            current=1;
        }
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        if(size<10)
        {
            size = 10;
        }
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestTitle() {
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
    }

    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser;
    }

    public String getRequestCreateTime() {
        return requestCreateTime;
    }

    public void setRequestCreateTime(String requestCreateTime) {
        this.requestCreateTime = requestCreateTime;
    }



}
