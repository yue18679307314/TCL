package com.kuyu.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("TpmFinancialModel(分公司财务人员信息)")
//@TableName("tpm_financial")
public class TpmFinancialModel implements Serializable{

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 948749476077931551L;
	
	@ApiModelProperty("自增id")
	@TableId
	private Integer id;
	
	@ApiModelProperty("部门编号")
	private String org_code;
	
	@ApiModelProperty("员工工号")
	private String person_code;
	
	@ApiModelProperty("创建时间:YYYY-MM-DD HH(24):mm:ss")
	private String create_time;
	
	@ApiModelProperty("修改时间:YYYY-MM-DD HH(24):mm:ss")
	private String modify_time;
	
	@ApiModelProperty("修改用户")
	private String modify_user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrg_code() {
		return org_code;
	}

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	public String getPerson_code() {
		return person_code;
	}

	public void setPerson_code(String person_code) {
		this.person_code = person_code;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getModify_time() {
		return modify_time;
	}

	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}

	public String getModify_user() {
		return modify_user;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}

}
