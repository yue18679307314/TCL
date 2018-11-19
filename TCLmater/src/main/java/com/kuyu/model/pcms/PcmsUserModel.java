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
@ApiModel("PcmsUserModel(供应商模型)")
@TableName("pcms_user")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsUserModel extends Model<PcmsUserModel> implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 7913074281876854565L;
    @TableId(type= IdType.AUTO)
    private Integer id;
    @TableField("openid")
    private String openid;
    @TableField("create_time")
    private String create_time;
    @TableField("openname")
    private String openname;
    @TableField("type")
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOpenname() {
        return openname;
    }

    public void setOpenname(String openname) {
        this.openname = openname;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
