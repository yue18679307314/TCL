package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by pc on 2018/12/11
 */
public class ReceiptDetailVo {
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

    @ApiModelProperty("派遣人")
    private String dispatch;

    @ApiModelProperty("派遣时间")
    private String dispatch_time;

    @ApiModelProperty("结束时间")
    private String end_time;

    @ApiModelProperty("申请人部门")
    private String request_dept;

    @ApiModelProperty("申请人/负责人")
    private String request_user_name;

    private String create_user;

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

    @ApiModelProperty("作废信息")
    private PcmsTovoidItemVo pcmsTovoidItemVo;

    @ApiModelProperty("门店信息")
    private PcmsShopVo pcmsShopVo;

    @ApiModelProperty("广告物料信息")
    private List<MaterialResult> materialResultList;

    @ApiModelProperty("展台信息")
    private PcmsShowcaseVo pcmsShowcaseVo;

    @ApiModelProperty("其他终端信息")
    private List<PcmsOthertmVo> pcmsOthertmVoList;

    @ApiModelProperty("待验收的物料信息")
    private List<PendingMaterialVo> pcmsPendingMaterialList;

    @ApiModelProperty("驳回信息")
    private List<PcmsRejectLogVo> pcmsRejectLogList;

    @ApiModelProperty("转办信息")
    private List<List<Transfer>> pcmsTransferVoList;

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

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getDispatch() {
        return dispatch;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public String getDispatch_time() {
        return dispatch_time;
    }

    public void setDispatch_time(String dispatch_time) {
        this.dispatch_time = dispatch_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getSubclass() {
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public List<PendingMaterialVo> getPcmsPendingMaterialList() {
        return pcmsPendingMaterialList;
    }

    public void setPcmsPendingMaterialList(List<PendingMaterialVo> pcmsPendingMaterialList) {
        this.pcmsPendingMaterialList = pcmsPendingMaterialList;
    }

    public List<PcmsRejectLogVo> getPcmsRejectLogList() {
        return pcmsRejectLogList;
    }

    public void setPcmsRejectLogList(List<PcmsRejectLogVo> pcmsRejectLogList) {
        this.pcmsRejectLogList = pcmsRejectLogList;
    }

    public List<List<Transfer>> getPcmsTransferVoList() {
        return pcmsTransferVoList;
    }

    public void setPcmsTransferVoList(List<List<Transfer>> pcmsTransferVoList) {
        this.pcmsTransferVoList = pcmsTransferVoList;
    }

    public PcmsTovoidItemVo getPcmsTovoidItemVo() {
        return pcmsTovoidItemVo;
    }

    public void setPcmsTovoidItemVo(PcmsTovoidItemVo pcmsTovoidItemVo) {
        this.pcmsTovoidItemVo = pcmsTovoidItemVo;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }
}
