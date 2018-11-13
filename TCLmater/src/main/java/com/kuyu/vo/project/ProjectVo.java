package com.kuyu.vo.project;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.kuyu.model.TpmProjectModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author jt_L
 * @Date 2017/9/20
 * @Description
 */
@ApiModel("导入立项单详情Model")
public class ProjectVo {

    @ApiModelProperty(value = "立项单详情Model")
    private ProjectDetialModelVo project;

    public ProjectDetialModelVo getProject() {
        return project;
    }

    public void setProject(ProjectDetialModelVo project) {
        this.project = project;
    }
}
