package com.kuyu.controller.pcms;

import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.common.CommonConstants;
import com.kuyu.controller.BaseController;
import com.kuyu.exception.ParamException;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmDeptModel;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.model.pcms.PcmsPaymentCheck;
import com.kuyu.model.pcms.PcmsPaymentDetail;
import com.kuyu.service.PcmsItemService;
import com.kuyu.service.PcmsReconciliationService;
import com.kuyu.util.DateUtils;
import com.kuyu.util.ResultVoUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.ItemDetail;
import com.kuyu.vo.pcms.ItemEndRequest;
import com.kuyu.vo.pcms.ItemResult;
import com.kuyu.vo.pcms.PaymentDetail;
import com.kuyu.vo.pcms.PaymentRequest;
import com.kuyu.vo.pcms.PaymentResult;
import com.kuyu.vo.pcms.SettlementDetailResult;
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

import static com.kuyu.util.DateUtils.DATE_FORMAT_DATEONLY;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@AOP_Controller_LOG
@RequestMapping("/item")
public class ItemController extends BaseController{

	@Autowired
	private PcmsItemService pcmsItemService;
	
	@Autowired
	private  PcmsReconciliationService pcmsReconciliationService;
	
	
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
			@RequestParam(value = "status",required=false)Integer status,
			@RequestParam(value = "employeenumber",required=false)String employeenumber) throws Exception {
		
		if(current==null||current<=0){
			current=1;
		} 
		if(size==null||size<=0){
			 size=10;
		}
		
		LoginUserInfo user=null;
		
		//app端获取用户登录信息
		if(employeenumber!=null&&!employeenumber.equals("")){
			 user=pcmsItemService.getUserInfo(employeenumber);
		}else{
		//PC端获取用户登录信息	
			 user=getUserInfo();
		}
		
		//如果获取不到用户登录信息
		if(user==null){
			throw new ParamException(ResultVo.getByEnumCode(CommonConstants.NOT_LOGIN_CODE));
		}
		
		
		
		//1 admin; 2  分公司财务负责人; 0  分公司管理员 ; 6 既是分公司管理员，也是分公司财务; -1市场人员
		String userRole=user.getUserRole();
		
		if(userRole==null||userRole.equals("")){
			userRole="-1";
		}
		
		
		
		//分公司代码和部门代码
		TpmEmployeeModel emp=user.getEmployeeModel();
//		String companyCode=emp.getCompany();
		String deptCode=emp.getOrg_code();
		String companyCode=null;
		if(userRole.equals("2")||userRole.equals("0")||userRole.equals("6")){
			TpmDeptModel tpmmodel=pcmsReconciliationService.selectTpmDept(deptCode);
			companyCode=tpmmodel.getOrg_code();
		}
		String personCode=emp.getPerson_code();
		
		Page<ItemResult> result =pcmsItemService.getItemListByParam(searchKey,current,size,
				companyCode,userRole,deptCode,approvalStatrTime,approvalEndTime,status,personCode);
		
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
	 * //0:未接单 1:已接单制作中 2待验收  3已验收 4已驳回 5已完结 -1已作废 -2已过期
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation("更改立项单状态")
	@RequestMapping(value = "/statusItem", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo statusItem(HttpServletRequest request,
			@RequestParam(value = "itid")Integer itid,
			@RequestParam(value = "status")Integer status,
			@RequestParam(value = "reason")String reason,
			@RequestParam(value = "employeenumber")String employeenumber) throws Exception {
		
		LoginUserInfo user=null;
		
		//app端获取用户登录信息
		if(employeenumber!=null&&!employeenumber.equals("")){
			 user=pcmsItemService.getUserInfo(employeenumber);
		}else{
		//PC端获取用户登录信息	
			 user=getUserInfo();
		}
		
		//如果获取不到用户登录信息
		if(user==null){
			throw new ParamException(ResultVo.getByEnumCode(CommonConstants.NOT_LOGIN_CODE));
		}
		
		return pcmsItemService.changeItemStatus(itid,status,user,reason,user);
	}
	


	
	/**
	 * 立项单结算
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@ApiOperation("结算")   
	@PostMapping(value = "/settlement", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo settlement(HttpServletRequest request,
			@ApiParam(value = "结算", required = true)@RequestBody SettlementRequest param)  {
		
//		LoginUserInfo user=getUserInfo();
		
		
		return pcmsItemService.settlement(param);
			
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
	public @ResponseBody String settlementStatus(HttpServletRequest request,
			@ApiParam(value = "更改结算单状态", required = true) String settlementNumber) throws ClientProtocolException, IOException {
		
		int i=pcmsItemService.settlementStatus(settlementNumber);
		if(i==1){
			return StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_FINISH_CODE, ""));
		}
		return StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_ERROR_CODE, ""));
	}
	
	
	/**
	 * 更新付款单
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@ApiOperation("更新付款单")   
	@PostMapping(value = "/createPayment", produces = "application/json;charset=utf-8")
	public @ResponseBody String createPayment(HttpServletRequest request,
			@ApiParam(value = "更新付款单", required = true) @RequestBody PaymentRequest payment)  {
		
		int i=pcmsItemService.createPayment(payment);
		if(i==1){
			return StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_FINISH_CODE, ""));
		}
		return StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_ERROR_CODE, ""));
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
	public @ResponseBody String createPaymentDetail(HttpServletRequest request,
			@ApiParam(value = "生成付款子单", required = true) @RequestBody PaymentRequest payment)  {
		
		int i=pcmsItemService.createPaymentDetail(payment);
		if(i==1){
			return StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_FINISH_CODE, ""));
		}
		return StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_ERROR_CODE, ""));
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
			@ApiParam(value = "向共享查询付款子单", required = true) String queryDate) throws IOException  {
		String synDate = DateUtils.toString(new Date(),DATE_FORMAT_DATEONLY);
		if(!queryDate.equals(synDate)){
			pcmsItemService.checkPaymentDetail(queryDate,1);
			return  ResultVo.get(ResultVo.SUCCESS);
		}
		return new ResultVo(ResultVo.FAIL, "不能同步当天的数据");
	}
	
	
//	/**
//	 * 结算单详情
//	 * @param request
//	 * @return
//	 * @throws IOException 
//	 */
//	@ApiOperation("结算单详情")   
//	@RequestMapping(value = "/settlementDetail", produces = "application/json;charset=utf-8")
//	public @ResponseBody ResultVo settlementDetail(HttpServletRequest request,
//			@ApiParam(value = "结算单详情", required = true) String settNumber)    {
//		
//		SettlementDetailResult result=pcmsItemService.settlementDetail(settNumber);
//		
//		return ResultVo.getData(ResultVo.SUCCESS, result);
//	}
	
	
	/**
	 * 付款单列表
	 * @param request
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	@ApiOperation("付款单列表")   
	@RequestMapping(value = "/paymentList", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo paymentList(HttpServletRequest request,
			@RequestParam(value = "searchKey",required=false)String searchKey,
			@RequestParam(value = "current",required=false)Integer current,
			@RequestParam(value = "size",required=false)Integer size,
			@RequestParam(value = "status",required=false)Integer status,
			@RequestParam(value = "approvalStatrTime",required=false)String approvalStatrTime,
			@RequestParam(value = "approvalEndTime",required=false)String approvalEndTime,
			@RequestParam(value = "employeenumber",required=false)String employeenumber,
			@RequestParam(value = "paymentType",required=false)String paymentType) throws Exception{
		
		//分页信息
		if(current==null||current<=0){
			current=1;
		} 
		if(size==null||size<=0){
			 size=10;
		}
		
		LoginUserInfo user=null;
		
		//app端获取用户登录信息
		if(employeenumber!=null&&!employeenumber.equals("")){
			 user=pcmsItemService.getUserInfo(employeenumber);
		}else{
		//PC端获取用户登录信息	
			 user=getUserInfo();
		}
		
		//如果获取不到用户登录信息
		if(user==null){
			throw new ParamException(ResultVo.getByEnumCode(CommonConstants.NOT_LOGIN_CODE));
		}
		
		
		
		//1 admin; 2  分公司财务负责人; 0  分公司管理员 ; 6 既是分公司管理员，也是分公司财务; -1市场人员
		String userRole=user.getUserRole();
		
		if(userRole==null||userRole.equals("")){
			userRole="-1";
		}
		
		
		//分公司代码和部门代码
		TpmEmployeeModel emp=user.getEmployeeModel();
		String deptCode=emp.getOrg_code();
		String companyCode=null;
		if(userRole.equals("2")||userRole.equals("0")||userRole.equals("6")){
			TpmDeptModel tpmmodel=pcmsReconciliationService.selectTpmDept(deptCode);
			companyCode=tpmmodel.getOrg_code();
		}
		String personCode=emp.getPerson_code();
		
		
		Page<PaymentResult> payList=pcmsItemService.paymentList(searchKey,current,size,approvalStatrTime,approvalEndTime,status,
				companyCode,deptCode,personCode,userRole,paymentType);
		
	return ResultVo.getData(ResultVo.SUCCESS, payList);
	}
	
	
	/**
	 * 付款单详情
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@ApiOperation("付款单详情")   
	@RequestMapping(value = "/paymentDetail", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo paymentDetail(HttpServletRequest request,
			@ApiParam(value = "付款单详情", required = true) String fsscBill)    {
//		List<PcmsPaymentDetail> result= pcmsItemService.paymentDetail(fsscBill);
		PaymentDetail result =pcmsItemService.getPaymentDetail(fsscBill);
		return ResultVo.getData(ResultVo.SUCCESS, result);
	}
	
	/**
	 * 报销单终止(被共享驳回) 唤醒
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@ApiOperation("报销单终止或者唤醒")   
	@PostMapping(value = "/payEndOrTranslate", produces = "application/json;charset=utf-8")
	public @ResponseBody String payEndOrTranslate(HttpServletRequest request,
			@ApiParam(value = "报销单终止或者唤醒", required = true)@RequestBody ItemEndRequest itemEnd)    {
		int i=pcmsItemService.payEndOrTranslate(itemEnd);
		if(i==1){
			return StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_FINISH_CODE, ""));
		}
		return StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_ERROR_CODE, ""));
	}
	
	
	/**
	 * 申请单完结
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@ApiOperation("申请单完结")   
	@PostMapping(value = "/itemEnd", produces = "application/json;charset=utf-8")
	public @ResponseBody String itemEnd(HttpServletRequest request,
			@ApiParam(value = "申请单完结", required = true)@RequestBody ItemEndRequest itemEnd)    {
		int i= pcmsItemService.itemEnd(itemEnd);
		if(i==1){
			return StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_FINISH_CODE, ""));
		}
		return StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_ERROR_CODE, ""));
	}
	
	
	/**
	 * 已校验付款单日期列表
	 * @param request
	 * @return
	 */
	@ApiOperation("已校验付款单日期列表")   
	@RequestMapping(value = "/payCheckList", produces = "application/json;charset=utf-8")
	public @ResponseBody ResultVo payCheckList(HttpServletRequest request,String checkDate)    {
		
		//查询指定年月的日期列表，如果没有，则查本月
		List<PcmsPaymentCheck> result= pcmsItemService.payCheckList(checkDate);
		return ResultVo.getData(ResultVo.SUCCESS, result);
	}
}
