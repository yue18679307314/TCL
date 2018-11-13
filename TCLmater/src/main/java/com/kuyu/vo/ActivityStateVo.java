package com.kuyu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author tangzhen
 * @Date 2017/12/07
 * @Description 活动状态的展示VO
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel("活动状态展示数据VO")
public class ActivityStateVo {
    /**
     * 0招募中（未开始） 1招募完成(未开始) 2招募中（进行中） 3已结束 4招募完成(进行中)
     */
    //    @ApiModelProperty(value = "该日期活动状态 0 已招满 1未招满未申请 2未招满已申请",position = 18)
    @ApiModelProperty(value = "0招募中（未开始） 1招募完成(未开始) 2招募中（进行中） 3已结束 4招募完成(进行中)")
    private Integer activityDaySate;
    /**
     * 活动状态 0招募中 1招募完成(未开始) 2进行中 3已结束
     */
    @ApiModelProperty(value = "临促申请状态:-1未申请 0已拒绝参加 1已申请 2申请通过 3已参加 4已结束 5已剔除（退出）")
    private Integer requestState;

    /**
     * 日期
     */
    @ApiModelProperty(value = "开始与结束时间内的 日期")
    private String date;

    /**
     * 审核原因
     * @return
     */
    @ApiModelProperty(value = "审批通过或者不通过原因")
    private  String approvalReason;

    public String getApprovalReason() {
        return approvalReason;
    }

    public void setApprovalReason(String approvalReason) {
        this.approvalReason = approvalReason;
    }

    public Integer getActivityDaySate() {
        return activityDaySate;
    }

    public void setActivityDaySate(Integer activityDaySate) {
        this.activityDaySate = activityDaySate;
    }

    public Integer getRequestState() {
        return requestState;
    }

    public void setRequestState(Integer requestState) {
        this.requestState = requestState;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

