package com.kuyu.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author tang_zhen
 * @Date 2018/1/19
 * @Description 移动端某个活动下所有待审核列表的封装Vo
 */
public class ActivityUserListVo
{


    @ApiModelProperty(value = "总数",position = 1)
    private Integer totalSize;
    /**
     * 申请列表
     */
    @ApiModelProperty(value = "申请列表",position = 2)
    private List<AcvitityUserVo> acvitityUserVoList ;


    public List<AcvitityUserVo> getAcvitityUserVoList() {
        return acvitityUserVoList;
    }

    public void setAcvitityUserVoList(List<AcvitityUserVo> acvitityUserVoList) {
        this.acvitityUserVoList = acvitityUserVoList;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }
}
