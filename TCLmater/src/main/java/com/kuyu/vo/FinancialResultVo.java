package com.kuyu.vo;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kuyu.common.ResultVoEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("财务返回结果Model")
public class FinancialResultVo {

		@ApiModelProperty("状态码")
		@JsonProperty(value ="RET_CODE")
//		@JSONField(name = "RET_CODE")
		private String ret_code;
		@ApiModelProperty("信息")
		@JsonProperty("RET_MSG")
//		@JSONField(name = "RET_MSG")
		private String ret_msg;
		public FinancialResultVo() {
			super();
		}
		public FinancialResultVo(String ret_code, String ret_msg) {
			super();
			this.ret_code = ret_code;
			this.ret_msg = ret_msg;
		}
		
		public String getRet_code() {
			return ret_code;
		}
		public void setRet_code(String ret_code) {
			this.ret_code = ret_code;
		}
		public String getRet_msg() {
			return ret_msg;
		}
		public void setRet_msg(String ret_msg) {
			this.ret_msg = ret_msg;
		}
		public  static FinancialResultVo get(String code){
			FinancialResultVo r = new FinancialResultVo();
			r.setRet_code(code);
			r.setRet_msg(codeMaps.get(code));
			return r;
		}
		public  static FinancialResultVo getByEnumCode(String code){
			FinancialResultVo f = new FinancialResultVo();
			f.setRet_code(code);
			f.setRet_msg(ResultVoEnum.getNameByKey(code));
			return f;
		}
		private static Map<String,String> codeMaps = new ConcurrentHashMap<>();
		
		public static final String SUCCESS = "9999"; 
		public static final String FAIL = "0000"; 
		
		static{
			codeMaps.put(SUCCESS, "成功");
			codeMaps.put(FAIL, "失败");
		}

	}
