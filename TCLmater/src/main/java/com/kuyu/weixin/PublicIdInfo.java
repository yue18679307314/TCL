package com.kuyu.weixin;

import java.io.Serializable;
import java.util.Date;

/**
 * 公众账号信息
 * 
 * @author 625586
 * 
 */
public class PublicIdInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6728686942080689079L;

	// 微信公众账号uuid
	private String uuid;

	// 微信公众账号昵称
	private String nickName;

	// token
	private String token;

	// accesstoken
	private String accesstoken;

	// appid
	private String appid;

	// appsecret
	private String appsecret;

	// 英文:EN 中文简体:CN 中文繁体:TW
	private String langCode;

	// 备注
	private String remark;

	// 创建时间
	private Date createDate;

	// 修改时间
	private Date updateDate;
	
	private String language;
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getAccesstoken() {
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
}
