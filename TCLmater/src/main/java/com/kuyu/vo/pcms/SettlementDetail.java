package com.kuyu.vo.pcms;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SettlementDetail {

	@JsonProperty("DETAIL_ID")
	private String detailId;
	
	/*PTFP:纸质普通发票或无发票
	PTFP01:电子普通发票
	ZZSZYFP:纸质增值税专用发票
	*/
	@JsonProperty("DETAIL_INVOUCETYPE")
	private String detailInvoucetype;
	
	@JsonProperty("DETAIL_INVOUCETNUM")
	private String detailInvoucetnum;
	
	@JsonProperty("DETAIL_MONEY")
	private String detailmoney;
	
	@JsonProperty("DETAIL_MEMO")
	private String detailMemo;

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getDetailInvoucetype() {
		return detailInvoucetype;
	}

	public void setDetailInvoucetype(String detailInvoucetype) {
		this.detailInvoucetype = detailInvoucetype;
	}

	public String getDetailInvoucetnum() {
		return detailInvoucetnum;
	}

	public void setDetailInvoucetnum(String detailInvoucetnum) {
		this.detailInvoucetnum = detailInvoucetnum;
	}

	public String getDetailmoney() {
		return detailmoney;
	}

	public void setDetailmoney(String detailmoney) {
		this.detailmoney = detailmoney;
	}

	public String getDetailMemo() {
		return detailMemo;
	}

	public void setDetailMemo(String detailMemo) {
		this.detailMemo = detailMemo;
	}
	
	
}
