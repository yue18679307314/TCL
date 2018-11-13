package com.kuyu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.kuyu.common.CommonConstants;
import com.kuyu.exception.ParamException;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmBranchAdminModel;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.model.UserRoleInfoModel;
import com.kuyu.model.WeixinUserInfo;
import com.kuyu.service.TpmBranchAdminService;
import com.kuyu.service.UserRoleInfoService;
import com.kuyu.util.ResultVoUtils;
import com.kuyu.vo.ResultVo;

public class BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(BaseController.class);
	//pc端登陆用户列表
	public static List<String> loginUserList = new ArrayList<>();
	
	public static Map<String, Integer> userLoginIndexMap = new HashMap<String, Integer>();
	
	@Autowired
	private UserRoleInfoService userRoleInfoService;
	
	@Autowired
	private TpmBranchAdminService tpmBranchAdminService;
	
	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public HttpSession getSessionWithGet() {
		return getRequest().getSession(false);
	}

	public HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttrs = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
		HttpServletRequest request = requestAttrs.getRequest();
		return request;
	}
	
	
	public String getJsessionID(){
		HttpServletRequest request = this.getRequest();
		Cookie[] cookies = request.getCookies();
		String jsessionid="";
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if(cookie.getName().equals("JSESSIONID")){
				jsessionid = cookie.getValue();
			}
		}
		return jsessionid;
	}
	// 获取用户信息
	public LoginUserInfo getLoginUserInfo() {
		HttpSession session = getSessionWithGet();
		if (null == session){
			return null;
		}
		return (LoginUserInfo) session.getAttribute(CommonConstants.LOGIN_USER_INFO);
	}
	
	// 获取用户信息
	public String getOpenid() {
		LoginUserInfo user = getLoginUserInfo();
		if( null != user && user.getWeixinUserInfo()!=null){
			return user.getWeixinUserInfo().getOpenId();
		}
		return "";
	}
	
	// 获取用户角色
	public String getUserRole() {
		LoginUserInfo user = getLoginUserInfo();
		if( null != user){
			return user.getUserRole();
		}
		return "";
	}
	
	// 判断是否微信登陆
	public boolean isWeiXinLogin() {
		LoginUserInfo user = getLoginUserInfo();
		if( null != user&& user.getWeixinUserInfo()!=null){
			return true;
		}
		return false;
	}
	
	// 获取用户信息
	public String getPersonCode() {
		LoginUserInfo user = getLoginUserInfo();
		if(null != user && user.getEmployeeModel()!=null){
			user.setUserType(CommonConstants.LOGIN_USER_TYPE_EMPLOYEE);
			return user.getEmployeeModel().getPerson_code();
		}
		return null;
	}
	
	// 设置
	public void setValue(String propKey, Object object) {
		HttpSession httpSession = getSession();
		if(object instanceof LoginUserInfo){
			LoginUserInfo loginUserInfo = (LoginUserInfo)object;
			if(loginUserInfo.getEmployeeModel()!=null){
				String personCode = loginUserInfo.getEmployeeModel().getPerson_code();
				List<UserRoleInfoModel> userRoleInfoModelList = userRoleInfoService.selectByPersonCode(personCode);
				List<TpmBranchAdminModel> tbamList = tpmBranchAdminService.selectList(new EntityWrapper<TpmBranchAdminModel>().eq("person_code",personCode));
				if(userRoleInfoModelList!=null&&userRoleInfoModelList.size()>0){
					if("1".equals(userRoleInfoModelList.get(0).getType())){
						loginUserInfo.setUserRole("1");
					}
					if("2".equals(userRoleInfoModelList.get(0).getType())){
						if(tbamList != null && tbamList.size() > 0){
							loginUserInfo.setUserRole("6");
						}else{
							loginUserInfo.setUserRole("2");
						}
					}
				}else {
					if(tbamList != null && tbamList.size() > 0){
						loginUserInfo.setUserRole("0");
					}else{
					loginUserInfo.setUserRole(null);
					}
				}
			}
			httpSession.setAttribute(propKey, loginUserInfo);
		}else{
			httpSession.setAttribute(propKey, object);
		}
		
	}

	/**
	 * 检查openid是否存在
	 *
	 * @throws Exception
	 */
	public String checkOpenid() throws Exception {
		String openid = getOpenid();
		if (StringUtils.isEmpty(openid)) {
			throw new ParamException(ResultVoUtils.defaultException(CommonConstants.OPENID_IS_NOT_FOUND));
		}
		return openid;
	}

	/**
	 * 检查用户是否登录
	 * @return
	 * @throws Exception
	 */
	// 获取用户信息
	public String checkPersonCode()throws Exception {
		String managerNo =getPersonCode();
		if (StringUtils.isEmpty(managerNo)){
			throw new ParamException(ResultVoUtils.defaultException(CommonConstants.EMPLOYEE_IS_NOT_FOUND));
		}
		return managerNo;
	}
	
	public static void main(String[] args) {
		LoginUserInfo loginUserInfo = new LoginUserInfo();
		Object object = loginUserInfo;
		System.out.println(object instanceof LoginUserInfo);
	}

	public LoginUserInfo getUserInfo()throws Exception{
		LoginUserInfo user = getLoginUserInfo();
		log.info("loginUser===>{}",user);
		if (user == null){
			throw new ParamException(ResultVo.getByEnumCode(CommonConstants.NOT_LOGIN_CODE));
		}
		//微信用户信息
		WeixinUserInfo weixinUserInfo = user.getWeixinUserInfo();
		//员工用户信息
		TpmEmployeeModel employeeModel = user.getEmployeeModel();

		if (weixinUserInfo != null){//微信端登录
			if (employeeModel ==null){
				if (StringUtils.isEmpty(weixinUserInfo.getOpenId())) {
					throw new ParamException(ResultVo.getByEnumCode(CommonConstants.NOT_LOGIN_CODE));
				}
			}else {
				if (StringUtils.isEmpty(employeeModel.getPerson_code())){
					throw new ParamException(ResultVo.getByEnumCode(CommonConstants.NOT_LOGIN_CODE));
				}
			}
		}else if (employeeModel == null){
			throw new ParamException(ResultVo.getByEnumCode(CommonConstants.NOT_LOGIN_CODE));
		}
		return user;
	}
	
}
