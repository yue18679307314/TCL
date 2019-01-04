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
 * Created by pc on 2019/1/4
 */
@ApiModel("UnspecifiedDetailsModel(期初余额初始化模型)")
@TableName("pcms_unspecified_details")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UnspecifiedDetailsModel extends Model<UnspecifiedDetailsModel> {

    @TableId(type= IdType.AUTO)
    private Integer id;
    @ApiModelProperty("结算编号")
    private String sett_number;
    @ApiModelProperty("立项单id")
    private Integer pcms_reconciliation_id;
    @ApiModelProperty("金额")
    private Double price;
    @ApiModelProperty("状态")
    private Integer state;
    @ApiModelProperty("时间")
    private Date create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSett_number() {
        return sett_number;
    }

    public void setSett_number(String sett_number) {
        this.sett_number = sett_number;
    }

    public Integer getPcms_reconciliation_id() {
        return pcms_reconciliation_id;
    }

    public void setPcms_reconciliation_id(Integer pcms_reconciliation_id) {
        this.pcms_reconciliation_id = pcms_reconciliation_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
