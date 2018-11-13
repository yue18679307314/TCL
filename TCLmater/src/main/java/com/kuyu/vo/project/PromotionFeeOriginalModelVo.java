package com.kuyu.vo.project;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author jt_L
 * @Date 2017/10/23
 * @Description
 */
@ApiModel("立项单临促费用(子表项目1)复制")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PromotionFeeOriginalModelVo{

    private String uuid;
    /**
     * 活动ID
     */
    @JsonProperty("ACTIVITY_ID")
    @ApiModelProperty(value = "活动ID",position = -50)
    private String projectId;
    /**
     * 费用细类
     */
    @JsonProperty("ACTIVITY_CATEGORY")
    @ApiModelProperty(value = "费用细类",position = -48)
    private String feeDetailType;
    /**
     * 门店/区域
     */
    @JsonProperty("ACTIVITY_STORE")
    @ApiModelProperty(value = "门店/区域",position = -47)
    private String district;
    /**
     * 现场负责人
     */
    @JsonProperty("ACTIVITY_LEADER")
    @ApiModelProperty(value = "现场负责人",position = -46)
    private String manager;
    /**
     * 开始日期(YYYY-MM-DD HH(24)-mm-ss)
     */
    @JsonProperty("ACTIVITY_START_DATE")
    @ApiModelProperty(value = "开始日期(YYYY-MM-DD",position = -45)
    private String startTime;
    /**
     * 结束日期(YYYY-MM-DD HH(24)-mm-ss)
     */
    @JsonProperty("ACTIVITY_END_DATE")
    @ApiModelProperty(value = "结束日期(YYYY-MM-DD",position = -44)
    private String endTime;
    /**
     * 总天数
     */
    @JsonProperty("ACTIVITY_DAY_COUNT")
    @ApiModelProperty(value = "总天数",position = -43)
    private Integer totalDay;
    /**
     * 人数
     */
    @JsonProperty("ACTIVITY_USER_NUM")
    @ApiModelProperty(value = "人数",position = -42)
    private Integer userNo;
    /**
     * 工作时长
     */
    @JsonProperty("ACTIVITY_DURATION")
    @ApiModelProperty(value = "工作时长",position = -41)
    private Integer workHours;
    /**
     * 人天单价
     */
    @JsonProperty("ACTIVITY_PRICE")
    @ApiModelProperty(value = "人天单价",position = -40)
    private Double unitPrice;
    /**
     * 其它激励金额
     */
    @JsonProperty("ACTIVITY_OTHER")
    @ApiModelProperty(value = "其它激励金额",position = -39)
    private Double incentiveAmount;
    /**
     * 结算方式
     */
    @JsonProperty("ACTIVITY_BALANCE")
    @ApiModelProperty(value = "结算方式",position = -37)
    private String settlementMethod;
    /**
     * 结算周期
     */
    @JsonProperty("ACTIVITY_CYCLE")
    @ApiModelProperty(value = "结算周期",position = -36)
    private String settlementCycle;
    /**
     * 临促费用总金额
     */
    @JsonProperty("ACTIVITY_TOTAL")
    @ApiModelProperty(value = "临促费用总金额",position = -35)
    private Double totalFee;


    public String getUuid(){
        return uuid;
    }

    public void setUuid(String uuid){
        this.uuid = uuid;
    }

    public String getProjectId(){
        return projectId;
    }

    public void setProjectId(String projectId){
        this.projectId = projectId;
    }

    public String getFeeDetailType(){
        return feeDetailType;
    }

    public void setFeeDetailType(String feeDetailType){
        this.feeDetailType = feeDetailType;
    }

    public String getDistrict(){
        return district;
    }

    public void setDistrict(String district){
        this.district = district;
    }

    public String getManager(){
        return manager;
    }

    public void setManager(String manager){
        this.manager = manager;
    }

    public String getStartTime(){
        return startTime;
    }

    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public String getEndTime(){
        return endTime;
    }

    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

    public Integer getTotalDay(){
        return totalDay;
    }

    public void setTotalDay(Integer totalDay){
        this.totalDay = totalDay;
    }

    public Integer getUserNo(){
        return userNo;
    }

    public void setUserNo(Integer userNo){
        this.userNo = userNo;
    }

    public Integer getWorkHours(){
        return workHours;
    }

    public void setWorkHours(Integer workHours){
        this.workHours = workHours;
    }

    public Double getUnitPrice(){
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice){
        this.unitPrice = unitPrice;
    }

    public Double getIncentiveAmount(){
        return incentiveAmount;
    }

    public void setIncentiveAmount(Double incentiveAmount){
        this.incentiveAmount = incentiveAmount;
    }

    public String getSettlementMethod(){
        return settlementMethod;
    }

    public void setSettlementMethod(String settlementMethod){
        this.settlementMethod = settlementMethod;
    }

    public String getSettlementCycle(){
        return settlementCycle;
    }

    public void setSettlementCycle(String settlementCycle){
        this.settlementCycle = settlementCycle;
    }

    public Double getTotalFee(){
        return totalFee;
    }

    public void setTotalFee(Double totalFee){
        this.totalFee = totalFee;
    }
}
