package com.kuyu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author jt_L
 * @Date 2017/9/27
 * @Description
 */
@ApiModel("活动首页列表VO")
public class ActivityListVo {

    @ApiModelProperty("我参加中的活动列表")
    private List<ActivityVo> myActivityList;

    @ApiModelProperty("全部活动列表")
    private List<ActivityVo> allActivityList;

    public List<ActivityVo> getMyActivityList() {
        return myActivityList;
    }

    public void setMyActivityList(List<ActivityVo> myActivityList) {
        this.myActivityList = myActivityList;
    }

    public List<ActivityVo> getAllActivityList() {
        return allActivityList;
    }

    public void setAllActivityList(List<ActivityVo> allActivityList) {
        this.allActivityList = allActivityList;
    }
}
