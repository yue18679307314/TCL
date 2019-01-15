package com.kuyu.vo.pcms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuyu.model.pcms.PcmsMaterialImgModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by pc on 2019/1/9
 */
public class PcmsPendingMaterialVo {

    private Integer id;

    /**供应商id*/
    @ApiModelProperty("物料类别")
    private String category;

    @ApiModelProperty("物料规格")
    private String specifications;

    @ApiModelProperty("单位")
    @JsonProperty("unit")
    private String unit;

    @ApiModelProperty("参考价")
    private String comparisonPrice;

    /**单价数量范围*/
    @ApiModelProperty("单价数量范围")
    private String ranges;

    /**创建时间*/
    @ApiModelProperty("创建时间")
    private Date create_time;

    @ApiModelProperty("拆单的主键ID")
    private Integer itid;

    @ApiModelProperty("拆单的主键ID")
    private Integer number;

    private String all_price;

    /**单价数量范围*/
    @ApiModelProperty("图片")
    private String[] image;

    private Integer state;

    private List<PcmsMaterialImgVo> imgList;

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

    public String getComparisonPrice() {
        return comparisonPrice;
    }

    public void setComparisonPrice(String comparisonPrice) {
        this.comparisonPrice = comparisonPrice;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAll_price() {
        return all_price;
    }

    public void setAll_price(String all_price) {
        this.all_price = all_price;
    }

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<PcmsMaterialImgVo> getImgList() {
        return imgList;
    }

    public void setImgList(List<PcmsMaterialImgVo> imgList) {
        this.imgList = imgList;
    }
}
