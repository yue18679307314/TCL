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
import com.kuyu.model.UserRoleInfoModel;
import com.kuyu.model.pcms.*;
import com.kuyu.service.PcmsItemService;
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
			String companyCode,String userType,String deptCode,String approvalStatrTime,
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
		
		//1 admin; 2  分公司财务负责人; 0  分公司管理员 ; 6 既是分公司管理员，也是分公司财务;
		if(userType==null||userType.equals("")){
			param.put("personCode", personCode);	
		}else if(userType.equals(1)){
			
		}
		//TODO
		
		
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
		if(status==5||status==6||status==7){
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
	public ResultVo changeItemStatus(Integer itid, Integer status, LoginUserInfo userInfo,String context) {
		//TODO
		//检测是否有权限操作
		
		
		if(status==5){
			//TODO
			//通知共享系统处理结算
		}
		
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
	    	log.setNote("作废原因:"+context);
			PcmsTovoidItemModel pcmsTovoidItemModel = new PcmsTovoidItemModel();
			pcmsTovoidItemModel.setContext(context);
			pcmsTovoidItemModel.setCreate_time(new Date());
			pcmsTovoidItemModel.setItid(itid);
			pcmsTovoidItemModel.setOperator(userInfo.getEmployeeModel().getPerson_name());
			pcmsTovoidItemMapper.insert(pcmsTovoidItemModel);
	    }
	    log.setCreateTime(new Date());
	    pcmsItemLogMapper.insertSelective(log);

		//更新为对应状态
		PcmsItemExample example=new PcmsItemExample();
		example.createCriteria().andItidEqualTo(itid);
		PcmsItem item=new PcmsItem();
		item.setStatus(status);
		item.setUpdateTime(new Date());
		int i= pcmsItemMapper.updateByExampleSelective(item, example);
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
		
		//获取详细结算信息
		List<SettlementDetailRequest> settlementDetail=settVo.getItemList();
		
		//写入结算单信息
		PcmsSettlement sett=new PcmsSettlement();
		sett.setSettNumber(settNumber);
		sett.setApplicationTitle(settVo.getApplyTitle());
		sett.setCompanyCode(settVo.getCompanyCode());
		sett.setRequestUser(settVo.getRequestUser());
		sett.setDept(settVo.getDept());
		sett.setApplicationNotes(settVo.getApplyNote());
		sett.setPayType(settVo.getType());
		sett.setSumMoney(settVo.getApplyMoney());
		sett.setTaxNumber(settVo.getTaxNumber());
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
			
			
			item.setStatus(5);
			item.setUpdateTime(new Date());
			pcmsItemMapper.updateByPrimaryKeySelective(item);
			
			
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
				return ResultVo.get(ResultVo.SUCCESS);
				
			}else{
				throw new ParamException(result.get("RET_MSG").toString());
			}
		} catch (Exception e) {
			throw new ParamException("连接共享失败！！！");
		}
		
	
	}

	
	  /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    private  String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
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
		List<Payment> paymentList=payment.getPaymentList();
		PcmsPayment info=new PcmsPayment(); 
		info.setFsscBill(fsscBill);
		if(CollectionUtils.isNotEmpty(paymentList)&&paymentList.size()==1){
			for (Payment pay : paymentList) {
				info.setVendorId(pay.getVendorId());
				info.setAccountNumber(pay.getAccountNumber());
				info.setAccountName(pay.getAccountName());
				info.setPayeeName(pay.getPayeeName());
				info.setPaymentType(pay.getPaymentType());
				info.setRecommentDate(pay.getRecommentDate());
				info.setPayAmount(pay.getPayAmount());
				info.setPayStandard(pay.getPayStandard());
				info.setBillExpireDate(pay.getBillExpireDate());
				info.setBankAccountNumber(pay.getBankAccountNumber());
				info.setCreateTime(new Date());
				
				return pcmsPaymentMapper.insertSelective(info);
			}
		}
		
		return 0;
	}



	@Override
	public int createPaymentDetail(PaymentRequest payment) {
		String fsscBill=payment.getFsscBill();
		
		//先删除
		PcmsPaymentDetailExample example =new PcmsPaymentDetailExample();
		example.createCriteria().andFsscBillEqualTo(fsscBill);
		pcmsPaymentDetailMapper.deleteByExample(example);
		
		//总金额
//		Double sumMoney=0d;
		
		List<Financial> financialList=payment.getFinancialList();
		if(CollectionUtils.isNotEmpty(financialList)){
			for (Financial fin : financialList) {
//				String status=fin.getFinancialStatus();
//				String money=fin.getFinancialMoney();
				
				PcmsPaymentDetail payDetail=new PcmsPaymentDetail();
				payDetail.setFsscBill(fsscBill);
				payDetail.setFinancialNum(fin.getFinancialNum());
				payDetail.setFinancialMoney(fin.getFinancialMoney());
				payDetail.setFinancialStatus(fin.getFinancialStatus());
				payDetail.setFinancialTime(fin.getFinancialTime());
				
//				//金额相加
//				if(status.equals("8")){
//					sumMoney+=Double.valueOf(money)+sumMoney;
//				}
				
				pcmsPaymentDetailMapper.insertSelective(payDetail);
			}
			
			
			
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



	@Override
	public SettlementDetailResult settlementDetail(String settNumber) {
		
		SettlementDetailResult result=pcmsSettlementMapper.getSettlementDetail(settNumber);
		return  result;
	}



	@Override
	public List<PaymentResult> paymentList() {
		List<PaymentResult> payList=pcmsPaymentMapper.getPaymentList();
		
		//统计已付金额、终止金额、失败金额
		for (PaymentResult a : payList) {
			String fsscBill=a.getFsscBill();
			PaymentResult moneyInfo=pcmsPaymentMapper.getDetailMoney(fsscBill);
			a.setAlreadyAmount(moneyInfo.getAlreadyAmount());
			a.setStopAmount(moneyInfo.getStopAmount());
			a.setFailAmount(moneyInfo.getFailAmount());
		}
		
		return payList;
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
		if(itemEnd.getStatus().equals("已完结")){
			String detailId=itemEnd.getFsscBillDetail();
			PcmsItemExample example=new PcmsItemExample();
			example.createCriteria().andDetailIdEqualTo(detailId);
			
			PcmsItem record=new PcmsItem();
			record.setStatus(7);
			pcmsItemMapper.updateByExampleSelective(record, example);
			
			return 1;
		}
		
		return 0;
	}



	@Override
	public int payEndOrTranslate(ItemEndRequest itemEnd) {
		String fsscBill=itemEnd.getFsscBill();
		String status=itemEnd.getStatus();
		if(status.equals("已终止")){
			//终止原因
			String reson=itemEnd.getReason();
			PcmsSettlement sett=pcmsSettlementMapper.selectByFsscBill(fsscBill);
			PcmsSettlementItemExample example=new PcmsSettlementItemExample();
			example.createCriteria().andSettlementNumberEqualTo(sett.getSettNumber());
			List<PcmsSettlementItem> settList=pcmsSettlementItemMapper.selectByExample(example);
			for (PcmsSettlementItem settIt : settList) {
				Integer itid=settIt.getItid();
				Date createTime=new Date();
				
				//更新立项单状态
				PcmsItemExample itExample=new PcmsItemExample();
				itExample.createCriteria().andItidEqualTo(itid);
				
				PcmsItem record=new PcmsItem();
				record.setStatus(-2);
				record.setUpdateTime(createTime);
				pcmsItemMapper.updateByExampleSelective(record, itExample);
				
				//增加日志
				PcmsItemLog itlog=new PcmsItemLog();
				itlog.setCreateTime(createTime);
				itlog.setItid(itid);
				itlog.setStatus(-1);
				itlog.setNote(reson);
				pcmsItemLogMapper.insertSelective(itlog);
				
			}
			return 1;
		}
		if(status.equals("已唤醒")){
			//TODO
			System.out.println("单号:"+itemEnd.getFsscBill()+",状态:"+itemEnd.getStatus());
			
			return 1;
		}
		
		
		return 0;
	}

	
}
