package com.kuyu.model.pcms;

public class PcmsProjectDeatil {
	
	
    private String requestId;

    private String detailId;

    private String detailMemo;

    private String detailAccount;

    private String detailSubject;

    private String detailCost;

    private String detailWbs;

    private String detailChannel;

    private String detailTask;

    private String detailAmount;

    private String detailStandard;

    private String detailVendor;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId == null ? null : detailId.trim();
    }

    public String getDetailMemo() {
        return detailMemo;
    }

    public void setDetailMemo(String detailMemo) {
        this.detailMemo = detailMemo == null ? null : detailMemo.trim();
    }

    public String getDetailAccount() {
        return detailAccount;
    }

    public void setDetailAccount(String detailAccount) {
        this.detailAccount = detailAccount == null ? null : detailAccount.trim();
    }

    public String getDetailSubject() {
        return detailSubject;
    }

    public void setDetailSubject(String detailSubject) {
        this.detailSubject = detailSubject == null ? null : detailSubject.trim();
    }

    public String getDetailCost() {
        return detailCost;
    }

    public void setDetailCost(String detailCost) {
        this.detailCost = detailCost == null ? null : detailCost.trim();
    }

    public String getDetailWbs() {
        return detailWbs;
    }

    public void setDetailWbs(String detailWbs) {
        this.detailWbs = detailWbs == null ? null : detailWbs.trim();
    }

    public String getDetailChannel() {
        return detailChannel;
    }

    public void setDetailChannel(String detailChannel) {
        this.detailChannel = detailChannel == null ? null : detailChannel.trim();
    }

    public String getDetailTask() {
        return detailTask;
    }

    public void setDetailTask(String detailTask) {
        this.detailTask = detailTask == null ? null : detailTask.trim();
    }

    public String getDetailAmount() {
        return detailAmount;
    }

    public void setDetailAmount(String detailAmount) {
        this.detailAmount = detailAmount == null ? null : detailAmount.trim();
    }

    public String getDetailStandard() {
        return detailStandard;
    }

    public void setDetailStandard(String detailStandard) {
        this.detailStandard = detailStandard == null ? null : detailStandard.trim();
    }

    public String getDetailVendor() {
        return detailVendor;
    }

    public void setDetailVendor(String detailVendor) {
        this.detailVendor = detailVendor == null ? null : detailVendor.trim();
    }
}