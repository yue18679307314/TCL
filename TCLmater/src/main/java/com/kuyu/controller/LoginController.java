package com.kuyu.controller;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kuyu.annotation.AOP_Controller_LOG;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kuyu.common.CommonConstants;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.model.WeixinEmployeeBinding;
import com.kuyu.service.PropertiesConfigService;
import com.kuyu.service.TpmEmployeeService;
import com.kuyu.service.WeixinUserInfoService;
import com.kuyu.util.DateUtils;
import com.kuyu.vo.LoginUserVo;
import com.kuyu.vo.ResultVo;
import com.tcl.idm.AuthApi;


/**
 * @author zkw
 * @since 2017-09-20 11:10
 */
@AOP_Controller_LOG
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private TpmEmployeeService employeeService;
	
	// 
	@RequestMapping(value = "/userLoginpc", method ={RequestMethod.POST, RequestMethod.GET})
	@CrossOrigin
	public Object userLoginpc(@RequestBody LoginUserVo  vo, 
			HttpServletResponse response,HttpServletRequest request) {
		if (StringUtils.isBlank(vo.getUsername()) || StringUtils.isBlank(vo.getPassword())) {
			return ResultVo.get(ResultVo.USERNAME_OR_PASSWORD_ISNULL);
		}
		
		ResultVo rs = ResultVo.get(ResultVo.SUCCESS);
		//密码解密
				String loginName = vo.getUsername();
				String loginPwd = vo.getPassword();
				if (loginName.length() > 96 && loginPwd.length() > 96) {
					try {
						loginName = new String(Base64.getDecoder().decode(loginName));
						loginPwd = new String(Base64.getDecoder().decode(loginPwd));
						
						loginName = loginName.substring(48, loginName.length() - 48);
						loginPwd = loginPwd.substring(48, loginPwd.length() - 48);
						
						loginName = new String(Base64.getDecoder().decode(loginName));
						loginPwd = new String(Base64.getDecoder().decode(loginPwd));
						
						vo.setUsername(loginName);
						vo.setPassword(loginPwd);
						TpmEmployeeModel employeeModel = employeeService.selectById(vo.getUsername());
						if(employeeModel!=null)
						{
							String result = AuthApi.authUserPwdGetUserId(response, loginName, loginPwd);
							if (result.equals("SUCCESS")) {
								System.out.println("login name:"+vo.getUsername()+",pwd:"+ vo.getPassword());
								LoginUserInfo loginUserInfo = new LoginUserInfo();
								loginUserInfo.setUserType(CommonConstants.LOGIN_USER_TYPE_EMPLOYEE);
								loginUserInfo.setEmployeeModel(employeeModel);
								
								this.setValue(CommonConstants.LOGIN_USER_INFO, loginUserInfo);
								return ResultVo.get(ResultVo.SUCCESS);
							}else{
								rs = ResultVo.get(ResultVo.USERNAME_OR_PASSWORD_ERROR);
							}
						}else{
							rs = ResultVo.get(ResultVo.USERNAME_OR_PASSWORD_ERROR);
						}
					} catch(Exception e){
						rs = ResultVo.get(ResultVo.FAIL);
					}
				}
		
		return  rs;
	}
	
	
}
