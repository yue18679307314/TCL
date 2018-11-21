package com.kuyu.model.pcms;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Created by pc on 2018/11/21
 */
@ApiModel("PcmsSupplierModel(供应商日志模型)")
@TableName("pcms_supplier_log")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsSupplierLogModel extends Model<PcmsSupplierLogModel> implements Serializable{

    private static final long serialVersionUID = 7913074201876854565L;
    @TableId(type= IdType.AUTO)
    private Integer id;
    @TableField("vendor_id")
    private String vendor_id;
    @TableField("create_time")
    private String create_time;
    @TableField("content")
    private String content;
    @TableField("operation")
    private String operation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
