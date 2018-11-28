package com.kuyu.model.pcms;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pc on 2018/11/28
 */
@ApiModel("PcmsSupplierInvoiceModel(发票模型)")
@TableName("pcms_supplier_invoice")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsSupplierInvoiceModel extends Model<PcmsSupplierInvoiceModel> {

    @TableId(type= IdType.AUTO)
    private Integer id;

    @ApiModelProperty("立项单ID")
    @TableField("itid")
    private Integer itid;

    @ApiModelProperty("供应商ID")
    @TableField("vendor_id")
    private String vendor_id;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date create_time;

    @ApiModelProperty("发票类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("发票代码")
    @TableField("invoice_code")
    private String invoice_code;

    @ApiModelProperty("发票号码")
    @TableField("invoice_no")
    private String invoice_no;

    @ApiModelProperty("发票金额")
    @TableField("invoice_price")
    private Double invoice_price;

    @ApiModelProperty("税金")
    @TableField("tax")
    private Double tax;

    @ApiModelProperty("图片")
    @TableField("image")
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItid() {
        return itid;
    }

    public void setItid(Integer itid) {
        this.itid = itid;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
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

    public String getInvoice_code() {
        return invoice_code;
    }

    public void setInvoice_code(String invoice_code) {
        this.invoice_code = invoice_code;
    }

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    public Double getInvoice_price() {
        return invoice_price;
    }

    public void setInvoice_price(Double invoice_price) {
        this.invoice_price = invoice_price;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
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
