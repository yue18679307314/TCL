package com.kuyu.model.pcms;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pc on 2018/12/8
 */
@ApiModel("PcmsSettlementItemModel(立项单结算模型)")
@TableName("pcms_settlement_item")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsSettlementItemModel extends Model<PcmsSettlementItemModel> {

    @TableId(type= IdType.AUTO)
    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("结算单ID")
    @TableField("settlement_id")
    private Integer settlement_id;
    @ApiModelProperty("立项单ID")
    @TableField("itid")
    private Integer itid;
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSettlement_id() {
        return settlement_id;
    }

    public void setSettlement_id(Integer settlement_id) {
        this.settlement_id = settlement_id;
    }

    public Integer getItid() {
        return itid;
    }

    public void setItid(Integer itid) {
        this.itid = itid;
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
