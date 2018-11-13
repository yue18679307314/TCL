package com.kuyu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author jt_L
 * @Date 2017/9/22 0022
 * @Description
 */
@ApiModel("收益活动明细列表VO")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StatementActivityDetailVo {

    /**
     * 活动名称
     */
    @ApiModelProperty(value = "活动名称",position = 1)
    private String projectName;

    /**
     * 总实际报酬
     */
    @ApiModelProperty(value = "临促所有活动总收益",position = 2)
    private double totalRealSalary;


    /**
     * 每天活动明细
     */
    @ApiModelProperty(value = "活动明细列表",position = 3)
    private List<StatementDetailVo> statementVoList;

    public List<StatementDetailVo> getStatementVoList() {
        return statementVoList;
    }

    public void setStatementVoList(List<StatementDetailVo> statementVoList) {
        this.statementVoList = statementVoList;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public double getTotalRealSalary() {
        return totalRealSalary;
    }

    public void setTotalRealSalary(double totalRealSalary) {
        this.totalRealSalary = totalRealSalary;
    }
}
