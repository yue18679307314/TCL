package com.kuyu.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @Author jt_L
 * @Date 2017/9/25
 * @description
 */
@ApiModel("活动负责人的活动详细VO")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ActivityDetailVo{

    @ApiModelProperty(value = "立项申请id",position = 1)
    private String requestId;

    /**
     * 活动项目状态 0招募中 1招募完成(未开始) 2进行中 3已结束
     */
    @ApiModelProperty(value = "活动项目状态 0招募中 1招募完成(未开始) 2进行中 3已结束",position = 2)
    private Integer activityState;

    @ApiModelProperty(value = "预计零售量",position = 3)
    private String projectedSalesNo;

    @ApiModelProperty(value = "预计零售额",position = 4)
    private String projectedSalesMoney;

    @ApiModelProperty(value = "预计费用率",position = 5)
    private String estimatedCostRate;

    @ApiModelProperty(value = "申请标题",position = 6)
    private String requestTitle;

    @ApiModelProperty(value = "公司代码",position = 7)
    private String companyCode;

    @ApiModelProperty(value = "申请人部门(名称)",position = 8)
    private String requestDeptName;

    @ApiModelProperty(value = "申请创建日期",position = 9)
    private String requestCreateTime;

    @ApiModelProperty(value = "申请结束日期",position = 10)
    private String requestEndTime;

    @ApiModelProperty(value = "公司代码",position = 11)
    private String requestCompanyCode;


    @ApiModelProperty(value = "活动项目名称",position = 12)
    private String projectName;

    @ApiModelProperty(value = "开始日期",position = 13)
    private String startTime;

    @ApiModelProperty(value = "结束日期",position = 14)
    private String endTime;

    @ApiModelProperty(value = "总天数",position = 15)
    private String totalDay;

    @ApiModelProperty(value = "预算部门(名称)",position = 16)
    private String deptName;

    @ApiModelProperty(value = "申请人(名称)",position = 17)
    private String requestUserName;

    @ApiModelProperty(value = "申请人手机",position = 18)
    private String requestTelphone;

    @ApiModelProperty(value = "申请事由",position = 19)
    private String requestReasons;

    @ApiModelProperty(value = "项目（WBS）",position = 20)
    private String wbs;

    @ApiModelProperty(value = "备注",position = 21)
    private String note;

    @ApiModelProperty(value = "现场负责人",position = 22)
    private String manager;

    @ApiModelProperty(value = "现场负责人(名称)",position = 23)
    private String managerName;

    @ApiModelProperty(value = "费用细类",position = 24)
    private String feeDetailType;

    @ApiModelProperty(value = "成本中心",position = 25)
    private String costCenter;

    @ApiModelProperty(value = "渠道/内部订单",position = 26)
    private String orderNo;

    @ApiModelProperty(value = "临促费用（子表项目1总金额）",position = 27)
    private Double tpFee;

    @ApiModelProperty(value = "其它费用（子表项目2总金额）",position = 28)
    private Double otherFee;

    @ApiModelProperty(value = "促销费用总金额",position = 29)
    private Double totalFee;


    @ApiModelProperty(value = "城市",position = 30)
    private String city;

    @ApiModelProperty(value = "门店/区域",position = 31)
    private String district;

    @ApiModelProperty(value = "招募人数",position = 32)
    private String userNo;

    @ApiModelProperty(value = "工作时间",position = 33)
    private String workHours;

    @ApiModelProperty(value = "单价",position = 34)
    private String unitPrice;

    @ApiModelProperty(value = "其它激励奖金",position = 35)
    private String incentiveAmount;

    @ApiModelProperty(value = "结算方式",position = 36)
    private String settlementMethod;

    @ApiModelProperty(value = "结算周期",position = 37)
    private String settlementCycle;

    @ApiModelProperty(value = "活动id",position = 38)
    private String activityUuid;

    @ApiModelProperty(value = "项目id",position = 39)
    private String projectId;

    @ApiModelProperty(value = "活动名称",position = 40)
    private String activityName;

    @ApiModelProperty(value = "计划招募人数",position = 41)
    private Integer user_no;

    @ApiModelProperty(value = "已参加人数",position = 42)
    private Integer user_statement_no;

    @ApiModelProperty(value = "审核状态，0：付款审核，1：审核完成",position = 43)
    private Integer auditStatus;

    @ApiModelProperty(value = "活动开始时间",position = 44)
    private String activityStartTime;

    @ApiModelProperty(value = "活动结束时间",position = 45)
    private String activityEndTime;

    @ApiModelProperty(value = "活动角色:1 admin 2 财务负责人 3 活动负责人 4其它",position = 46)
    private Integer activityRole;

    @ApiModelProperty(value = "是否存在立项单使用人 1存在 0不存在",position = 47)
    private Integer hasCreateUser;
    
    @ApiModelProperty(value = "申请人编号",position = 48)
    private String requestUser;

    @ApiModelProperty(value = "是否被停止",position = 49)
    private String stopFlag;

    public String getStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(String stopFlag) {
        this.stopFlag = stopFlag;
    }

    public String getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(String requestUser) {
		this.requestUser = requestUser;
	}

	public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getUser_no() {
        return user_no;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public Integer getUser_statement_no() {
        return user_statement_no;
    }

    public void setUser_statement_no(Integer user_statement_no) {
        this.user_statement_no = user_statement_no;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public String getActivityUuid() {
        return activityUuid;
    }

    public void setActivityUuid(String activityUuid) {
        this.activityUuid = activityUuid;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public String getProjectedSalesNo() {
        return projectedSalesNo;
    }

    public void setProjectedSalesNo(String projectedSalesNo) {
        this.projectedSalesNo = projectedSalesNo;
    }

    public String getProjectedSalesMoney() {
        return projectedSalesMoney;
    }

    public void setProjectedSalesMoney(String projectedSalesMoney) {
        this.projectedSalesMoney = projectedSalesMoney;
    }

    public String getEstimatedCostRate() {
        return estimatedCostRate;
    }

    public void setEstimatedCostRate(String estimatedCostRate) {
        this.estimatedCostRate = estimatedCostRate;
    }

    public String getRequestCreateTime() {
        return requestCreateTime;
    }

    public void setRequestCreateTime(String requestCreateTime) {
        this.requestCreateTime = requestCreateTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getIncentiveAmount() {
        return incentiveAmount;
    }

    public void setIncentiveAmount(String incentiveAmount) {
        this.incentiveAmount = incentiveAmount;
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

    public String getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(String totalDay) {
        this.totalDay = totalDay;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSettlementMethod() {
        return settlementMethod;
    }

    public void setSettlementMethod(String settlementMethod) {
        this.settlementMethod = settlementMethod;
    }

    public String getSettlementCycle() {
        return settlementCycle;
    }

    public void setSettlementCycle(String settlementCycle) {
        this.settlementCycle = settlementCycle;
    }

    public Integer getActivityState() {
        return activityState;
    }

    public void setActivityState(Integer activityState) {
        this.activityState = activityState;
    }

    public String getRequestTitle(){
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle){
        this.requestTitle = requestTitle;
    }

    public String getCompanyCode(){
        return companyCode;
    }

    public void setCompanyCode(String companyCode){
        this.companyCode = companyCode;
    }

    public String getRequestEndTime(){
        return requestEndTime;
    }

    public void setRequestEndTime(String requestEndTime){
        this.requestEndTime = requestEndTime;
    }

    public String getWbs(){
        return wbs;
    }

    public void setWbs(String wbs){
        this.wbs = wbs;
    }

    public String getRequestCompanyCode(){
        return requestCompanyCode;
    }

    public void setRequestCompanyCode(String requestCompanyCode){
        this.requestCompanyCode = requestCompanyCode;
    }

    public String getRequestTelphone(){
        return requestTelphone;
    }

    public void setRequestTelphone(String requestTelphone){
        this.requestTelphone = requestTelphone;
    }

    public String getRequestReasons(){
        return requestReasons;
    }

    public void setRequestReasons(String requestReasons){
        this.requestReasons = requestReasons;
    }

    public String getFeeDetailType(){
        return feeDetailType;
    }

    public void setFeeDetailType(String feeDetailType){
        this.feeDetailType = feeDetailType;
    }

    public String getOrderNo(){
        return orderNo;
    }

    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }

    public Double getTpFee(){
        return tpFee;
    }

    public void setTpFee(Double tpFee){
        this.tpFee = tpFee;
    }

    public Double getOtherFee(){
        return otherFee;
    }

    public void setOtherFee(Double otherFee){
        this.otherFee = otherFee;
    }

    public String getRequestId(){
        return requestId;
    }

    public void setRequestId(String requestId){
        this.requestId = requestId;
    }

    public String getRequestUserName(){
        return requestUserName;
    }

    public void setRequestUserName(String requestUserName){
        this.requestUserName = requestUserName;
    }

    public String getManagerName(){
        return managerName;
    }

    public void setManagerName(String managerName){
        this.managerName = managerName;
    }

    public String getRequestDeptName(){
        return requestDeptName;
    }

    public void setRequestDeptName(String requestDeptName){
        this.requestDeptName = requestDeptName;
    }

    public String getDeptName(){
        return deptName;
    }

    public void setDeptName(String deptName){
        this.deptName = deptName;
    }

    public String getActivityStartTime(){
        return activityStartTime;
    }

    public void setActivityStartTime(String activityStartTime){
        this.activityStartTime = activityStartTime;
    }

    public String getActivityEndTime(){
        return activityEndTime;
    }

    public void setActivityEndTime(String activityEndTime){
        this.activityEndTime = activityEndTime;
    }

    public Integer getActivityRole(){
        return activityRole;
    }

    public void setActivityRole(Integer activityRole){
        this.activityRole = activityRole;
    }

    public Integer getHasCreateUser(){
        return hasCreateUser;
    }

    public void setHasCreateUser(Integer hasCreateUser){
        this.hasCreateUser = hasCreateUser;
    }
}
