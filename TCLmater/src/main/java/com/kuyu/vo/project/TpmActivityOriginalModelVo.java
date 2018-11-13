package com.kuyu.vo.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuyu.model.TpmActivityOriginalModel;
import com.kuyu.model.TpmOtherFeeOriginalModel;
import com.kuyu.model.TpmPromotionFeeOriginalModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author jt_L
 * @Date 2017/9/28
 * @Description
 */
@ApiModel("活动数据项目列表VO")
public class TpmActivityOriginalModelVo extends ActivityOriginalModelVo {

    @JsonProperty("PROMOTION_FEE_LIST")
    @ApiModelProperty(value = "活动子表项目1",position = 1)
    private List<PromotionFeeOriginalModelVo> promotionFeeOriginalList;

    @JsonProperty("OTHER_FEE_LIST")
    @ApiModelProperty(value = "活动子表项目2",position = 2)
    private List<OtherFeeOriginalModelVo> otherFeeOriginalModelList;

    public List<PromotionFeeOriginalModelVo> getPromotionFeeOriginalList(){
        return promotionFeeOriginalList;
    }

    public void setPromotionFeeOriginalList(List<PromotionFeeOriginalModelVo> promotionFeeOriginalList){
        this.promotionFeeOriginalList = promotionFeeOriginalList;
    }

    public List<OtherFeeOriginalModelVo> getOtherFeeOriginalModelList(){
        return otherFeeOriginalModelList;
    }

    public void setOtherFeeOriginalModelList(List<OtherFeeOriginalModelVo> otherFeeOriginalModelList){
        this.otherFeeOriginalModelList = otherFeeOriginalModelList;
    }
}
