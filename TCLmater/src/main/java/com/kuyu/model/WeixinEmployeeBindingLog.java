package com.kuyu.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kuyu.util.DateUtils;


@TableName("tpm_weixin_employee_binding_log")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WeixinEmployeeBindingLog extends Model<WeixinEmployeeBindingLog> implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7913074281876854561L;
	@TableId(type=IdType.AUTO)
    private Integer id;
	@TableField("person_code")
    private String person_code;
	@TableField("openid")
    private String openid;
	@TableField("operate_type")
	private String operate_type;
	@TableField("create_time")
    private String create_time = DateUtils.getLongDateStr();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPerson_code() {
		return person_code;
	}
	public void setPerson_code(String person_code) {
		this.person_code = person_code;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getOperate_type() {
		return operate_type;
	}
	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}
	@Override
	protected Serializable pkVal() {
		return null;
	}
    
    
}
