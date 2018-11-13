package com.kuyu.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author jt_L
 * @Date 2017/10/19 0019
 * @Description
 */
@ApiModel("添加或更新上报数据Vo")
public class ActualDataVo{

    /**
     * 活动uuid
     */
    @ApiModelProperty(value = "活动uuid",position = 2,required = true)
    private String activityUuid;
    /**
     * 实际销售量
     */
    @ApiModelProperty(value = "实际销售量",position = 3,required = true)
    private Integer actualSalesNo;
    /**
     * 实际销售额
     */
    @ApiModelProperty(value = "实际销售额",position = 4,required = true)
    private Double actualSalesMoney;
    /**
     * 实际花费
     */
//    @ApiModelProperty(value = "实际花费",position = 4,required = true)
//    private Double actualCost;
    /**
     * 实际费用率
     */
//    @ApiModelProperty(value = "实际费用率",position = 5,required = true)
//    private Double actualCostRate;

    public String getActivityUuid(){
        return activityUuid;
    }

    public void setActivityUuid(String activityUuid){
        this.activityUuid = activityUuid;
    }

    public Integer getActualSalesNo(){
        return actualSalesNo;
    }

    public void setActualSalesNo(Integer actualSalesNo){
        this.actualSalesNo = actualSalesNo;
    }

    public Double getActualSalesMoney(){
        return actualSalesMoney;
    }

    public void setActualSalesMoney(Double actualSalesMoney){
        this.actualSalesMoney = actualSalesMoney;
    }

   /* public Double getActualCostRate(){
        return actualCostRate;
    }

    public void setActualCostRate(Double actualCostRate){
        this.actualCostRate = actualCostRate;
    }

    public Double getActualCost(){
        return actualCost;
    }

    public void setActualCost(Double actualCost){
        this.actualCost = actualCost;
    }*/
}
