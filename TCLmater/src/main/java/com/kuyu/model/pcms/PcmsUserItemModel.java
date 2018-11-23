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
 * Created by pc on 2018/11/22
 */
@ApiModel("PcmsUserItemModel(立项单与用户模型)")
@TableName("pcms_user_item")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsUserItemModel extends Model<PcmsUserItemModel> implements Serializable {

    private static final long serialVersionUID = 7913074281876854565L;
    @TableId(type= IdType.AUTO)
    private Integer id;
    @TableField("openid")
    private String openid;
    @TableField("create_time")
    private String create_time;
    @TableField("itid")
    private Integer itid;

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

    public Integer getItid() {
        return itid;
    }

    public void setItid(Integer itid) {
        this.itid = itid;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
