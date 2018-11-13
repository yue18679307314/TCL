package com.kuyu.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
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
 * @Date 2017-09-26
 * @Description 实体类:关注用户微信信息表
 */
@ApiModel("关注用户微信信息表")
@TableName("tpm_user_wx_info")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TpmUserWxInfoModel extends Model<TpmUserWxInfoModel> {

    /**
     * 主键
     */
	@TableId
	@ApiModelProperty("主键")
	private String openid;
    /**
     * 性别
     */
	@ApiModelProperty("性别")
	private String sex;
    /**
     * 昵称
     */
	@ApiModelProperty("昵称")
	private String nikeName;
    /**
     * 头像url
     */
	@ApiModelProperty("头像url")
	private String headImgUrl;
    /**
     * 所在城市
     */
	@ApiModelProperty("所在城市")
	private String city;
    /**
     * 所在省
     */
	@ApiModelProperty("所在省")
	private String province;
    /**
     * 所在国家
     */
	@ApiModelProperty("所在国家")
	private String country;
    /**
     * 关注时间
     */
	@ApiModelProperty("关注时间")
	private String subscribeTime;
	private String unionid;
	private String remark;
	private String groupid;
	private String tagidList;
    /**
     * 创建时间
     */
	@ApiModelProperty("创建时间")
	private String createTime;


	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNikeName() {
		return nikeName;
	}

	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getTagidList() {
		return tagidList;
	}

	public void setTagidList(String tagidList) {
		this.tagidList = tagidList;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.openid;
	}

}
