package com.kuyu.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.kuyu.util.DateUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * @Author tang_zhen
 * @Date 2017/9/26
 * @Description
 */
@ApiModel("日志操作的Model")
@TableName("tpm_logs")
public class TpmOptLogsModel extends Model<TpmOptLogsModel>{

    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("数据库表id（自增）")
    @TableField("id")
    private  int id;

    /**
     *日志操作的类型
     */
    @TableField("type")
    @ApiModelProperty("日志操作类型：1 修改银行信息；2 pc端考勤审核；3 移动端考勤申请；4 pc端结算审核；5 移动端结算审核；" +
            "6 修改立项单可使用人 7 修改活动负责人等")
    private  Integer type;

    /**
     * 日志操作的时间
     */
    @TableField("create_time")
    @ApiModelProperty("日志操作的时间")
    private String createTime = DateUtils.currentTime();

    /**
     * 操作用户
     */
    @TableField("opt_user")
    @ApiModelProperty("日志操作的用户")
    private  String optUser;

    /**
     * 操作的内容
     */
    @TableField("content")
    @ApiModelProperty("日志操作的内容")
    private String content;

    /**
     *日志操作的类型
     */
    @TableField("opt_user_dept")
    @ApiModelProperty("操作人的部门")
    private  String optUserDept;


    public String getOptUserDept() {
        return optUserDept;
    }

    public void setOptUserDept(String optUserDept) {
        this.optUserDept = optUserDept;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOptUser() {
        return optUser;
    }

    public void setOptUser(String optUser) {
        this.optUser = optUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    protected Serializable pkVal(){
        return id;
    }
}
