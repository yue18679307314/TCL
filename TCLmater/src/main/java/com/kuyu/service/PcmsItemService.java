package com.kuyu.service;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.pcms.PcmsMaterial;
import com.kuyu.model.pcms.PcmsPaymentDetail;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.ItemDetail;
import com.kuyu.vo.pcms.ItemEndRequest;
import com.kuyu.vo.pcms.ItemResult;
import com.kuyu.vo.pcms.PaymentRequest;
import com.kuyu.vo.pcms.PaymentResult;
import com.kuyu.vo.pcms.SettlementDetailResult;
import com.kuyu.vo.pcms.SettlementRequest;
import com.kuyu.vo.pcms.SettlementVo;

public interface PcmsItemService  {


	Page<ItemResult> getItemListByParam(String searchKey,Integer current,Integer size, 
			String companyCode,String userType,String deptCode,String approvalStatrTime,
			String approvalEndTime,Integer status, String personCode);

	ItemDetail getItemDetailById(Integer itid,Integer type);

	int editMaterial(PcmsMaterial metarial);

//	ResultVo changeItemStatus(Integer itid,Integer status,String reason);
	ResultVo changeItemStatus(Integer itid, Integer status, LoginUserInfo userInfo,String context);

	ResultVo settlement(SettlementRequest settVo);

	int settlementStatus(String settlementNumber);

	void updateItemStatusBystatus(String synDate);

	int createPayment(PaymentRequest payment);

	int createPaymentDetail(PaymentRequest payment);

	ResultVo queryPaymentDetail(String fsscBill) throws UnsupportedEncodingException, IOException;

	SettlementDetailResult settlementDetail(String settNumber);

	List<PaymentResult> paymentList();

	List<PcmsPaymentDetail> paymentDetail(String fsscBill);

	int itemEnd(ItemEndRequest itemEnd);

	int payEndOrTranslate(ItemEndRequest itemEnd);

	LoginUserInfo getUserInfo(String employeenumber);

}
