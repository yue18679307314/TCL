package com.kuyu.vo.pcms;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class ActivityOriginalVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private String projectId;
	
	private String requestId;
	
	private String vendorId;
	
	private String vendorName;
	
	private String projectName;
	
	private String feeDetailType;
	
	private String dept;

	private String deptName;
	
	private String city;
	
	private String costCenter;
	
	private String wbs;
	
	private String orderNo;
   
	private Double totalFee;
  
	private Integer projectedSalesNo;
   
	private Double projectedSalesMoney;
    
	private Double estimatedCostRate;
   
	private String note;

	private List<MaterialResult> maList;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getFeeDetailType() {
		return feeDetailType;
	}

	public void setFeeDetailType(String feeDetailType) {
		this.feeDetailType = feeDetailType;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getWbs() {
		return wbs;
	}

	public void setWbs(String wbs) {
		this.wbs = wbs;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public Integer getProjectedSalesNo() {
		return projectedSalesNo;
	}

	public void setProjectedSalesNo(Integer projectedSalesNo) {
		this.projectedSalesNo = projectedSalesNo;
	}

	public Double getProjectedSalesMoney() {
		return projectedSalesMoney;
	}

	public void setProjectedSalesMoney(Double projectedSalesMoney) {
		this.projectedSalesMoney = projectedSalesMoney;
	}

	public Double getEstimatedCostRate() {
		return estimatedCostRate;
	}

	public void setEstimatedCostRate(Double estimatedCostRate) {
		this.estimatedCostRate = estimatedCostRate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public List<MaterialResult> getMaList() {
		return maList;
	}

	public void setMaList(List<MaterialResult> maList) {
		this.maList = maList;
	}
	
	
}
