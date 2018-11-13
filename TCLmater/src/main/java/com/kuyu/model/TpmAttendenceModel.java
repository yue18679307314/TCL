package com.kuyu.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kuyu.util.DateUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("导出考勤表")
@TableName("tpm_attendence")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TpmAttendenceModel {
	
	@TableId
	@ApiModelProperty("主键")
	private String uuid;
	@ApiModelProperty("生成人员编号")
	private String person_code;
	@ApiModelProperty("生成人员姓名")
	private String person_name;
	@ApiModelProperty("创建时间")
	private String create_time = DateUtils.currentTime();
	@ApiModelProperty("考勤表单号")
	private String attendence_uuid;
	@ApiModelProperty("结算单单号")
	private String tus_uuid;
	@ApiModelProperty("生成的文件路径")
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAttendence_uuid() {
		return attendence_uuid;
	}
	public void setAttendence_uuid(String attendence_uuid) {
		this.attendence_uuid = attendence_uuid;
	}
	public String getTus_uuid() {
		return tus_uuid;
	}
	public void setTus_uuid(String tus_uuid) {
		this.tus_uuid = tus_uuid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getPerson_code() {
		return person_code;
	}
	public void setPerson_code(String person_code) {
		this.person_code = person_code;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	
}
