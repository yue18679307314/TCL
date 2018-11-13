package com.kuyu.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author jt_L
 * @Date 2017/9/26
 * @Description
 */
@ApiModel("临促人员参数活动Vo")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AcvitityUserVo {

    @ApiModelProperty(value = "临促openid",position = 1)
    private String openid;

    @ApiModelProperty(value = "姓名",position = 2)
    private String userName;

    @ApiModelProperty(value = "性别",position = 3)
    private String sex;

    @ApiModelProperty(value = "微信性别(2女 1男 0未设置)",position = 3)
    private String wxSex="";

    @ApiModelProperty(value = "工作天数",position = 4)
    private Integer workDays;

    @ApiModelProperty(value = "头像url",position = 5)
    private String headImgUrl;

    @ApiModelProperty(value = "手机号码",position = 6)
    private String mobile;

    @ApiModelProperty(value = "身份证号码",position = 7)
    private String id_card;

    @ApiModelProperty(value = "身份证地址",position = 8)
    private String address;

    @ApiModelProperty(value = "身份证正面url",position = 9)
    private String idimgurl_front;

    @ApiModelProperty(value = "身份证背面url",position = 10)
    private String idimgurl_back;

    @ApiModelProperty(value = "开户名",position = 11)
    private String account_name;

    @ApiModelProperty(value = "开户银行省份",position = 12)
    private String open_province;

    @ApiModelProperty(value = "开户银行城市",position = 13)
    private String open_city;

    @ApiModelProperty(value = "开户银行",position = 14)
    private String account_bank_name;

    @ApiModelProperty(value = "开户银行网点",position = 15)
    private String open_branch;

    @ApiModelProperty(value = "开户银行账号",position = 16)
    private String account_value;

    @ApiModelProperty(value = "年龄,0代表身份证错误",position = 17)
    private Integer age=0;

    @ApiModelProperty(value = "临促审核状态:(0 现场负责人已确认拒绝参加；1 申请中； 2 现场负责人已确认可以参加；)",position = 18)
    private Integer requestState;

    @ApiModelProperty(value = "临促申请参加哪一天的活动)",position = 18)
    private String requestActivityDay;

    @ApiModelProperty(value = "临促审核备注",position = 19)
    private String requestReason;

    @ApiModelProperty(value = "临促的申请是否过期,0正常，1已过期",position = 20)
    private String outOfDateOrNot;

    public String getOutOfDateOrNot() {
        return outOfDateOrNot;
    }

    public void setOutOfDateOrNot(String outOfDateOrNot) {
        this.outOfDateOrNot = outOfDateOrNot;
    }

    public String getRequestActivityDay() {
        return requestActivityDay;
    }

    public void setRequestActivityDay(String requestActivityDay) {
        this.requestActivityDay = requestActivityDay;
    }

    public String getWxSex() {
        return wxSex;
    }

    public void setWxSex(String wxSex) {
        this.wxSex = wxSex;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getWorkDays() {
        return workDays;
    }

    public void setWorkDays(Integer workDays) {
        this.workDays = workDays;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdimgurl_front() {
        return idimgurl_front;
    }

    public void setIdimgurl_front(String idimgurl_front) {
        this.idimgurl_front = idimgurl_front;
    }

    public String getIdimgurl_back() {
        return idimgurl_back;
    }

    public void setIdimgurl_back(String idimgurl_back) {
        this.idimgurl_back = idimgurl_back;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getOpen_province(){
        return open_province;
    }

    public void setOpen_province(String open_province){
        this.open_province = open_province;
    }

    public String getOpen_city(){
        return open_city;
    }

    public void setOpen_city(String open_city){
        this.open_city = open_city;
    }

    public String getAccount_bank_name(){
        return account_bank_name;
    }

    public void setAccount_bank_name(String account_bank_name){
        this.account_bank_name = account_bank_name;
    }



    public String getOpen_branch(){
        return open_branch;
    }

    public void setOpen_branch(String open_branch){
        this.open_branch = open_branch;
    }

    public String getAccount_value() {
        return account_value;
    }

    public void setAccount_value(String account_value) {
        this.account_value = account_value;
    }

    public Integer getAge(){
        return age;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public Integer getRequestState(){
        return requestState;
    }

    public void setRequestState(Integer requestState){
        this.requestState = requestState;
    }

    public String getRequestReason(){
        return requestReason;
    }

    public void setRequestReason(String requestReason){
        this.requestReason = requestReason;
    }
}
