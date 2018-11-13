package com.kuyu.vo;
/**
 * JSSDK ticket
 * 
 *   
 */
public class JSSDKTicket {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4786669798387190512L;
	/**
	 * 公众号
	 */
	private String uuId;
	/**
	 * ticket
	 */
	private String ticket;
	/**
	 * 描述
	 */
	private String remark;
	/**
	 * 描述
	 */
	private String appId;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUuId() {
		return uuId;
	}
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	
	
	
}
