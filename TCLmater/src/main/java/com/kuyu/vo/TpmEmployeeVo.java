package com.kuyu.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONPOJOBuilder;
import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuyu.common.BasePageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("员工基本信息Model")
public class TpmEmployeeVo extends BasePageQuery implements Serializable{
	
	/**
	 * 
	 */
	@TableField(exist=false)
	private static final long serialVersionUID = 4999163694304048702L;
	
	/**员工工号*/
	@ApiModelProperty("员工工号")
	@JsonProperty("PERSON_CODE")
	private String person_code;
	
	/**员工姓名*/
	@ApiModelProperty("员工姓名")
	@JsonProperty("PERSON_NAME")
	private String person_name;
	
	/**员工账号*/
	@ApiModelProperty("员工账号")
	@JsonProperty("ITCODE")
	private String itcode;
	
	/**电子邮箱*/
	@ApiModelProperty("电子邮箱")
	@JsonProperty("EMAIL")
	private String email;
	
	/**电话号码*/
	@ApiModelProperty("电话号码")
	@JsonProperty("MOBILE")
	private String mobile;
	
	/**职务编码*/
	@ApiModelProperty("职务编码")
	@JsonProperty("ZHIWU_CODE")
	private String zhiwu_code;
	
	/**职务名称*/
	@ApiModelProperty("职务名称")
	@JsonProperty("ZHIWU_NAME")
	private String zhiwu_name;
	
	/**所在部门编码*/
	@ApiModelProperty("所在部门编码")
	@JsonProperty("ORG_CODE")
	private String org_code;
	
	/**所在部门名称*/
	@ApiModelProperty("所在部门名称")
	@JsonProperty("ORG_NAME")
	private String org_name;
	
	/**公司代码*/
	@ApiModelProperty("公司代码")
	@JsonProperty("COMPANY")
	private String company;
	
	/**删除标记：空或者有值，有值表示禁用用户*/
	@ApiModelProperty("删除标记")
	@JsonProperty("DEL_FLAG")
	private String del_flag;

	public String getPerson_code() {
		return person_code;
	}

	public void setPerson_code(String person_code) {
		this.person_code = person_code;
	}

	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}

	public String getItcode() {
		return itcode;
	}

	public void setItcode(String itcode) {
		this.itcode = itcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getZhiwu_code() {
		return zhiwu_code;
	}

	public void setZhiwu_code(String zhiwu_code) {
		this.zhiwu_code = zhiwu_code;
	}

	public String getZhiwu_name() {
		return zhiwu_name;
	}

	public void setZhiwu_name(String zhiwu_name) {
		this.zhiwu_name = zhiwu_name;
	}

	public String getOrg_code() {
		return org_code;
	}

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

}