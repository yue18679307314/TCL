package com.kuyu.vo.query;

import com.kuyu.common.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pc on 2018/12/10
 */
@ApiModel("结算查询类")
public class SettlementQuery extends BasePageQuery {

    @ApiModelProperty("立项单ID")
    private Integer itid;

    @ApiModelProperty("立项单编号")
    private String item_number;

    @ApiModelProperty("原始立项单ID")
    private String request_id;

    @ApiModelProperty("供应商名称")
    private String vendor_name;

    @ApiModelProperty("供应商ID")
    private String vendor_id;

    @ApiModelProperty("费用细类")
    private String subclass;

    @ApiModelProperty("申请人部门")
    private String request_dept;

    @ApiModelProperty("申请人/负责人")
    private String request_user_name;

    @ApiModelProperty("申请标题")
    private String request_title;

    @ApiModelProperty("物料费用=项目费用")
    private Double item_price;
}
