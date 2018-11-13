package com.kuyu.task;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.kuyu.annotation.AOP_Service_LOG;
import com.kuyu.mapper.WeixinEmployeeBindingMapper;
import com.kuyu.model.*;
import com.kuyu.service.TpmActivityService;
import com.kuyu.service.TpmUserBaseInfoService;
import com.kuyu.service.TpmUserStatementService;
import com.kuyu.util.SendWeiXinTemplateMSGUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author jt_L
 * @Date 2017/10/29
 * @Description 微信消息发送服务接口
 */
@Service
@Async
public class WeiXinSendMessageService{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SendWeiXinTemplateMSGUtil sendWeiXinTemplateMSGUtil;

    @Autowired
    private TpmUserStatementService tpmUserStatementServiceImpl;

    @Autowired
    private TpmActivityService tpmActivityServiceImpl;

    @Autowired
    private TpmUserBaseInfoService tpmUserBaseInfoServiceImpl;

    @Autowired
    private WeixinEmployeeBindingMapper weixinEmployeeBindingMapper;
    /**
     * 结算银行卡未维护userStatement.error.tempId
     */
    @Value("${userStatement.error.tempId}")
    private String userStatementTempId;
    @Value("${userStatement.error.bankAccount.headContent}")
    private String userStatementHeadContent;
    @Value("${userStatement.error.bankAccount.tailContent}")
    private String userStatementTailContent;

    /**
     * 财务共享回传支付结果resultPayment.tempId
     */
    @Value("${resultPayment.tempId}")
    private String resultPaymentTempId;
    @Value("${resultPayment.headContent.success}")
    private String resultPaymentSuccessHeadContent;
    @Value("${resultPayment.headContent.fail}")
    private String resultPaymentFailHeadContent;
    @Value("${resultPayment.tailContent}")
    private String resultPaymentTailContent;

    /*@Value("${activity.join.tempId}")
    private String activityJoinTempId;
    @Value("${activity.join.headContent.success}")
    private String activityJoinSuccessHeadContent;
    @Value("${activity.join.headContent.fail}")
    private String activityJoinFailHeadContent;*/

  /*  @Value("${userPayment.error.tempId}")
    private String userPaymentTempId;
    @Value("${userPayment.error.headContent}")
    private String userPaymentHeadContent;
    @Value("${userPayment.error.tailContent}")
    private String userPaymentContent;*/


    /**
     * 活动申请审核
     */
 /*   @Value("${activity.join.headContent.success}")
    private String activitySuccessHeadContent;
*/
    /**
     *  在向财务共享发借款单之前做判断 如果没有银行卡信息，则不创建借款单，通知发送银行卡未维护提醒给业务负责人
     * @return
     */
    @AOP_Service_LOG("发送银行卡未维护推送")
    public void sendMessageBankError(String managerOpenid,TpmUserBaseInfoModel model){
        if (null == model){
            log.info("临促信息不存在,发送银行卡未维护推送失败!!!");
            return;
        }
        String openid = managerOpenid;
        String userName = model.getName();
        if (StringUtils.isEmpty(userName)){
            log.info("临促{}名称不存在,发送银行卡未维护推送失败!!!",openid);
            return;
        }

        String firstStr = userStatementHeadContent.replace("{0}",userName)+"\n\n";
        String[] args = new String[2];
        args[0] = userName+"\n";
        args[1] = "1张\n\n";
        String endStr = userStatementTailContent;

//        openid = "oPB6kt9CDybH7rVXBq_HnUnX_HW8";
        boolean ret = sendWeiXinTemplateMSGUtil.sendMSG(userStatementTempId, openid, firstStr, args, endStr);
        boolean ret2=false;
        if (null!=model.getOpenid()) {
           ret2 = sendWeiXinTemplateMSGUtil.sendMSG(userStatementTempId, model.getOpenid(), firstStr, args, endStr);
        }
        log.info("给活动负责人发送银行卡未维护提醒结果:{}",ret);
        log.info("给临促发送银行卡未维护提醒结果:{}",ret2);
    }

    /**
     *  财务共享回传支付结果后推送给临促
     * @return
     */
    @AOP_Service_LOG("财务共享回传支付结果后推送")
    public void sendMessagePaymentReturn(boolean isSuccess,String payment_id){
        //查询结算信息
        TpmUserStatementModel tpmUserStatementModel = tpmUserStatementServiceImpl.selectById(payment_id);
        if (null == tpmUserStatementModel){
            log.info("结算单{}不存在,发送财务共享回传支付结果后推送失败!!!",payment_id);
            return;
        }
        //查询临促信息
        TpmUserBaseInfoModel model = new TpmUserBaseInfoModel().selectById(tpmUserStatementModel.getOpenid());
        if (null == tpmUserStatementModel){
            log.info("临促信息{}不存在,发送财务共享回传支付结果后推送失败!!!",tpmUserStatementModel.getOpenid());
            return;
        }
        String openid = model.getOpenid();
        //查询活动信息
        TpmActivityModel activityModel = new TpmActivityModel().selectById(tpmUserStatementModel.getActivity_uuid());
        if (null == activityModel){
            log.info("活动信息{}不存在,发送财务共享回传支付结果后推送失败!!!",tpmUserStatementModel.getActivity_uuid());
            return;
        }
        //查询项目名称
        String activityName = activityModel.getProjectName();
        if (StringUtils.isEmpty(activityName)){
            log.info("活动{}项目名称信息不存在,发送财务共享回传支付结果后推送失败!!!",tpmUserStatementModel.getActivity_uuid());
            return;
        }
        String dateTime;
        Double real_salary;
        try{
            dateTime = tpmUserStatementModel.getWork_start_time().substring(0,10);
            real_salary = tpmUserStatementModel.getReal_salary();
        }catch (Exception e){
            log.info("数据异常,结算单:{},发送财务共享回传支付结果后推送失败!!!",payment_id);
            return;
        }

        String firstStr;
        if (isSuccess){
            firstStr = resultPaymentSuccessHeadContent+"\n\n";
        }else {
            //String account_value = model.getAccount_value();
            //firstStr = resultPaymentFailHeadContent.replace("{0}",account_value.substring(account_value.length()-4))+"\n\n";
            firstStr = resultPaymentFailHeadContent+"\n\n";
        }
        String[] args = new String[3];
        args[0] = activityName+"\n";
        args[1] = dateTime+"\n";
        args[2] = real_salary+"元\n\n";
        String endStr = resultPaymentTailContent;

//        openid = "oPB6kt9CDybH7rVXBq_HnUnX_HW8";
        boolean ret = sendWeiXinTemplateMSGUtil.sendMSG(resultPaymentTempId, openid, firstStr, args, endStr);
        log.info("发送财务共享回传支付结果:{}",ret);
    }

    /**
     *  加入活动申请
     * @return
     */
    /*@AOP_Service_LOG("加入活动申请")
    public void sendMessageActivityJoin(boolean isSuccess, String activityUuid, String openid){

        TpmActivityModel activityModel = tpmActivityServiceImpl.selectById(activityUuid);
        if (null == activityModel){
            log.info("活动信息{}不存在,发送加入活动申请推送失败!!!",activityUuid);
            return;
        }
       *//* TpmActivityOriginalModel tpmActivityOriginalModel = new TpmActivityOriginalModel().selectById(activityModel
                .getProjectId());
        if (null == tpmActivityOriginalModel){
            log.info("活动元数据信息{}不存在,发送加入活动申请推送失败!!!",activityUuid);
            return;
        }*//*
        String projectName = activityModel.getProjectName();
        if (StringUtils.isEmpty(projectName)){
            log.info("活动名称{}不存在,发送加入活动申请推送失败!!!",activityUuid);
            return;
        }
        TpmUserBaseInfoModel userBaseInfoModel = tpmUserBaseInfoServiceImpl.selectById(openid);
        if (null == userBaseInfoModel){
            log.info("用户信息{}不存在,发送加入活动申请推送失败!!!",openid);
            return;
        }
        String userName = userBaseInfoModel.getName();
        String mobile = userBaseInfoModel.getMobile();
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(mobile)){
            log.info("数据异常,活动:{},openid:{},发送加入活动申请推送失败!!!",activityUuid,openid);
            return;
        }
        String firstStr;
        if (isSuccess){
            firstStr = activityJoinSuccessHeadContent.replace("{0}",projectName)+"\n\n";
        }else {
            firstStr = activityJoinFailHeadContent.replace("{0}",projectName)+"\n\n";
        }
        String[] args = new String[2];
        args[0] = userName+"\n";
        args[1] = mobile+"\n";

//        openid = "oPB6kt9CDybH7rVXBq_HnUnX_HW8";
        boolean ret = sendWeiXinTemplateMSGUtil.sendMSG(activityJoinTempId, openid, firstStr, args, null);
        log.info("发送加入活动申请结果:{}",ret);
    }*/


  /*  *//**
     * 支付异常信息提醒财务
     * @param personCode
     * @param errorCount
     *//*
    @AOP_Service_LOG("支付异常信息提醒财务")
    public void sendMessageFinalError(String personCode, int errorCount){
        WeixinEmployeeBinding weixinEmployeeBinding = weixinEmployeeBindingMapper.selectByCode(personCode);
        if (null == weixinEmployeeBinding){
            log.info("绑定员工openid数据异常,员工:{},发送支付异常信息提醒财务推送失败!!!",personCode);
            return;
        }
        String openid = weixinEmployeeBinding.getOpenid();
        TpmUserBaseInfoModel userBaseInfoModel = tpmUserBaseInfoServiceImpl.selectById(openid);
        if (null == userBaseInfoModel){
            log.info("员工信息数据异常,openid:{},发送支付异常信息提醒财务推送失败!!!",openid);
            return;
        }
        String userName = userBaseInfoModel.getName();

        String firstStr = userPaymentHeadContent.replace("{0}", String.valueOf(errorCount)) + "\n\n";

        String[] args = new String[2];
        args[0] = userName + "\n";
        args[1] = errorCount + "\n";

//        openid = "oPB6kt9CDybH7rVXBq_HnUnX_HW8";
        boolean ret = sendWeiXinTemplateMSGUtil.sendMSG(userPaymentTempId, openid, firstStr, args, null);
        log.info("发送支付异常信息提醒财务结果:{}", ret);

    }*/
}
