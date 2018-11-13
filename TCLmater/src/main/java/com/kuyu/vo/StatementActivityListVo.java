package com.kuyu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author jt_L
 * @Date 2017/9/22 0022
 * @Description
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel("收益活动列表VO")
public class StatementActivityListVo {

    /**
     * 总实际报酬
     */
    @ApiModelProperty(value = "临促所有活动总收益",position = 1)
    private Double totalRealSalary;

    /**
     * 近期总收益
     */
    @ApiModelProperty(value = "近期总收益",position = 2)
    private Double lastTotalSalary;


    @ApiModelProperty(value = "活动详情列表",position = 3)
    List<ActivityVo> activityVoList;


    public List<ActivityVo> getActivityVoList() {
        return activityVoList;
    }

    public void setActivityVoList(List<ActivityVo> activityVoList) {
        this.activityVoList = activityVoList;
    }

    public Double getTotalRealSalary() {
        return totalRealSalary;
    }

    public void setTotalRealSalary(Double totalRealSalary) {
        this.totalRealSalary = totalRealSalary;
    }

    public Double getLastTotalSalary() {
        return lastTotalSalary;
    }

    public void setLastTotalSalary(Double lastTotalSalary) {
        this.lastTotalSalary = lastTotalSalary;
    }
}
