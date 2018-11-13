package com.kuyu.vo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuyu.common.BasePageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("部门信息模型查询类")
public class TpmDeptVo extends BasePageQuery implements Serializable{
	/**
	 * 
	 */
	@TableField(exist=false)
	private static final long serialVersionUID = -9084664322127784668L;
	
	/**部门编码*/
	@ApiModelProperty("部门编码")
	@JsonProperty(value = "ORG_CODE")
	private String org_code;
	
	/**部门名称*/
	@ApiModelProperty("部门名称")
	@JsonProperty(value = "ORG_NAME")
	private String org_name;
	
	/**上级部门编码*/
	@ApiModelProperty("上级部门编码")
	@JsonProperty(value = "ORG_CODE_PARENT")
	private String org_code_parent;
	
	/**上级部门名称*/
	@ApiModelProperty("上级部门名称")
	@JsonProperty(value = "ORG_NAME_PARENT")
	private String org_name_parent;
	
	/**组织类型：小于等于30时为机构，大于30或者为空的时候为部门*/
	@ApiModelProperty("组织类型：小于等于30时为机构，大于30或者为空的时候为部门")
	@JsonProperty(value = "ORG_TYPE")
	private String org_type;
	
	/**更新标识：UPDATE，INSERT，DELETE*/
	@ApiModelProperty("更新标识：UPDATE，INSERT，DELETE")
	@JsonProperty(value = "UPDATE_FLAG")
	private String update_flag;

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

	
}
