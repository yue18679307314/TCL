package com.kuyu.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("临促结算单表")
@TableName("tpm_user_statement")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TpmUserStatementModel extends Model<TpmUserStatementModel> {

	/* 结算单编号 */
	@TableId
	@ApiModelProperty("结算单编号:唯一值（不可重复）(结算单编号使用此字段)")
	private String uuid;

	/* 活动id */
	@ApiModelProperty("活动id")
	private String activity_uuid;

	/* 参加日期:YYYY-MM-DD */
	@ApiModelProperty("参加日期:YYYY-MM-DD")
	private String activity_time;

	@ApiModelProperty("临促openid")
	private String openid;

	@ApiModelProperty("参加的状态:0未通过；1 已生成结算单； 2 现场负责人已确认申请单； 4 财务审核通过；5 财务审核不通过")
	private Integer request_state;

	@ApiModelProperty("原因")
	private String reason;

	@ApiModelProperty("付款状态:-1未处理 1 申请付款中；0失败；2 付款成功,默认-1")
	private Integer pay_state=-1;

	@ApiModelProperty("借款单id:生成的借款单id，在未生成借款单前为空")
	private String load_bill_uuid;

	@ApiModelProperty("上班打卡时间:YYYY-MM-DD HH(24):mm:ss")
	private String work_start_time;

	@ApiModelProperty("上班打卡上传图片URL")
	private String work_start_imgurl;

	@ApiModelProperty("下班打卡时间")
	private String work_end_time;

	@ApiModelProperty("下班打卡上传图片URL")
	private String work_end_imgurl;

	@ApiModelProperty("工作小时")
	private Integer work_hours;
	
	@ApiModelProperty("工作分钟")
	private Integer work_minute;

	@ApiModelProperty("有效的结算工作时长")
	private Integer valid_work_hours;
	
	@ApiModelProperty("有效的结算工作分钟")
	private Integer valid_work_minute;

	@ApiModelProperty("修改工时原因")
	private String reduce_hours_reason;

	@ApiModelProperty("审核时间")
	private String check_time;
	
	@ApiModelProperty("修改工时的状态:0未审核； 1通过； 2不通过")
	private Integer hours_state;

	@ApiModelProperty("扣减工时报酬")
	private Double reduce_money;

	@ApiModelProperty("扣减工时报酬事由")
	private String reduce_money_reason;

	@ApiModelProperty("其它激励金额")
	private Double incentive_amount;

	@ApiModelProperty("其它激励事由")
	private String incentive_reason;

	@ApiModelProperty("实际报酬")
	private Double real_salary;
	
	@ApiModelProperty("调整工时备注")
	private String adjust_hours_note;
	
	@ApiModelProperty("调整工资备注")
	private String adjust_money_note;
	
	@ApiModelProperty("财务审核时间")
	private String financial_check_time;

	@ApiModelProperty("查询时间(年月)：yyyy-MM")
	@TableField(exist = false)
	private String month;
	
	@ApiModelProperty("奖励或者扣除，true奖励，false扣除")
	@TableField(exist = false)
	private Boolean isIncentiveOrReduce; 

	@ApiModelProperty("人天单价")
	@TableField(exist = false)
	private Double unit_price;
	
	@ApiModelProperty("临促姓名")
	@TableField(exist = false)
	private String name;
	
	@ApiModelProperty("签到地址")
	@TableField(exist = false)
	private String start_clock_address;
	
	@ApiModelProperty("签退地址")
	@TableField(exist = false)
	private String end_clock_address;
	
	@ApiModelProperty("现场负责人姓名")
	@TableField(exist = false)
	private String person_name;
	
	@ApiModelProperty("负责人手机")
	@TableField(exist = false)
	private String mobile;
	
	@ApiModelProperty("计划工作时间")
	@TableField(exist = false)
	private Integer planWorkHours;
	
	@ApiModelProperty("预算部门")
	@TableField(exist = false)
	private String dept;
	
	@ApiModelProperty("活动名称")
	@TableField(exist = false)
	private String activity_name;

	@ApiModelProperty("财务共享借款单流水号")
	@TableField("payment_id")
	private String paymentId;
	
	@ApiModelProperty("城市")
	@TableField(exist = false)
	private String clock_city;
	
	@ApiModelProperty("地址")
	@TableField(exist = false)
	private String clock_address;
	
	@ApiModelProperty("签到地址")
	@TableField(exist = false)
	private String  work_start_address;
	
	@ApiModelProperty("签退地址")
	@TableField(exist = false)
	private String work_end_address;
	
	@ApiModelProperty("付款方式")
	@TableField(exist = false)
	private String settlement_method;
	
	@ApiModelProperty("财务负责人编号")
	@TableField(exist = false)
	private String financialPersonCode;
	
	@ApiModelProperty("财务负责人姓名")
	@TableField(exist = false)
	private String financialPersonName;
	
	@ApiModelProperty("活动负责人审核结算单时间")
	private String statement_check_time;
	
	@ApiModelProperty("是否生成考勤表，1已生成null未生成")
	private String is_attendence;
	
	@ApiModelProperty("立项单号")
	@TableField(exist = false)
	private String requestId;
	
	public String getIs_attendence() {
		return is_attendence;
	}

	public void setIs_attendence(String is_attendence) {
		this.is_attendence = is_attendence;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getStatement_check_time() {
		return statement_check_time;
	}

	public void setStatement_check_time(String statement_check_time) {
		this.statement_check_time = statement_check_time;
	}

	public String getFinancial_check_time() {
		return financial_check_time;
	}

	public void setFinancial_check_time(String financial_check_time) {
		this.financial_check_time = financial_check_time;
	}

	public String getFinancialPersonCode() {
		return financialPersonCode;
	}

	public void setFinancialPersonCode(String financialPersonCode) {
		this.financialPersonCode = financialPersonCode;
	}

	public String getFinancialPersonName() {
		return financialPersonName;
	}

	public void setFinancialPersonName(String financialPersonName) {
		this.financialPersonName = financialPersonName;
	}

	public String getSettlement_method() {
		return settlement_method;
	}

	public void setSettlement_method(String settlement_method) {
		this.settlement_method = settlement_method;
	}

	public String getWork_start_address() {
		return work_start_address;
	}

	public void setWork_start_address(String work_start_address) {
		this.work_start_address = work_start_address;
	}

	public String getWork_end_address() {
		return work_end_address;
	}

	public void setWork_end_address(String work_end_address) {
		this.work_end_address = work_end_address;
	}

	public String getClock_city() {
		return clock_city;
	}

	public void setClock_city(String clock_city) {
		this.clock_city = clock_city;
	}

	public String getClock_address() {
		return clock_address;
	}

	public void setClock_address(String clock_address) {
		this.clock_address = clock_address;
	}

	public Integer getValid_work_minute() {
		return valid_work_minute;
	}

	public void setValid_work_minute(Integer valid_work_minute) {
		this.valid_work_minute = valid_work_minute;
	}

	public Integer getWork_minute() {
		return work_minute;
	}

	public void setWork_minute(Integer work_minute) {
		this.work_minute = work_minute;
	}

	public String getAdjust_hours_note() {
		return adjust_hours_note;
	}

	public void setAdjust_hours_note(String adjust_hours_note) {
		this.adjust_hours_note = adjust_hours_note;
	}

	public String getAdjust_money_note() {
		return adjust_money_note;
	}

	public void setAdjust_money_note(String adjust_money_note) {
		this.adjust_money_note = adjust_money_note;
	}

	public String getCheck_time() {
		return check_time;
	}

	public void setCheck_time(String check_time) {
		this.check_time = check_time;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Integer getPlanWorkHours() {
		return planWorkHours;
	}

	public void setPlanWorkHours(Integer planWorkHours) {
		this.planWorkHours = planWorkHours;
	}

	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getStart_clock_address() {
		return start_clock_address;
	}


	public void setStart_clock_address(String start_clock_address) {
		this.start_clock_address = start_clock_address;
	}


	public String getEnd_clock_address() {
		return end_clock_address;
	}


	public void setEnd_clock_address(String end_clock_address) {
		this.end_clock_address = end_clock_address;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Double getUnit_price() {
		return unit_price;
	}


	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getActivity_uuid() {
		return activity_uuid;
	}


	public void setActivity_uuid(String activity_uuid) {
		this.activity_uuid = activity_uuid;
	}


	public String getActivity_time() {
		return activity_time;
	}


	public void setActivity_time(String activity_time) {
		this.activity_time = activity_time;
	}


	public String getOpenid() {
		return openid;
	}


	public void setOpenid(String openid) {
		this.openid = openid;
	}


	public Integer getRequest_state() {
		return request_state;
	}


	public void setRequest_state(Integer request_state) {
		this.request_state = request_state;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public Integer getPay_state() {
		return pay_state;
	}


	public void setPay_state(Integer pay_state) {
		this.pay_state = pay_state;
	}


	public String getLoad_bill_uuid() {
		return load_bill_uuid;
	}


	public void setLoad_bill_uuid(String load_bill_uuid) {
		this.load_bill_uuid = load_bill_uuid;
	}


	public String getWork_start_time() {
		return work_start_time;
	}


	public void setWork_start_time(String work_start_time) {
		this.work_start_time = work_start_time;
	}


	public String getWork_start_imgurl() {
		return work_start_imgurl;
	}


	public void setWork_start_imgurl(String work_start_imgurl) {
		this.work_start_imgurl = work_start_imgurl;
	}


	public String getWork_end_time() {
		return work_end_time;
	}


	public void setWork_end_time(String work_end_time) {
		this.work_end_time = work_end_time;
	}


	public String getWork_end_imgurl() {
		return work_end_imgurl;
	}


	public void setWork_end_imgurl(String work_end_imgurl) {
		this.work_end_imgurl = work_end_imgurl;
	}


	public Integer getWork_hours() {
		return work_hours;
	}


	public void setWork_hours(Integer work_hours) {
		this.work_hours = work_hours;
	}


	public Integer getValid_work_hours() {
		return valid_work_hours;
	}


	public void setValid_work_hours(Integer valid_work_hours) {
		this.valid_work_hours = valid_work_hours;
	}


	public String getReduce_hours_reason() {
		return reduce_hours_reason;
	}


	public void setReduce_hours_reason(String reduce_hours_reason) {
		this.reduce_hours_reason = reduce_hours_reason;
	}


	public Integer getHours_state() {
		return hours_state;
	}


	public void setHours_state(Integer hours_state) {
		this.hours_state = hours_state;
	}


	public Double getReduce_money() {
		return reduce_money;
	}


	public void setReduce_money(Double reduce_money) {
		this.reduce_money = reduce_money;
	}


	public String getReduce_money_reason() {
		return reduce_money_reason;
	}


	public void setReduce_money_reason(String reduce_money_reason) {
		this.reduce_money_reason = reduce_money_reason;
	}


	public Double getIncentive_amount() {
		return incentive_amount;
	}


	public void setIncentive_amount(Double incentive_amount) {
		this.incentive_amount = incentive_amount;
	}


	public String getIncentive_reason() {
		return incentive_reason;
	}


	public void setIncentive_reason(String incentive_reason) {
		this.incentive_reason = incentive_reason;
	}


	public Double getReal_salary() {
		return real_salary;
	}


	public void setReal_salary(Double real_salary) {
		this.real_salary = real_salary;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public Boolean getIsIncentiveOrReduce() {
		return isIncentiveOrReduce;
	}


	public void setIsIncentiveOrReduce(Boolean isIncentiveOrReduce) {
		this.isIncentiveOrReduce = isIncentiveOrReduce;
	}

	public String getPaymentId(){
		return paymentId;
	}

	public void setPaymentId(String paymentId){
		this.paymentId = paymentId;
	}

	/**
	 * 主键值
	 */
	@Override
	protected Serializable pkVal() {
		return this.uuid;
	}
}
