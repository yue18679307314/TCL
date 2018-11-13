package com.kuyu.vo.project;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuyu.model.TpmProjectModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author jt_L
 * @Date 2017/9/20
 * @Description
 */
@ApiModel("立项单详情Model")
public class ProjectDetialModelVo extends ProjectModelVo{

    @JsonProperty("ACTIVITY_LIST")
    @ApiModelProperty("立项单详情元数据列表")
    private List<TpmActivityOriginalModelVo> ActivityOriginalList;

    public List<TpmActivityOriginalModelVo> getActivityOriginalList() {
        return ActivityOriginalList;
    }

    public void setActivityOriginalList(List<TpmActivityOriginalModelVo> activityOriginalList) {
        ActivityOriginalList = activityOriginalList;
    }
}
