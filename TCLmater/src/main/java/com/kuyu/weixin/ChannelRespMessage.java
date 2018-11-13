package com.kuyu.weixin;


import java.io.Serializable;
import java.util.List;

import com.kuyu.common.CommonConstants;

public class ChannelRespMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5451619174900647516L;

	/**
	 * 消息发送方， 顺丰公众平台账号
	 */
	private String FromUserName;

	/**
	 * 微信普通用户微信号
	 */
	private String ToUserName;

	/**
	 * 消息创建时间
	 */
	private String CreateTime;

	/**
	 * 消息类型，文本消息必须填写text
	 */
	private String MsgType;

	/**
	 * 消息内容，大小限制在2048字节，字段为空为不合法请求
	 */
	private String Content;

	/**
	 * 对消息进行星标，可在实时消息的星标消息分类中找 到该消息
	 */
	private String FuncFlag;
	
	private String PicUrl;
	
	private String MediaId;
	
	private String title; //视频消息的标题
	
	private String description;//视频消息的描述
	
	private String Format;
	
	/**
	 * 图文消息条数
	 */
	private int ArticleCount;
	
	private List<MessageItem> Articles;

	
	public ChannelRespMessage(String fromUserName, String toUserName,
			String createTime,String msgType, String typeContent, String funcFlag) {
		this.FromUserName = fromUserName;
		this.ToUserName = toUserName;
		this.CreateTime = createTime;
		this.MsgType = msgType;
		//文本信息
		if(CommonConstants.MSGTYPE_TEXT.equals(MsgType)){
			this.Content = typeContent;
		}else if(CommonConstants.MSGTYPE_IMAGE.equals(MsgType)|| //图片信息
				CommonConstants.MSGTYPE_VIDEO.equals(MsgType) ||
				CommonConstants.MSGTYPE_VOICE.equals(MsgType)){	//语音、视频信息
			this.MediaId = typeContent;
		}
		this.FuncFlag = funcFlag;
	}

	public ChannelRespMessage(String fromUserName, String toUserName,
			String createTime, List<MessageItem> articles, String funcFlag) {
		this.FromUserName = fromUserName;
		this.ToUserName = toUserName;
		this.CreateTime = createTime;
		this.MsgType = CommonConstants.MSGTYPE_NEWS;
		this.Articles = articles;
		if(articles != null){
			this.ArticleCount = articles.size();
		}
		this.FuncFlag = funcFlag;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getFuncFlag() {
		return FuncFlag;
	}

	public void setFuncFlag(String funcFlag) {
		FuncFlag = funcFlag;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<MessageItem> getArticles() {
		return Articles;
	}

	public void setArticles(List<MessageItem> articles) {
		if(articles != null){
			this.ArticleCount = articles.size();
		}
		Articles = articles;
	}
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
