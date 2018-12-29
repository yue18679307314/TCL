package com.kuyu.vo.pcms;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pc on 2018/12/28
 */
public class CurrentDetailModelVo {

    @TableId(type= IdType.AUTO)
    private Integer id;

    @ApiModelProperty("对账ID")
    private Integer pcms_reconciliation_id;

    @ApiModelProperty("是否新增")
    private Integer type;

    @ApiModelProperty("子表日期:")
    private String create_date;

    @ApiModelProperty("付款单号:")
    private String fssc_bill;

    @ApiModelProperty("本期增加金额:")
    private String increase_amount;

    @ApiModelProperty("本期已付金额:")
    private String pay_amount;

    @ApiModelProperty("期初余额:")
    private String initial_balance;

    @ApiModelProperty("余额:")
    private String balance;

    @ApiModelProperty("备注:")
    private String remarks;

    @ApiModelProperty("主表时间")
    private String pr_time;


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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getFssc_bill() {
        return fssc_bill;
    }

    public void setFssc_bill(String fssc_bill) {
        this.fssc_bill = fssc_bill;
    }

    public String getIncrease_amount() {
        return increase_amount;
    }

    public void setIncrease_amount(String increase_amount) {
        this.increase_amount = increase_amount;
    }

    public String getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(String pay_amount) {
        this.pay_amount = pay_amount;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPr_time() {
        return pr_time;
    }

    public void setPr_time(String pr_time) {
        this.pr_time = pr_time;
    }
}
