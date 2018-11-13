package com.kuyu.weixin;

import java.util.Date;

public class AccessToken {
	public static final String BINGDING = "1";
	public static final String UNBINGDING = "0";
	
	//tokenId
	private String id;
	
	//微信用户Id
	private String uuid;
	
	//公众账号ID
	private String other;
	
	//创建时间
	private Date createTm;
	
	//验证时间
	private Date validTm;
	
	//token类型[默认类型为:"9"-微信]
	private String type = "9";
	
	//是否绑定 : "1"-绑定 , "0"-未绑定
	private String binding;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getBinding() {
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

	public Date getCreateTm() {
		return createTm;
	}

	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}

	public Date getValidTm() {
		return validTm;
	}

	public void setValidTm(Date validTm) {
		this.validTm = validTm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
}
