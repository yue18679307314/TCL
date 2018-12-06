package com.kuyu.model.pcms;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pc on 2018/12/6
 */
@ApiModel("PcmsTovoidItemModel(供应商模型)")
@TableName("pcms_tovoid_item")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsTovoidItemModel extends Model<PcmsTovoidItemModel> {
    private static final long serialVersionUID = 7914074281876854565L;
    @TableId(type= IdType.AUTO)
    private Integer id;
    private Integer itid;
    private Date create_time;
    private String context;
    private String operator;

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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
