package com.kuyu.model.pcms;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pc on 2018/12/10
 */
@ApiModel("PcmsFeedbackModel(反馈信息模型)")
@TableName("pcms_feedback")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsFeedbackModel extends Model<PcmsFeedbackModel> {

    @ApiModelProperty("主键")
    @TableId(type= IdType.AUTO)
    private Integer id;

    @ApiModelProperty("创建时间")
    private Date create_time;

    @ApiModelProperty("反馈说明")
    private String context;

    @ApiModelProperty("反馈主键")
    private Integer transfer_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getTransfer_id() {
        return transfer_id;
    }

    public void setTransfer_id(Integer transfer_id) {
        this.transfer_id = transfer_id;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
