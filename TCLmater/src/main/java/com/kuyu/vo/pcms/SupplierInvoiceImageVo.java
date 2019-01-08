package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pc on 2019/1/8
 */
public class SupplierInvoiceImageVo {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("关联ID")
    private Integer invoice_id;
    @ApiModelProperty("图片地址")
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(Integer invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
