package com.kuyu.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.kuyu.annotation.AOP_Service_LOG;
import com.kuyu.common.CommonConstants;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.PropertiesConfigMapper;
import com.kuyu.model.PropertiesConfig;
import com.kuyu.model.TpmActivityModel;
import com.kuyu.model.TpmBranchAdminModel;
import com.kuyu.model.TpmEmployeeModel;
import com.kuyu.model.TpmLoanBillModel;
import com.kuyu.model.TpmUserBaseInfoModel;
import com.kuyu.model.TpmUserStatementModel;
import com.kuyu.model.UserRoleInfoModel;
import com.kuyu.model.WeixinEmployeeBinding;
import com.kuyu.service.TpmActivityService;
import com.kuyu.service.TpmBranchAdminService;
import com.kuyu.service.TpmEmployeeService;
import com.kuyu.service.TpmUserBaseInfoService;
import com.kuyu.service.TpmUserStatementService;
import com.kuyu.service.UserRoleInfoService;
import com.kuyu.util.DateUtils;
import com.kuyu.util.HttpRequest;
import com.kuyu.util.ResultVoUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.borrow.BorrowDetialVo;
import com.kuyu.vo.borrow.BorrowMoneyVo;
import com.kuyu.vo.borrow.BorrowOrderVo;
import com.kuyu.vo.borrow.PaymentBillResultVo;
import com.kuyu.vo.borrow.PaymentBillVo;
import com.kuyu.vo.borrow.PaymentVo;

/**
 * @Author jt_L
 * @Date 2017/10/10
 * @Description 定时任务服务类
 */
@Service
public class TaskService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TpmActivityService tpmActivityServiceImpl;
    @Autowired
    private TpmUserStatementService tpmUserStatementServiceImpl;
    @Autowired
    private TpmUserBaseInfoService tpmUserBaseInfoServiceImpl;
    @Autowired
    private WeiXinSendMessageService weiXinSendMessageServiceImpl;
    @Autowired
    private PropertiesConfigMapper propertiesConfigMapper;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserRoleInfoService userRoleInfoService;
    @Autowired
    private TpmBranchAdminService tpmBranchAdminService;
    @Autowired
    private TpmEmployeeService tpmEmployeeService;

    @Value("${tpm.sharePlatform.url}")
    private String tpmUrl;

    /**
     * 导入借款单任务
     * 中午12:50，晚上23:30
     */
    @Scheduled(cron = "${cron.importBorrowCronTime1}")//中午12:50，晚上23:30
    @Scheduled(cron = "${cron.importBorrowCronTime2}")//中午12:50，晚上23:30
    @AOP_Service_LOG("导入借款单任务")
    public ResultVo importBorrowMoney() {
    	
    	List<String> l = new ArrayList<>();
    	
        log.info("*****正在导入借款单,执行时间:{}*****", DateUtils.currentTime());
        log.info("---查询要导入的立项单的结算信息---");
        List<BorrowOrderVo> borrowOrderVoList = tpmUserStatementServiceImpl.importBorrowMoneyList();
        if (CollectionUtils.isEmpty(borrowOrderVoList)) {
            log.info("当天没有申请单需要借款!!!");
            return ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_ERROR_CODE, null);
        }
        /* List<BorrowOrderVo> bovList = new ArrayList<>();
       for (BorrowOrderVo bov : borrowOrderVoList) {
		
			String cycle = bov.getSettlement_cycle();
			if("活动接受后1天付款".equals(cycle) || "活动结束后1天付款".equals(cycle)) {
				Date date = DateUtils.getDateByStr(bov.getEnd_time());
				String d = DateUtils.formatDate(date, DateUtils.DATE_FORMAT_DATEONLY);
				String today = DateUtils.today();
				if(d.compareTo(today) > 0) {
					continue;
				}
				bovList.add(bov);
			}
		}
       borrowOrderVoList = bovList;*/
        Set<String> requestSets = new HashSet<>();//申请集合
        List<String> uuidList = new ArrayList<>();//结算单集合

        //控制立项单号
        PropertiesConfig propertiesConfig = propertiesConfigMapper.selectBykey(CommonConstants.ALLOW_PROJECT_IDS_KEY);
        if (null != propertiesConfig) {
            String value = propertiesConfig.getPvalue();
            if (null != value && StringUtils.isNotEmpty(value = value.trim())) {
                String[] values = value.split(",");
                List<String> valuesList = Arrays.asList(values);
                //使用迭代器遍历借款单集合
                ListIterator<BorrowOrderVo> borrowOrderVoListIterator = borrowOrderVoList.listIterator();
                while (borrowOrderVoListIterator.hasNext()) {
                    BorrowOrderVo borrowOrderVo = borrowOrderVoListIterator.next();
                    String requestId = borrowOrderVo.getRequest_id();
                    boolean continueFlag = false;
                    if (valuesList.contains(requestId)) continueFlag = true;
                    if (!continueFlag) {
                        borrowOrderVoListIterator.remove();
                    }
                }
            }

         }
        ListIterator<BorrowOrderVo> borrowOrderVoListIterator = borrowOrderVoList.listIterator();
        while (borrowOrderVoListIterator.hasNext()){
            BorrowOrderVo borrowOrderVo = borrowOrderVoListIterator.next();
            String requestId = borrowOrderVo.getRequest_id();

            String openid = borrowOrderVo.getOpenid();
            String bank_account_verify = borrowOrderVo.getBank_account_verify();
            if ((!CommonConstants.ONE.equals(bank_account_verify))||("暂未填写".equals(borrowOrderVo.getAccount_value()))||("暂未填写".equals(borrowOrderVo.getAccount_name()))) {
                log.info("临促人员银行卡未审核或者未填写,openid为{}", openid);
                //发送银行卡审核信息给临促的活动负责人
                String manager = borrowOrderVo.getManager();
                WeixinEmployeeBinding managerBindingModel = new WeixinEmployeeBinding().selectOne(Condition
                        .create().eq("person_code", manager));
                if (null != managerBindingModel) {
                    String managerOpenid = managerBindingModel.getOpenid();
                    TpmUserBaseInfoModel userBaseInfoModel = tpmUserBaseInfoServiceImpl.selectById(openid);
                    weiXinSendMessageServiceImpl.sendMessageBankError(managerOpenid, userBaseInfoModel);
                    log.info("发送银行卡未审核消息给活动负责人成功,负责人为:{}", manager);
                } else {
                    log.info("发送银行卡未审核消息给活动负责人失败,该活动负责人{}没有绑定临促openid", manager);
                }
                borrowOrderVoListIterator.remove();
                continue;
            }
            requestSets.add(requestId);//为申请单集合添加元素
            uuidList.add(borrowOrderVo.getUser_statement_uuid());//为结算单集合添加元素
        }
        log.info("\n筛选后需要导入的借款单的申请单集合为:{}",requestSets);
        boolean globalFlag = false;//当天定时任务成功标记
        try {
			for (String requestId : requestSets) {
			    log.info("正在处理申请单号:{}", requestId);
			    boolean isRightFlag = false;//当前申请id定时任务成功标记

			    BorrowMoneyVo borrowMoneyVo = new BorrowMoneyVo();
			    List<BorrowDetialVo> borrowDetialVoList = new ArrayList<>();//借款明细区信息
			    List<PaymentVo> paymentVoList = new ArrayList<>();//付款区信息

			    int i = 0;
			    List<String> accountValueList = new ArrayList<>(); //付款账号list；
			    for (BorrowOrderVo borrowOrderVo : borrowOrderVoList) {
			        String projectId = borrowOrderVo.getProject_id();
			        if (requestId.equals(borrowOrderVo.getRequest_id())) {
			            if (++i == 1) {
			                borrowMoneyVo.setFssc_bill(requestId);//日常费用申请单单号
			                borrowMoneyVo.setRequest_depart(tpmEmployeeService.selectOne(new EntityWrapper<TpmEmployeeModel>().eq("person_code", borrowOrderVo.getRequest_user())).getOrg_code());//申请人部门
			                borrowMoneyVo.setRequest_user(borrowOrderVo.getRequest_user());//申请人
			                String lastDay = DateUtils.toString(DateUtils.getLastDayOfMonth(new Date()), DateUtils.DATE_FORMAT_DATEONLY);
			                if (DateUtils.getDateDiff(lastDay, DateUtils.currentTime()) < 10) {//离月底少于10天加10
			                    Date date = DateUtils.addDay(new Date(), 10);
			                    lastDay = DateUtils.formatDate(date, DateUtils.DATE_FORMAT_DATEONLY);
			                }
			                List<UserRoleInfoModel> userRoleInfoModelList = userRoleInfoService.selectList(Condition.create().where("org_code ={0} and roleType = 2",borrowOrderVo.getDept()));
			                if(userRoleInfoModelList!=null&&userRoleInfoModelList.size()>0){
			                	borrowMoneyVo.setRepay_rel_user(userRoleInfoModelList.get(0).getPerson_code());
			                }
			                borrowMoneyVo.setRepay_date(lastDay);//预计还款日期
			            }

			            boolean flag = true;
			            for (BorrowDetialVo borrowDetialVo : borrowDetialVoList) {
			                if (projectId.equals(borrowDetialVo.getDetail_Id())){
			                    flag = false;
			                    break;
			                }
			            }
			            if (flag){
			                //借款单明细区总额
			                double TotalSalary = tpmUserStatementServiceImpl.findTotalSalaryByProjectId(projectId);
			                //借款明细区信息
			                BorrowDetialVo borrowDetialVo = new BorrowDetialVo();
			                borrowDetialVo.setDetail_Id(projectId);
			                //实际报销金额
			                borrowDetialVo.setNatural_currency(String.valueOf(TotalSalary));
			                borrowDetialVo.setDetail_memo(CommonConstants.DETIAL_MEMO);
			                borrowDetialVoList.add(borrowDetialVo);
			            }

			            
			            
			            if(!accountValueList.contains(borrowOrderVo.getAccount_value())){
			            	//付款区信息
			            	PaymentVo paymentVo = new PaymentVo();
			            	paymentVo.setAccount_bank_name(borrowOrderVo.getAccount_bank_name());
			            	paymentVo.setAccount_name(borrowOrderVo.getAccount_name());
			            	paymentVo.setAccount_value(borrowOrderVo.getAccount_value());
			            	paymentVo.setPayment_currency(String.valueOf(borrowOrderVo.getReal_salary()));
			            	paymentVo.setPayment_date(DateUtils.today());
			            	paymentVoList.add(paymentVo);
			            	accountValueList.add(paymentVo.getAccount_value());            	
			            }else{
			            	//当一个人有多笔付款时候合并成为一个付款明细区
			            	List<PaymentVo> paymentVoListTemp = new ArrayList<>();//付款区信息
			            	for(PaymentVo paymentVo:paymentVoList){
			            		if(paymentVo.getAccount_value().equals(borrowOrderVo.getAccount_value())){
			            			paymentVo.setPayment_currency(String.valueOf(Double.valueOf(paymentVo.getPayment_currency())+borrowOrderVo.getReal_salary()));
			            			paymentVoListTemp.add(paymentVo);
			            		}else{
			            			paymentVoListTemp.add(paymentVo);                    			
			            		}
			            	}
			            	paymentVoList = paymentVoListTemp;
			            }
			            isRightFlag = true;
			        }
			    }

			    if (!isRightFlag) {
			        log.info("本次申请借款单失败!!费用申请单单号:{}", requestId);
			        continue;
			    }
			    borrowMoneyVo.setDetail_list(borrowDetialVoList);
			    borrowMoneyVo.setPayment_list(paymentVoList);

			    String jsonString = JSON.toJSONString(borrowMoneyVo);
			    log.info("*****导入借款申请单:{}查询成功!完成时间:{},数据如下*****\n{}\n", requestId,DateUtils.currentTime(),jsonString);

			    //调用共享平台接口发送借款单到共享平台
			    log.info("正在发送数据到共享平台:{}", tpmUrl);
			    String params = "interfaceNo=1&requestParams=\n" + jsonString;
			    String result = HttpRequest.sendPost(tpmUrl, params);
			    log.info("申请单:{},共享平台返回的数据为:\n{}\n", requestId,result);
			    
			    PaymentBillResultVo platformPaymentResultVo = StringUtil.getPlatformToPaymentResultVo(result);
			    if (null != platformPaymentResultVo && CommonConstants.SHARE_PLATFORM_FINISH_CODE.equals(platformPaymentResultVo.getRetCode())) {

			        List<PaymentBillVo> paymentBillVoList = platformPaymentResultVo.getPayment_bill_list();
			        Map<String,String> paymentIdMap =new HashMap<>();
			        //发送付款请求后更改临促付款状态为付款申请中
			        for (String user_uuid : uuidList) {
			        	
			            TpmUserStatementModel userStatementModel = tpmUserStatementServiceImpl.selectById(user_uuid);
			            if (null == userStatementModel) {
			                continue;
			            }
			            TpmActivityModel tpmActivityModel = tpmActivityServiceImpl.selectById(userStatementModel.getActivity_uuid());
			            if(!requestId.equals(tpmActivityModel.getRequestId())){
			                 continue;
			            }
			            String openid = userStatementModel.getOpenid();
			            TpmUserBaseInfoModel userBaseInfoModel = tpmUserBaseInfoServiceImpl.selectById(openid);
			            if (null == userBaseInfoModel) {
			                continue;
			            }
			            String account_value = userBaseInfoModel.getAccount_value();
			            if (StringUtils.isEmpty(account_value)) {
			                continue;
			            }
			            
			            String paymentId = null;
			            ListIterator<PaymentBillVo> paymentBillVoListIterator = paymentBillVoList.listIterator();
			            while (paymentBillVoListIterator.hasNext()){
			                PaymentBillVo paymentBillVo = paymentBillVoListIterator.next();
			                if (account_value.equals(paymentBillVo.getAccountValue())) {//判断银行账号是否一致
//                            double paymentCurrency = Double.valueOf(paymentBillVo.getPaymentCurrency());
//                            double real_salary = userStatementModel.getReal_salary();
//                            if (paymentCurrency == real_salary) {//金额与账号一致为一个流水单
			                        paymentId = paymentBillVo.getPaymentId();
//                                paymentBillVoListIterator.remove();
			                        break;
//                            }
			                }
			            }
			            if (StringUtils.isEmpty(paymentId)) {
			                log.info("更新临促结算支付状态异常，没找到对应的流水号，结算id：{}", user_uuid);
			                continue;
			            }
			            
			            String loadBillUuid ="";
			            Set<String> keysets = paymentIdMap.keySet();
			            //插入借款记录
			            if(!keysets.contains(paymentId)){
			                TpmLoanBillModel tpmLoanBillModel = new TpmLoanBillModel();
			                loadBillUuid = IdWorker.get32UUID();
			                tpmLoanBillModel.setLoad_bill_uuid(loadBillUuid);
			                tpmLoanBillModel.setCreate_time(DateUtils.currentTime());
			                tpmLoanBillModel.setFssc_bill(requestId);
			                String paymentBillNo = platformPaymentResultVo.getPaymentBillNo();//借款单单号
			                tpmLoanBillModel.setFssc_loan_bill_no(paymentBillNo);
			                tpmLoanBillModel.setStatus(1);
			                tpmLoanBillModel.setResultvojson(result);
			                tpmLoanBillModel.setPayment_id(paymentId);
			                tpmLoanBillModel.insert();
			                paymentIdMap.put(paymentId, loadBillUuid);
			                log.info("插入借款单记录立项申请:{},借款单id:{}", requestId, loadBillUuid);
			            }else{
			            	loadBillUuid = paymentIdMap.get(paymentId);
			            }

			            TpmUserStatementModel tpmUserStatementModel = new TpmUserStatementModel();
			            tpmUserStatementModel.setUuid(user_uuid);
			            tpmUserStatementModel.setPay_state(1);
			            tpmUserStatementModel.setLoad_bill_uuid(loadBillUuid);
			            tpmUserStatementModel.setPaymentId(paymentId);
			            tpmUserStatementModel.updateById();
			            log.info("更新临促结算支付状态成功!!结算uuid:{}，流水号：{}", user_uuid, paymentId);

			            globalFlag = true;
			        }
			    } else {
			        log.info("本次发送共享平台联调立项申请号:{},失败,发送参数:\n{}", requestId, params);
			        //发送邮件
			        List<String> uuids = new ArrayList<>();
		        	for (String user_uuid : uuidList) {
			            TpmUserStatementModel userStatementModel = tpmUserStatementServiceImpl.selectById(user_uuid);
			            if (null == userStatementModel) {
			                continue;
			            }
			            TpmActivityModel tpmActivityModel = tpmActivityServiceImpl.selectById(userStatementModel.getActivity_uuid());
			            if(!requestId.equals(tpmActivityModel.getRequestId())){
			                 continue;
			            }
			            String openid = userStatementModel.getOpenid();
			            TpmUserBaseInfoModel userBaseInfoModel = tpmUserBaseInfoServiceImpl.selectById(openid);
			            if (null == userBaseInfoModel) {
			                continue;
			            }
			            String account_value = userBaseInfoModel.getAccount_value();
			            if (StringUtils.isEmpty(account_value)) {
			                continue;
			            }
			            uuids.add(user_uuid);
		        	}
			        List<String> mailList = new ArrayList<>();//接收邮件对象：活动负责人，财务，分公司管理员，超级管理员
			        for(String uuid:uuids){
			        	TpmUserStatementModel tusm = tpmUserStatementServiceImpl.selectById(uuid);
			        	TpmActivityModel tam = tpmActivityServiceImpl.selectById(tusm.getActivity_uuid());
			        	String dept = tam.getDept();
			        	UserRoleInfoModel urim = userRoleInfoService.selectOne(new EntityWrapper<UserRoleInfoModel>().eq("org_code", dept).eq("roleType", "2"));
			        	TpmBranchAdminModel tbam = tpmBranchAdminService.selectOne(new EntityWrapper<TpmBranchAdminModel>().eq("org_code", dept));
			        	if(!mailList.contains(tam.getManager())){
			        		mailList.add(tam.getManager());
			        	}
			        	if(urim != null){
			        		if(!mailList.contains(urim.getPerson_code())){
			        			mailList.add(urim.getPerson_code());
			        		}
			        	}
			        	if(tbam != null){
				        	if(!mailList.contains(tbam.getPerson_code())){
				        		mailList.add(tbam.getPerson_code());
				        	}
			        	}
			        	//超级管理员
			        	List<UserRoleInfoModel> urimList = userRoleInfoService.selectList(new EntityWrapper<UserRoleInfoModel>().eq("roleType", "1"));
			        	for (UserRoleInfoModel rim : urimList) {
			        		if(!mailList.contains(rim.getPerson_code())){
			        			mailList.add(rim.getPerson_code());
			        		}
			        	}
			        	TpmUserBaseInfoModel tubim = tpmUserBaseInfoServiceImpl.selectById(tusm.getOpenid());
			        	if(mailList.size() > 0){
			        		for (String personCode : mailList) {
			        			TpmEmployeeModel tem = tpmEmployeeService.selectOne(new EntityWrapper<TpmEmployeeModel>().eq("person_code", personCode));
			        			if(tem != null && tem.getEmail() != null){
			        				log.info("发送邮件开始：");
		        					String content = "您好，“"+tam.getProjectName()+"活动”立项单号为："+requestId+"，临促"+tubim.getName()
		        							+tusm.getActivity_time().substring(5,7)+"月"+tusm.getActivity_time().substring(8,10)+"日"+tusm.getReal_salary()+"元的费用无法生成借款单，"
		        									+ "原因为："+platformPaymentResultVo.getRetMsg()+ ",请及时沟通处理！(这是一封系统自动发送的邮件，请勿直接回复本邮件！)";
		        					mailService.sendEmail(tem.getEmail(), "临促系统自动生成借款单失败",content);
		        					log.info("发送邮件结束！");
			        			}
			        		}
			        	}
			        }
			    }

			}
		} catch (Exception e) {
			log.info("*****导入借款单发生异常*****---->"+e.getMessage());
			e.printStackTrace();
		}

        if (!globalFlag) {
            log.info("*****本次定时任务导入借款单全部失败*****");
            throw new ParamException(ResultVo.getByEnumCode(CommonConstants.FAIL_CODE));
        }

        return ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_FINISH_CODE, null);
    }


    /*@Scheduled(cron = "${cron.paymentErrorCronTime}")//支付异常推送定时任务(早上9点 下午等4点)
    @AOP_Service_LOG("发送财务支付异常信息提醒任务")
    public void paymentErrorCronTime() {
        List<PaymentErrorVo> paymentErrorVoList = tpmUserStatementServiceImpl.findUserStatementPaymentError();
        Map<String, Integer> map = new ConcurrentHashMap<>();
        for (PaymentErrorVo vo : paymentErrorVoList) {
            String personCode = tpmActivityServiceImpl.findFinancialPersoncodeByActivityUuid(vo.getActivity_uuid());
            int errorCount = vo.getErrorCount();
            errorCount = map.containsKey(personCode) ? (errorCount + map.get(personCode)) : errorCount;
            map.put(personCode, errorCount);
        }
        for (Map.Entry<String, Integer> entrySet : map.entrySet()) {
            String key = entrySet.getKey();//personCode
            Integer value = entrySet.getValue();//count
            //发送支付异常消息推送给财务
            weiXinSendMessageServiceImpl.sendMessageFinalError(key, value);
        }

    }*/


}
