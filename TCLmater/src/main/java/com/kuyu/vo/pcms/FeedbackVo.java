package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by pc on 2018/12/11
 */
public class FeedbackVo {

    private Integer id;

    @ApiModelProperty("创建时间")
    private Date create_time;

    @ApiModelProperty("反馈说明")
    private String context;

    @ApiModelProperty("反馈主键")
    private Integer transfer_id;

    @ApiModelProperty("数量")
    private Integer number;

    @ApiModelProperty("转办人")
    private String person_name;

    private List<FeedbackImageVo> list;

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

    public Integer getTransfer_id() {
        return transfer_id;
    }

    public void setTransfer_id(Integer transfer_id) {
        this.transfer_id = transfer_id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<FeedbackImageVo> getList() {
        return list;
    }

    public void setList(List<FeedbackImageVo> list) {
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }
}
