package com.kuyu.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author jt_L
 * @Date 2017/9/15
 * @Description 分页查询基类
 */
@ApiModel("分页查询参数")
//@JsonIgnoreProperties(value = {"current", "size"})
public class BasePageQuery {
    /**
     * 当前页的默认页：默认第1页
     */
    private final  Integer CURRENT_DEFAULT = 1;
    /**
     * 每页显示默认大小：默认10
     */
    private final  Integer SIZE_DEFAULT = 10;
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页，如果未设置当前页或者当前页小于1，默认设置为1",position = -100)
    private Integer current = CURRENT_DEFAULT;
    /**
     * 每页显示记录数
     */
    @ApiModelProperty(value = "每页显示大小，如果未设置每页显示大小或者每页显示大小小于10，默认设置为10",position = -99)
    private Integer size = SIZE_DEFAULT;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        if (current < CURRENT_DEFAULT) {
            current = CURRENT_DEFAULT;
        }
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        if (size < SIZE_DEFAULT) {
            size = SIZE_DEFAULT;
        }
        this.size = size;
    }
}
