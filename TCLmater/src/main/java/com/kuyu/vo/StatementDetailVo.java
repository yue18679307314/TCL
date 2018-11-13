package com.kuyu.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author jt_L
 * @Date 2017/9/22
 * @Description
 */
@ApiModel("收益明细VO")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StatementDetailVo {

    /**
     * 结算uuid
     */
    @ApiModelProperty(value = "结算uuid", position = 1)
    private String uuid;

    /**
     * 付款状态
     */
    @ApiModelProperty(value = "付款状态(-1未处理 1 申请付款中；0失败；2 付款成功,默认-1)", position = 2)
    private Integer payState;
    /**
     * 打卡时间
     */
    @ApiModelProperty(value = "时间(yyyy-MM-dd)", position = 3)
    private String activityTime;

    /**
     * 扣费
     */
    @ApiModelProperty(value = "扣费", position = 4)
    private double reduceMoney;

    /**
     * 扣费原因
     */
    @ApiModelProperty(value = "扣费原因", position = 5)
    private String reduceMoneyReason;
    /**
     * 奖金
     */
    @ApiModelProperty(value = "其它激励奖金", position = 6)
    private double incentiveAmount;

    /**
     * 其它激励事由
     */
    @ApiModelProperty(value = "其它激励事由", position = 7)
    private String incentiveReason;

    /**
     * 日薪
     */
    @ApiModelProperty(value = "日薪", position = 8)
    private double unitPrice;


    /**
     * 实际要求工作时间
     */
    @ApiModelProperty(value = "实际要求工作时间", position = 9)
    private int workHours;


    /**
     * 有效工作时间
     */
    @ApiModelProperty(value = "有效工作时间", position = 17)
    private int validWorkHours;

    /**
     * 修改工时原因
     */
    @ApiModelProperty(value = "修改工时原因", position = 10)
    private String reduceHoursReason;

    /**
     * 审核工时备注
     */
    @ApiModelProperty(value = "审核工时备注", position = 11)
    private String adjustHoursNote;



    /**
     * 实际报酬
     */
    @ApiModelProperty(value = "实际报酬", position = 12)
    private double realSalary;
    
    @ApiModelProperty(value = "实际分钟", position = 15)
    private Integer validWorkMinute;
    
    @ApiModelProperty(value = "实际分钟", position = 16)
    private String adjustMoneyNote;
    
    @ApiModelProperty(value = "结算方式", position = 17)
    private String settlementMethod;
    
	public String getSettlementMethod() {
		return settlementMethod;
	}

	public void setSettlementMethod(String settlementMethod) {
		this.settlementMethod = settlementMethod;
	}

	public Integer getValidWorkMinute() {
		return validWorkMinute;
	}

	public void setValidWorkMinute(Integer validWorkMinute) {
		this.validWorkMinute = validWorkMinute;
	}

	public String getAdjustMoneyNote() {
		return adjustMoneyNote;
	}

	public void setAdjustMoneyNote(String adjustMoneyNote) {
		this.adjustMoneyNote = adjustMoneyNote;
	}

	public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public double getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(double reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public double getIncentiveAmount() {
        return incentiveAmount;
    }

    public void setIncentiveAmount(double incentiveAmount) {
        this.incentiveAmount = incentiveAmount;
    }

    public double getRealSalary() {
        return realSalary;
    }

    public void setRealSalary(double realSalary) {
        this.realSalary = realSalary;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getReduceMoneyReason() {
        return reduceMoneyReason;
    }

    public double getUnitPrice(){
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice){
        this.unitPrice = unitPrice;
    }

    public String getActivityTime(){
        return activityTime;
    }

    public void setActivityTime(String activityTime){
        this.activityTime = activityTime;
    }

    public String getIncentiveReason(){
        return incentiveReason;
    }

    public void setIncentiveReason(String incentiveReason){
        this.incentiveReason = incentiveReason;
    }

    public int getWorkHours(){
        return workHours;
    }

    public void setWorkHours(int workHours){
        this.workHours = workHours;
    }

    public int getValidWorkHours(){
        return validWorkHours;
    }

    public void setValidWorkHours(int validWorkHours){
        this.validWorkHours = validWorkHours;
    }

    public String getReduceHoursReason(){
        return reduceHoursReason;
    }

    public void setReduceHoursReason(String reduceHoursReason){
        this.reduceHoursReason = reduceHoursReason;
    }

    public String getAdjustHoursNote(){
        return adjustHoursNote;
    }

    public void setAdjustHoursNote(String adjustHoursNote){
        this.adjustHoursNote = adjustHoursNote;
    }

    public void setReduceMoneyReason(String reduceMoneyReason) {
        this.reduceMoneyReason = reduceMoneyReason;
    }

}
