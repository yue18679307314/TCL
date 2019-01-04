package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pc on 2019/1/4
 */
public class ReplyMessageVo {
    @ApiModelProperty("价格")
    private String price;
    @ApiModelProperty("内容")
    private String context;
    @ApiModelProperty("id")
    private Integer statisticsId;
    @ApiModelProperty("类型1-无异议 2-有异议")
    private Integer type;
    @ApiModelProperty("对账ID")
    private Integer pcms_reconciliation_id;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getStatisticsId() {
        return statisticsId;
    }

    public void setStatisticsId(Integer statisticsId) {
        this.statisticsId = statisticsId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPcms_reconciliation_id() {
        return pcms_reconciliation_id;
    }

    public void setPcms_reconciliation_id(Integer pcms_reconciliation_id) {
        this.pcms_reconciliation_id = pcms_reconciliation_id;
    }
}
