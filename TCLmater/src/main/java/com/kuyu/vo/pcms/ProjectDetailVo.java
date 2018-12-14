package com.kuyu.vo.pcms;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.kuyu.model.pcms.PcmsOthertm;
import com.kuyu.model.pcms.PcmsOutdoors;
import com.kuyu.model.pcms.PcmsShowcase;

@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class ProjectDetailVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@JsonProperty("DETAIL_CHANNEL")
	private String detailChannel;
		
	@JsonProperty("DETAIL_COST")
	private String detailCost;
		
	@JsonProperty("DETAIL_WBS")
	private String detailWbs;

	@JsonProperty("DETAIL_VENDOR")
	private String detailVendor;
	
	@JsonProperty("DETAIL_SUBJECT")
	private String detailSubject;
	
	@JsonProperty("DETAIL_ACCOUNT")
	private String detailAccount;
	
	@JsonProperty("DETAIL_ID")
	private String detailId;
	
	@JsonProperty("DETAIL_AMOUNT")
	private String detailAmount;
	
	@JsonProperty("DETAIL_STANDARD")
	private String detailStandard;
	
	@JsonProperty("DETAIL_TASK")
	private String detailTask;
	
	@JsonProperty("DETAIL_MEMO")
	private String detailMemo;
	
	@JsonProperty("CHILDREN3_LIST")
	private List<PcmsOthertm> CHILDREN3_LIST;
	
	@JsonProperty("CHILDREN2_LIST")
	private List<PcmsOutdoors> CHILDREN2_LIST;
	
	@JsonProperty("CHILDREN1_LIST")
	private List<PcmsShowcase> CHILDREN1_LIST;

	public String getDetailChannel() {
		return detailChannel;
	}

	public void setDetailChannel(String detailChannel) {
		this.detailChannel = detailChannel;
	}

	public String getDetailCost() {
		return detailCost;
	}

	public void setDetailCost(String detailCost) {
		this.detailCost = detailCost;
	}

	public String getDetailWbs() {
		return detailWbs;
	}

	public void setDetailWbs(String detailWbs) {
		this.detailWbs = detailWbs;
	}

	public String getDetailVendor() {
		return detailVendor;
	}

	public void setDetailVendor(String detailVendor) {
		this.detailVendor = detailVendor;
	}

	public String getDetailSubject() {
		return detailSubject;
	}

	public void setDetailSubject(String detailSubject) {
		this.detailSubject = detailSubject;
	}

	public String getDetailAccount() {
		return detailAccount;
	}

	public void setDetailAccount(String detailAccount) {
		this.detailAccount = detailAccount;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getDetailAmount() {
		return detailAmount;
	}

	public void setDetailAmount(String detailAmount) {
		this.detailAmount = detailAmount;
	}

	public String getDetailStandard() {
		return detailStandard;
	}

	public void setDetailStandard(String detailStandard) {
		this.detailStandard = detailStandard;
	}

	public String getDetailTask() {
		return detailTask;
	}

	public void setDetailTask(String detailTask) {
		this.detailTask = detailTask;
	}

	public String getDetailMemo() {
		return detailMemo;
	}

	public void setDetailMemo(String detailMemo) {
		this.detailMemo = detailMemo;
	}

	public List<PcmsOthertm> getCHILDREN3_LIST() {
		return CHILDREN3_LIST;
	}

	public void setCHILDREN3_LIST(List<PcmsOthertm> cHILDREN3_LIST) {
		CHILDREN3_LIST = cHILDREN3_LIST;
	}

	public List<PcmsOutdoors> getCHILDREN2_LIST() {
		return CHILDREN2_LIST;
	}

	public void setCHILDREN2_LIST(List<PcmsOutdoors> cHILDREN2_LIST) {
		CHILDREN2_LIST = cHILDREN2_LIST;
	}

	public List<PcmsShowcase> getCHILDREN1_LIST() {
		return CHILDREN1_LIST;
	}

	public void setCHILDREN1_LIST(List<PcmsShowcase> cHILDREN1_LIST) {
		CHILDREN1_LIST = cHILDREN1_LIST;
	}
	
}
