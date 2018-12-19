package com.kuyu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.mapper.pcms.*;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.pcms.*;
import com.kuyu.service.PcmsItemService;
import com.kuyu.util.PcmsProjectUtil;
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
	
	
	@Override
	public Page<ItemResult> getItemListByParam(String searchKey,Integer current,Integer size,
			String companyCode,String userType,String deptCode,String approvalStatrTime,
			String approvalEndTime,Integer status) {
		
		Integer linimt=(current-1)*size;  
		
		//组装参数
		HashMap<String, Object> param=new HashMap<>();
		if(companyCode!=null&&!companyCode.equals("")){
			param.put("companyCode", companyCode);
		}
		param.put("deptCode", deptCode);
		param.put("userType", userType);
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
	public int settlement(SettlementRequest settVo) throws ClientProtocolException, IOException {
		
		//结算单编号
		String settNumber=PcmsProjectUtil.creatItemNumber();
		
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
//		sett.setSumMoney(sumMoney);
		sett.setStatus(1);
		sett.setCreateTime(new Date());
		pcmsSettlementMapper.insertSelective(sett);
		
		
		
		// 获取默认的请求客户端
		CloseableHttpClient client = HttpClients.createDefault();
		// 通过HttpPost来发送post请求
		HttpPost httpPost = new HttpPost("http://localhost:9000/e7cctest/promotionFee/importReimburseBill.do");
		
		/*
		* post带参数开始
		*/
		// 第三步：构造list集合，往里面丢数据
				
		JSONObject param=new JSONObject();
		param.put("REQUEST_TITLE", settVo.getApplyTitle());
		param.put("COMPANY_CODE", settVo.getCompanyCode());
		param.put("CURRENCY_TYPE", "币种");
		param.put("REQUEST_USER", settVo.getRequestUser());
		param.put("REQUEST_DEPART", settVo.getDept());
		param.put("REQUEST_MEMO", settVo.getApplyNote());
				
				
		JSONArray detailList=new JSONArray();
		for (SettlementDetailRequest settlement : settlementDetail) {
			
			Integer itid=settlement.getItid();
			
			PcmsItem item=pcmsItemMapper.selectByPrimaryKey(itid);
			
			JSONObject detail=new JSONObject();
			detail.put("DETAIL_BILL", item.getRequestId());
			detail.put("DETAIL_ID", item.getDetailId());
			detail.put("DETAIL_INVOUCETYPE", "");
			detail.put("DETAIL_INVOUCETNUM", "12000.00");
			detail.put("DETAIL_MONEY", "测试摘要2");
			detail.put("DETAIL_ISLAST", "测试摘要2");
			detail.put("DETAIL_MEMO", "测试摘要2");		
			detailList.add(detail);
			param.put("DETAIL_LIST", detailList);
			

			//写入结算单详细信息
			PcmsSettlementItem settIt=new PcmsSettlementItem();
			settIt.setItid(itid);
			settIt.setSedetailId(item.getDetailId());
//			settIt.setSedetailMoney(sedetailMoney);
//			settIt.setSedetailMemo(sedetailMemo);
//			settIt.setTaxType(taxType);
			pcmsSettlementItemMapper.insertSelective(settIt);
			
			
		}
		
				
		JSONArray paymentList=new JSONArray();
		JSONObject payment=new JSONObject();
				
		payment.put("PAYMENT_VENDORCODE", "0000105361");
		payment.put("ACCOUNT_NAME", "北京歌华有线电视网络股份有限公司");
		payment.put("ACCOUNT_VALUE", "0200053709022514851");
		payment.put("PAYMENT_CURRENCY", "12000.00");
		payment.put("PAYMENT_METHOD", "电汇");
		paymentList.add(payment);
		param.put("PAYMENT_LIST", paymentList);
				
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
		System.out.println(str);
		// 关闭
		response.close();
		
		
		
		
		//更新立项单状态
		PcmsItemExample example=new PcmsItemExample();
//		example.createCriteria().andItidEqualTo(itid);
		PcmsItem item=new PcmsItem();
//		item.setStatus(status);
		item.setUpdateTime(new Date());
		int i=pcmsItemMapper.updateByExampleSelective(item, example);
		
		
		return i;
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

}
