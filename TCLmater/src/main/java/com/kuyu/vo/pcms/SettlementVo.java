package com.kuyu.vo.pcms;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SettlementVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String itids;
	
	@JsonProperty("FSSC_BILL")
	private String fsscBill;
	
	@JsonProperty("REQUEST_USER")
	private String requestUser;
	
	@JsonProperty("REQUEST_DEPART")
	private String requestDepart;
	
	@JsonProperty("DETAIL_LIST")
	private List<SettlementDeatil> detailList;

	@JsonProperty("PAYMENT_LIST")
	private List<SettlementPayment> paymentList;

	public String getFsscBill() {
		return fsscBill;
	}

	public void setFsscBill(String fsscBill) {
		this.fsscBill = fsscBill;
	}

	public String getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(String requestUser) {
		this.requestUser = requestUser;
	}

	public String getRequestDepart() {
		return requestDepart;
	}

	public void setRequestDepart(String requestDepart) {
		this.requestDepart = requestDepart;
	}

	public List<SettlementDeatil> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<SettlementDeatil> detailList) {
		this.detailList = detailList;
	}

	public List<SettlementPayment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<SettlementPayment> paymentList) {
		this.paymentList = paymentList;
	}

	public String getItids() {
		return itids;
	}

	public void setItids(String itids) {
		this.itids = itids;
	}
	
	
}
