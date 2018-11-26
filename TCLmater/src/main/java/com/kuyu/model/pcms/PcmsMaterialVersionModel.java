package com.kuyu.model.pcms;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pc on 2018/11/26
 */
@ApiModel("PcmsMaterialVersionModel(供应商物料版本模型)")
@TableName("pcms_material_version")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsMaterialVersionModel extends Model<PcmsMaterialVersionModel> {

    @TableField(exist=false)
    private static final long serialVersionUID = 4999163694306048704L;

    /**id*/
    @TableId(type= IdType.AUTO)
    private Integer id;

    @ApiModelProperty("版本号")
    @JsonProperty("version")
    private Integer version;

    /**供应商id*/
    @ApiModelProperty("供应商id")
    @JsonProperty("vendor_id")
    private String vendor_id;

    /**公司代码*/
    @ApiModelProperty("公司代码")
    @JsonProperty("company")
    private String company;

    /**创建时间*/
    @ApiModelProperty("创建时间")
    @JsonProperty("create_time")
    private Date create_time;

    /**名称*/
    @ApiModelProperty("名称")
    @JsonProperty("name")
    private String name;

    /**下载链接*/
    @ApiModelProperty("下载链接")
    @JsonProperty("url")
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
