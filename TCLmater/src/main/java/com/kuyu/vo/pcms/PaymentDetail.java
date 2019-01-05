package com.kuyu.vo.pcms;

import java.util.List;

import com.kuyu.model.pcms.PcmsPaymentDetail;

public class PaymentDetail {

	private List<PcmsPaymentDetail> paymentDetailList; //子单详细
	
	private List<SettlementItemResult> itemList; //结算详细

	public List<PcmsPaymentDetail> getPaymentDetailList() {
		return paymentDetailList;
	}

	public void setPaymentDetailList(List<PcmsPaymentDetail> paymentDetailList) {
		this.paymentDetailList = paymentDetailList;
	}

	public List<SettlementItemResult> getItemList() {
		return itemList;
	}

	public void setItemList(List<SettlementItemResult> itemList) {
		this.itemList = itemList;
	}
	
	
}
