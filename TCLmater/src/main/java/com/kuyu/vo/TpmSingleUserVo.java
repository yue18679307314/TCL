package com.kuyu.vo;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("立项单可使用人传入类")
public class TpmSingleUserVo{

	@ApiModelProperty("共享申请单单据号")
	@JsonProperty("FSSC_BILL")
	@JSONField(name = "FSSC_BILL")
	private String fssc_bill;
	@ApiModelProperty("用户列表")
	@JsonProperty("PERSON_LIST")
	@JSONField(name = "PERSON_LIST")
	private List<PersonCodeVo> person_list;
	public String getFssc_bill() {
		return fssc_bill;
	}
	public void setFssc_bill(String fssc_bill) {
		this.fssc_bill = fssc_bill;
	}
	public List<PersonCodeVo> getPerson_list() {
		return person_list;
	}
	public void setPerson_list(List<PersonCodeVo> person_list) {
		this.person_list = person_list;
	}
	
	
	
}
