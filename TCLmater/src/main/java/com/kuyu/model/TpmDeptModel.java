package com.kuyu.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuyu.util.DateUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("TpmDept(部门信息模型)")
@TableName("tpm_dept")
public class TpmDeptModel implements Serializable{
	/**
	 * 
	 */
	@TableField(exist=false)
	private static final long serialVersionUID = -9084664322127784668L;
	/**主键*/
	@TableId
	@ApiModelProperty("主键")
	@JsonProperty("UUID")
	private String uuid;
	
	/**部门编码*/
	@ApiModelProperty("部门编码")
	@JsonProperty("ORG_CODE")
	private String org_code;
	
	/**部门名称*/
	@ApiModelProperty("部门名称")
	@JsonProperty("ORG_NAME")
	private String org_name;
	
	/**上级部门编码*/
	@ApiModelProperty("上级部门编码")
	@JsonProperty("ORG_CODE_PARENT")
	private String org_code_parent;
	
	/**上级部门名称*/
	@ApiModelProperty("上级部门名称")
	@JsonProperty("ORG_NAME_PARENT")
	private String org_name_parent;
	
	/**组织类型：小于等于30时为机构，大于30或者为空的时候为部门*/
	@ApiModelProperty("组织类型：小于等于30时为机构，大于30或者为空的时候为部门")
	@JsonProperty("ORG_TYPE")
	private String org_type;
	
	/**更新标识：UPDATE，INSERT，DELETE*/
	@ApiModelProperty("更新标识：UPDATE，INSERT，DELETE")
	@JsonProperty("UPDATE_FLAG")
	private String update_flag;
	
	@ApiModelProperty(hidden = true,value = "财务负责人姓名")
	@JsonProperty("PERSON_NAME")
	@TableField(exist = false)
	private String person_name;
	
	@ApiModelProperty("更新时间")
	@JsonProperty("UPDATE_TIME")
	private String update_time = DateUtils.currentTime();
	
	@ApiModelProperty(hidden = true,value = "分公司管理员")
	@JsonProperty("BRANCH_ADMIN")
	@TableField(exist = false)
	private String branchAdmin;
	
	public String getBranchAdmin() {
		return branchAdmin;
	}
	public void setBranchAdmin(String branchAdmin) {
		this.branchAdmin = branchAdmin;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	public String getOrg_code_parent() {
		return org_code_parent;
	}
	public void setOrg_code_parent(String org_code_parent) {
		this.org_code_parent = org_code_parent;
	}
	public String getOrg_name_parent() {
		return org_name_parent;
	}
	public void setOrg_name_parent(String org_name_parent) {
		this.org_name_parent = org_name_parent;
	}
	public String getOrg_type() {
		return org_type;
	}
	public void setOrg_type(String org_type) {
		this.org_type = org_type;
	}
	public String getUpdate_flag() {
		return update_flag;
	}
	public void setUpdate_flag(String update_flag) {
		this.update_flag = update_flag;
	}
	@Override
	public String toString() {
		return "TpmDeptModel [uuid=" + uuid + ", org_code=" + org_code + ", org_name=" + org_name + ", org_code_parent="
				+ org_code_parent + ", org_name_parent=" + org_name_parent + ", org_type=" + org_type + ", update_flag="
				+ update_flag + "]";
	}

}
