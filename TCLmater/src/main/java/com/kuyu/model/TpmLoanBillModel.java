package com.kuyu.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("借款单")
@TableName("tpm_loan_bill")
public class TpmLoanBillModel extends Model<TpmLoanBillModel>{

	@ApiModelProperty("唯一值")
	@TableId
	private String load_bill_uuid;
	
	@ApiModelProperty("立项申请单编号")
	private String fssc_bill;
	
	@ApiModelProperty("共享借款的编号")
	private String fssc_loan_bill_no;
	
	@ApiModelProperty("创建时间:YYYY-MM-DD HH(24):mm:ss")
	private String create_time;
	
	@ApiModelProperty("借款单状态:1 财务审核中；2 审核通过；3 已付款；4 已报销；")
	private Integer status;

	@ApiModelProperty("付款成功后返回的json数据")
	private String resultvojson;
	
	@ApiModelProperty("共享付款单流水号")
	private String payment_id;

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	public String getLoad_bill_uuid() {
		return load_bill_uuid;
	}

	public void setLoad_bill_uuid(String load_bill_uuid) {
		this.load_bill_uuid = load_bill_uuid;
	}

	public String getFssc_bill() {
		return fssc_bill;
	}

	public void setFssc_bill(String fssc_bill) {
		this.fssc_bill = fssc_bill;
	}

	public String getFssc_loan_bill_no() {
		return fssc_loan_bill_no;
	}

	public void setFssc_loan_bill_no(String fssc_loan_bill_no) {
		this.fssc_loan_bill_no = fssc_loan_bill_no;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getResultvojson(){
		return resultvojson;
	}

	public void setResultvojson(String resultvojson){
		this.resultvojson = resultvojson;
	}

	public Integer getStatus(){
		return status;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	@Override
	protected Serializable pkVal(){
		return this.load_bill_uuid;
	}
}
