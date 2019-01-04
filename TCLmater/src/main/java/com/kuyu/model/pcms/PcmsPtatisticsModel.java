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
 * Created by pc on 2018/12/28
 */
@ApiModel("PcmsPtatisticsModel(对账中间表模型)")
@TableName("pcms_statistics")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsPtatisticsModel extends Model<PcmsPtatisticsModel> {

    @TableId(type= IdType.AUTO)
    private Integer id;

    @ApiModelProperty("对账ID")
    private Integer pcms_reconciliation_id;

    @ApiModelProperty("type")
    private Integer type;

    @ApiModelProperty("备注:")
    private String remark;

    @ApiModelProperty("创建时间:")
    private Date create_time;

    @ApiModelProperty("更新时间:")
    private Date update_time;

    @ApiModelProperty("供应商ID:")
    private String vendor_id;

    @ApiModelProperty("经办人:")
    private String person_name;

    @ApiModelProperty("电话:")
    private String mobile;

    @ApiModelProperty("金额:")
    private String amount;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPcms_reconciliation_id() {
        return pcms_reconciliation_id;
    }

    public void setPcms_reconciliation_id(Integer pcms_reconciliation_id) {
        this.pcms_reconciliation_id = pcms_reconciliation_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
