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
 * Created by pc on 2018/11/19
 */
@ApiModel("PcmsSupplierUserModel(供应商模型)")
@TableName("pcms_supplier_user")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsSupplierUserModel extends Model<PcmsSupplierUserModel> implements Serializable {

    private static final long serialVersionUID = 7913074281876854562L;

    @TableId(type= IdType.AUTO)
    private Integer suid;
    @TableField("vendor_id")
    private String vendor_id;
    @TableField("openid")
    private String openid;

    public Integer getSuid() {
        return suid;
    }

    public void setSuid(Integer suid) {
        this.suid = suid;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
