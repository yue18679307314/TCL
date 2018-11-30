package com.kuyu.model.pcms;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Created by pc on 2018/11/29
 */
@ApiModel("PcmsInvoiceImageModel(发票图片模型)")
@TableName("pcms_invoice_image")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsInvoiceImageModel extends Model<PcmsInvoiceImageModel> {
    private int id;
    private int invoice_id;
    private String image;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
