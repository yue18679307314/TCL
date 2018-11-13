package com.kuyu.vo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.kuyu.common.ResultVoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("返回结果Model")
public class ResultVo {

	@ApiModelProperty("状态码")
	private String code;
	@ApiModelProperty("信息")
	private String msg;
	@ApiModelProperty("数据")
	private Object data;
	
	public ResultVo(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public ResultVo() {
		super();
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public  static ResultVo get(String code){
		ResultVo r = new ResultVo();
		r.setCode(code);
		r.setMsg(codeMaps.get(code));
		return r;
	}

	public  static ResultVo getByEnumCode(String code){
		ResultVo r = new ResultVo();
		r.setCode(code);
		r.setMsg(ResultVoEnum.getNameByKey(code));
		return r;
	}

	public  static ResultVo getData(String code,Object obj){
		ResultVo r = new ResultVo();
		r.setCode(code);
		r.setData(obj);
		return r;
	}

	public  static ResultVo getDataWithSuccess(Object obj){
		ResultVo r = ResultVo.get(ResultVo.SUCCESS);
		r.setData(obj);
		return r;
	}
	
	private static Map<String,String> codeMaps = new ConcurrentHashMap<>();
	
	public static final String SUCCESS = "0"; 
	public static final String UPDATE_SUCCESS = "1";
	public static final String INSERT_SUCCESS = "2";
	public static final String FAIL = "-1"; 
	public static final String REGISTER_ERROR = "-2"; 
	public static final String BIND_ERROR = "-3"; 
	public static final String VERIFY_CODE_FAIL = "-4"; 
	public static final String VERIFY_CODE_NEDD_REFRESH = "-5"; 
	public static final String NO_LOGIN = "-6"; 
	public static final String NOT_FOUND = "-7";
	public static final String PAGE_PARAM_ERROR = "-8";
	public static final String PARAM_ERROR = "-9";
	public static final String USERNAME_OR_PASSWORD_ISNULL = "-10";
	public static final String USERNAME_OR_PASSWORD_ERROR = "-11";
	public static final String FILE_TO_BIG = "2001";
	public static final String SUFFIX_ERROR = "2002";
	public static final String FILE_IS_NULL = "2003";
	public static final String NOT_AUTHORISED = "2004";
	static{
		codeMaps.put(SUCCESS, "SUCCESS");
		codeMaps.put(UPDATE_SUCCESS, "update success");
		codeMaps.put(INSERT_SUCCESS, "insert success");
		codeMaps.put(FAIL, "操作失败");
		codeMaps.put(REGISTER_ERROR, "系统出错，注册失败，请稍后重试");
		codeMaps.put(BIND_ERROR, "系统出错，绑定失败，请稍后重试");
		codeMaps.put(VERIFY_CODE_FAIL, "verify code fail");
		codeMaps.put(VERIFY_CODE_NEDD_REFRESH, "verify code need refresh");
		codeMaps.put(NO_LOGIN, "no login");
		codeMaps.put(NOT_FOUND, "数据不存在,或者你没有权限访问");
		codeMaps.put(PAGE_PARAM_ERROR, "分页参数有误");
		codeMaps.put(PARAM_ERROR, "参数有误");
		codeMaps.put(USERNAME_OR_PASSWORD_ISNULL, "用户名或者密码为空");
		codeMaps.put(USERNAME_OR_PASSWORD_ERROR, "用户名或密码错误");
		codeMaps.put(FILE_TO_BIG, "图片过大");
		codeMaps.put(SUFFIX_ERROR, "只支持jpg、gif、png、jpeg、bmp图片格式");
		codeMaps.put(FILE_IS_NULL, "上传图片为空");
		codeMaps.put(NOT_AUTHORISED, "没有权限此操作");
	}

}
