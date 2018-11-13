package com.kuyu.weixin;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public abstract class BaseEntity implements Serializable {
	protected String id;
	protected Date createTm; // 创建时间
	protected Date modifiedTm;// 修改时间
	protected int version;// 版本号

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getModifiedTm() {
		return modifiedTm;
	}

	public void setModifiedTm(Date modifiedTm) {
		this.modifiedTm = modifiedTm;
	}

	public Date getCreateTm() {
		return createTm;
	}

	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
