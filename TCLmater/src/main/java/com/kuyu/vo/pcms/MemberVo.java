package com.kuyu.vo.pcms;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by pc on 2018/12/12
 */
public class MemberVo {

    /**
     * code码
     */
    @JsonProperty("ERR_CODE")
    private String errcode;
    /**
     * 返回信息
     */
    @JsonProperty("ERR_MSG")
    private String errmsg;
    @JsonProperty("RESULT")
    private List<UserVo> result;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<UserVo> getResult() {
        return result;
    }

    public void setResult(List<UserVo> result) {
        this.result = result;
    }
}
