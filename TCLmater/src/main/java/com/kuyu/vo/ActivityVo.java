package com.kuyu.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.security.PrivateKey;
import java.util.List;

/**
 * @Author jt_L
 * @Date 2017/9/21
 * @Description 活动详情展示数据VO
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel("活动详情展示数据VO")
public class ActivityVo {

    /**
     * 活动uuid
     */
    @ApiModelProperty(value = "活动uuid",position = 1)
    private String activityUuid;

    @ApiModelProperty(value = "活动城市",position = 1)
    private String city;

    @ApiModelProperty(value = "临促openid",position = 2)
    private String openid;

    /**
     * 活动名称
     */
    @ApiModelProperty(value = "活动名称",position = 3)
    private String projectName;
    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期(yyyy-MM-dd)",position = 4)
    private String startTime;
    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期(yyyy-MM-dd)",position = 5)
    private String endTime;
    /**
     * 活动总天数
     */
    @ApiModelProperty(value = "活动总天数",position = 6)
    private Integer totalDay;
    /**
     * 已招募人数
     */
    @ApiModelProperty(value = "已招募人数",position = 7)
    private Integer count;
    /**
     * 招募总人数
     */
    @ApiModelProperty(value = "招募总人数",position = 8)
    private Integer userNo;
    /**
     * 工作时长
     */
    @ApiModelProperty(value = "工作时长",position = 9)
    private Integer workHours;
    /**
     * 日薪
     */
    @ApiModelProperty(value = "日薪",position = 10)
    private Double unitPrice;
    /**
     * 活动状态 0 未开始 1进行中 2已结束
     */
    @ApiModelProperty(value = "活动状态 0 未开始 1进行中 2已结束",position = 11)
    private Integer activityState;

    /**
     * 临促申请状态
     * 0 现场负责人已确认拒绝参加；1 申请中； 2 现场负责人已确认可以参加； 3 已参加；4 已结束
     *
     * 0进行中:3
     * 1我申请的:0申请失败 	1申请中	 2、3、4申请成功
     * 2已结束:	0、1申请失败 	2、3、4申请成功
     *
     * 未申请参加:-1
     * 已申请:1
     * 申请审核未通过:0
     * 申请审核通过:2
     */
    @ApiModelProperty(value = "临促申请状态:-1未申请 0已拒绝参加 1已申请 2申请通过 3已参加 4已结束",position = 12)
    private Integer requestState;

    @ApiModelProperty(value = "临促申请审核原因",position = 13)
    private String reason;

    /**
     * 实际报酬
     */
    @ApiModelProperty(value = "实际报酬",position = 14)
    private Double realSalary;

    @ApiModelProperty(value = "付款状态(-1未处理 1 申请付款中；0失败；2 付款成功,默认-1)(用于收益)",position = 15)
    private Integer payState;

    /**
     * 临促单个活动总收益
     */
    @ApiModelProperty(value = "临促单个活动总收益",position = 15)
    private Double totalRealSalary;

    /**
     * 没有预期收益的时候显示，已到帐收益
     */
    @ApiModelProperty(value = "是否有近期收益，1,有；0，没有", position = 4)
    private  Integer isHasLastRealSalary=1;
    
    @ApiModelProperty(value = "审核工时的状态", position = 16)
    private Integer hour_state;
    
    @ApiModelProperty(value = "活动时间", position = 17)
    private String activityTime;
    
    @ApiModelProperty(value = "结算方式", position = 18)
    private String settlementMethod;


    @ApiModelProperty(value = "是否剔除，1剔除，0正常", position = 21)
    private String rejectFlag;

    /**
     * 活动状态 0招募中 1招募完成(未开始) 2进行中 3已结束
     */
//    @ApiModelProperty(value = "该日期活动状态 0 已招满 1未招满未申请 2未招满已申请",position = 18)
//    @ApiModelProperty(value = "0招募中（未开始） 1招募完成(未开始) 2招募中（进行中） 3已结束 4招募完成",position = 18)
//    private List<Integer> activityDaySateList;

    /**
     * 日期
     */

//    @ApiModelProperty(value = "该日期活动状态 0 已招满 1未招满未申请 2未招满已申请",position = 18)
    @ApiModelProperty(value = "临促申请状态:-1未申请 0已拒绝参加 1已申请 2申请通过 3已参加 4已结束",position = 19)
//    private List<Integer> requestStateList;
    List<ActivityStateVo> activityStateVoList;

    @ApiModelProperty(value = "活动负责人的电话", position = 19)
    private String managerMobile;

    @ApiModelProperty(value = "活动负责人姓名", position = 20)
    private String managerName;


    public String getManagerMobile() {
        return managerMobile;
    }

    public void setManagerMobile(String managerMobile) {
        this.managerMobile = managerMobile;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getRejectFlag() {
        return rejectFlag;
    }

    public void setRejectFlag(String rejectFlag) {
        this.rejectFlag = rejectFlag;
    }

    public List<ActivityStateVo> getActivityStateVoList() {
        return activityStateVoList;
    }

    public void setActivityStateVoList(List<ActivityStateVo> activityStateVoList) {
        this.activityStateVoList = activityStateVoList;
    }

    public String getActivityTime() {
		return activityTime;
	}

	public String getSettlementMethod() {
		return settlementMethod;
	}

	public void setSettlementMethod(String settlementMethod) {
		this.settlementMethod = settlementMethod;
	}

	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}

	public Integer getHour_state() {
		return hour_state;
	}

	public void setHour_state(Integer hour_state) {
		this.hour_state = hour_state;
	}

	public Integer getIsHasLastRealSalary() {
        return isHasLastRealSalary;
    }

    public void setIsHasLastRealSalary(Integer isHasLastRealSalary) {
        this.isHasLastRealSalary = isHasLastRealSalary;
    }
    public String getActivityUuid() {
        return activityUuid;
    }

    public void setActivityUuid(String activityUuid) {
        this.activityUuid = activityUuid;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(Integer totalDay) {
        this.totalDay = totalDay;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Integer workHours) {
        this.workHours = workHours;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getActivityState() {
        return activityState;
    }

    public void setActivityState(Integer activityState) {
        this.activityState = activityState;
    }

    public Integer getRequestState() {
        return requestState;
    }

    public void setRequestState(Integer requestState) {
        this.requestState = requestState;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getRealSalary() {
        return realSalary;
    }

    public void setRealSalary(Double realSalary) {
        this.realSalary = realSalary;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }


    public Double getTotalRealSalary() {
        return totalRealSalary;
    }

    public void setTotalRealSalary(Double totalRealSalary) {
        this.totalRealSalary = totalRealSalary;
    }
}
