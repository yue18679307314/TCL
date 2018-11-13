package com.kuyu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.security.PrivateKey;

/**
 * @Author jt_L
 * @Date 2017/9/21
 * @Description 活动详情展示数据VO
 */
@ApiModel("用户登陆信息")
public class LoginUserVo {


    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;
    
    @ApiModelProperty("验证码")
    private String validCode;
    
    @ApiModelProperty("1 微信绑定； 2 pc版登陆")
    private String type; //

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


   
}
