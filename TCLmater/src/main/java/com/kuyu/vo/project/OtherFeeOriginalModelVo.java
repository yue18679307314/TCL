package com.kuyu.vo.project;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author jt_L
 * @Date 2017/10/23
 * @Description
 */
@ApiModel("立项活动其它费用(子表项目2)复制")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OtherFeeOriginalModelVo{

    /**
     * 活动ID
     */
    @JsonProperty("ACTIVITY_ID")
    @ApiModelProperty(value = "活动ID",position = -50)
    private String projectId;
    /**
     * 物料类别
     */
    @JsonProperty("ACTIVITY_TYPE")
    @ApiModelProperty(value = "物料类别",position = -47)
    private String materialCategory;
    /**
     * 规格
     */
    @JsonProperty("ACTIVITY_SPEC")
    @ApiModelProperty(value = "规格",position = -46)
    private String specifications;
    /**
     * 单位
     */
    @JsonProperty("ACTIVITY_UNIT")
    @ApiModelProperty(value = "单位",position = -45)
    private String unit;
    /**
     * 数量
     */
    @NotBlank
    @Pattern(regexp = "^//d$",message = "不为空,只能是数字")
    @JsonProperty("ACTIVITY_NUM")
    @ApiModelProperty(value = "数量",position = -44)
    private Double amount;
    /**
     * 单价
     */
    @JsonProperty("ACTIVITY_PRICE")
    @ApiModelProperty(value = "单价",position = -43)
    private Double unitPrice;
    /**
     * 其它促销费用总金额
     */
    @JsonProperty("ACTIVITY_TOTAL")
    @ApiModelProperty(value = "其它促销费用总金额",position = -42)
    private Double otherTotalFee;
    
    @JsonProperty("ACTIVITY_VENDOR")
    @ApiModelProperty(value = "供应商",position = -41)
    private String activityVendor;


    public String getActivityVendor() {
		return activityVendor;
	}

	public void setActivityVendor(String activityVendor) {
		this.activityVendor = activityVendor;
	}

	public String getProjectId(){
        return projectId;
    }

    public void setProjectId(String projectId){
        this.projectId = projectId;
    }

    public String getMaterialCategory(){
        return materialCategory;
    }

    public void setMaterialCategory(String materialCategory){
        this.materialCategory = materialCategory;
    }

    public String getSpecifications(){
        return specifications;
    }

    public void setSpecifications(String specifications){
        this.specifications = specifications;
    }

    public String getUnit(){
        return unit;
    }

    public void setUnit(String unit){
        this.unit = unit;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getUnitPrice(){
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice){
        this.unitPrice = unitPrice;
    }

    public Double getOtherTotalFee(){
        return otherTotalFee;
    }

    public void setOtherTotalFee(Double otherTotalFee){
        this.otherTotalFee = otherTotalFee;
    }
}
