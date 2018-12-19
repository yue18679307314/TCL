package com.kuyu.controller.pcms;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.service.PcmsItemService;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.SettlementRequest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@AOP_Controller_LOG
@RequestMapping("/settlement")
public class SettlementController extends BaseController{

	
	@Autowired
	private PcmsItemService pcmsItemService;
	
	
	
	/**
	 * 开始结算
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@ApiOperation("开始结算")   
	@PostMapping(value = "/start", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo start(HttpServletRequest request,
			@ApiParam(value = "结算", required = true)@RequestBody SettlementRequest param) throws ClientProtocolException, IOException {
		
//		LoginUserInfo user=getUserInfo();
		
		
		pcmsItemService.settlement(param);
			
		return ResultVo.get(ResultVo.SUCCESS);
	}
	
}
