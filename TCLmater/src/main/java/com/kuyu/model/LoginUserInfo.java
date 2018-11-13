package com.kuyu.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("登陆用户信息")
public class LoginUserInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6274872696553593869L;
	@ApiModelProperty("用户类型：1 临促；2 内部员工")
	private String userType;
	private String userRole; //1 admin; 2  分公司财务负责人; 0  分公司管理员 ; 6 既是分公司管理员，也是分公司财务;
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	private WeixinUserInfo weixinUserInfo;
	private TpmEmployeeModel employeeModel;
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public WeixinUserInfo getWeixinUserInfo() {
		return weixinUserInfo;
	}
	public void setWeixinUserInfo(WeixinUserInfo weixinUserInfo) {
		this.weixinUserInfo = weixinUserInfo;
	}
	public TpmEmployeeModel getEmployeeModel() {
		return employeeModel;
	}
	public void setEmployeeModel(TpmEmployeeModel employeeModel) {
		this.employeeModel = employeeModel;
	}

}
