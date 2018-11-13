package com.kuyu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author jt_L
 * @Date 2017/10/22
 * @Description 活动申请详细VO
 */
@ApiModel("活动申请详细VO")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ActivityJoinDetailVo {

    @ApiModelProperty(value = "活动id",position = 1)
    private String activityUuid;

    @ApiModelProperty(value = "预算部门名称",position = 2)
    private String deptName;

    @ApiModelProperty(value = "现场负责人名称",position = 3)
    private String managerName;

    @ApiModelProperty(value = "活动名称",position = 4)
    private String projectName;

    @ApiModelProperty(value = "关注openid",position = 5)
    private String openid;

    @ApiModelProperty(value = "姓名",position = 5)
    private String userName;

    @ApiModelProperty(value = "微信性别",position = 5)
    private String wxSex;

    @ApiModelProperty(value = "性别",position = 5)
    private String sex;

    @ApiModelProperty(value = "年龄",position = 6)
    private String age;

    @ApiModelProperty(value = "手机号码",position = 7)
    private String telPhone;

    @ApiModelProperty(value = "微信号",position = 8)
    private String weixinName;

    @ApiModelProperty(value = "身份证",position = 9)
    private String idCard;

    @ApiModelProperty(value = "申请时间",position = 10)
    private String requestTime;

    @ApiModelProperty(value = "审核时间",position = 11)
    private String auditTime;

    @ApiModelProperty(value = "审核状态",position = 12)
    private String requestState;
    
    @ApiModelProperty(value = "活动负责人编号",position = 13)
    private String manager;
    
    @ApiModelProperty(value = "是否是活动负责人",position = 14)
    private Boolean isManager = false;

    @ApiModelProperty(value = "申请参加哪一天的活动",position = 15)
    private String activityDay;

    public String getActivityDay() {
        return activityDay;
    }

    public void setActivityDay(String activityDay) {
        this.activityDay = activityDay;
    }

    public String getWxSex() {
        return wxSex;
    }

    public void setWxSex(String wxSex) {
        this.wxSex = wxSex;
    }

    public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	public String getActivityUuid() {
        return activityUuid;
    }

    public void setActivityUuid(String activityUuid) {
        this.activityUuid = activityUuid;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getWeixinName() {
        return weixinName;
    }

    public void setWeixinName(String weixinName) {
        this.weixinName = weixinName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getRequestState(){
        return requestState;
    }

    public void setRequestState(String requestState){
        this.requestState = requestState;
    }

    public String getProjectName(){
        return projectName;
    }

    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
