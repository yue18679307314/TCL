package com.kuyu.vo.pcms;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by pc on 2018/12/12
 */
public class Transfer {

    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("待验收物料ID")
    private Integer pending_id;
    @ApiModelProperty("员工编码")
    private String person_code;
    @ApiModelProperty("员工姓名")
    private String person_name;
    @ApiModelProperty("创建时间")
    private Date create_time;
    @ApiModelProperty("任务说明")
    private String context;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPending_id() {
        return pending_id;
    }

    public void setPending_id(Integer pending_id) {
        this.pending_id = pending_id;
    }

    public String getPerson_code() {
        return person_code;
    }

    public void setPerson_code(String person_code) {
        this.person_code = person_code;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
