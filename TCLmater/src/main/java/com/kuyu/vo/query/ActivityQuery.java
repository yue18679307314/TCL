package com.kuyu.vo.query;

import com.kuyu.common.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author jt_L
 * @Date 2017/10/22
 * @Description 促销活动查询VO
 */
@ApiModel("促销活动查询VO")
public class ActivityQuery extends BasePageQuery{

    @ApiModelProperty(value = "立项申请id",position = 1)
    private String requestId;

    @ApiModelProperty(value = "活动id",position = 2)
    private String activityUuid;

    @ApiModelProperty(value = "活动项目名称(模糊查询)",position = 3)
    private String projectName;

    @ApiModelProperty(value = "预算部门",position = 4)
    private String dept;

    @ApiModelProperty(value = "预算部门名称(模糊查询)",position = 5)
    private String deptName;

    @ApiModelProperty(value = "开始日期",position = 6)
    private String startTime;

    @ApiModelProperty(value = "结束日期",position = 7)
    private String endTime;

    @ApiModelProperty(value = "活动状态(0未开始 1进行中 2已结束 其它:全部)",position = 7)
    private Integer activityState;

    @ApiModelProperty(value = "所在城市",position = 8)
    private String city;

    @ApiModelProperty(value = "立项人姓名",position = 9)
    private String requestUserName;

    @ApiModelProperty(value = "活动负责人姓名",position = 9)
    private String managerName;

    public String getRequestUserName() {
        return requestUserName;
    }

    public void setRequestUserName(String requestUserName) {
        this.requestUserName = requestUserName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getRequestId(){
        return requestId;
    }

    public void setRequestId(String requestId){
        this.requestId = requestId;
    }

    public String getActivityUuid(){
        return activityUuid;
    }

    public void setActivityUuid(String activityUuid){
        this.activityUuid = activityUuid;
    }

    public String getProjectName(){
        return projectName;
    }

    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    public String getDept(){
        return dept;
    }

    public void setDept(String dept){
        this.dept = dept;
    }

    public String getDeptName(){
        return deptName;
    }

    public void setDeptName(String deptName){
        this.deptName = deptName;
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

    public Integer getActivityState(){
        return activityState;
    }

    public void setActivityState(Integer activityState){
        this.activityState = activityState;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }


}
