package com.kuyu.vo;

import com.kuyu.model.TpmActivityOriginalModel;
import com.kuyu.model.TpmOtherFeeOriginalModel;
import com.kuyu.model.TpmProjectModel;
import com.kuyu.model.TpmPromotionFeeOriginalModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author jt_L
 * @Date 2017/10/17
 * @Description
 */
public class ProjectDetailVo{

    /**
     * 立项单
     */
    @ApiModelProperty(value = "立项单", position = 1)
    private TpmProjectModel project;
    /**
     * 立项单活动元数据
     */
    @ApiModelProperty(value = "立项单活动元数据", position = 2)
    private List<TpmActivityOriginalModel> activityOriginalList;
    /**
     * 活动子表项目1
     */
    @ApiModelProperty(value = "活动子表项目1", position = 3)
    private List<TpmPromotionFeeOriginalModel> promotionFeeOriginalList;
    /**
     * 活动子表项目2
     */
    @ApiModelProperty(value = "活动子表项目2", position = 4)
    private List<TpmOtherFeeOriginalModel> otherFeeOriginalModelList;

    public TpmProjectModel getProject(){
        return project;
    }

    public void setProject(TpmProjectModel project){
        this.project = project;
    }

    public List<TpmActivityOriginalModel> getActivityOriginalList(){
        return activityOriginalList;
    }

    public void setActivityOriginalList(List<TpmActivityOriginalModel> activityOriginalList){
        this.activityOriginalList = activityOriginalList;
    }

    public List<TpmPromotionFeeOriginalModel> getPromotionFeeOriginalList(){
        return promotionFeeOriginalList;
    }

    public void setPromotionFeeOriginalList(List<TpmPromotionFeeOriginalModel> promotionFeeOriginalList){
        this.promotionFeeOriginalList = promotionFeeOriginalList;
    }

    public List<TpmOtherFeeOriginalModel> getOtherFeeOriginalModelList(){
        return otherFeeOriginalModelList;
    }

    public void setOtherFeeOriginalModelList(List<TpmOtherFeeOriginalModel> otherFeeOriginalModelList){
        this.otherFeeOriginalModelList = otherFeeOriginalModelList;
    }

}
