package com.kuyu.service;

import java.util.List;

import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.PcmsProjectVo;
import com.kuyu.vo.pcms.RequestUserVo;

public interface PcmsProjectService {

	String importProjectDetail(PcmsProjectVo projectvo);

	PcmsProjectVo getProjectDeatil(String requestId);

	List<RequestUserVo> getRequestNameList(String orgCode);

	ResultVo changeRequestName(String requestId,String personCode);

}
