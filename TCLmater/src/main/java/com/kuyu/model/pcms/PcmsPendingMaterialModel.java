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
import java.util.List;

/**
 * Created by pc on 2018/11/27
 */
@ApiModel("PcmsPendingMaterialModel(待验收物料模型)")
@TableName("pcms_pending_material")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsPendingMaterialModel extends Model<PcmsPendingMaterialModel> {

    @TableField(exist=false)
    private static final long serialVersionUID = 4998163694304048704L;
    /**id*/
    @TableId(type= IdType.AUTO)
    private Integer id;

    /**供应商id*/
    @ApiModelProperty("物料类别")
    @JsonProperty("category")
    private String category;

    @ApiModelProperty("物料规格")
    @JsonProperty("specifications")
    private String specifications;

    @ApiModelProperty("单位")
    @JsonProperty("unit")
    private String unit;

    @ApiModelProperty("参考价")
    @JsonProperty("comparisonPrice")
    private Double comparison_price;

    /**单价数量范围*/
    @ApiModelProperty("单价数量范围")
    @JsonProperty("ranges")
    private String ranges;

    /**创建时间*/
    @ApiModelProperty("创建时间")
    @JsonProperty("create_time")
    private Date create_time;

    @ApiModelProperty("拆单的主键ID")
    @JsonProperty("itid")
    private Integer itid;


    /**单价数量范围*/
    @ApiModelProperty("图片")
    @JsonProperty("image")
    private String[] image;


    private List<PcmsMaterialImgModel> list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getComparison_price() {
        return comparison_price;
    }

    public void setComparison_price(Double comparison_price) {
        this.comparison_price = comparison_price;
    }

    public String getRanges() {
        return ranges;
    }

    public void setRanges(String ranges) {
        this.ranges = ranges;
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

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }

    public List<PcmsMaterialImgModel> getList() {
        return list;
    }

    public void setList(List<PcmsMaterialImgModel> list) {
        this.list = list;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
