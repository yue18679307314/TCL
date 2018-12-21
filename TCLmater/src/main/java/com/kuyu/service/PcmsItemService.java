package com.kuyu.service;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.pcms.PcmsMaterial;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.ItemDetail;
import com.kuyu.vo.pcms.ItemResult;
import com.kuyu.vo.pcms.SettlementRequest;
import com.kuyu.vo.pcms.SettlementVo;

public interface PcmsItemService  {


	Page<ItemResult> getItemListByParam(String searchKey,Integer current,Integer size, 
			String companyCode,String userType,String deptCode,String approvalStatrTime,
			String approvalEndTime,Integer status);

	ItemDetail getItemDetailById(Integer itid,Integer type);

	int editMaterial(PcmsMaterial metarial);

//	ResultVo changeItemStatus(Integer itid,Integer status,String reason);
	ResultVo changeItemStatus(Integer itid, Integer status, LoginUserInfo userInfo,String context);

	ResultVo settlement(SettlementRequest settVo) throws UnsupportedEncodingException, ClientProtocolException, IOException;

	int settlementStatus(String settlementNumber);

	

}
