package com.kuyu.model.pcms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kuyu.vo.pcms.MaterialResult;
import com.kuyu.vo.pcms.PcmsOthertmVo;
import com.kuyu.vo.pcms.PcmsShopVo;
import com.kuyu.vo.pcms.PcmsShowcaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by pc on 2018/11/22
 */
@ApiModel("ReceiptDetailModel(挂单详情模型)")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReceiptDetailModel implements Serializable{

    private static final long serialVersionUID = 7913174281676854565L;

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

    @ApiModelProperty("物料费用=项目费用")
    private Double item_price;

    @ApiModelProperty("分公司")
    private String request_company_code;

    @ApiModelProperty("申请时间")
    private Date create_time;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("门店信息")
    private PcmsShopVo pcmsShopVo;

    @ApiModelProperty("广告物料信息")
    private List<MaterialResult> materialResultList;

    @ApiModelProperty("展台信息")
    private PcmsShowcaseVo pcmsShowcaseVo;

    @ApiModelProperty("其他终端信息")
    private List<PcmsOthertmVo> pcmsOthertmVoList;

    public Integer getItid() {
        return itid;
    }

    public void setItid(Integer itid) {
        this.itid = itid;
    }

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

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public PcmsShopVo getPcmsShopVo() {
        return pcmsShopVo;
    }

    public void setPcmsShopVo(PcmsShopVo pcmsShopVo) {
        this.pcmsShopVo = pcmsShopVo;
    }

    public PcmsShowcaseVo getPcmsShowcaseVo() {
        return pcmsShowcaseVo;
    }

    public void setPcmsShowcaseVo(PcmsShowcaseVo pcmsShowcaseVo) {
        this.pcmsShowcaseVo = pcmsShowcaseVo;
    }

    public List<MaterialResult> getMaterialResultList() {
        return materialResultList;
    }

    public void setMaterialResultList(List<MaterialResult> materialResultList) {
        this.materialResultList = materialResultList;
    }

    public List<PcmsOthertmVo> getPcmsOthertmVoList() {
        return pcmsOthertmVoList;
    }

    public void setPcmsOthertmVoList(List<PcmsOthertmVo> pcmsOthertmVoList) {
        this.pcmsOthertmVoList = pcmsOthertmVoList;
    }
}
