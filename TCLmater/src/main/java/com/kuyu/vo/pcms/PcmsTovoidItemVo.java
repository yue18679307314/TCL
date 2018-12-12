package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by pc on 2018/12/11
 */
public class PcmsTovoidItemVo {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("立项单ID")
    private Integer itid;
    @ApiModelProperty("时间")
    private Date create_time;
    @ApiModelProperty("理由")
    private String context;
    @ApiModelProperty("作废人")
    private String operator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
