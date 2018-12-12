package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by pc on 2018/12/11
 */
public class TransferDetailVo {
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("转办的内容")
    private String context;
    @ApiModelProperty("供应商名称")
    private String vendor_name;
    @ApiModelProperty("负责人")
    private String request_user_name;
    @ApiModelProperty("状态")
    private Integer state;
    @ApiModelProperty("类别")
    private String category;
    @ApiModelProperty("规格")
    private String specifications;
    @ApiModelProperty("反馈信息")
    private List<FeedbackVo> listFeedbackVo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getRequest_user_name() {
        return request_user_name;
    }

    public void setRequest_user_name(String request_user_name) {
        this.request_user_name = request_user_name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public List<FeedbackVo> getListFeedbackVo() {
        return listFeedbackVo;
    }

    public void setListFeedbackVo(List<FeedbackVo> listFeedbackVo) {
        this.listFeedbackVo = listFeedbackVo;
    }
}
