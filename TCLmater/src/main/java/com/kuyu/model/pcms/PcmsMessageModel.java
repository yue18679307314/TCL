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
 * Created by pc on 2019/1/3
 */
@ApiModel("PcmsMessageModel(消息模型)")
@TableName("pcms_message")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsMessageModel extends Model<PcmsMessageModel> {

    @TableId(type= IdType.AUTO)
    private Integer id;
    @ApiModelProperty("时间")
    private Date create_time;
    @ApiModelProperty("其他id")
    private String other_id;
    @ApiModelProperty("内容")
    private String context;
    @ApiModelProperty("供应商id")
    private String vendor_id;
    @ApiModelProperty("状态 0-未读 1-已读")
    private int state;
    @ApiModelProperty("类型1-未结明细 2-对账")
    private int type;

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

    public String getOther_id() {
        return other_id;
    }

    public void setOther_id(String other_id) {
        this.other_id = other_id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
