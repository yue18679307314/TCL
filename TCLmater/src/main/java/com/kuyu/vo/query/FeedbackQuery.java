package com.kuyu.vo.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by pc on 2018/12/11
 */
public class FeedbackQuery {

    @ApiModelProperty("创建时间")
    private Date create_time;

    @ApiModelProperty("反馈说明")
    private String context;

    @ApiModelProperty("反馈主键")
    private Integer transfer_id;

    private Integer number;

    @ApiModelProperty("图片")
    @JsonProperty("image")
    private String[] image;

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

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }
}
