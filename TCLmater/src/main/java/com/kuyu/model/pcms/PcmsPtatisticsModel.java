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

    @ApiModelProperty("期初余额:")
    private String initial_balance;

    @ApiModelProperty("余额:")
    private String balance;

    @ApiModelProperty("期末余额:")
    private String ending_balance;

    @ApiModelProperty("时间:")
    private Date create_time;

    @ApiModelProperty("经办人:")
    private String person_name;

    @ApiModelProperty("电话:")
    private String mobile;

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

    public String getInitial_balance() {
        return initial_balance;
    }

    public void setInitial_balance(String initial_balance) {
        this.initial_balance = initial_balance;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getEnding_balance() {
        return ending_balance;
    }

    public void setEnding_balance(String ending_balance) {
        this.ending_balance = ending_balance;
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

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
