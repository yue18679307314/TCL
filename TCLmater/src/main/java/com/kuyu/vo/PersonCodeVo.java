package com.kuyu.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("员工工号传入类")
public class PersonCodeVo {

	@ApiModelProperty("员工工号")
	@JsonProperty(value = "PERSON_CODE")
	@JSONField(name = "PERSON_CODE")
	private String person_code;

	public String getPerson_code() {
		return person_code;
	}

	public void setPerson_code(String person_code) {
		this.person_code = person_code;
	}

	
	
}
