package com.kuyu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kuyu.common.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author tang_zhen
 * @Date 2017/9/27
 * @Description
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel("日志操作参数VO")
public class OperateLogsVo extends BasePageQuery{
    /**
     * 操作日志类型
     */
    @ApiModelProperty("操作日志类型")
    private  Integer type;
    /**
     * 查询的日志开始时间
     */
    @ApiModelProperty("查询的日志开始时间")
    private String startTime;
    /**
     * 查询的日志结束时间
     */
    @ApiModelProperty("查询的日志结束时间")
    private  String endTime;

    /**
     * 查询日志内容
     */
    @ApiModelProperty("查询的日志内容")
    private  String content;

    /**
     * 下载当前页还是下载所有
     */
    @ApiModelProperty("下载当前页还是下载所有数据")
    private  String downloadFlag;

    public String getDownloadFlag() {
        return downloadFlag;
    }

    public void setDownloadFlag(String downloadFlag) {
        this.downloadFlag = downloadFlag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
