package com.kuyu.common;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * @Author jt_L
 * @Date 2017/9/15
 * @Description 常量工具类
 */
public class CommonConstants {
	
    public static final String ERROR_DEFAULT_CODE = "-20000";

    public static final String PROJECT_DATA_EXISTS = "该立项申请记录已存在";

	public static final String DEFAULT_DENCODE = "UTF-8";
	// 渠道消息事件类型
	public static final String MSGTYPE_EVENT = "event";
	// 消息类型 : 文本
	public static final String MSGTYPE_TEXT = "text";
	// 消息类型 : 链接
	public static final String MSGTYPE_LINK = "link";
	// 消息类型 : 图片
	public static final String MSGTYPE_IMAGE = "image";
	// 消息类型 : 地理位置
	public static final String MSGTYPE_LOCATION = "location";
	// 消息类型 : 语音
	public static final String MSGTYPE_VOICE = "voice";
	// 消息类型 : 视频
	public static final String MSGTYPE_VIDEO = "video";
	// 消息类型：小视频
	public static final String MSGTYPE_SHORT_VIDEO = "shortvideo";

	// 事件类型 : 订阅
	public static final String EVENT_SUBSCRIBE = "subscribe";
	// 事件类型 : 取消订阅
	public static final String EVENT_UNSUBSCRIBE = "unsubscribe";
	// 事件类型 : 用户进入微信页卡页面
	public static final String EVENT_VIEWPAGE = "viewpage";
	// 事件类型 : 自定义菜单点击
	public static final String EVENT_CLICK = "click";
	// 事件类型: 扫描二维码用户已关注时的事件推送
	public static final String EVENT_SCAN = "scan";

	// 图文消息
	public static final String MSGTYPE_NEWS = "news";
    
	public static final String GET_WEIXIN_USER_INFO_URL = "getweixinUserInfoUrl";
	
	public static final String SUBSCRIBE_GET_WEIXIN_USER_INFO_URL = "subscribeGetWeiXinUserInfoUrl";
    
    public static final String LOGIN_USER_INFO = "loginUserInfo";

    public static final String OPENID_IS_NOT_FOUND = "用户openid不存在";

    public static final String GET_WEIXIN_ACCESSTOKEN_URL = "getweixinAccessTokenUrl";

    public static final String SSO_LOGIN_FLAG = "userssoFlag";
    
    public static final String LINCU_URL = "lincuUrl";
    
    public static final String MANAGER_URL = "managerUrl";
    
    public static final String FINANCIAL_URL = "financialUrl";
    
    public static final String LOGIN_USER_TYPE_LINCHU="1";
    
    public static final String LOGIN_USER_TYPE_EMPLOYEE="2";
    
    public static final String WEIXIN_AUTH2_GET_TOKEN_URL = "get_token_url";
    
    public static final String WEIXIN_TOKEN = "token";

    public static final String ACTIVITY_DATA_EXISTS = "该活动记录已存在";

    public static final String PARAM_IS_EMPTY = "参数不能为空";
    
    public static final String WEIXIN_ACCESSTOKEN = "accesstoken";
    
    public static final String ACCESS_TOKEN_UPDATE_TIME = "access_token_update_time";
    
    public static final String WEIXIN_SEND_TEMPLATE_MSGURL = "sendTemplateMsgUrl";
    
    public static final String WEIXIN_JSSDKTICKET = "jssdkticket";
    
    public static final String WEIXIN_PUBLICID = "publicid";
    
    public static final String WEIXIN_APPID = "appid";
    
    public static final String WEIXIN_APPSECRET = "appsecret";

    public static final String IS_NOT_SAME_CITY = "你不在此活动城市中,不能参加";

    public static final String IS_NOT_FOUND_CITY = "你的城市不存在,请先设置自己的城市";

    public static final String PARAM_IS_ERROR = "参数错误";

    public static final String EMPLOYEE_IS_NOT_FOUND = "工号不存在";

    public static final String SUCCESS = "操作成功";

    public static final String ACTIVITY_AUDIT_PASS = "通过";

    public static final String ACTIVITY_AUDIT_NOT_PASS = "不通过";

	//共享平台正确Code值
	public static final String SHARE_PLATFORM_FINISH_CODE = "9999";
	public static final String SHARE_PLATFORM_FINISH_MSG = "成功";

	public static final String SHARE_PLATFORM_ERROR_CODE = "0000";
	public static final String SHARE_PLATFORM_ERROR_MSG = "失败";

	public static final String SHARE_PLATFORM_RESULTVO_CODE = "RET_CODE";
	public static final String SHARE_PLATFORM_RESULTVO_MSG = "RET_MSG";

	public static final String NOT_PERMISSION = "没有权限或者没有登录";

    public static final String MESSAGE_SEND_SUCCESS = "微信消息发送成功!";
    public static final String MESSAGE_SEND_FAIL = "微信消息发送失败!";

    public static final String HOUR_STATE0 = "未审核工时，请您先审核工时";
	public static final String HOUR_STATE2 = "工时审核未通过，不能审核工资";
	
	public static final String REQUEST_STATUS = "现场负责人未审核，您不能审核结算单";
	
	public static final String SYSTEM_ERROR_CODE = "-5000";
	public static final String SYSTEM_ERROR_MSG = "系统异常,请稍后再试或者联系管理员";

	public static final String NOT_INFORMATION_CODE = "-5001";
	public static final String NOT_INFORMATION_MSG = "你的个人信息不存在,请先维护个人信息";

	public static final String ACTIVITY_RECRUIT_FINISH_CODE = "-5002";
	public static final String ACTIVITY_RECRUIT_FINISH_MSG = "该活动当天人数已经招募完成";

	public static final String ACTIVITY_IS_ENDING_CODE = "-5003";
	public static final String ACTIVITY_IS_ENDING_MSG = "该活动已结束";

	public static final String ACTIVITY_DATA_IS_JOIN_CODE = "-5004";
	public static final String ACTIVITY_DATA_IS_JOIN_MSG = "你已申请过该活动";
	
	public static final String PARAM_IS_ERROR_CODE = "-5005";
	public static final String PARAM_IS_ERROR_MSG = "参数错误";

	public static final String NOT_PERMISSION_CODE = "-5007";
	public static final String NOT_PERMISSION_MSG = "权限不足";

	public static final String NOT_LOGIN_CODE = "-5012";
	public static final String NOT_LOGIN_MSG = "请先登录";

	public static final String ACTIVITY_NOT_FOUND_CODE = "-6001";
	public static final String ACTIVITY_NOT_FOUND_MSG = "活动不存在";

	public static final String CANNOT_SIGN_IN_CODE = "-6002";
	public static final String CANNOT_SIGN_IN_MSG = "结算单已审核，您不能再打卡";
	
	public static final String OPENID_NOT_FOUND_CODE = "-6003";
	public static final String OPENID_NOT_FOUND_MSG = "会话已超时失效，请重新登陆后再打卡";
	
	public static final String PERSONCODE_NOT_FOUND_CODE = "-6004";
	public static final String PERSONCODE_NOT_FOUND_MSG = "工号不存在";
	
	public static final String HOUR_STATE0_CODE = "-6008";
	public static final String HOUR_STATE0_MSG = "未审核工时，请您先审核工时";
	
	public static final String HOUR_STATE2_CODE = "-6005";
	public static final String HOUR_STATE2_MSG = "工时审核未通过，不能审核工资";
	
	public static final String REQUEST_STATUS_CODE = "-6006";
	public static final String REQUEST_STATUS_MSG = "现场负责人未审核或审核不通过，您不能审核结算单";
	
	public static final String STATEMENT_NOT_EXIST_CODE = "-6007";
	public static final String STATEMENT_NOT_EXIST_MSG = "结算单不存在";


	public static final String ACTIVITY_DATA_NOT_EXISTS_CODE = "-5006";
	public static final String ACTIVITY_DATA_NOT_EXISTS_MSG = "该活动记录不存在";


	public static final String ACTIVITY_AUDIT_REASON_NOT_EMPTY_CODE = "-5008";
	public static final String ACTIVITY_AUDIT_REASON_NOT_EMPTY_MSG = "审核不通过时审核备注不能为空";

	public static final String ACTIVITY_NO_ERROR_CODE = "-5009";
	public static final String ACTIVITY_NO_ERROR_MSG = "活动招募人数数据异常,请检查!!!";

	public static final String FAIL_CODE = "-5010";
	public static final String FAIL_MSG = "操作失败";

	public static final String PAGE_ERROR_CODE = "-5011";
	public static final String PAGE_ERROR_MSG = "分页参数错误";
	
	public static final String VALID_CODE_ERROR_CODE = "-7001";
	public static final String VALID_CODE_ERROR_MSG = "验证码错误";
	
	public static final String WEIXIN_BINGIN_ECHO_CODE = "-7002";
	public static final String WEIXIN_BINGIN_ECHO_MSG = "用户已绑定其它员工账号，请先解除绑定！";
	
	public static final String USER_BINGIN_ECHO_CODE = "-7003";
	public static final String USER_BINGIN_ECHO_MSG = "该员工账号已绑定其它微信用户，请先解除绑定！";
	
	public static final String VALID_CODE_OVER_TIME = "-7004";
	public static final String VALID_CODE_OVER_TIME_MSG = "验证码已过期";

	public static final String UNBINDING_FAIL_CODE = "-7005";
	public static final String UNBINDING_FAIL_MSG = "解绑失败";
	
	public static final String USERNAME_OR_PASSWORD_ISNULL_CODE = "-7006";
	public static final String USERNAME_OR_PASSWORD_ISNULL_MSG = "用户密码不能为空";
	
	public static final String USERNAME_OR_PASSWORD_ERROR_CODE = "-7007";
	public static final String USERNAME_OR_PASSWORD_ERROR_MSG = "用户名或密码错误";
	
	public static final String ERROR_CODE = "/error";
	public static final String NOT_FOUNT_404_CODE = "404";
	public static final String NOT_FOUNT_404_MSG = "尊敬的用户:你好!非常很抱歉,你访问的页面不存在或已过期";

	public static final String CITY = "市";

	public static final String ALLOW_PROJECT_IDS_KEY = "allow_project_ids";//借款单导入允许申请单key

	public static final String AUDIT_NO_PASS_CODE = "-6009";
	public static final String AUDIT_NO_PASS_MSG = "审核不通过，您必须填写备注";

	public static final String DATA_IS_NO_CHANGE = "数据一致,不需要修改";

	public static final String DETIAL_MEMO = "临促系统支付临促劳务费借款单";
	public static final String ONE = "1";
	
	public static final String URL_IS_NULL_CODE = "-6010";
	public static final String URL_IS_NULL_MSG = "图片上传失败，无法打卡";

	public static final String ACTIVITY_TIME_IS_NULL_CODE = "-6011";
	public static final String ACTIVITY_TIME_IS_NULL_MSG = "活动时间为空";

	public static final String VALID_WORK_HOURS_NULL_CODE = "-6012";
	public static final String VALID_WORK_HOURS_NULL_MSG = "实际工时为空";

	public static final String CHECK_STATUS_CODE = "-6013";
	public static final String CHECK_STATUS_MSG = "审核状态错误";

	public static final String SETTLEMENT_IS_NULL_CODE = "-6014";
	public static final String SETTLEMENT_IS_NULL_MSG = "结算单为空";

	public static final String FSSC_BILL_CODE = "-6015";
	public static final String FSSC_BILL_MSG = "共享单为空";

	public static final String ADJUST_MONEY_NULL_CODE = "-6016";
	public static final String ADJUST_MONEY_NULL_MSG = "调整金额不能为空";

	public static final String REWARD_OVER_PREDICTION_CODE = "-6017";
	public static final String REWARD_OVER_PREDICTION_MSG = "该活动申请的预算奖励余额已不足，请减少当前奖励金额";
	
	public static final String MODIFIY_NOT_FOUND_CODE = "-5013";
	public static final String MODIFIY_NOT_FOUND_MSG = "修改失败,找不到记录";
	
	public static final String PERSON_NUM_OVER_CODE = "-5014";
	public static final String PERSON_NUM_OVER_MSG = "所选择人数超规定人数，重新审核";
	
	public static final String AUDIT__PASS_CODE = "-6018";
	public static final String AUDIT__PASS_MSG = "审核通过，您必须填写修改原因";
	
	public static final String FINANCIAL_NULL_CODE = "-6019";
	public static final String FINANCIAL_NULL_MSG = "负责该活动的部门未设置财务负责人，请联系相关人员处理";

	public static final String ADDRESS_TOO_LONG_CODE = "-9001";
	public static final String ADDRESS_TOO_LONG_MSG = "您输入的详细地址过长，请重新输入";

	public static final String MOBILE_TOO_SHORT_CODE = "-9002";
	public static final String MOBILE_TOO_SHORT_MSG = "您输入的手机号有误，请重新输入";
	
	public static final String PERSON_NULL_CODE = "-6020";
	public static final String PERSON_NULL_MSG = "该部门没有员工，不能设置财务负责人";
	
	public static final String SETTLEMENT_DONE_CODE = "-6021";
	public static final String SETTLEMENT_DONE_MSG = "该结算单已审核，不能重复审核";

	public static final String  ACCOUNT_VALUE_NOT_FOUND_CODE = "-9003";
	public static final String ACCOUNT_VALUE_NOT_FOUND_MSG = "您的银行卡信息未维护，请先维护银行卡信息";

	public static final String  JOIN_ACTIVITY_DAY_WRONG_CODE = "-9004";
	public static final String JOIN_ACTIVITY_DAY_WRONG_MSG = "申请活动日期有误，请重新选取日期";

	public static final String  TOO_MANY_ACTIVITY_REQUESTS_CODE = "-9005";
	public static final String TOO_MANY_ACTIVITY_REQUESTS_MSG = "您当天已申请过活动!";

	public static final String  JOIN_ACTIVITY_DAY_OVER_CODE= "-9006";
	public static final String JOIN_ACTIVITY_DAY_OVER_MSG= "您申请的该日期的活动已结束!";

	public static final String  ACCOUNTNAME_NOT_EQUAL_NAME_CODE= "-9007";
	public static final String ACCOUNTNAME_NOT_EQUAL_NAME_MSG= "银行帐户名与临促姓名不一致！";

	public static final String ATTENDENCE_IS_EXIST_CODE = "-6022";
	public static final String ATTENDENCE_IS_EXIST_MSG = "正在生成考勤表，请稍后再操作";

	public static final String REJECT_USER_FAIL_CODE = "-9008";
	public static final String REJECT_USER_FAIL_MSG = "该临促已打卡，无法剔除";

	public static final String REJECT_USER_NOT_PASSED_CODE = "-9009";
	public static final String REJECT_USER_NOT_PASSED_MSG = "申请通过的临促才能剔除！";

	public static final String ACTIVITY_NOT_EXISTS_OR_NO_AUTHORITY_CODE = "-9010";
	public static final String ACTIVITY_NOT_EXISTS_OR_NO_AUTHORITY_MSG = "该活动记录不存在或权限不足！";

	public static final String  NAME_TOO_LONG_CODE= "-9011";
	public static final String NAME_TOO_LONG_MSG= "姓名过长！";

	public static final String  OPEN_BRANCH_TOO_LONG_CODE= "-9012";
	public static final String OPEN_BRANCH_TOO_LONG_MSG= "银行支行名过长！";

	public static final String  OPEN_CITY_TOO_LONG_CODE= "-9013";
	public static final String OPEN_CITY_TOO_LONG_MSG= "银行支行名过长！";

	
	public static final String WORK_TIME_ERROR_CODE = "-6023";
	public static final String WORK_TIME_ERROR_MSG = "审核通过时，实际工作时长不能为0";


	public static final String  AUDIT_USER_FAIL_CODE= "-9014";
	public static final String AUDIT_USER_FAIL_MSG= "该申请已被审核！";
	
	public static final String  HAVE_ACTIVITY_CODE= "-9015";
	public static final String 	HAVE_ACTIVITY_MSG= "该临促这天已参加了其他活动，一天只能参加一场活动！";
	
	public static final String  ONLY_ALLOW_ONE_CODE= "-9016";
	public static final String 	ONLY_ALLOW_ONE_MSG= "每人每天只能参加一场活动，请选择要参加哪个活动！";

	public static final String  AUDIT_DAY_IS_OUT_OF_DATE_CODE= "-9017";
	public static final String 	AUDIT_DAY_IS_OUT_OF_DATE_MSG= "审核的日期已经结束，无法通过！";

	public static final String  SELECT_IS_NULL_CODE= "-9018";
	public static final String 	SELECT_IS_NULL_MSG= "所选数据为空，无法导出！";

	public static final String 	REFUSED_THE_BINDING_USER_CODE= "-9019";
	public static final String 	REFUSED_THE_BINDING_USER_MSG= "活动负责人和财务负责人不能参加活动！";

}
