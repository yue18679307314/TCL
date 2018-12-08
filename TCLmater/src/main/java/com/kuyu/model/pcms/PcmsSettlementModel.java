package com.kuyu.model.pcms;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pc on 2018/12/8
 */
@ApiModel("PcmsSettlementModel(结算模型)")
@TableName("pcms_settlement")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsSettlementModel extends Model<PcmsSettlementModel> {

    @TableId(type= IdType.AUTO)
    private Integer id;
    @TableField("pay_type")
    private Integer pay_type;
    @TableField("state")
    private Integer state;
    @TableField("application_title")
    private String application_title;
    @TableField("application_notes")
    private String application_notes;
    @TableField("create_time")
    private Date create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPay_type() {
        return pay_type;
    }

    public void setPay_type(Integer pay_type) {
        this.pay_type = pay_type;
    }

    public String getApplication_title() {
        return application_title;
    }

    public void setApplication_title(String application_title) {
        this.application_title = application_title;
    }

    public String getApplication_notes() {
        return application_notes;
    }

    public void setApplication_notes(String application_notes) {
        this.application_notes = application_notes;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
