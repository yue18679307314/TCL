package com.kuyu.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("立项单可使用人")
@TableName("tpm_single_user")
public class TpmSingleUserModel implements Serializable{

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	@TableId
	@ApiModelProperty("主键")
	private String uuid;
	
	@ApiModelProperty("共享申请单单据号")
	private String fssc_bill;
	
	@ApiModelProperty("员工工号")
	private String person_code;

	@ApiModelProperty("操作时间")
	private String op_time;
	
	@ApiModelProperty("原立项人")
	private String request_user_original;
	public String getRequest_user_original() {
		return request_user_original;
	}

	public void setRequest_user_original(String request_user_original) {
		this.request_user_original = request_user_original;
	}

	public String getOp_time() {
		return op_time;
	}

	public void setOp_time(String op_time) {
		this.op_time = op_time;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFssc_bill() {
		return fssc_bill;
	}

	public void setFssc_bill(String fssc_bill) {
		this.fssc_bill = fssc_bill;
	}

	public String getPerson_code() {
		return person_code;
	}

	public void setPerson_code(String person_code) {
		this.person_code = person_code;
	}
	
	
}
