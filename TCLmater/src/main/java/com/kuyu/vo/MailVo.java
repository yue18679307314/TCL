package com.kuyu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author tang_zhen
 * @Date 2018/1/15
 * @Description
 */
@ApiModel("查询活动负责人和财务负责人的邮箱的VO")
public class MailVo {

    /**
     * 活动负责人的邮箱
     */
    @ApiModelProperty(value = "活动负责人的邮箱",position = 1)
    private String mailOfManager;

    /**
     * 财务负责人的邮箱
     */
    @ApiModelProperty(value = "财务负责人的邮箱",position = 2)
    private String mailOfFinance;


    /**
     * paymentId对应临促的姓名
     */
    @ApiModelProperty(value = "财务负责人的邮箱",position = 3)
    private String userName;

    /**
     * paymentId对应临促的微信名
     */
    @ApiModelProperty(value = "财务负责人的邮箱",position = 4)
    private String wxName;

    /**
     * paymentId对应临促的微信名
     */
    @ApiModelProperty(value = "财务负责人的邮箱",position = 5)
    private String userMobile;

    /**
     * 立项单号
     */
    @ApiModelProperty(value = "立项单号",position = 6)
    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getMailOfManager() {
        return mailOfManager;
    }

    public void setMailOfManager(String mailOfManager) {
        this.mailOfManager = mailOfManager;
    }

    public String getMailOfFinance() {
        return mailOfFinance;
    }

    public void setMailOfFinance(String mailOfFinance) {
        this.mailOfFinance = mailOfFinance;
    }
}
