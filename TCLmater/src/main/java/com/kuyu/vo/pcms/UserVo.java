package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pc on 2018/12/11
 */
public class UserVo {

    @ApiModelProperty("分部编码")
    private String branchId;

    @ApiModelProperty("分部名称")
    private String branchName;

    @ApiModelProperty("分公司编码")
    private String filialeId;

    @ApiModelProperty("分公司名称")
    private String filialeName;

    @ApiModelProperty("职位名称")
    private String positionName;

    @ApiModelProperty("用户编码")
    private String userId;

    @ApiModelProperty("用户姓名")
    private String userName;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getFilialeId() {
        return filialeId;
    }

    public void setFilialeId(String filialeId) {
        this.filialeId = filialeId;
    }

    public String getFilialeName() {
        return filialeName;
    }

    public void setFilialeName(String filialeName) {
        this.filialeName = filialeName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
