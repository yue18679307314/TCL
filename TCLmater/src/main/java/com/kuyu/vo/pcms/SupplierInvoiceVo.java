package com.kuyu.vo.pcms;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by pc on 2019/1/8
 */
public class SupplierInvoiceVo {

    @TableId(type= IdType.AUTO)
    private Integer id;

    @ApiModelProperty("发票类型")
    private Integer type;

    @ApiModelProperty("发票代码")
    private String invoice_code;

    @ApiModelProperty("发票号码")
    private String invoice_no;

    @ApiModelProperty("发票金额")
    private Double invoice_price;

    @ApiModelProperty("税金")
    private Double tax;
    @ApiModelProperty("图片")
    private List<SupplierInvoiceImageVo> list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<SupplierInvoiceImageVo> getList() {
        return list;
    }

    public void setList(List<SupplierInvoiceImageVo> list) {
        this.list = list;
    }
}
