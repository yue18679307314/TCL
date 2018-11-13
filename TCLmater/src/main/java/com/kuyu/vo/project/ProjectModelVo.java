package com.kuyu.vo.project;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author jt_L
 * @Date 2017/10/23
 * @Description
 */
@ApiModel("立项单VO(复制)")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProjectModelVo{
    /**
     * 申请单ID
     */
    @NotBlank(message = "申请单ID不能为空")
    @Length(max = 32,message = "申请单ID长度最大为32")
    @JsonProperty("REQUEST_ID")
    @ApiModelProperty(value = "申请单ID",position = -50)
    private String requestId;
    /**
     * 申请标题
     */
    @NotBlank(message = "申请单标题不能为空")
    @Length(max = 255,message = "申请单标题长度最大为255")
    @JsonProperty("REQUEST_TITLE")
    @ApiModelProperty(value ="申请标题",position = -49)
    private String requestTitle;
    /**
     * 申请人
     */
    @NotBlank(message = "申请人不能为空")
    @Length(max = 30,message = "申请人长度最大为30")
    @JsonProperty("REQUEST_USER")
    @ApiModelProperty(value = "申请人",position = -48)
    private String requestUser;

    /**
     * 申请时间(YYYY-MM-DD)
     */
    @NotBlank(message = "申请时间不能为空")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",message = "申请时间格式有误(YYYY-MM-DD)")
    @JsonProperty("REQUEST_CREATE_TIME")
    @ApiModelProperty(value = "申请时间(YYYY-MM-DD)",position = -43)
    private String requestCreateTime;
    /**
     * 申请结束时间(YYYY-MM-DD)
     */
    @JsonProperty("REQUEST_END_TIME")
    @ApiModelProperty(value = "申请结束时间(YYYY-MM-DD)",position = -42)
    private String requestEndTime;
    /**
     * 申请人手机
     */
    @JsonProperty("REQUEST_TELPHONE")
    @ApiModelProperty(value = "申请人手机",position = -41)
    private String requestTelphone;
    /**
     * 申请人邮箱
     */

    @JsonProperty("REQUEST_EMAIL")
    @ApiModelProperty(value = "申请人邮箱",position = -40)
    private String requestEmail;
    /**
     * 申请人部门
     */
    @NotBlank(message = "申请人部门不能为空")
    @Length(max = 40,message = "申请人部门最大长度为40")
    @JsonProperty("REQUEST_DEPT")
    @ApiModelProperty(value = "申请人部门",position = -39)
    private String requestDept;

    /**
     * 申请事由
     */
    @Length(max = 255,message = "申请事由最大长度为255")
    @JsonProperty("REQUEST_INFO")
    @ApiModelProperty(value = "申请事由",position = -37)
    private String requestInfo;
    /**
     * 公司代码
     */
    @Length(max = 40,message = "公司代码最大长度为40")
    @JsonProperty("REQUEST_COMPANY_CODE")
    @ApiModelProperty(value = "公司代码",position = -36)
    private String requestCompanyCode;

    public String getRequestId(){
        return requestId;
    }

    public void setRequestId(String requestId){
        this.requestId = requestId;
    }

    public String getRequestTitle(){
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle){
        this.requestTitle = requestTitle;
    }

    public String getRequestUser(){
        return requestUser;
    }

    public void setRequestUser(String requestUser){
        this.requestUser = requestUser;
    }

    public String getRequestCreateTime(){
        return requestCreateTime;
    }

    public void setRequestCreateTime(String requestCreateTime){
        this.requestCreateTime = requestCreateTime;
    }

    public String getRequestEndTime(){
        return requestEndTime;
    }

    public void setRequestEndTime(String requestEndTime){
        this.requestEndTime = requestEndTime;
    }

    public String getRequestTelphone(){
        return requestTelphone;
    }

    public void setRequestTelphone(String requestTelphone){
        this.requestTelphone = requestTelphone;
    }

    public String getRequestEmail(){
        return requestEmail;
    }

    public void setRequestEmail(String requestEmail){
        this.requestEmail = requestEmail;
    }

    public String getRequestDept(){
        return requestDept;
    }

    public void setRequestDept(String requestDept){
        this.requestDept = requestDept;
    }

    public String getRequestInfo(){
        return requestInfo;
    }

    public void setRequestInfo(String requestInfo){
        this.requestInfo = requestInfo;
    }

    public String getRequestCompanyCode(){
        return requestCompanyCode;
    }

    public void setRequestCompanyCode(String requestCompanyCode){
        this.requestCompanyCode = requestCompanyCode;
    }
}
