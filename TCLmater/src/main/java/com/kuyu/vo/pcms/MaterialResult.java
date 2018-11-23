package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

public class MaterialResult {
	@ApiModelProperty("物料名称")
	private String mrname;
	@ApiModelProperty("费用细类")
	private String cost;
	@ApiModelProperty("物料规格")
	private String specifications;
	@ApiModelProperty("数量")
	private Integer number;
	@ApiModelProperty("单位")
	private String unit;
	@ApiModelProperty("参考价")
	private Double comparisonPrice;
	@ApiModelProperty("门店信息")
	private Double price;
	@ApiModelProperty("备注")
	private String note;

	public String getMrname() {
		return mrname;
	}

	public void setMrname(String mrname) {
		this.mrname = mrname;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getComparisonPrice() {
		return comparisonPrice;
	}

	public void setComparisonPrice(Double comparisonPrice) {
		this.comparisonPrice = comparisonPrice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
}
