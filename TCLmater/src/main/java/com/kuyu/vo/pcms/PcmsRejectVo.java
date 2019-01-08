package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by pc on 2019/1/8
 */
public class PcmsRejectVo {
    /**id*/
    private Integer id;

    /**创建时间*/
    @ApiModelProperty("创建时间")
    private Date create_time;

    @ApiModelProperty("拆单的主键ID")
    private Integer itid;

    @ApiModelProperty("驳回理由")
    private String context;

    @ApiModelProperty("操作人")
    private String operator;

    @ApiModelProperty("状态")
    private Integer type;

    private String employeenumber;

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

    public Integer getItid() {
        return itid;
    }

    public void setItid(Integer itid) {
        this.itid = itid;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(String employeenumber) {
        this.employeenumber = employeenumber;
    }
}
