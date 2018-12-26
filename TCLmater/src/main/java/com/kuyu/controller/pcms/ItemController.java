package com.kuyu.controller.pcms;

import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.service.PcmsItemService;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.ItemDetail;
import com.kuyu.vo.pcms.ItemResult;
import com.kuyu.vo.pcms.PaymentRequest;
import com.kuyu.vo.pcms.SettlementRequest;
import com.kuyu.vo.pcms.SettlementVo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

@AOP_Controller_LOG
@RequestMapping("/item")
public class ItemController extends BaseController{

	@Autowired
	private PcmsItemService pcmsItemService;
	
	
	/**
	 * 获取立项单列表
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation("获取立项单列表")
	@RequestMapping(value = "/list", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo itemList(HttpServletRequest request,
			@RequestParam(value = "searchKey",required=false)String searchKey,
			@RequestParam(value = "current",required=false)Integer current,
			@RequestParam(value = "size",required=false)Integer size,
			@RequestParam(value = "approvalStatrTime",required=false)String approvalStatrTime,
			@RequestParam(value = "approvalEndTime",required=false)String approvalEndTime,
			@RequestParam(value = "status",required=false)Integer status) throws Exception {
		
		if(current==null||current<=0){
			current=1;
		} 
		if(size==null||size<=0){
			 size=10;
		}
		
//		LoginUserInfo user=getUserInfo();

		
		
		//1 admin; 2  分公司财务负责人; 0  分公司管理员 ; 6 既是分公司管理员，也是分公司财务;
//		String userType=user.getUserType();
		String userType="1";
		//分公司代码和部门代码
//		TpmEmployeeModel emp=user.getEmployeeModel();
//		String companyCode=emp.getCompany();
//		String deptCode=emp.getOrg_code();
		String companyCode="";
		String deptCode="";
		
		Page<ItemResult> result =pcmsItemService.getItemListByParam(searchKey,current,size,companyCode,userType,deptCode,approvalStatrTime,approvalEndTime,status);
		
		return ResultVo.getData(ResultVo.SUCCESS, result); 
	}
	
	/**
	 * 获取立项单详情
	 * type 0 查询原始立项单详情  type 1 查询待验收立项单详情
	 * @param request
	 * @return
	 */
	@ApiOperation("获取立项单详情")
	@RequestMapping(value = "/detail", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo detail(HttpServletRequest request,
			@RequestParam(value = "itid",required=true)Integer itid,
			@RequestParam(value = "type",required=true)Integer type) {
		
		ItemDetail result =pcmsItemService.getItemDetailById(itid,type);
		
		return ResultVo.getData(ResultVo.SUCCESS, result); 
	}
	
	
	/**
	 * 更改立项单状态
	 * //0:未接单 1:已接单制作中 2待验收 3待结算 4已驳回 5结算中 6结算失败 7已结算 -1已作废
	 * @param request
	 * @return
	 */
	@ApiOperation("更改立项单状态")
	@RequestMapping(value = "/statusItem", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo statusItem(HttpServletRequest request,
			@RequestParam(value = "itid")Integer itid,
			@RequestParam(value = "status")Integer status,
			@RequestParam(value = "reason")String reason) {
											  
//		return pcmsItemService.changeItemStatus(itid,status,reason);
		return pcmsItemService.changeItemStatus(itid,status,getLoginUserInfo(),reason);
	}
	


	
	/**
	 * 立项单结算
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@ApiOperation("结算")   
	@PostMapping(value = "/settlement", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo settlement(HttpServletRequest request,
			@ApiParam(value = "结算", required = true)@RequestBody SettlementRequest param) throws ClientProtocolException, IOException {
		
//		LoginUserInfo user=getUserInfo();
		
		
		pcmsItemService.settlement(param);
			
		return ResultVo.get(ResultVo.SUCCESS);
	}
	
	
	/**
	 * 更改结算单状态
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@ApiOperation("更改结算单状态")   
	@RequestMapping(value = "/settlementStatus", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo settlementStatus(HttpServletRequest request,
			@ApiParam(value = "更改结算单状态", required = true) String settlementNumber) throws ClientProtocolException, IOException {
		
		int i=pcmsItemService.settlementStatus(settlementNumber);
		if(i==1){
			return ResultVo.get(ResultVo.SUCCESS);
		}
		
		return ResultVo.get(ResultVo.FAIL);
	}
	
	
	/**
	 * 生成付款单
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@ApiOperation("生成付款单")   
	@PostMapping(value = "/createPayment", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo createPayment(HttpServletRequest request,
			@ApiParam(value = "生成付款单", required = true) @RequestBody PaymentRequest payment)  {
		
		int i=pcmsItemService.createPayment(payment);
		if(i==1){
			return ResultVo.get(ResultVo.SUCCESS);
		}
		return ResultVo.get(ResultVo.FAIL);
	}
	
	/**
	 * 生成付款子单
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@ApiOperation("生成付款单子单")   
	@PostMapping(value = "/createPaymentDetail", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo createPaymentDetail(HttpServletRequest request,
			@ApiParam(value = "生成付款子单", required = true) @RequestBody PaymentRequest payment)  {
		
		int i=pcmsItemService.createPaymentDetail(payment);
		if(i==1){
			return ResultVo.get(ResultVo.SUCCESS);
		}
		return ResultVo.get(ResultVo.FAIL);
	}
	
	
	/**
	 * 向共享查询付款子单
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@ApiOperation("向共享查询付款子单")   
	@RequestMapping(value = "/queryPaymentDetail", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo queryPaymentDetail(HttpServletRequest request,
			@ApiParam(value = "向共享查询付款子单", required = true) String fsscBill) throws IOException  {
		
		return pcmsItemService.queryPaymentDetail(fsscBill);
	}
}
