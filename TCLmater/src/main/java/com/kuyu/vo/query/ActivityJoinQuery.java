package com.kuyu.vo.query;

import com.kuyu.common.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author jt_L
 * @Date 2017/10/22
 * @Description 活动申请查询Vo
 */
@ApiModel("活动申请查询Vo")
public class ActivityJoinQuery extends BasePageQuery {

    @ApiModelProperty(value = "姓名(模糊查询)",position = 1)
    private String userName;

    @ApiModelProperty(value = "手机号码",position = 2)
    private String telPhone;

    @ApiModelProperty(value = "身份证",position = 3)
    private String idCard;

    @ApiModelProperty(value = "预算部门名称(模糊查询)",position = 4)
    private String deptName;

    @ApiModelProperty(value = "申请状态:0 现场负责人已确认拒绝参加；1 申请中 2 现场负责人已确认可以参加",position = 5)
    private Integer requestState;

    @ApiModelProperty(value = "申请时间",position = 6)
    private String requestTime;

    @ApiModelProperty(value = "活动名称(模糊查询)",position = 7)
    private String projectName;

    @ApiModelProperty(value = "活动负责人名称(模糊查询)",position = 8)
    private String managerName;

    @ApiModelProperty(value = "待办事项查询：1",position = 9)
	private String toDoState;
    public String getToDoState() {
		return toDoState;
	}

	public void setToDoState(String toDoState) {
		this.toDoState = toDoState;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getRequestState() {
        return requestState;
    }

    public void setRequestState(Integer requestState) {
        this.requestState = requestState;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
