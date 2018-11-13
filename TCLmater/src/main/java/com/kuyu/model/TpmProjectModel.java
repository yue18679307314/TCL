package com.kuyu.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 *
 *
 * @Author jt_L
 * @Date 2017-09-19
 * @Description 实体类:立项单
 */
@ApiModel("立项单")
@TableName("tpm_project")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TpmProjectModel  extends Model<TpmProjectModel> {

    /**
     * 申请单ID
     */
    @TableId("request_id")
	@ApiModelProperty(value = "申请单ID",position = -50)
	private String requestId;
    /**
     * 申请标题
     */
	@TableField("request_title")
	@ApiModelProperty(value ="申请标题",position = -49)
	private String requestTitle;
    /**
	 * 申请人
	 */
	@TableField("request_user")
	@ApiModelProperty(value = "申请人",position = -48)
	private String requestUser;

	/**
	 * 申请人名称
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "申请人(名称)",position = -48)
	private String requestUserName;
	/**
	 * 申请时间(YYYY-MM-DD)
	 */
	@TableField("request_create_time")
	@ApiModelProperty(value = "申请时间(YYYY-MM-DD)",position = -43)
	private String requestCreateTime;
	/**
	 * 申请结束时间(YYYY-MM-DD)
	 */
	@TableField("request_end_time")
	@ApiModelProperty(value = "申请结束时间(YYYY-MM-DD)",position = -42)
	private String requestEndTime;
	/**
	 * 申请人手机
	 */
	@TableField("request_telphone")
	@ApiModelProperty(value = "申请人手机",position = -41)
	private String requestTelphone;
	/**
	 * 申请人邮箱
	 */
	@TableField("request_email")
	@ApiModelProperty(value = "申请人邮箱",position = -40)
	private String requestEmail;
	/**
	 * 申请人部门
	 */
	@TableField("request_dept")
	@ApiModelProperty(value = "申请人部门",position = -39)
	private String requestDept;

	@TableField(exist = false)
	@ApiModelProperty(value = "申请人部门名称",position = -38)
	private String requestDeptName;
	/**
	 * 申请事由
	 */
	@TableField("request_info")
	@ApiModelProperty(value = "申请事由",position = -37)
	private String requestInfo;
	/**
	 * 公司代码
	 */
	@TableField("request_company_code")
	@ApiModelProperty(value = "公司代码",position = -36)
	private String requestCompanyCode;

	/**
	 * 导入的json字符串
	 */
	@JsonIgnore
	@TableField("request_json")
	private String requestJson;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getRequestTitle() {
		return requestTitle;
	}

	public void setRequestTitle(String requestTitle) {
		this.requestTitle = requestTitle;
	}

	public String getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(String requestUser) {
		this.requestUser = requestUser;
	}

	public String getRequestCreateTime() {
		return requestCreateTime;
	}

	public void setRequestCreateTime(String requestCreateTime) {
		this.requestCreateTime = requestCreateTime;
	}

	public String getRequestEndTime() {
		return requestEndTime;
	}

	public void setRequestEndTime(String requestEndTime) {
		this.requestEndTime = requestEndTime;
	}

	public String getRequestTelphone(){
		return requestTelphone;
	}

	public void setRequestTelphone(String requestTelphone){
		this.requestTelphone = requestTelphone;
	}

	public String getRequestEmail(){
		return requestEmail;
	}

	public void setRequestEmail(String requestEmail){
		this.requestEmail = requestEmail;
	}

	public String getRequestDept(){
		return requestDept;
	}

	public void setRequestDept(String requestDept){
		this.requestDept = requestDept;
	}

	public String getRequestCompanyCode(){
		return requestCompanyCode;
	}

	public void setRequestCompanyCode(String requestCompanyCode){
		this.requestCompanyCode = requestCompanyCode;
	}

	public String getRequestUserName(){
		return requestUserName;
	}

	public void setRequestUserName(String requestUserName){
		this.requestUserName = requestUserName;
	}

	public String getRequestDeptName(){
		return requestDeptName;
	}

	public void setRequestDeptName(String requestDeptName){
		this.requestDeptName = requestDeptName;
	}

	public String getRequestInfo(){
		return requestInfo;
	}

	public void setRequestInfo(String requestInfo){
		this.requestInfo = requestInfo;
	}

	public String getRequestJson() {
		return requestJson;
	}

	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}

	/**
	 * 主键值
	 */
	@Override
	protected Serializable pkVal() {
		return this.requestId;
	}
}
