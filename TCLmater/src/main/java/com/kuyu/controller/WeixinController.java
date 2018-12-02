package com.kuyu.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.common.CommonConstants;
import com.kuyu.exception.ParamException;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.model.TpmUserBaseInfoModel;
import com.kuyu.model.WeixinEmployeeBinding;
import com.kuyu.model.WeixinEmployeeBindingLog;
import com.kuyu.model.WeixinUserInfo;
import com.kuyu.service.PropertiesConfigService;
import com.kuyu.service.TpmEmployeeService;
import com.kuyu.service.TpmUserBaseInfoService;
import com.kuyu.service.WeixinEmployeeBindingLogService;
import com.kuyu.service.WeixinEmployeeBindingService;
import com.kuyu.service.WeixinUserInfoService;
import com.kuyu.util.ChannelIOUtil;
import com.kuyu.util.DateUtils;
import com.kuyu.util.DegistUtils;
import com.kuyu.util.HttpUtils;
import com.kuyu.util.IOUtils;
import com.kuyu.util.ImageMarkLogoByIcon;
import com.kuyu.util.RSAUtils;
import com.kuyu.util.SSORestful;
import com.kuyu.util.SendWeiXinTemplateMSGUtil;
import com.kuyu.util.WxJsSdkUtil;
import com.kuyu.util.XstreamUtils;
import com.kuyu.vo.JSSDKTicket;
import com.kuyu.vo.LoginUserVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.weixin.ChannelReqDispatcher;
import com.kuyu.weixin.ChannelReqMessage;
import com.tcl.idm.AuthApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * @author zkw
 * @since 2017-09-20 11:10
 */
@AOP_Controller_LOG
@Api(tags = "微信鉴权绑定相关接口")
@RequestMapping("/weixin")
public class WeixinController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(WeixinController.class);
	
	@Autowired
	private PropertiesConfigService propertiesConfigService;
	
	@Autowired
	private WeixinUserInfoService weixinUserInfoService;
	
	@Autowired
	private WeixinEmployeeBindingService weixinEmployeeBindingService;
	
	@Autowired
	private WeixinEmployeeBindingLogService weixinEmployeeBindingLogService;
	
	@Autowired
	private TpmEmployeeService employeeService;
	
	@Autowired
	private ChannelReqDispatcher channelReqDispatcher;
	
	@Autowired
	private TpmUserBaseInfoService userBaseInfoService;
	
	@Autowired
	private SendWeiXinTemplateMSGUtil sendWeiXinTemplateMSGUtil;
	
	@Value("${sso.appcode}")
	private String  appcode;
	@Value("${sso.appkey}")
	private String  appkey;
	@Value("${sso.host}")
	private String  host;
	
	@Value("${image.url}")
	private String imageUrl;
	
	@Value("${image.path}")
	private String folder;
	
	@Value("${image.iconPath}")
	private String iconPath;
	
	// 接微信收消息
	@RequestMapping(value = "/weixincheck", method = RequestMethod.POST)
	@ResponseBody
	public void onReceiveWeixinReqMsg(
			@RequestParam(value = "signature") String signature,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "nonce") String nonce,
			HttpServletRequest request, HttpServletResponse response) {
		boolean isValid = true;
		// 向渠道发送空响应信息
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().print("");
			response.getWriter().flush();
		} catch (IOException e) {

		}
		try {
			// 验证消息的有效性
			isValid = verify(signature, true, timestamp, nonce);
		} catch (Throwable casue) {
			log.error(
					" Weixin  request message verifing exception "
							+ casue.getMessage(), casue);
			isValid = false;
		}
		if (!isValid) {
			log.warn("verify  request message of weixin fail..........");
			return;
		}
		String reqMessage = null; // 请求信息
		ChannelReqMessage channelReqMsg = null;
		
		try {
			reqMessage = IOUtils.readLine(request.getInputStream(), null);
		} catch (IOException e) {
			log.error("Fail read xml from request " + e.getMessage(), e);
			reqMessage = null;
		}
		if (StringUtils.isNotEmpty(reqMessage)) {
			try {
				log.info("receive xml =======" + reqMessage); // 打印渠道请求消息
				Object object =  XstreamUtils.fromXML("xml",
						ChannelReqMessage.class, reqMessage);
				channelReqMsg = (ChannelReqMessage)object;
				
			} catch (Throwable e) {
				log.error(
						"Fail transform XML to ChannelReqMessage "
								+ e.getMessage(), e);
			}
		}
		
		try {
			channelReqDispatcher.dispatch(channelReqMsg,request);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	// 首次配置需要
	@RequestMapping(value = "/weixincheck", method = RequestMethod.GET)
	@ResponseBody
	public void onReceiveWeixinReqMsg(
			@RequestParam(value = "signature") String signature,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "nonce") String nonce,
			@RequestParam(value = "echostr") String echostr,
			HttpServletRequest request, HttpServletResponse response) {
		boolean isValid = true;
		// 向渠道发送空响应信息
		try {
			// 验证消息的有效性
			isValid = verify(signature, true, timestamp, nonce);
			log.info("/weixin/weixincheck is called..........");
		} catch (Throwable casue) {
//			log.error(
//					" Weixin  request message verifing exception "
//							+ casue.getMessage(), casue);
			isValid = false;
		}
		if (isValid) {
			try {
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(echostr); // 验证通过会写字符串
				response.getWriter().flush();
			} catch (IOException e) {
			}
		}
	}
	// 验证消息的合法性
	public boolean verify(String digest, boolean isSorted, String... params)
			throws Exception {
		boolean isValid = false;
		List<String> digestParams = new ArrayList<String>();
		if (params != null && params.length > 0) {
			for (String param : params) {
				digestParams.add(param);
			}
			String token = propertiesConfigService.selectBykey(CommonConstants.WEIXIN_TOKEN).getPvalue();
			if (StringUtils.isNotEmpty(token)) {
				digestParams.add(token);
				isValid = DegistUtils.verify(digest, "SHA-1", digestParams,
						true);
			}
		}
		return isValid;
	}

	// 微信鉴权跳转
	@RequestMapping(value = "/weixin/callback", method = RequestMethod.GET)
	public void callback(@RequestParam(value = "code") String code,
			@RequestParam(value = "state") String state,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		authorizationCallback(code, state, request, response, null);
	}
	
	/**
	 * 授权跳转页面
	 * 
	 * @param code
	 * @param state
	 * @param request
	 * @param response
	 * @param backurl
	 * @throws Exception 
	 */
	private void authorizationCallback(String code, String state,
			HttpServletRequest request, HttpServletResponse response,
			String backurl) throws Exception {
		// 根据state获取公共账号以及type
		// 获取公共账号信息
		String appId =propertiesConfigService.selectBykey(CommonConstants.WEIXIN_APPID).getPvalue();
		String secret = propertiesConfigService.selectBykey(CommonConstants.WEIXIN_APPSECRET).getPvalue();
		String auth_url = propertiesConfigService.selectBykey(CommonConstants.WEIXIN_AUTH2_GET_TOKEN_URL).getPvalue();
		auth_url = auth_url + "?appid=" + appId + "&secret=" + secret
				+ "&code=" + code + "&grant_type=authorization_code";
		auth(state, "weixin", auth_url, request, response,
				backurl);
	}
	
	// oauth2.0鉴权跳转
	private void auth(String type, String origin, String url,
			HttpServletRequest request, HttpServletResponse response,String backurl)
			throws Exception {
		long temptime = System.currentTimeMillis();
		LoginUserInfo userInfo = this.getLoginUserInfo();
		String openid = "";
		String accessToken = "";

//		if (userInfo == null) {
			String rejson = HttpUtils.get(url);
			JSONObject jsonObj = JSONObject.fromObject(rejson);
			if (jsonObj.containsKey("errcode")) {
				log.error("通过code换取网页授权access_token错误" + url + " ret:" + rejson);
			} else {
				openid = jsonObj.getString("openid");
				accessToken = jsonObj.getString("access_token");
			}

			if (StringUtils.isNotEmpty(openid)) {
				WeixinUserInfo weixinUserInfo = weixinUserInfoService.selectBykey(openid);
				if (null == weixinUserInfo ) {
//					//TODO 重新插入关注记录
//					String getUserInfoUrl =  propertiesConfigService.selectBykey(CommonConstants.GET_WEIXIN_USER_INFO_URL).getPvalue()+"?access_token="+accessToken+"&openid="+openid+"&lang=zh_CN ";
//					String userinforejson = HttpUtils.get(getUserInfoUrl);
//					JSONObject userinfojsonObj = JSONObject.fromObject(userinforejson);
//					weixinUserInfo = (WeixinUserInfo) JSONObject.toBean(userinfojsonObj, WeixinUserInfo.class);
//					weixinUserInfo.setPrivilege(null);
//					weixinUserInfo.setPersonCode(null);
					try {
						weixinUserInfo = channelReqDispatcher.getUserBaseInfo(openid, null);
						weixinUserInfoService.insertTpmUserWxInfo(weixinUserInfo);			
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
				userInfo = new LoginUserInfo(); // 用户信息
				WeixinEmployeeBinding weixinEmployeeBinding = weixinEmployeeBindingService.selectByOpenid(weixinUserInfo.getOpenId());
				if(weixinEmployeeBinding!=null){
					TpmEmployeeModel employeeModel = new TpmEmployeeModel();
					employeeModel.setPerson_code(weixinEmployeeBinding.getPerson_code());
					ResultVo rs =null;
					try {
						rs = employeeService.getTpmEmployee(employeeModel);
					} catch (ParamException e) {
						e.printStackTrace();
					}
					if(rs!=null&&ResultVo.SUCCESS.equals(rs.getCode())){
						employeeModel=(TpmEmployeeModel)rs.getData();
					}
//					userInfo.setUserType(CommonConstants.LOGIN_USER_TYPE_EMPLOYEE);
					userInfo.setEmployeeModel(employeeModel);
				}
				if(CommonConstants.LINCU_URL.equals(type)){
					userInfo.setUserType(CommonConstants.LOGIN_USER_TYPE_LINCHU);
				}else{
					userInfo.setUserType(CommonConstants.LOGIN_USER_TYPE_EMPLOYEE);
				}
				userInfo.setWeixinUserInfo(weixinUserInfo);
				this.setValue(CommonConstants.LOGIN_USER_INFO, userInfo);
			}
//		} 
		
		// 获取页面URL
		String redirectUrl = "";
		String random = String.valueOf(Math.random());
		if (CommonConstants.LINCU_URL.equals(type)) {
			redirectUrl = propertiesConfigService.selectBykey(CommonConstants.LINCU_URL).getPvalue();
			redirectUrl = redirectUrl.contains("?")?redirectUrl+"&random="+random:redirectUrl+"?random="+random;
		}
		if (CommonConstants.MANAGER_URL.equals(type)) {
			redirectUrl = propertiesConfigService.selectBykey(CommonConstants.MANAGER_URL).getPvalue();
			redirectUrl = redirectUrl.contains("?")?redirectUrl+"&random="+random:redirectUrl+"?random="+random;
		}
		if (CommonConstants.FINANCIAL_URL.equals(type)) {
			redirectUrl = propertiesConfigService.selectBykey(CommonConstants.FINANCIAL_URL).getPvalue();
			redirectUrl = redirectUrl.contains("?")?redirectUrl+"&random="+random:redirectUrl+"?random="+random;
		}
		if("3".equals(type)){
			redirectUrl = propertiesConfigService.selectBykey(CommonConstants.TOURIST_URL).getPvalue();
			redirectUrl = redirectUrl +"?openid="+openid;
		}
		response.sendRedirect(redirectUrl);
		log.debug("auth end :" + (System.currentTimeMillis() - temptime)
				+ ",auth_url:" + url);
	}
	
	// 虚拟登陆
	@RequestMapping(value = "/weixinSimulationLogin", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo  weixinSimulationLogin(@RequestParam(value = "token") String token,@RequestParam(value = "type") String type) throws Exception {
		LoginUserInfo loginUserInfo = new LoginUserInfo();
		if(CommonConstants.LOGIN_USER_TYPE_LINCHU.equals(type)){
			WeixinUserInfo weixinUserInfo = weixinUserInfoService.selectBykey(token);
			if(weixinUserInfo!=null){
				loginUserInfo.setUserType(CommonConstants.LOGIN_USER_TYPE_LINCHU);
				loginUserInfo.setWeixinUserInfo(weixinUserInfo);
				WeixinEmployeeBinding weixinEmployeeBinding = weixinEmployeeBindingService.selectByOpenid(weixinUserInfo.getOpenId());
				if(weixinEmployeeBinding!=null){
					TpmEmployeeModel employeeModel = new TpmEmployeeModel();
					employeeModel.setPerson_code(weixinEmployeeBinding.getPerson_code());
					ResultVo rs = employeeService.getTpmEmployee(employeeModel);
					if(rs!=null&&ResultVo.SUCCESS.equals(rs.getCode())){
						employeeModel=(TpmEmployeeModel)rs.getData();
					}
					loginUserInfo.setEmployeeModel(employeeModel);
				}
				this.setValue(CommonConstants.LOGIN_USER_INFO, loginUserInfo);
			}else{
				return ResultVo.get(ResultVo.FAIL);
			}
		}else if(CommonConstants.LOGIN_USER_TYPE_EMPLOYEE.equals(type)){
			TpmEmployeeModel employeeModelquery =  new TpmEmployeeModel();
			employeeModelquery.setPerson_code(token);
			ResultVo vo = employeeService.getTpmEmployee(employeeModelquery);
			TpmEmployeeModel employeeModel = null;
			if(ResultVo.SUCCESS.equals(vo.getCode())){
				employeeModel = (TpmEmployeeModel)vo.getData();
			}
			if(employeeModel!=null){
				loginUserInfo.setUserType(CommonConstants.LOGIN_USER_TYPE_EMPLOYEE);
				loginUserInfo.setEmployeeModel(employeeModel);
				this.setValue(CommonConstants.LOGIN_USER_INFO, loginUserInfo);
			}else{
				return ResultVo.get(ResultVo.FAIL);
			}
		}else if("3".equals(type)){
			WeixinUserInfo weixinUserInfo = weixinUserInfoService.selectBykey(token);
			if(weixinUserInfo!=null){
				loginUserInfo.setUserType(CommonConstants.LOGIN_USER_TYPE_EMPLOYEE);
				loginUserInfo.setWeixinUserInfo(weixinUserInfo);
				WeixinEmployeeBinding weixinEmployeeBinding = weixinEmployeeBindingService.selectByOpenid(weixinUserInfo.getOpenId());
				if(weixinEmployeeBinding!=null){
					TpmEmployeeModel employeeModel = new TpmEmployeeModel();
					employeeModel.setPerson_code(weixinEmployeeBinding.getPerson_code());
					ResultVo rs = employeeService.getTpmEmployee(employeeModel);
					if(rs!=null&&ResultVo.SUCCESS.equals(rs.getCode())){
						employeeModel=(TpmEmployeeModel)rs.getData();
					}
					loginUserInfo.setEmployeeModel(employeeModel);
					this.setValue(CommonConstants.LOGIN_USER_INFO, loginUserInfo);
				}else{
					return ResultVo.get(ResultVo.FAIL);
				}	
			}else{
				return ResultVo.get(ResultVo.FAIL);
			}
		}else{
			return ResultVo.get(ResultVo.FAIL);
		}
		return ResultVo.get(ResultVo.SUCCESS);
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/binding", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation("财务和现场负责人微信绑定工号以及pc版登陆接口")
	public ResultVo binding(@RequestBody LoginUserVo  vo,HttpServletResponse response) {
		
		ResultVo rs = ResultVo.getByEnumCode(CommonConstants.SYSTEM_ERROR_CODE);
		//密码解密
				String loginName = vo.getUsername();
				String loginPwd = vo.getPassword();
//				if (loginName.length() > 96 && loginPwd.length() > 96) {
				
					try {
						
						try {
							loginPwd = URLDecoder.decode(loginPwd, "UTF-8");
							byte[] decodedPwd = RSAUtils.decryptByPrivateKey(loginPwd,RSAUtils.PRIVATE_KEY);
							loginPwd = new String(decodedPwd);
							loginName = URLDecoder.decode(loginName, "UTF-8");
							byte[] decodedUsername = RSAUtils.decryptByPrivateKey(loginName, RSAUtils.PRIVATE_KEY);
							loginName = new String(decodedUsername);
						} catch (Exception e1) {
							e1.printStackTrace();
							return ResultVo.getByEnumCode(CommonConstants.USERNAME_OR_PASSWORD_ERROR_CODE);
						}
						
//						loginName = new String(Base64.getDecoder().decode(loginName));
//						loginPwd = new String(Base64.getDecoder().decode(loginPwd));
//						
//						loginName = loginName.substring(48, loginName.length() - 48);
//						loginPwd = loginPwd.substring(48, loginPwd.length() - 48);
//						
//						loginName = new String(Base64.getDecoder().decode(loginName));
//						loginPwd = new String(Base64.getDecoder().decode(loginPwd));
						
						if (StringUtils.isBlank(loginName) || StringUtils.isBlank(loginPwd)) {
							return ResultVo.getByEnumCode(CommonConstants.USERNAME_OR_PASSWORD_ISNULL_CODE);
						}
						
						vo.setUsername(loginName);
						vo.setPassword(loginPwd);

//						if(vo.getType().equals("2")){
							Map<String, String> validCodeMap = (HashMap<String, String>)this.getSession().getAttribute("verifyCode");
							if(vo.getValidCode()==null||validCodeMap==null){
								return ResultVo.getByEnumCode(CommonConstants.VALID_CODE_ERROR_CODE);
							}else{
								if(!vo.getValidCode().toLowerCase().equals(validCodeMap.get("verifyCode").toLowerCase())){
									return ResultVo.getByEnumCode(CommonConstants.VALID_CODE_ERROR_CODE);
								}else {
									String timeStr = validCodeMap.get("time");
									Date validTime = DateUtils.getDateByStr(timeStr);
									Date lastValidTime = DateUtils.addSecond(validTime, 60);
									if(lastValidTime.before(new Date())){
										return ResultVo.getByEnumCode(CommonConstants.VALID_CODE_OVER_TIME);
									}
								}									
							}
							this.getSession().removeAttribute("verifyCode");
							
//						}
						
						TpmEmployeeModel employeeModel = employeeService.getTpmEmployeeByItcode(loginName);
						
						if(employeeModel!=null)
						{
							String result;
//							try {
//								result = AuthApi.authUserPwdGetUserId(response, loginName, loginPwd);
//							} catch (Exception e) {
//								e.printStackTrace();
//								rs = ResultVo.get(ResultVo.USERNAME_OR_PASSWORD_ERROR);
//							}
							
							String usessoFlag = propertiesConfigService.selectBykey(CommonConstants.SSO_LOGIN_FLAG).getPvalue();
							String ssoResult = "";
							if("1".equals(usessoFlag)){
								SSORestful.host = this.host;
								SSORestful.appcode=this.appcode;
								SSORestful.appkey=this.appkey;
								ssoResult = SSORestful.authenticateSSOUser(loginName, loginPwd);
								JSONObject userJsonObj = JSONObject.fromObject(ssoResult);
								result = userJsonObj.get("status").toString();
							}else{								
								result = "true";
							}
							if ("true".equals(result)) {
								System.out.println("login name:"+vo.getUsername()+",pwd:"+ vo.getPassword());
//								
								LoginUserInfo loginUserInfo =  this.getLoginUserInfo();
								if(loginUserInfo==null){
									loginUserInfo = new LoginUserInfo();
								}
								loginUserInfo.setUserType(CommonConstants.LOGIN_USER_TYPE_EMPLOYEE);
								if("1".equals(vo.getType())){
									WeixinEmployeeBinding weixinEmployeeBindingdel = weixinEmployeeBindingService.selectByCode(employeeModel.getPerson_code());
									
									if(weixinEmployeeBindingdel!=null){
										return ResultVo.getByEnumCode(CommonConstants.USER_BINGIN_ECHO_CODE);
									}
									weixinEmployeeBindingdel = weixinEmployeeBindingService.selectByOpenid(loginUserInfo.getWeixinUserInfo().getOpenId());
									if(weixinEmployeeBindingdel!=null){
										return ResultVo.getByEnumCode(CommonConstants.WEIXIN_BINGIN_ECHO_CODE);
									}
									WeixinEmployeeBinding weixinEmployeeBinding = new WeixinEmployeeBinding();
									weixinEmployeeBinding.setOpenid(this.getOpenid());
									weixinEmployeeBinding.setPerson_code(employeeModel.getPerson_code());
									weixinEmployeeBinding.setCreate_time(DateUtils.getLongDateStr());
									weixinEmployeeBindingService.insertWeixinEmployeeBinding(weixinEmployeeBinding);	
									WeixinEmployeeBindingLog weixinEmployeeBindingLog = new WeixinEmployeeBindingLog();
									weixinEmployeeBindingLog.setOpenid(this.getOpenid());
									weixinEmployeeBindingLog.setPerson_code(employeeModel.getPerson_code());
									weixinEmployeeBindingLog.setOperate_type("1");//1 绑定；2 解绑
									weixinEmployeeBindingLogService.insert(weixinEmployeeBindingLog);
									if(loginUserInfo!=null&&loginUserInfo.getWeixinUserInfo()!=null){
										TpmUserBaseInfoModel userBaseInfoModel = userBaseInfoService.getUserBaseInfoOriginal(loginUserInfo.getWeixinUserInfo().getOpenId());
										if(userBaseInfoModel!=null){
											userBaseInfoModel.setBinding_verify("1");
											userBaseInfoService.update(userBaseInfoModel);
										}
									}
								}
								loginUserInfo.setEmployeeModel(employeeModel);
								this.setValue(CommonConstants.LOGIN_USER_INFO, loginUserInfo);
								
								//针对一个账号只允许在一个地方登陆处理
								if(vo.getType().equals("2")){
									if(loginUserList.contains(employeeModel.getPerson_code())){
										int loginIndex = userLoginIndexMap.get(employeeModel.getPerson_code()).intValue();
										loginIndex++;
										userLoginIndexMap.put(employeeModel.getPerson_code(), loginIndex);
										this.setValue("loginIndex", loginIndex);
									}else{
										loginUserList.add(employeeModel.getPerson_code());
										userLoginIndexMap.put(employeeModel.getPerson_code(), 1);
										this.setValue("loginIndex", 1);
									}
								}
								return ResultVo.get(ResultVo.SUCCESS);
							}else{
								rs = ResultVo.getByEnumCode(CommonConstants.USERNAME_OR_PASSWORD_ERROR_CODE);
							}
						}else{
							rs = ResultVo.getByEnumCode(CommonConstants.USERNAME_OR_PASSWORD_ERROR_CODE);
						}
					} catch(Exception e){
						e.printStackTrace();
						rs = ResultVo.getByEnumCode(CommonConstants.SYSTEM_ERROR_CODE);
					}		
		return  rs;
	}
	
	@RequestMapping(value = "/unbinding", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation("财务和现场负责人微信工号解绑")
	public ResultVo unbinding(@RequestBody LoginUserVo vo, HttpServletResponse response) throws Exception {
		
		//密码解密
		String loginPwd = vo.getPassword();
//		loginPwd = new String(Base64.getDecoder().decode(loginPwd));
//		loginPwd = loginPwd.substring(48, loginPwd.length() - 48);
//		loginPwd = new String(Base64.getDecoder().decode(loginPwd));

		loginPwd = URLDecoder.decode(loginPwd, "UTF-8");
		byte[] decodedUsername = RSAUtils.decryptByPrivateKey(loginPwd, RSAUtils.PRIVATE_KEY);
		loginPwd = new String(decodedUsername);
		
		if (StringUtils.isBlank(loginPwd)) {
			return ResultVo.getByEnumCode(CommonConstants.USERNAME_OR_PASSWORD_ISNULL_CODE);
		}
		
		try {
			LoginUserInfo loginUserInfo = this.getLoginUserInfo();
			TpmEmployeeModel employeeModel = loginUserInfo.getEmployeeModel();
			
			String usessoFlag =  propertiesConfigService.selectBykey(CommonConstants.SSO_LOGIN_FLAG).getPvalue();
			String ssoResult = "";
			String result = "";
			if("1".equals(usessoFlag)){
				if(employeeModel!=null){
					String loginName = employeeModel.getItcode();
					SSORestful.host = this.host;
					SSORestful.appcode=this.appcode;
					SSORestful.appkey=this.appkey;
					ssoResult = SSORestful.authenticateSSOUser(loginName, loginPwd);
					JSONObject userJsonObj = JSONObject.fromObject(ssoResult);
					log.info("调用SSO接口返回数据"+ssoResult);
					result = userJsonObj.get("status").toString();				
				}
			}else{								
				result = "true";
			}
			
			if ("true".equals(result)) {
			
				weixinEmployeeBindingService.deleteByPersonCode(employeeModel.getPerson_code());
				if(loginUserInfo!=null&&loginUserInfo.getWeixinUserInfo()!=null){
					TpmUserBaseInfoModel userBaseInfoModel = userBaseInfoService.getUserBaseInfoOriginal(loginUserInfo.getWeixinUserInfo().getOpenId());
					if(userBaseInfoModel!=null){
						userBaseInfoModel.setBinding_verify("0");
						userBaseInfoService.update(userBaseInfoModel);
					}

					WeixinEmployeeBindingLog weixinEmployeeBindingLog = new WeixinEmployeeBindingLog();
					weixinEmployeeBindingLog.setOpenid(this.getOpenid());
					weixinEmployeeBindingLog.setPerson_code(employeeModel.getPerson_code());
					weixinEmployeeBindingLog.setOperate_type("2");//1 绑定；2 解绑
					weixinEmployeeBindingLogService.insert(weixinEmployeeBindingLog);
				}
				
				loginUserInfo.setEmployeeModel(null);
				loginUserInfo.setUserType(null);
				this.setValue(CommonConstants.LOGIN_USER_INFO, loginUserInfo);
				//针对一个账号只允许在一个地方登陆处理
				loginUserList.remove(employeeModel.getPerson_code());
				userLoginIndexMap.remove(employeeModel.getPerson_code());
				this.getSession().removeAttribute("loginIndex");
			}else if("false".equals(result)){
				return ResultVo.getByEnumCode(CommonConstants.USERNAME_OR_PASSWORD_ERROR_CODE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultVo.getByEnumCode(CommonConstants.SYSTEM_ERROR_CODE);
		}
		return ResultVo.get(ResultVo.SUCCESS);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ApiOperation("PC版退出登录")
	public ResultVo logout() throws Exception {
		this.getSession().removeAttribute(CommonConstants.LOGIN_USER_INFO);
		this.getSession().invalidate();
		return ResultVo.get(ResultVo.SUCCESS);
	}
	
	@RequestMapping(value = "/getloginuser", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation("testlogin")
	public Object getloginuser() {
		
		String openid =this.getOpenid();
		String person_code = this.getPersonCode();
		Map<String, Object> map = new HashMap<>();
		map.put("openid", openid);
		map.put("person_code", person_code);
		map.put("loginuserinfo", this.getLoginUserInfo());
		return map;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public Object test() {
		
		String templateId = "I8-V2k7d3f7mIWThHmG_Sy8tNPnCQAoFF5W0ibA2FWs";
		String toUserOpenid = "oPB6kt9Kt2FiXquMEKZ07fkov6yg";
		String firstStr = "亲，您有一笔劳务费用已成功支付，请及时查收哦！\n";
		String endStr = "感谢你的辛勤付出";
		String[] args = new String[]{"双十一狂欢线上线下一起来\n","2017-11-09\n","100元\n"};
		sendWeiXinTemplateMSGUtil.sendMSG(templateId, toUserOpenid, firstStr, args, endStr);
		return "ok";
	}
	
	/**
	 * 微信签名信息
	 * @return
	 */
	@RequestMapping(value = "/querySignService", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getQuerySignService(@RequestParam(value = "url") String url){
		LoginUserInfo userInfo = this.getLoginUserInfo();
//		if(null == userInfo)
//		{
//			return null;
//		}
		String publicId=propertiesConfigService.selectBykey(CommonConstants.WEIXIN_PUBLICID).getPvalue();
		String ticket = propertiesConfigService.selectBykey(CommonConstants.WEIXIN_JSSDKTICKET).getPvalue();
		String appid = propertiesConfigService.selectBykey(CommonConstants.WEIXIN_APPID).getPvalue();
		
		JSSDKTicket  jsapi_ticket=new JSSDKTicket();
		jsapi_ticket.setAppId(appid);
		jsapi_ticket.setUuId(publicId);
		jsapi_ticket.setTicket(ticket);
		
		JSONObject resultJson= WxJsSdkUtil.getSign(jsapi_ticket.getTicket(), url);
		resultJson.put("appId", jsapi_ticket.getAppId());
		return resultJson;
		
	}
	
	/**
	 * 通过media_id从腾讯下载图片，保存到本地并返回图片URL
	 * @return
	 */
	@RequestMapping(value = "/downloandWeiXinImage", method = RequestMethod.GET)
	@ResponseBody
	public Object downloandWeiXinImage(@RequestParam(value = "media_ids") String media_ids){
		LoginUserInfo userInfo = this.getLoginUserInfo();
//		if(null == userInfo)
//		{
//			return null;
//		}
		ResultVo rv = ResultVo.get(ResultVo.SUCCESS);
		String accessToken=propertiesConfigService.selectBykey(CommonConstants.WEIXIN_ACCESSTOKEN).getPvalue();
//		media_ids = deco
		String[] media_idArr = media_ids.split(",");
		for(String media_id:media_idArr){
			String fileName = media_id;
			File file = ChannelIOUtil.downLoadImage(accessToken, media_id,fileName,folder);
			file =ImageMarkLogoByIcon.markFileLogo(file,iconPath);
			rv.setData(imageUrl+"/"+file.getName());
		}
		return rv;
	}
	
	
	
	/**
	 * 通过media_id从腾讯下载图片，保存到本地并返回图片URL
	 * @return
	 */
	@RequestMapping(value = "/requestbodytest", method = RequestMethod.POST)
	@ResponseBody
	public void imagetest(@RequestBody() String bodystr){
		System.out.println(bodystr);
	}
	
	public static void main(String[] args) {
//		String loginName = "TmpkbU5URXpNRGN0Tm1ReE5pMHpNalUyTFRObFptTXRNVGMxWkdWaFpXSTVOVGs1TVRJek5EVTJOalV5TXpneFl6Y3RNMlkxTWkwNU1XVTVMVE5qWmpndFlqQXpOR1l5TWpBeE5qaGg";
//		String loginName = "T1dVeFl6QmxNV0V0TlRNMU55MWxOVEF3TFdVNFltTXROV1U1WVRWallqQmlZVFkwYzNOelpEUTBPV1UwTUdFdE5XSTJZeTB6T0RZd0xXTXhNVGt0TXpjek1HSmhaVFl6WXpnNQ==";
//		if (loginName.length() > 96 ) {
//				loginName = new String(Base64.getDecoder().decode(loginName));	
//				loginName = loginName.substring(48, loginName.length() - 48);
//				loginName = new String(Base64.getDecoder().decode(loginName));
//				System.out.println(loginName);
//		}	
//		try {
//			String name = "XZFIg4H%2B8E%2BDEAchhMe%2FnvbsbIzW7cIO7jtLOjjQc3e5kXIpFuaEA3ohFwulGtiAyAIYmTR1DqAFqY0aHswYfg%3D%3D";
//			String loginPwd = name;
//			loginPwd = URLDecoder.decode(loginPwd, "UTF-8");
//			byte[] decodedPwd = RSAUtils.decryptByPrivateKey(loginPwd,RSAUtils.PRIVATE_KEY);
//			loginPwd = new String(decodedPwd);
//			System.out.println(loginPwd);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		System.out.println(String.valueOf(Math.random()));	
		
//		idmtest();
	}
	
	/**
     * emoji表情替换
     *
     * @param source 原字符串
     * @param slipStr emoji表情替换成的字符串                
     * @return 过滤后的字符串
     */
    public static String filterEmoji(String source,String slipStr) {
        if(StringUtils.isNotBlank(source)){
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
        }else{
            return source;
        }
    }
	
	public static String idmtest(){
		
		String result = "";
		try {
			result = AuthApi.authUserPwdGetUserId(null, "hepz", "tcl#2017");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
