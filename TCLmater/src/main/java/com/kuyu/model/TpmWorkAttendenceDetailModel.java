package com.kuyu.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("打卡明细表")
@TableName("tpm_work_attendence_detail")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class TpmWorkAttendenceDetailModel implements Serializable{

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 4540063797221371366L;

	/* 自增id*/
	@TableId
	@ApiModelProperty("自增id")
	private int id;
	
	/*临促openid*/
	@ApiModelProperty("临促openid")
	private String openid;
	
	/*参加的活动*/
	@ApiModelProperty("参加的活动")
	private String activity_uuid;
	
	/*城市*/
	@ApiModelProperty("城市")
	private String clock_city;
	
	/*打卡地址*/
	@ApiModelProperty("打卡地址")
	private String clock_address;
	
	/*打卡时间*/
	@ApiModelProperty("打卡时间")
	private String clock_time;
	
	/*上传图片url*/
	@ApiModelProperty("上传图片url")
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getActivity_uuid() {
		return activity_uuid;
	}

	public void setActivity_uuid(String activity_uuid) {
		this.activity_uuid = activity_uuid;
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

	public String getClock_time() {
		return clock_time;
	}

	public void setClock_time(String clock_time) {
		this.clock_time = clock_time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "TpmWorkAttendenceDetail [id=" + id + ", openid=" + openid + ", activity_uuid=" + activity_uuid
				+ ", clock_city=" + clock_city + ", clock_address=" + clock_address + ", clock_time=" + clock_time
				+ ", url=" + url + "]";
	}
	
}
