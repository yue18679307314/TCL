package com.kuyu.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;


@TableName("tpm_properties_config")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PropertiesConfig extends Model<PropertiesConfig>{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7913074281876854561L;
	@TableId
    private Integer id;
    private String pkey;
    private String pvalue;
    private String note;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPkey() {
		return pkey;
	}
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	public String getPvalue() {
		return pvalue;
	}
	public void setPvalue(String pvalue) {
		this.pvalue = pvalue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	protected Serializable pkVal(){
		return this.id;
	}
}
