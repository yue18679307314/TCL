package com.kuyu.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.kuyu.util.DateUtils;


@TableName("tpm_branch_admin")
public class TpmBranchAdminModel extends Model<TpmBranchAdminModel> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5174166850295754655L;
	@TableId(type=IdType.AUTO)
    private Integer id;
	private String person_code ;
	private String roleType;//0 分公司管理员
	private String org_code;//分公司管理员负责的分公司代码'
	private String create_time = DateUtils.getTimeStampStr(new Date());
	private String flag;

	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getPerson_code() {
		return person_code;
	}


	public void setPerson_code(String person_code) {
		this.person_code = person_code;
	}


	public String getType() {
		return roleType;
	}


	public void setType(String type) {
		this.roleType = type;
	}


	public String getOrg_code() {
		return org_code;
	}


	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	
	public String getCreate_time() {
		return create_time;
	}


	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}


	public String getRoleType() {
		return roleType;
	}


	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}


	@Override
	protected Serializable pkVal() {
		return null;
	}
	
	
}
