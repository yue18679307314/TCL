package com.kuyu.service;

import java.util.List;

import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.InitItemRequest;
import com.kuyu.vo.pcms.InitPayMentRequest;
import com.kuyu.vo.pcms.PaymentRequest;
import com.kuyu.vo.pcms.PcmsProjectVo;
import com.kuyu.vo.pcms.PcmsProjectVo2;
import com.kuyu.vo.pcms.RequestUserVo;
import com.kuyu.vo.project.ProjectDetialModelVo;

public interface PcmsProjectService {

//	String importProjectDetail(PcmsProjectVo projectvo);

	String importProjectDetail2(PcmsProjectVo2 projectvo,int type);
	
	PcmsProjectVo2 getProjectDeatil(String requestId);

	List<RequestUserVo> getRequestNameList(String orgCode,String searchKey);

	ResultVo changeRequestName(String requestId,String personCode,String personName,String userId);

	void importProjectMaterialDetail(ProjectDetialModelVo vo,int type);

	String initItem(InitItemRequest initItem);

	ResultVo initPayMent(List<PaymentRequest> initPayMent);

	ResultVo initPayMentDetail(List<PaymentRequest> initPayMent);


}
