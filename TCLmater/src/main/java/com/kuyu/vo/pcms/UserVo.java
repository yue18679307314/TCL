package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pc on 2018/12/11
 */
public class UserVo {
    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("员工姓名")
    private String cn;

    @ApiModelProperty("所属机构编码")
    private String departmentNumber;

    @ApiModelProperty("所属机构名称")
    private String departmentsPositions;

    @ApiModelProperty("展示顺序 按升序展示（从小到大）")
    private String displayId;

    @ApiModelProperty("员工编码")
    private String employeeNumber;

    @ApiModelProperty("是否离职 3是在职。0是离职")
    private String empstatus;

    @ApiModelProperty("英文名")
    private String en;

    @ApiModelProperty("拓展属性，使用JSON格式存储")
    private String extras;

    @ApiModelProperty("员工性别")
    private String gender;

    @ApiModelProperty("邮箱")
    private String mail;

    @ApiModelProperty("手机")
    private String mobile;

    @ApiModelProperty("手机短号")
    private String mobileShort;

    @ApiModelProperty("职位")
    private String positionName;

    @ApiModelProperty("职位英文名称")
    private String positionNameEn;

    @ApiModelProperty("姓名拼音")
    private String py;

    @ApiModelProperty("电话号码")
    private String telephoneNumber;

    @ApiModelProperty("办公短号")
    private String telephoneShort;

    @ApiModelProperty("员工账号")
    private String uId;

    @ApiModelProperty("更新时间")
    private String uploadTime;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getDepartmentsPositions() {
        return departmentsPositions;
    }

    public void setDepartmentsPositions(String departmentsPositions) {
        this.departmentsPositions = departmentsPositions;
    }

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmpstatus() {
        return empstatus;
    }

    public void setEmpstatus(String empstatus) {
        this.empstatus = empstatus;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobileShort() {
        return mobileShort;
    }

    public void setMobileShort(String mobileShort) {
        this.mobileShort = mobileShort;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionNameEn() {
        return positionNameEn;
    }

    public void setPositionNameEn(String positionNameEn) {
        this.positionNameEn = positionNameEn;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getTelephoneShort() {
        return telephoneShort;
    }

    public void setTelephoneShort(String telephoneShort) {
        this.telephoneShort = telephoneShort;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }
}
