package com.kuyu.vo;

import com.kuyu.common.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author jt_L
 * @Date 2017/10/17
 * @Description 活动详细查询vo
 */
@ApiModel("活动详细查询vo")
public class ProjectActivityQuery extends BasePageQuery{

    @ApiModelProperty(value = "公司代码",position = 1)
    private String companyCode;

    @ApiModelProperty(value = "申请部门名称(模糊查询)",position = 2)
    private String requestDeptName;

    @ApiModelProperty(value = "申请人名称(模糊查询)",position = 3)
    private String requestUserName;

    @ApiModelProperty(value = "申请单ID(申请单号)",position = 4)
    private String requestId;

    @ApiModelProperty(value = "申请标题(模糊查询)",position = 5)
    private String requestTitle;

    @ApiModelProperty(value = "活动项目名称(模糊查询)",position = 6)
    private String projectName;

    @ApiModelProperty(value = "申请创建开始日期",position = 7)
    private String startTime1;

    @ApiModelProperty(value = "申请创建结束日期",position = 8)
    private String endTime1;

    @ApiModelProperty(value = "申请结束开始日期",position = 9)
    private String startTime2;

    @ApiModelProperty(value = "申请结束结束日期",position = 10)
    private String endTime2;

    @ApiModelProperty(value = "所在城市",position = 11)
    private String city;

    public String getCompanyCode(){
        return companyCode;
    }

    public void setCompanyCode(String companyCode){
        this.companyCode = companyCode;
    }

    public String getRequestId(){
        return requestId;
    }

    public void setRequestId(String requestId){
        this.requestId = requestId;
    }

    public String getRequestTitle(){
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle){
        this.requestTitle = requestTitle;
    }

    public String getProjectName(){
        return projectName;
    }

    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

    public String getStartTime1(){
        return startTime1;
    }

    public void setStartTime1(String startTime1){
        this.startTime1 = startTime1;
    }

    public String getEndTime1(){
        return endTime1;
    }

    public void setEndTime1(String endTime1){
        this.endTime1 = endTime1;
    }

    public String getStartTime2(){
        return startTime2;
    }

    public void setStartTime2(String startTime2){
        this.startTime2 = startTime2;
    }

    public String getEndTime2(){
        return endTime2;
    }

    public void setEndTime2(String endTime2){
        this.endTime2 = endTime2;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getRequestUserName(){
        return requestUserName;
    }

    public void setRequestUserName(String requestUserName){
        this.requestUserName = requestUserName;
    }

    public String getRequestDeptName(){
        return requestDeptName;
    }

    public void setRequestDeptName(String requestDeptName){
        this.requestDeptName = requestDeptName;
    }
}
