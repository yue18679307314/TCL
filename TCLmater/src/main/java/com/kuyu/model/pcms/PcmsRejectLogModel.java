package com.kuyu.model.pcms;

/**
 * Created by pc on 2018/11/27
 */

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel("PcmsRejectLogModel(待验收物料模型)")
@TableName("pcms_reject_log")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsRejectLogModel extends Model<PcmsRejectLogModel> {

    /**id*/
    @TableId(type= IdType.AUTO)
    private Integer id;

    /**创建时间*/
    @ApiModelProperty("创建时间")
    @JsonProperty("create_time")
    private Date create_time;

    @ApiModelProperty("拆单的主键ID")
    @JsonProperty("itid")
    private Integer itid;

    @ApiModelProperty("驳回理由")
    @JsonProperty("context")
    private String context;

    @ApiModelProperty("操作人")
    @JsonProperty("operator")
    private String operator;

    @ApiModelProperty("状态")
    @JsonProperty("type")
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getItid() {
        return itid;
    }

    public void setItid(Integer itid) {
        this.itid = itid;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
