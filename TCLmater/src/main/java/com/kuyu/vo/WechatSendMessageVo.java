package com.kuyu.vo;

public class WechatSendMessageVo {

	private String uuid;
	private String activity_uuid;
	private String city;
	private String activity_name;
	private String district;
	private String person_name;
	private String activity_time;
	private String openid;
	private Double real_salary;
	public Double getReal_salary() {
		return real_salary;
	}
	public void setReal_salary(Double real_salary) {
		this.real_salary = real_salary;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getActivity_name() {
		return activity_name;
	}
	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
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
	@Override
	public String toString() {
		return "WechatSendMessageVo [uuid=" + uuid + ", activity_uuid=" + activity_uuid + ", city=" + city
				+ ", activity_name=" + activity_name + ", district=" + district + ", person_name=" + person_name
				+ ", activity_time=" + activity_time + ", openid=" + openid + ", real_salary=" + real_salary + "]";
	}
	
}
