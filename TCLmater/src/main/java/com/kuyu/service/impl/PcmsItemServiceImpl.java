package com.kuyu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.pcms.*;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmBranchAdminModel;
import com.kuyu.model.TpmDeptModel;
import com.kuyu.model.UserRoleInfoModel;
import com.kuyu.model.pcms.*;
import com.kuyu.service.PcmsItemService;
import com.kuyu.service.PcmsReconciliationService;
import com.kuyu.service.TpmBranchAdminService;
import com.kuyu.service.TpmEmployeeService;
import com.kuyu.service.UserRoleInfoService;
import com.kuyu.util.PcmsProjectUtil;
import com.kuyu.util.ResultVoUtils;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.*;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

@Service
@Transactional
public class PcmsItemServiceImpl implements PcmsItemService{
	
	@Value("${settlement.url}")
    private String settlementUrl;
	
	@Value("${queryPaymentDetail.url}")
    private String queryPaymentDetailUrl;
	
	@Resource
	private PcmsProjectMapper pcmsProjectMapper;
	
	@Resource
	private PcmsItemMapper pcmsItemMapper;
	
	@Resource
	private PcmsMaterialMapper pcmsMaterialMapper;
	
	@Resource
	private PcmsOthertmMapper pcmsOthertmMapper;
	
	@Resource
	private PcmsShowcaseMapper pcmsShowcaseMapper;
	
	@Resource
	private PcmsShowcaseSourceMapper pcmsShowcaseSourceMapper;
	
	@Resource
	private PcmsMaterialSourceMapper pcmsMaterialSourceMapper;
	
	@Resource
	private PcmsOthertmSourceMapper pcmsOthertmSourceMapper;
	
	@Resource
	private PcmsItemLogMapper pcmsItemLogMapper;

	@Resource
	private PcmsTovoidItemMapper pcmsTovoidItemMapper;

	@Resource
	private PcmsRejectLogMapper pcmsRejectLogMapper;

	@Resource
	private PcmsPendingMaterialMapper pcmsPendingMaterialMapper;

	@Resource
	private PcmsMaterialImgMapper pcmsMaterialImgMapper;
	
	@Resource
	private PcmsSettlementMapper pcmsSettlementMapper;
	
	@Resource
	private PcmsSettlementItemMapper pcmsSettlementItemMapper;
	
	@Resource
	private PcmsSupplierMapper pcmsSupplierMapper;
	
	@Resource
	private PcmsPaymentMapper pcmsPaymentMapper;
	
	@Resource
	private PcmsPaymentDetailMapper pcmsPaymentDetailMapper;
	
	@Autowired
	private UserRoleInfoService userRoleInfoService;
	
	@Autowired
	private TpmBranchAdminService tpmBranchAdminService;
	
	@Autowired
	private TpmEmployeeService employeeService;
	
	@Autowired
	private PcmsPaymentCheckMapper pcmsPaymentCheckMapper;
	
	@Autowired
	private  PcmsReconciliationService pcmsReconciliationService;
	
	@Override
	public LoginUserInfo getUserInfo(String employeenumber) {
		LoginUserInfo loginUserInfo=new LoginUserInfo();
		
		List<UserRoleInfoModel> userRoleInfoModelList = userRoleInfoService.selectByPersonCode(employeenumber);
		List<TpmBranchAdminModel> tbamList = tpmBranchAdminService.selectList(new EntityWrapper<TpmBranchAdminModel>().eq("person_code",employeenumber));
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
		
		try {
			loginUserInfo.setEmployeeModel(employeeService.getTpmEmployeebyPersonCode(employeenumber));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loginUserInfo;
	}
	
	
	@Override
	public Page<ItemResult> getItemListByParam(String searchKey,Integer current,Integer size,
			String companyCode,String userRole,String deptCode,String approvalStatrTime,
			String approvalEndTime,Integer status,String personCode) {
		
		Integer linimt=(current-1)*size;  
		
		//组装参数
		HashMap<String, Object> param=new HashMap<>();
		if(companyCode!=null&&!companyCode.equals("")){
			param.put("companyCode", companyCode);
		}
		param.put("deptCode", deptCode);
//		param.put("userType", userType);
		if(searchKey!=null&&!searchKey.equals("")){
			param.put("searchKey", "%"+searchKey+"%");
		}
		param.put("linimt", linimt);
		param.put("size", size);
		if(approvalStatrTime!=null&&approvalEndTime!=null){
			param.put("approvalStatrTime", approvalStatrTime);
			param.put("approvalEndTime", approvalEndTime);
		}
		if(status!=null){
			param.put("status", status);
		}
		
		//1 admin; 2  分公司财务负责人; 0  分公司管理员 ; 6 既是分公司管理员，也是分公司财务; -1市场人员
		param.put("userRole", userRole);
		param.put("personCode", personCode);	
			
		
		
		//分页查询
		Page<ItemResult> page=new Page<>(current, size);
		List<ItemResult> result=pcmsItemMapper.getItemListByParam(param);
		Integer count=pcmsItemMapper.getItemListCountByParam(param);
		page.setRecords(result);
		page.setTotal(count);
		return page;
	}



	@Override
	public ItemDetail getItemDetailById(Integer itid,Integer type) {
		ItemDetail result=pcmsItemMapper.getItemItemDetailById(itid);
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("itid",itid);
//		List<PcmsTovoidItemModel> pcmsTovoidItem = pcmsTovoidItemMapper.selectByMap(map);
		PcmsItemLogExample example=new PcmsItemLogExample();
		example.createCriteria().andItidEqualTo(itid);
		example.setOrderByClause("create_time DESC");
		List<PcmsItemLog> logList = pcmsItemLogMapper.selectByExample(example);
//		if(pcmsTovoidItem != null && pcmsTovoidItem.size()>0){
//			result.setContext(pcmsTovoidItem.get(0).getContext());
//		}
		result.setItemLog(logList);
//		if(logList != null && logList.size()>0){
//			result.setContext(logList.get(0).getNote());
//		}

		/**上传的图片信息*/
		List<PcmsPendingMaterialModel> pcmsPendingMaterialModelList =  new ArrayList<>();
		List<PcmsPendingMaterialModel> list =  pcmsPendingMaterialMapper.selectByItid(itid);
		if(list.size()>0){
			for(PcmsPendingMaterialModel pcmsPendingMaterialModel : list){
				List<PcmsMaterialImgModel> pcmsMaterialImg = pcmsMaterialImgMapper.selectById(pcmsPendingMaterialModel.getId());
				pcmsPendingMaterialModel.setImgList(pcmsMaterialImg);
				pcmsPendingMaterialModelList.add(pcmsPendingMaterialModel);
			}
			result.setPcmsPendingMaterialModelList(pcmsPendingMaterialModelList);
		}
		// 类型1表示展台展柜，2表示其他终端，3表示广告物料
		Integer itType = result.getItType();
		if(type==1){
			if (itType == 1) {
				List<ShowcaseResult> scList = pcmsShowcaseMapper.selectByItid(itid);
				result.setScList(scList);
			}
			if (itType == 2) {
				List<OthertmResult> otList = pcmsOthertmMapper.selectByItid(itid);
				result.setOtList(otList);
			}
			if (itType == 3) {
				List<MaterialResult> mrList = pcmsMaterialMapper.selectByItid(itid);
				result.setMrList(mrList);
			}
		}else{
			if (itType == 1) {
				List<ShowcaseResult> scList = pcmsShowcaseSourceMapper.selectByItid(itid);
				result.setScList(scList);
			}
			if (itType == 2) {
				List<OthertmResult> otList = pcmsOthertmSourceMapper.selectByItid(itid);
				result.setOtList(otList);
			}
			if (itType == 3) {
				List<MaterialResult> mrList = pcmsMaterialSourceMapper.selectByItid(itid);
				result.setMrList(mrList);
			}
		}
		
		Integer status=result.getStatus();
		if(status==3||status==5){
			List<SettlementResult> settList=pcmsSettlementItemMapper.selectByItid(itid);
			result.setSettList(settList);
		}
		
		return result;
	}



	@Override
	public int editMaterial(PcmsMaterial metarial) {
		PcmsMaterialExample example=new PcmsMaterialExample();
		example.createCriteria().andMridEqualTo(metarial.getMrid());
		return pcmsMaterialMapper.updateByExampleSelective(metarial, example);
	}


	
	
	//0:未接单 1:已接单制作中 2待验收 3待结算 4已驳回 5结算中 6结算失败 7已结算 -1已作废
	@Override
//	public ResultVo changeItemStatus(Integer itid,Integer status,String reason) {
	public ResultVo changeItemStatus(Integer itid, Integer status, LoginUserInfo userInfo,String context,LoginUserInfo user) {
		
		
		//1 admin; 2  分公司财务负责人; 0  分公司管理员 ; 6 既是分公司管理员，也是分公司财务; -1市场人员
		String userRole=user.getUserRole();
				
		if(userRole==null||userRole.equals("")){
			userRole="-1";
		}
		
		PcmsItem item=pcmsItemMapper.selectByPrimaryKey(itid);
		Integer queryStatus=item.getStatus();
		
		 //增加立项单日志
	    PcmsItemLog log=new PcmsItemLog();
	    log.setItid(itid);
	    log.setStatus(status);
	    if(status==4){
	    	log.setNote("驳回原因:"+context);
	    	PcmsRejectLogModel pcmsRejectLogModel = new PcmsRejectLogModel();
	    	pcmsRejectLogModel.setContext(context);
	    	pcmsRejectLogModel.setCreate_time(new Date());
	    	pcmsRejectLogModel.setItid(itid);
	    	pcmsRejectLogModel.setOperator(userInfo.getEmployeeModel().getPerson_name());
			pcmsRejectLogMapper.insert(pcmsRejectLogModel);
	    }
	    if(status==3){
	    	log.setNote("物料已验收，费用待结算");
	    }
	    if(status==-1){
	    	if(userRole.equals("1")||userRole.equals("0")||userRole.equals("6")){
	    		log.setNote("作废原因:"+context);
	    		PcmsTovoidItemModel pcmsTovoidItemModel = new PcmsTovoidItemModel();
	    		pcmsTovoidItemModel.setContext(context);
	    		pcmsTovoidItemModel.setCreate_time(new Date());
	    		pcmsTovoidItemModel.setItid(itid);
	    		pcmsTovoidItemModel.setOperator(userInfo.getEmployeeModel().getPerson_name());
	    		pcmsTovoidItemMapper.insert(pcmsTovoidItemModel);
	    	}else{
	    		if(queryStatus==0){
	    			log.setNote("作废原因:"+context);
		    		PcmsTovoidItemModel pcmsTovoidItemModel = new PcmsTovoidItemModel();
		    		pcmsTovoidItemModel.setContext(context);
		    		pcmsTovoidItemModel.setCreate_time(new Date());
		    		pcmsTovoidItemModel.setItid(itid);
		    		pcmsTovoidItemModel.setOperator(userInfo.getEmployeeModel().getPerson_name());
		    		pcmsTovoidItemMapper.insert(pcmsTovoidItemModel);
	    		}else{
	    			return new ResultVo("-1", "没有作废权限，请联系管理员");
	    		}
	    	}
	    }
	    log.setCreateTime(new Date());
	    pcmsItemLogMapper.insertSelective(log);

		//更新为对应状态
//		PcmsItemExample example=new PcmsItemExample();
//		example.createCriteria().andItidEqualTo(itid);
//		PcmsItem item=new PcmsItem();
		item.setStatus(status);
		item.setUpdateTime(new Date());
//		int i= pcmsItemMapper.updateByExampleSelective(item, example);
		int i= pcmsItemMapper.updateByPrimaryKeySelective(item);
		if(i==1){
			return ResultVo.get(ResultVo.SUCCESS);
		}
		return ResultVo.get(ResultVo.FAIL);
	}



	@Override
	public ResultVo settlement(SettlementRequest settVo) {
		
		//结算单编号
		String settNumber=PcmsProjectUtil.creatSettNumber();
		
		//获取供应商信息
		String vendorId=settVo.getVendorId();
		PcmsSupplierModel supp=pcmsSupplierMapper.selectById(vendorId);
		String dept=settVo.getDept();
		
		//获取详细结算信息
		List<SettlementDetailRequest> settlementDetail=settVo.getItemList();
		
		//写入结算单信息
		PcmsSettlement sett=new PcmsSettlement();
		sett.setSettNumber(settNumber);
		sett.setApplicationTitle(settVo.getApplyTitle());
		sett.setCompanyCode(settVo.getCompanyCode());
		sett.setRequestUser(settVo.getRequestUser());
		sett.setDept(dept);
		sett.setApplicationNotes(settVo.getApplyNote());
		sett.setPayType(settVo.getType());
		sett.setSumMoney(settVo.getApplyMoney());
		sett.setTaxNumber(settVo.getTaxNumber());
		//结算中
		sett.setStatus(1);
		sett.setCreateTime(new Date());
//		pcmsSettlementMapper.insertSelective(sett);
		
		
		
		// 获取默认的请求客户端
		CloseableHttpClient client = HttpClients.createDefault();
		// 通过HttpPost来发送post请求
//		HttpPost httpPost = new HttpPost("http://localhost:9000/e7cctest/promotionFee/importReimburseBill.do");
		HttpPost httpPost=new HttpPost(settlementUrl);
		
		
		/*
		* post带参数开始
		*/
		// 第三步：构造list集合，往里面丢数据
				
		JSONObject param=new JSONObject();
		param.put("REQUEST_TITLE", settVo.getApplyTitle());
		param.put("COMPANY_CODE", settVo.getCompanyCode());
		param.put("CURRENCY_TYPE", "CNY");
		param.put("REQUEST_USER", settVo.getRequestUser());
		param.put("REQUEST_DEPART", settVo.getDept());
		param.put("REQUEST_MEMO", settVo.getApplyNote());
				
				
		JSONArray detailList=new JSONArray();
		
		for (SettlementDetailRequest settlement : settlementDetail) {
			
			Integer itid=settlement.getItid();
			
			PcmsItem item=pcmsItemMapper.selectByPrimaryKey(itid);
			
			
			Integer taxType=settlement.getInvoiceType();
			String taxTypeStr="PTFP";
			switch (taxType) {
			case 2:
				taxTypeStr="PTFP";
				break;
			case 3:
				taxTypeStr="PTFP01";
				break;
			case 4:
				taxTypeStr="ZZSZYFP";
				break;
			}
			
			JSONObject detail=new JSONObject();
			detail.put("DETAIL_BILL", item.getRequestId());
			detail.put("DETAIL_ID", item.getDetailId());
			detail.put("DETAIL_INVOUCETYPE", taxTypeStr);
			detail.put("DETAIL_INVOUCETNUM", settlement.getInvoiceNumber());
			detail.put("DETAIL_MONEY", settlement.getDetailMoney());
			detail.put("DETAIL_ISLAST", settlement.getIsLast()==1?"是":"否");
			detail.put("DETAIL_MEMO", settlement.getDetailMemo());
			detailList.add(detail);
			param.put("DETAIL_LIST", detailList);
			

			//写入结算单详细信息
			PcmsSettlementItem settIt=new PcmsSettlementItem();
			settIt.setItid(itid);
			settIt.setSedetailId(item.getDetailId());
			settIt.setSedetailMoney(settlement.getDetailMoney());
			settIt.setSedetailMemo(settlement.getDetailMemo());
			settIt.setTaxType(taxTypeStr);
			settIt.setIsLast(settlement.getIsLast());
			settIt.setInvoiceNumber(settlement.getInvoiceNumber());
			settIt.setTaxRate(settlement.getTaxRate()+"%");
			settIt.setSettlementNumber(settNumber);
			settIt.setCreateTime(new Date());
			pcmsSettlementItemMapper.insertSelective(settIt);
			
			
			
//			item.setStatus(5);
			item.setSubclass(PcmsProjectUtil.calculation(item.getSubclass().toString(), settlement.getDetailMoney(), 2));
			item.setUpdateTime(new Date());
			pcmsItemMapper.updateByPrimaryKeySelective(item);
			
			//增加日志
			PcmsItemLog log=new PcmsItemLog();
			log.setItid(item.getItid());
			log.setNote("立项单结算,可用余额减去:-"+settlement.getDetailMoney());
			log.setStatus(3);
			log.setCreateTime(new Date());

			pcmsItemLogMapper.insertSelective(log);
			
		}
		
				
		JSONArray paymentList=new JSONArray();
		JSONObject payment=new JSONObject();
				
		payment.put("PAYMENT_VENDORCODE", vendorId);
		payment.put("ACCOUNT_NAME", supp.getVendor_name());
		payment.put("ACCOUNT_VALUE", supp.getOpening_account());
		payment.put("PAYMENT_CURRENCY", settVo.getApplyMoney());
		payment.put("PAYMENT_METHOD", settVo.getType()==1?"电汇":"挂账不付款");
		paymentList.add(payment);
		param.put("PAYMENT_LIST", paymentList);
				
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		BasicNameValuePair basicNameValuePair = new BasicNameValuePair("requestParams", param.toString());
		list.add(basicNameValuePair);
		// 第二步：我们发现Entity是一个接口，所以只能找实现类，发现实现类又需要一个集合，集合的泛型是NameValuePair类型
		try {
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list,"UTF-8");
			// 第一步：通过setEntity 将我们的entity对象传递过去
			httpPost.setEntity(formEntity);
		} catch (UnsupportedEncodingException e1) {
			throw new ParamException("结算参数错误");
		}
		/*
		* post带参数结束
		*/
		// HttpEntity
		// 是一个中间的桥梁，在httpClient里面，是连接我们的请求与响应的一个中间桥梁，所有的请求参数都是通过HttpEntity携带过去的
		// 通过client来执行请求，获取一个响应结果
		
		
			try {
				CloseableHttpResponse response = client.execute(httpPost);
				HttpEntity entity = response.getEntity();
				String str = EntityUtils.toString(entity, "UTF-8");
				// 关闭
				response.close();
				JSONObject result=JSON.parseObject(str);
				if(result.get("RET_CODE").equals("9999")){
					String paymentNo=result.get("PAYMENT_BILL_NO").toString();
					sett.setFccsBill(paymentNo);
					pcmsSettlementMapper.insertSelective(sett);
					
					//写入付款单信息
					PcmsPayment info=new PcmsPayment();
					info.setFsscBill(paymentNo);
					info.setRequestDept(dept);
					info.setType(0);
					info.setVendorId(vendorId);
					info.setStatus(1);//结算中
					info.setCreateTime(new Date());
					pcmsPaymentMapper.insertSelective(info);
					
					return ResultVo.get(ResultVo.SUCCESS);
					
				}else{
					throw new ParamException(result.get("RET_MSG").toString());
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				throw new ParamException("连接共享失败！！！协议异常");
			} catch (IOException e) {
				e.printStackTrace();
				throw new ParamException("连接共享失败！！！IO异常");
			}
	}



	@Override
	public int settlementStatus(String settlementNumber) {
		
		PcmsSettlement record=new PcmsSettlement();
		record.setStatus(2);
		
		PcmsSettlementExample example=new PcmsSettlementExample();
		example.createCriteria().andSettNumberEqualTo(settlementNumber);
		int i=pcmsSettlementMapper.updateByExampleSelective(record, example);
		return i;
	}



	@Override
	public void updateItemStatusBystatus(String synDate) {
		//查询今天到期的立项单
		List<PcmsProject> projectList= pcmsProjectMapper.selectByEndTime(synDate);
		if(CollectionUtils.isNotEmpty(projectList)){
			for (PcmsProject project : projectList) {
				PcmsItemExample example=new PcmsItemExample();
				example.createCriteria().andRequestIdEqualTo(project.getRequestId());
				//将结算中以前的状态，都置为过期状态
				List<Integer> values=new ArrayList<>();
				values.add(0);
				values.add(1);
				values.add(2);
				values.add(3);
				values.add(4);
				example.createCriteria().andStatusIn(values);
				
				PcmsItem record=new PcmsItem();
				record.setStatus(-2);
				record.setUpdateTime(new Date());
				pcmsItemMapper.updateByExampleSelective(record, example);
				
			}
		}    
	}



	@Override
	public int createPayment(PaymentRequest payment) {
		String fsscBill=payment.getFsscBill();
		String dept=payment.getRequestDept();
		String companyCode="none";
		if(dept!=null){
			//公司代码
			TpmDeptModel deptModel=pcmsReconciliationService.selectTpmDept(dept);
			 companyCode=deptModel.getOrg_code();
		}
		
		
		
		List<Payment> paymentList=payment.getPaymentList();
		
		//查询付款单信息，如果有则更新，代表此单为付款单，没有则为余额单
		PcmsPayment info=pcmsPaymentMapper.selectByFsscBill(fsscBill);
		if(CollectionUtils.isNotEmpty(paymentList)&&paymentList.size()==1){
			for (Payment pay : paymentList) {
				if(info==null){
					PcmsPayment balance=new PcmsPayment();
					balance.setFsscBill(fsscBill);
					balance.setCompanyCode(companyCode);
					balance.setRequestDept(dept);
					balance.setVendorId(pay.getVendorId());
					balance.setType(1);//余额单
					balance.setAccountNumber(pay.getAccountNumber());
					balance.setAccountName(pay.getAccountName());
					balance.setPayeeName(pay.getPayeeName());
					balance.setPaymentType(pay.getPaymentType());
					balance.setRecommentDate(pay.getRecommentDate());
					balance.setPayAmount(pay.getPayAmount());
					balance.setPayStandard(pay.getPayStandard());
					balance.setBillExpireDate(pay.getBillExpireDate());
					balance.setBankAccountNumber(pay.getBankAccountNumber());
					balance.setStatus(2);
					balance.setCreateTime(new Date());
					pcmsPaymentMapper.insertSelective(balance);
				}else{
					info.setCompanyCode(companyCode);
					info.setRequestDept(dept);
					info.setAccountNumber(pay.getAccountNumber());
					info.setAccountName(pay.getAccountName());
					info.setPayeeName(pay.getPayeeName());
					info.setPaymentType(pay.getPaymentType());
					info.setRecommentDate(pay.getRecommentDate());
					info.setPayAmount(pay.getPayAmount());
					info.setPayStandard(pay.getPayStandard());
					info.setBillExpireDate(pay.getBillExpireDate());
					info.setBankAccountNumber(pay.getBankAccountNumber());
					info.setStatus(2);
					pcmsPaymentMapper.updateByPrimaryKeySelective(info);
				}
		
			}
			return 1;
		}
		return 0;
	}



	@Override
	public int createPaymentDetail(PaymentRequest payment) {

		if (payment != null) {

			String fsscBill = payment.getFsscBill();
			// 先删除
			PcmsPaymentDetailExample example = new PcmsPaymentDetailExample();
			example.createCriteria().andFsscBillEqualTo(fsscBill);
			pcmsPaymentDetailMapper.deleteByExample(example);

			// 写入传入的付款子单信息
			List<Financial> financialList = payment.getFinancialList();
			if (CollectionUtils.isNotEmpty(financialList)) {
				for (Financial fin : financialList) {
					PcmsPaymentDetail payDetail = new PcmsPaymentDetail();
					payDetail.setFsscBill(fsscBill);
					payDetail.setFinancialNum(fin.getFinancialNum());
					payDetail.setFinancialMoney(fin.getFinancialMoney());
					payDetail.setFinancialStatus(fin.getFinancialStatus());
					payDetail.setFinancialTime(fin.getFinancialTime());
					pcmsPaymentDetailMapper.insertSelective(payDetail);
				}

				// 汇总信息
				BigDecimal successMoney = new BigDecimal(0);
				BigDecimal failMoney = new BigDecimal(0);

				// 根据单号查询所有的付款子单
				List<PcmsPaymentDetail> detailList = pcmsPaymentDetailMapper.selectByExample(example);
				for (PcmsPaymentDetail check : detailList) {
					String financialMoney = check.getFinancialMoney();
					String financialStatus = check.getFinancialStatus();
					// 根据子单状态统计成功的金额
					if (financialStatus.equals("8")) {
						BigDecimal financialMoneyBig = new BigDecimal(financialMoney);
						successMoney = successMoney.add(financialMoneyBig);
					}
					// 根据子单状态统计终止的金额
					if (financialStatus.equals("-1")) {
						BigDecimal failMoneyBig = new BigDecimal(financialMoney);
						failMoney = failMoney.add(failMoneyBig);
					}
				}

				// 查询结算单，如果没有对应结算信息，则此单为余额单。
				// PcmsSettlement
				// sett=pcmsSettlementMapper.selectByFsscBill(fsscBill);

				// 查询付款单，根据余额更新状态
				PcmsPayment queryPayment = pcmsPaymentMapper.selectByFsscBill(fsscBill);

				// 成功支付金额+终止支付金额=报销单金额,则此报销单完结 。
				BigDecimal sumMoney = successMoney.add(failMoney);

				if (queryPayment != null) {
					BigDecimal settMoney = new BigDecimal(queryPayment.getPayAmount());
					if (sumMoney.compareTo(settMoney) == 0) {
						// 更新状态为已完结
						queryPayment.setStatus(3);
						queryPayment.setUpdateTime(new Date());
						pcmsPaymentMapper.updateByPrimaryKeySelective(queryPayment);
					}
				}
			}

			// 可结算余额信息
			List<BillAvailable> availableList = payment.getAvailableList();
			for (BillAvailable available : availableList) {
				String detailId = available.getBillDetailId();
				// 剩余可结算金额
				String availableMoney = available.getAvailableMoney();

				PcmsItem item = pcmsItemMapper.selectByDetailId(detailId);
				if (item != null) {
					PcmsItemLog log = new PcmsItemLog();
					log.setItid(item.getItid());
					if (availableMoney.equals("0")) {
						item.setStatus(5);
						log.setStatus(5);
						log.setNote("已完结");
					} else {
						log.setStatus(3);
						log.setNote("已结算，剩余可结算金额=" + availableMoney);
					}
					log.setCreateTime(new Date());
					pcmsItemLogMapper.insertSelective(log);

					item.setSubclass(availableMoney);
					item.setUpdateTime(new Date());
					pcmsItemMapper.updateByPrimaryKeySelective(item);
				}
			}

			// //付款单信息
			// List<Payment> paymentList=payment.getPaymentList();
			// for (Payment pay : paymentList) {
			// PcmsPayment info=new PcmsPayment();
			// info.setFsscBill(fsscBill);
			// info.setVendorId(pay.getVendorId());
			// info.setAccountNumber(pay.getAccountNumber());
			// info.setAccountName(pay.getAccountName());
			// info.setPayeeName(pay.getPayeeName());
			// info.setPaymentType(pay.getPaymentType());
			// info.setRecommentDate(pay.getRecommentDate());
			// info.setPayAmount(pay.getPayAmount());
			// info.setPayStandard(pay.getPayStandard());
			// info.setBillExpireDate(pay.getBillExpireDate());
			// info.setBankAccountNumber(pay.getBankAccountNumber());
			// info.setCreateTime(new Date());
			// info.setStatus(2);
			// //初始化数据设置为3
			// info.setType(3);
			//
			// pcmsPaymentMapper.insertSelective(info);
			// }

			return 1;
		}
		return 0;
	}



	@Override
	public ResultVo queryPaymentDetail(String queryDate) throws IOException {
		
		// 获取默认的请求客户端
		CloseableHttpClient client = HttpClients.createDefault();
		// 通过HttpPost来发送post请求
		HttpPost httpPost=new HttpPost(queryPaymentDetailUrl);
		
		/*
		* post带参数开始
		*/
		// 第三步：构造list集合，往里面丢数据
				
		JSONObject param=new JSONObject();
//		param.put("FSSC_BILL", fsscBill);
		param.put("FINANCIAL_DATE", queryDate);
		param.put("FROM_INDEX", 1);
		param.put("END_INDEX", 1000);
		
				
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		BasicNameValuePair basicNameValuePair = new BasicNameValuePair("requestParams", param.toString());
		list.add(basicNameValuePair);
		// 第二步：我们发现Entity是一个接口，所以只能找实现类，发现实现类又需要一个集合，集合的泛型是NameValuePair类型
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list,"UTF-8");
		// 第一步：通过setEntity 将我们的entity对象传递过去
		httpPost.setEntity(formEntity);
		/*
		* post带参数结束
		*/
		// HttpEntity
		// 是一个中间的桥梁，在httpClient里面，是连接我们的请求与响应的一个中间桥梁，所有的请求参数都是通过HttpEntity携带过去的
		// 通过client来执行请求，获取一个响应结果
		CloseableHttpResponse response = client.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String str = EntityUtils.toString(entity, "UTF-8");
		// 关闭
		response.close();
		
		JSONObject result=JSON.parseObject(str);
		
		System.out.println(result);
		
		return  ResultVo.getData("0000",result);
		
		/*
		##################调用

		public static void main(String[] args) throws Exception {
				// 获取默认的请求客户端
				CloseableHttpClient client = HttpClients.createDefault();
				// 通过HttpPost来发送post请求
				HttpPost httpPost = new HttpPost("http://localhost:9000/e7cctest/promotionFee/getBillPayStatus.do");
				JSONObject param=new JSONObject();
				param.put("FSSC_BILL", "M4301812260041");
				
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				BasicNameValuePair basicNameValuePair = new BasicNameValuePair("requestParams", param.toString());
				list.add(basicNameValuePair);
				// 第二步：我们发现Entity是一个接口，所以只能找实现类，发现实现类又需要一个集合，集合的泛型是NameValuePair类型
				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list,"UTF-8");
				// 第一步：通过setEntity 将我们的entity对象传递过去
				httpPost.setEntity(formEntity);
				
				CloseableHttpResponse response = client.execute(httpPost);
				HttpEntity entity = response.getEntity();
				String str = EntityUtils.toString(entity, "UTF-8");
				System.out.println(str);
				// 关闭
				response.close();
			}



		#############查询json结果

		{"RET_CODE":"9999","RET_MSG":"","FINANCIAL_RESULT":{"FSSC_BILL":"M4301812260041","FINANCIAL_LIST":[{"FINANCIAL_NUM":"1","FINANCIAL_MONEY":"1000","FINANCIAL_STATUS":"8","FINANCIAL_TIME":"2018-12-26 15:01:59"}]}}

		*/
		
		
	}


//
//	@Override
//	public SettlementDetailResult settlementDetail(String settNumber) {
//		
//		SettlementDetailResult result=pcmsSettlementMapper.getSettlementDetail(settNumber);
//		return  result;
//	}



	@Override
	public Page<PaymentResult> paymentList(String searchKey, Integer current, Integer size, 
			String approvalStatrTime, String approvalEndTime,Integer status,String companyCode,
			String deptCode, String personCode,String userRole) {
		
		Integer linimt=(current-1)*size;  
		
		
		//组装参数
		HashMap<String, Object> param=new HashMap<>();
		if(searchKey!=null&&!searchKey.equals("")){
			param.put("searchKey", "%"+searchKey+"%");
		}
		param.put("linimt", linimt);
		param.put("size", size);
		if(approvalStatrTime!=null&&approvalEndTime!=null){
			param.put("approvalStatrTime", approvalStatrTime);
			param.put("approvalEndTime", approvalEndTime);
		}
		if(status!=null){
			param.put("status", status);
		}
		
		if(!userRole.equals("1")){
			param.put("companyCode", companyCode);
		}
		
				
		//分页查询
		Page<PaymentResult> page=new Page<>(current, size);
		List<PaymentResult> payList=pcmsPaymentMapper.getPaymentList(param);
		Integer count=pcmsPaymentMapper.getPaymentListCount(param);
		page.setRecords(payList);
		page.setTotal(count);
		
		//统计已付金额、终止金额、失败金额
		for (PaymentResult a : payList) {
			String fsscBill=a.getFsscBill();
			PaymentResult moneyInfo=pcmsPaymentMapper.getDetailMoney(fsscBill);
			if(moneyInfo!=null){
				a.setAlreadyAmount(moneyInfo.getAlreadyAmount());
				a.setStopAmount(moneyInfo.getStopAmount());
				a.setFailAmount(moneyInfo.getFailAmount());
			}else{
				a.setAlreadyAmount("0");
				a.setStopAmount("0");
				a.setFailAmount("0");
			}
		}
		
		return page;
	}



	@Override
	public List<PcmsPaymentDetail> paymentDetail(String fsscBill) {
		PcmsPaymentDetailExample example =new PcmsPaymentDetailExample();
		example.createCriteria().andFsscBillEqualTo(fsscBill);
		pcmsPaymentDetailMapper.selectByExample(example);
		List<PcmsPaymentDetail> payDetailList=pcmsPaymentDetailMapper.selectByExample(example);
		return payDetailList;
	}



	@Override
	public int itemEnd(ItemEndRequest itemEnd) {
		if(itemEnd.getStatus().equals("完结")){
			
			//剩余可结算金额
			String availableMoney=itemEnd.getAvailableMoney();
			
			String detailId=itemEnd.getFsscBillDetail();
		
			PcmsItem item=pcmsItemMapper.selectByDetailId(detailId);
			
			//增加日志
			PcmsItemLog itlog=new PcmsItemLog();
			itlog.setCreateTime(new Date());
			itlog.setItid(item.getItid());
			if(availableMoney.equals("0")){
				item.setStatus(5);
				itlog.setStatus(5);
				itlog.setNote("已过期（完结），可用余额清空");
			}else{
				itlog.setStatus(7);
				itlog.setNote("余额变动,剩余可用金额:"+availableMoney+",可再次发起结算");
			}
			pcmsItemLogMapper.insertSelective(itlog);
			item.setSubclass(availableMoney);
			return pcmsItemMapper.updateByPrimaryKeySelective(item);
			
		}
		
		return 0;
	}



//	@Override
//	public int payEndOrTranslate(ItemEndRequest itemEnd) {
//		String fsscBill=itemEnd.getFsscBill();
//		String status=itemEnd.getStatus();
//		if(status.equals("已终止")){
//			//终止原因
//			String reson=itemEnd.getReason();
//			PcmsSettlement sett=pcmsSettlementMapper.selectByFsscBill(fsscBill);
//			PcmsSettlementItemExample example=new PcmsSettlementItemExample();
//			example.createCriteria().andSettlementNumberEqualTo(sett.getSettNumber());
//			List<PcmsSettlementItem> settList=pcmsSettlementItemMapper.selectByExample(example);
//			for (PcmsSettlementItem settIt : settList) {
//				Integer itid=settIt.getItid();
//				Date createTime=new Date();
//				
//				//更新立项单状态
//				PcmsItemExample itExample=new PcmsItemExample();
//				itExample.createCriteria().andItidEqualTo(itid);
//				
//				PcmsItem record=new PcmsItem();
//				record.setStatus(-3);
//				record.setUpdateTime(createTime);
//				pcmsItemMapper.updateByExampleSelective(record, itExample);
//				
//				//增加日志
//				PcmsItemLog itlog=new PcmsItemLog();
//				itlog.setCreateTime(createTime);
//				itlog.setItid(itid);
//				itlog.setStatus(-3);
//				itlog.setNote(reson);
//				pcmsItemLogMapper.insertSelective(itlog);
//				
//			}
//			sett.setStatus(-3);
//			return pcmsSettlementMapper.updateByPrimaryKeySelective(sett);
//			
//		}
//		if(status.equals("已唤醒")){
//			System.out.println("单号:"+itemEnd.getFsscBill()+",状态:"+itemEnd.getStatus());
//			
//			PcmsSettlement sett=pcmsSettlementMapper.selectByFsscBill(fsscBill);
//			PcmsSettlementItemExample example=new PcmsSettlementItemExample();
//			example.createCriteria().andSettlementNumberEqualTo(sett.getSettNumber());
//			List<PcmsSettlementItem> settList=pcmsSettlementItemMapper.selectByExample(example);
//			for (PcmsSettlementItem settIt : settList) {
//				Integer itid=settIt.getItid();
//				Date createTime=new Date();
//				
//				//更新立项单状态
//				PcmsItemExample itExample=new PcmsItemExample();
//				itExample.createCriteria().andItidEqualTo(itid);
//				
//				PcmsItem record=new PcmsItem();
//				record.setStatus(8);
//				record.setUpdateTime(createTime);
//				pcmsItemMapper.updateByExampleSelective(record, itExample);
//				
//				//增加日志
//				PcmsItemLog itlog=new PcmsItemLog();
//				itlog.setCreateTime(createTime);
//				itlog.setItid(itid);
//				itlog.setStatus(8);
//				itlog.setNote("已唤醒");
//				pcmsItemLogMapper.insertSelective(itlog);
//				
//			}
//			sett.setStatus(8);
//			return pcmsSettlementMapper.updateByPrimaryKeySelective(sett);
//			
//		}
//		
//		return 0;
//	}


	@Override
	public int payEndOrTranslate(ItemEndRequest itemEnd) {
		String fsscBill=itemEnd.getFsscBill();
		String status=itemEnd.getStatus();
		PcmsSettlement sett=pcmsSettlementMapper.selectByFsscBill(fsscBill);
		PcmsPayment pcmsPayment=pcmsPaymentMapper.selectByFsscBill(fsscBill);
		
		if(status.equals("已终止")){
			//终止原因
			String reason=itemEnd.getReason();
			
			//更新状态
			pcmsPayment.setStopReason(reason);
			pcmsPayment.setStatus(-3);
			pcmsPayment.setUpdateTime(new Date());
			
			//增加可用余额
			PcmsSettlementItemExample example=new PcmsSettlementItemExample();
			example.createCriteria().andSettlementNumberEqualTo(sett.getSettNumber());
			List<PcmsSettlementItem> settItemList=pcmsSettlementItemMapper.selectByExample(example);
			for (PcmsSettlementItem settItem : settItemList) {
				PcmsItem item=pcmsItemMapper.selectByPrimaryKey(settItem.getItid());
				item.setSubclass(PcmsProjectUtil.calculation(item.getSubclass(), settItem.getSedetailMoney(),1));
				pcmsItemMapper.updateByPrimaryKeySelective(item);
				
				//增加日志
				PcmsItemLog log=new PcmsItemLog();
				log.setItid(item.getItid());
				log.setNote("报销单驳回,可用余额增加:+"+settItem.getSedetailMoney());
				log.setStatus(3);
				log.setCreateTime(new Date());
				pcmsItemLogMapper.insertSelective(log);
			}
			
			return pcmsPaymentMapper.updateByPrimaryKeySelective(pcmsPayment);
		}
		if(status.equals("已唤醒")){
			pcmsPayment.setStatus(8);
			pcmsPayment.setUpdateTime(new Date());
			return pcmsPaymentMapper.updateByPrimaryKeySelective(pcmsPayment);
		}
		return 0;
	}
	
	
	

	@Override
	public void checkPaymentDetail(String synDate, int i) throws ClientProtocolException, IOException {
		
		PcmsPaymentCheckExample example=new PcmsPaymentCheckExample();
		example.createCriteria().andCheckDateEqualTo(synDate);
		int j=pcmsPaymentCheckMapper.countByExample(example);
		if(j!=0){
			throw new ParamException("该日期已经同步过数据，请勿重复同步！");
		}
		
		
		// 获取默认的请求客户端
		CloseableHttpClient client = HttpClients.createDefault();
		// 通过HttpPost来发送post请求
		HttpPost httpPost = new HttpPost(queryPaymentDetailUrl);

		/*
		 * post带参数开始
		 */
		// 第三步：构造list集合，往里面丢数据

		JSONObject param = new JSONObject();
		// param.put("FSSC_BILL", fsscBill);
		param.put("FINANCIAL_DATE", synDate);
		param.put("FROM_INDEX", 1);
		param.put("END_INDEX", 1000);

		List<NameValuePair> list = new ArrayList<NameValuePair>();
		BasicNameValuePair basicNameValuePair = new BasicNameValuePair("requestParams", param.toString());
		list.add(basicNameValuePair);
		// 第二步：我们发现Entity是一个接口，所以只能找实现类，发现实现类又需要一个集合，集合的泛型是NameValuePair类型
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list, "UTF-8");
		// 第一步：通过setEntity 将我们的entity对象传递过去
		httpPost.setEntity(formEntity);
		/*
		 * post带参数结束
		 */
		// HttpEntity
		// 是一个中间的桥梁，在httpClient里面，是连接我们的请求与响应的一个中间桥梁，所有的请求参数都是通过HttpEntity携带过去的
		// 通过client来执行请求，获取一个响应结果
		CloseableHttpResponse response = client.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String str = EntityUtils.toString(entity, "UTF-8");
		// 关闭
		response.close();

		JSONObject result = JSON.parseObject(str);
		System.out.println(result);
		
		if(result.get("RET_CODE").equals("9999")){
			
			
			String FINANCIAL_RESULT=result.get("FINANCIAL_RESULT").toString();
			List<PaymentRequest> paymentDetailList=JSON.parseArray(FINANCIAL_RESULT, PaymentRequest.class);
			for (PaymentRequest paymentDetail : paymentDetailList) {
				this.createPaymentDetail(paymentDetail);
			}
			
			PcmsPaymentCheck check = new PcmsPaymentCheck();
			check.setCheckDate(synDate);
			check.setCheckTime(new Date());
			check.setCheckType(i);
			check.setResultJson(str);
			pcmsPaymentCheckMapper.insertSelective(check);
			
		}else{
			throw new ParamException(result.get("RET_MSG").toString());
		}

	}


	@Override
	public List<PcmsPaymentCheck> payCheckList(String checkDate) {
		
		if(checkDate==null||checkDate.equals("")){
			checkDate=PcmsProjectUtil.getCheckDate();
		}else{
			checkDate=checkDate+"%";
		}
		
		return pcmsPaymentCheckMapper.payCheckList(checkDate);
	}


	@Override
	public PaymentDetail getPaymentDetail(String fsscBill) {
		
		String checkFsscBill=PcmsProjectUtil.subFsscBill(fsscBill);
		
		PaymentDetail result=new PaymentDetail();
		
		PcmsPaymentDetailExample example =new PcmsPaymentDetailExample();
		example.createCriteria().andFsscBillEqualTo(checkFsscBill);
		pcmsPaymentDetailMapper.selectByExample(example);
		List<PcmsPaymentDetail> payDetailList=pcmsPaymentDetailMapper.selectByExample(example);
		
		result.setPaymentDetailList(payDetailList);
		List<SettlementItemResult> itemList =pcmsSettlementMapper.getSettlementItemResult(checkFsscBill);
		result.setItemList(itemList);
		
		return result;
	}


	@Override
	public Set<String> initPayMent(List<PaymentRequest> initPayMent) {

		Set<String> error = new HashSet<>();

		for (PaymentRequest payment : initPayMent) {

			String fsscBill = payment.getFsscBill();
			String dept = payment.getRequestDept();
			
			//公司代码
			TpmDeptModel deptModel=pcmsReconciliationService.selectTpmDept(dept);
			String companyCode=deptModel.getOrg_code();
			
			System.out.println("初始化审批中的报销单:" + fsscBill);

			List<Payment> paymentList = payment.getPaymentList();
			List<ProjectDetailVo> detailList = payment.getDetailList();
			Integer paymentListSize=paymentList.size();
				for (int i = 0; i < paymentListSize; i++) {
					PcmsPayment balance = new PcmsPayment();
					if(paymentListSize==1){
						balance.setFsscBill(fsscBill);
					}else{
						balance.setFsscBill(fsscBill+"("+i+")");
					}
					balance.setCompanyCode(companyCode);
					balance.setRequestDept(dept);
					balance.setVendorId(paymentList.get(i).getVendorId());
					balance.setAccountNumber(paymentList.get(i).getAccountNumber());
					balance.setAccountName(paymentList.get(i).getAccountName());
					balance.setPayeeName(paymentList.get(i).getPayeeName());
					balance.setPaymentType(paymentList.get(i).getPaymentType());
					balance.setRecommentDate(paymentList.get(i).getRecommentDate());
					balance.setPayAmount(paymentList.get(i).getPayAmount());
					balance.setPayStandard(paymentList.get(i).getPayStandard());
					balance.setBillExpireDate(paymentList.get(i).getBillExpireDate());
					balance.setBankAccountNumber(paymentList.get(i).getBankAccountNumber());
					balance.setStatus(1);
					balance.setCreateTime(new Date());
					if (detailList == null || detailList.size() == 0) {
						balance.setType(1);// 余额单
					} else {
						balance.setType(0);// 付款单
					}
					pcmsPaymentMapper.insertSelective(balance);
					
				}
				
				for (ProjectDetailVo detailVo : detailList) {
					
					PcmsItem item = pcmsItemMapper.selectByDetailId(detailVo.getDetailId());
					if (item != null) {
						PcmsSettlementItem record = new PcmsSettlementItem();
						record.setFsscBill(fsscBill);
						record.setItid(item.getItid());
						record.setCreateTime(new Date());
						pcmsSettlementItemMapper.insertSelective(record);
					}else{
						error.add(fsscBill);
					}
				}

			
		}
		return error;
	}


	@Override
	public List<String> initPayMentDetail(List<PaymentRequest> initPayMent) {
		
		List<String> error = new ArrayList<>();
		
		for (PaymentRequest payment : initPayMent) {
			
			String fsscBill = payment.getFsscBill();
			String dept = payment.getRequestDept();
			
			//公司代码
			TpmDeptModel deptModel=pcmsReconciliationService.selectTpmDept(dept);
			String companyCode=deptModel.getOrg_code();
			
			System.out.println("初始化审批中的报销单:" + fsscBill);
			
			List<Payment> paymentList = payment.getPaymentList();
			List<ProjectDetailVo> detailList = payment.getDetailList();
			List<BillAvailable> availableList =payment.getAvailableList();
			List<Financial> financialList =payment.getFinancialList();
			Integer paymentListSize=0;
			if(CollectionUtils.isNotEmpty(detailList)){
				 paymentListSize=paymentList.size();
			}
			for (int i = 0; i < paymentListSize; i++) {
				PcmsPayment balance = new PcmsPayment();
				if(paymentListSize==1){
					balance.setFsscBill(fsscBill);
				}else{
					balance.setFsscBill(fsscBill+"("+i+")");
				}
				balance.setCompanyCode(companyCode);
				balance.setRequestDept(dept);
				balance.setVendorId(paymentList.get(i).getVendorId());
				balance.setAccountNumber(paymentList.get(i).getAccountNumber());
				balance.setAccountName(paymentList.get(i).getAccountName());
				balance.setPayeeName(paymentList.get(i).getPayeeName());
				balance.setPaymentType(paymentList.get(i).getPaymentType());
				balance.setRecommentDate(paymentList.get(i).getRecommentDate());
				balance.setPayAmount(paymentList.get(i).getPayAmount());
				balance.setPayStandard(paymentList.get(i).getPayStandard());
				balance.setBillExpireDate(paymentList.get(i).getBillExpireDate());
				balance.setBankAccountNumber(paymentList.get(i).getBankAccountNumber());
				balance.setStatus(2);
				balance.setCreateTime(new Date());
				if (detailList == null || detailList.size() == 0) {
					balance.setType(1);// 余额单
				} else {
					balance.setType(0);// 付款单
				}
				pcmsPaymentMapper.insertSelective(balance);
				
			}
			
			if(CollectionUtils.isNotEmpty(detailList)){
				for (ProjectDetailVo detailVo : detailList) {
					PcmsItem item = pcmsItemMapper.selectByDetailId(detailVo.getDetailId());
					if (item != null) {
						PcmsSettlementItem record = new PcmsSettlementItem();
						record.setFsscBill(fsscBill);
						record.setItid(item.getItid());
						record.setCreateTime(new Date());
						pcmsSettlementItemMapper.insertSelective(record);
					}
				}
			}
			

			
			
			if (CollectionUtils.isNotEmpty(financialList)) {
				for (Financial fin : financialList) {
					PcmsPaymentDetail payDetail = new PcmsPaymentDetail();
					payDetail.setFsscBill(fsscBill);
					payDetail.setFinancialNum(fin.getFinancialNum());
					payDetail.setFinancialMoney(fin.getFinancialMoney());
					payDetail.setFinancialStatus(fin.getFinancialStatus());
					payDetail.setFinancialTime(fin.getFinancialTime());
					pcmsPaymentDetailMapper.insertSelective(payDetail);
				}

				// 汇总信息
				BigDecimal successMoney = new BigDecimal(0);
				BigDecimal failMoney = new BigDecimal(0);

				
				PcmsPaymentDetailExample example = new PcmsPaymentDetailExample();
				example.createCriteria().andFsscBillEqualTo(fsscBill);
				
				// 根据单号查询所有的付款子单
				List<PcmsPaymentDetail> detailPayList = pcmsPaymentDetailMapper.selectByExample(example);
				for (PcmsPaymentDetail check : detailPayList) {
					String financialMoney = check.getFinancialMoney();
					String financialStatus = check.getFinancialStatus();
					// 根据子单状态统计成功的金额
					if (financialStatus.equals("8")) {
						BigDecimal financialMoneyBig = new BigDecimal(financialMoney);
						successMoney = successMoney.add(financialMoneyBig);
					}
					// 根据子单状态统计终止的金额
					if (financialStatus.equals("-1")) {
						BigDecimal failMoneyBig = new BigDecimal(financialMoney);
						failMoney = failMoney.add(failMoneyBig);
					}
				}

				// 查询付款单，根据余额更新状态
				PcmsPayment queryPayment = pcmsPaymentMapper.selectByFsscBill(fsscBill);

				// 成功支付金额+终止支付金额=报销单金额,则此报销单完结 。
				BigDecimal sumMoney = successMoney.add(failMoney);

				if (queryPayment != null) {
					BigDecimal settMoney = new BigDecimal(queryPayment.getPayAmount());
					if (sumMoney.compareTo(settMoney) == 0) {
						// 更新状态为已完结
						queryPayment.setStatus(3);
						queryPayment.setUpdateTime(new Date());
						pcmsPaymentMapper.updateByPrimaryKeySelective(queryPayment);
					}
				}
			}

			// 可结算余额信息
			for (BillAvailable available : availableList) {
				String detailId = available.getBillDetailId();
				// 剩余可结算金额
				String availableMoney = available.getAvailableMoney();

				PcmsItem item = pcmsItemMapper.selectByDetailId(detailId);
				if (item != null) {
					PcmsItemLog log = new PcmsItemLog();
					log.setItid(item.getItid());
					if (availableMoney.equals("0")) {
						item.setStatus(5);
						log.setStatus(5);
						log.setNote("已完结");
					} else {
						log.setStatus(3);
						log.setNote("已结算，剩余可结算金额=" + availableMoney);
					}
					log.setCreateTime(new Date());
					pcmsItemLogMapper.insertSelective(log);

					item.setSubclass(availableMoney);
					item.setUpdateTime(new Date());
					pcmsItemMapper.updateByPrimaryKeySelective(item);
				}
			}
			
		}
		
		return error;
	}
	
	
}
