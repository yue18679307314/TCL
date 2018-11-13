package com.kuyu.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 *
 *
 * @Author jt_L
 * @Date 2017-09-21
 * @Description 实体类:临促参加活动
 */
@ApiModel("临促参加活动")
@TableName("tpm_user_activity")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TpmUserActivityModel extends Model<TpmUserActivityModel> {

    /**
     * 临促参加编号
     */
	@ApiModelProperty("临促参加编号")
	@TableId("uuid")
	private String uuid;
    /**
     * 活动id
     */
	@ApiModelProperty("活动id")
	@TableField("activity_uuid")
	private String activityUuid;
    /**
     * 临促openid
     */
	@ApiModelProperty("临促openid")
	private String openid;
    /**
     * 申请时间(YYYY-MM-DD HH(24)-mm-ss)
     */
	@ApiModelProperty("申请时间(YYYY-MM-DD HH(24)-mm-ss)")
	@TableField("request_time")
	private String requestTime;
	/**
	 * 申请的活动时间
	 */
	@ApiModelProperty("申请哪一天的活动(YYYY-MM-DD)")
	@TableField("request_activity_day")
	private String requestActivityDay;
    /**
     * 参加的状态(0 现场负责人已确认拒绝参加；1 申请中； 2 现场负责人已确认可以参加； 4 已参加)
     */
	@ApiModelProperty("参加的状态,默认为1(0 现场负责人已确认拒绝参加；1 申请中； 2 现场负责人已确认可以参加； 3 已参加 4 已结束)")
	@TableField("request_state")
	private Integer requestState=1;

	@ApiModelProperty("审核原因")
	private String reason;

	@TableField("audit_time")
	@ApiModelProperty("审核时间")
	private String auditTime;

	@TableField("reject_flag")
	@ApiModelProperty("剔除标记，为1表示被剔除")
	private Integer rejectFlag=0;


	public Integer getRejectFlag() {
		return rejectFlag;
	}

	public void setRejectFlag(Integer rejectFlag) {
		this.rejectFlag = rejectFlag;
	}

	public String getRequestActivityDay() {
		return requestActivityDay;
	}

	public void setRequestActivityDay(String requestActivityDay) {
		this.requestActivityDay = requestActivityDay;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getActivityUuid() {
		return activityUuid;
	}

	public void setActivityUuid(String activityUuid) {
		this.activityUuid = activityUuid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public Integer getRequestState() {
		return requestState;
	}

	public void setRequestState(Integer requestState) {
		this.requestState = requestState;
	}

	public String getReason(){
		return reason;
	}

	public void setReason(String reason){
		this.reason = reason;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	@Override
	protected Serializable pkVal() {
		return null;
	}
}
