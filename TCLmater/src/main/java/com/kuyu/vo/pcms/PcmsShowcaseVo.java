package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pc on 2018/11/22
 * 展台信息VO
 */
public class PcmsShowcaseVo {

    @ApiModelProperty("ID")
    private Integer scid;
    @ApiModelProperty("店型")
    private String children1_type;
    @ApiModelProperty("最近一次展台投建时间")
    private String children1_last_buildtime;
    @ApiModelProperty("面积（平米）")
    private String children1_area;
    @ApiModelProperty("延米（米）")
    private String children1_linear;
    @ApiModelProperty("展台投建时间")
    private String children1_buildtime;

    public Integer getScid() {
        return scid;
    }

    public void setScid(Integer scid) {
        this.scid = scid;
    }

    public String getChildren1_type() {
        return children1_type;
    }

    public void setChildren1_type(String children1_type) {
        this.children1_type = children1_type;
    }

    public String getChildren1_last_buildtime() {
        return children1_last_buildtime;
    }

    public void setChildren1_last_buildtime(String children1_last_buildtime) {
        this.children1_last_buildtime = children1_last_buildtime;
    }

    public String getChildren1_area() {
        return children1_area;
    }

    public void setChildren1_area(String children1_area) {
        this.children1_area = children1_area;
    }

    public String getChildren1_linear() {
        return children1_linear;
    }

    public void setChildren1_linear(String children1_linear) {
        this.children1_linear = children1_linear;
    }

    public String getChildren1_buildtime() {
        return children1_buildtime;
    }

    public void setChildren1_buildtime(String children1_buildtime) {
        this.children1_buildtime = children1_buildtime;
    }
}
