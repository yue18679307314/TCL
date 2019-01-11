package com.kuyu.vo.pcms;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuyu.vo.project.ProjectDetialModelVo;

public class InitItemRequest {

	@JsonProperty("M41E")
	private List<ProjectDetialModelVo> m41e;
	
	@JsonProperty("M41F")
	private List<PcmsProjectVo2> m41f;

	public List<ProjectDetialModelVo> getM41e() {
		return m41e;
	}

	public void setM41e(List<ProjectDetialModelVo> m41e) {
		this.m41e = m41e;
	}

	public List<PcmsProjectVo2> getM41f() {
		return m41f;
	}

	public void setM41f(List<PcmsProjectVo2> m41f) {
		this.m41f = m41f;
	}
	
	
}
