package com.kuyu.model.pcms;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by pc on 2018/12/10
 */
@ApiModel("PcmsFeedbackImgModel(反馈信息模型)")
@TableName("pcms_feedback_img")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsFeedbackImgModel extends Model<PcmsFeedbackImgModel> {

    @ApiModelProperty("主键")
    @TableId(type= IdType.AUTO)
    private Integer id;

    @ApiModelProperty("反馈ID")
    private Integer feedback_id;

    @ApiModelProperty("图片地址")
    private String img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(Integer feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
