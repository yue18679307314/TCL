package com.kuyu.weixin;

import java.io.Serializable;


public class ChannelReqMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5941077759326198030L;
	// 访问生成的token
	private AccessToken token;
	// 访问的语言版本
	private String language;

	// 公众微信号的uuid
	private String ToUserName;

	// 公众微信号的详细信息
	PublicIdInfo publicIdInfo;

	// 普通微信用户
	private String FromUserName;

	// 消息创建时间
	private String CreateTime;

	// 文本消息为text,图片消息为image,地理位置消息为location,链接消息为link,事件推送为event
	private String MsgType;

	// 文本消息内容
	private String Content;

	// 图片消息的图片链接
	private String PicUrl;

	// 多媒体文件的ID
	private String MediaId;

	// 事件类型
	private String Event;

	// 事件Key值
	private String EventKey;

	// 腾讯标记的信息ID
	private String MsgId;

	// 经度
	private String Longitude;
	// MsgType为location时生效
	private String Location_X;
	// 纬度
	private String Latitude;

	// MsgType为location时生效
	private String Location_Y;

	// 语音识别结果,UTF-8编码
	private String Recognition;

	// 二维码的ticket，可用来换取二维码图片
	private String Ticket;

	private String Precision;// 地理坐标精度
    private String Scale;//地址坐标精度
    private String Label;
    
    private ScanCodeInfo ScanCodeInfo; //二维码扫描的内容
    
    //链接消息
    private String Title;
    private String Description;
    private String Url;
    
    
    

	/**
	 * 渠道来源
	 */
	private String Source;

	public AccessToken getToken() {
		return token;
	}

	public void setToken(AccessToken token) {
		this.token = token;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public PublicIdInfo getPublicIdInfo() {
		return publicIdInfo;
	}

	public void setPublicIdInfo(PublicIdInfo publicIdInfo) {
		this.publicIdInfo = publicIdInfo;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
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

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}

	public String getPrecision() {
		return Precision;
	}

	public void setPrecision(String precision) {
		Precision = precision;
	}

	public String getSource() {
		return Source;
	}

	public void setSource(String source) {
		Source = source;
	}

	public String getScale() {
		return Scale;
	}

	public void setScale(String scale) {
		Scale = scale;
	}

	public ScanCodeInfo getScanCodeInfo() {
		return ScanCodeInfo;
	}

	public void setScanResult(ScanCodeInfo scanCodeInfo) {
		ScanCodeInfo = scanCodeInfo;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}


}
