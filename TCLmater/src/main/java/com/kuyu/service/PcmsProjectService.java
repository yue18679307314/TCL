package com.kuyu.service;

import com.kuyu.vo.pcms.PcmsProjectVo;

public interface PcmsProjectService {

	String importProjectDetail(PcmsProjectVo projectvo);

	PcmsProjectVo getProjectDeatil(String requestId);

}
