package com.kuyu.model.pcms;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pc on 2018/11/21
 */
@ApiModel("ReceiptModel(挂单模型)")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReceiptModel implements Serializable {

    private static final long serialVersionUID = 7913074281676854565L;

    @ApiModelProperty("立项单ID")
    private Integer itid;

    @ApiModelProperty("立项单编号")
    private String item_number;

    @ApiModelProperty("申请人部门")
    private String request_dept;

    @ApiModelProperty("申请人/负责人")
    private String request_user_name;

    @ApiModelProperty("申请标题")
    private String request_title;

    @ApiModelProperty("费用细类")
    private String cost;

    @ApiModelProperty("物料费用=项目费用")
    private Double item_price;

    @ApiModelProperty("分公司")
    private String request_company_code;

    @ApiModelProperty("申请时间")
    private String create_time;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("状态")
    private Integer status;

    public String getItem_number() {
        return item_number;
    }

    public void setItem_number(String item_number) {
        this.item_number = item_number;
    }

    public String getRequest_dept() {
        return request_dept;
    }

    public void setRequest_dept(String request_dept) {
        this.request_dept = request_dept;
    }

    public String getRequest_user_name() {
        return request_user_name;
    }

    public void setRequest_user_name(String request_user_name) {
        this.request_user_name = request_user_name;
    }

    public String getRequest_title() {
        return request_title;
    }

    public void setRequest_title(String request_title) {
        this.request_title = request_title;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Double getItem_price() {
        return item_price;
    }

    public void setItem_price(Double item_price) {
        this.item_price = item_price;
    }

    public String getRequest_company_code() {
        return request_company_code;
    }

    public void setRequest_company_code(String request_company_code) {
        this.request_company_code = request_company_code;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getItid() {
        return itid;
    }

    public void setItid(Integer itid) {
        this.itid = itid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
