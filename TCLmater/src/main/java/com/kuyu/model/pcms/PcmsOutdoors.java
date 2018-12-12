package com.kuyu.model.pcms;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PcmsOutdoors {
	
	@JsonProperty("DETAIL_ID")
    private String detailId;

    private String requestId;

	@JsonProperty("CHILDREN2_TASK")
    private String children2Task;

	@JsonProperty("CHILDREN2_LAST_BUILDTIME")
    private String children2LastBuildtime;

	@JsonProperty("CHILDREN2_AREA")
    private String children2Area;

	@JsonProperty("CHILDREN2_AREA_DETAIL")
    private String children2AreaDetail;

	@JsonProperty("CHILDREN2_BUILDTIME")
    private String children2Buildtime;

	@JsonProperty("CHILDREN2_VENDOR")
    private String children2Vendor;

	@JsonProperty("CHILDREN2_AMOUNT")
    private String children2Amount;

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId == null ? null : detailId.trim();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    public String getChildren2Task() {
        return children2Task;
    }

    public void setChildren2Task(String children2Task) {
        this.children2Task = children2Task == null ? null : children2Task.trim();
    }

    public String getChildren2LastBuildtime() {
        return children2LastBuildtime;
    }

    public void setChildren2LastBuildtime(String children2LastBuildtime) {
        this.children2LastBuildtime = children2LastBuildtime == null ? null : children2LastBuildtime.trim();
    }

    public String getChildren2Area() {
        return children2Area;
    }

    public void setChildren2Area(String children2Area) {
        this.children2Area = children2Area == null ? null : children2Area.trim();
    }

    public String getChildren2AreaDetail() {
        return children2AreaDetail;
    }

    public void setChildren2AreaDetail(String children2AreaDetail) {
        this.children2AreaDetail = children2AreaDetail == null ? null : children2AreaDetail.trim();
    }

    public String getChildren2Buildtime() {
        return children2Buildtime;
    }

    public void setChildren2Buildtime(String children2Buildtime) {
        this.children2Buildtime = children2Buildtime == null ? null : children2Buildtime.trim();
    }

    public String getChildren2Vendor() {
        return children2Vendor;
    }

    public void setChildren2Vendor(String children2Vendor) {
        this.children2Vendor = children2Vendor == null ? null : children2Vendor.trim();
    }

    public String getChildren2Amount() {
        return children2Amount;
    }

    public void setChildren2Amount(String children2Amount) {
        this.children2Amount = children2Amount == null ? null : children2Amount.trim();
    }
}