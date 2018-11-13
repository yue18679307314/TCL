package com.kuyu.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel("部门员工查询类")
public class TpmDeptEmplyeeVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String person_code;
	private String person_name;
	private String org_code;
	private String org_name;
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
	
}
