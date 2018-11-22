package com.kuyu.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by pc on 2018/11/21
 */
@ApiModel("PsmsCompanyVo(公司模型)")
public class PsmsCompanyVo implements Serializable {

    @TableField(exist=false)
    private static final long serialVersionUID = -9085664322127784665L;

    /**公司代码*/
    @ApiModelProperty("公司代码")
    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
