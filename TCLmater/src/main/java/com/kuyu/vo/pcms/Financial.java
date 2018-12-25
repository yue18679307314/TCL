package com.kuyu.vo.pcms;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Financial {

	@JsonProperty("FINANCIAL_NUM")
	private String financialNum;
	
	@JsonProperty("FINANCIAL_MONEY")
	private String financialMoney;
	
	@JsonProperty("FINANCIAL_STATUS")
	private String financialStatus;
	
	@JsonProperty("FINANCIAL_TIME")
	private String financialTime;

	public String getFinancialNum() {
		return financialNum;
	}

	public void setFinancialNum(String financialNum) {
		this.financialNum = financialNum;
	}

	public String getFinancialMoney() {
		return financialMoney;
	}

	public void setFinancialMoney(String financialMoney) {
		this.financialMoney = financialMoney;
	}

	public String getFinancialStatus() {
		return financialStatus;
	}

	public void setFinancialStatus(String financialStatus) {
		this.financialStatus = financialStatus;
	}

	public String getFinancialTime() {
		return financialTime;
	}

	public void setFinancialTime(String financialTime) {
		this.financialTime = financialTime;
	}
	
	
}
