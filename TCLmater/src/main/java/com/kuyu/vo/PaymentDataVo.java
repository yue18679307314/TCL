package com.kuyu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

@ApiModel("付款区明细类")
public class PaymentDataVo {

	@ApiModelProperty("共享借款单单据号")
	@JsonProperty("FSSC_BILL")
	@JSONField(name = "FSSC_BILL")
	private String fssc_bill;
	
	@ApiModelProperty("借款单付款区ID")
	@JsonProperty("PAYMENT_ID")
	@JSONField(name = "PAYMENT_ID")
	private String payment_id;

	public String getFssc_bill() {
		return fssc_bill;
	}

	public void setFssc_bill(String fssc_bill) {
		this.fssc_bill = fssc_bill;
	}

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}
	
	
}
