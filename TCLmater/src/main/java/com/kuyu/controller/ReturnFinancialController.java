package com.kuyu.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Condition;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.common.CommonConstants;
import com.kuyu.model.TpmUserStatementModel;
import com.kuyu.service.TpmUserStatementService;
import com.kuyu.service.impl.TpmUserStatementServiceImpl;
import com.kuyu.task.MailService;
import com.kuyu.task.WeiXinSendMessageService;
import com.kuyu.util.ResultVoUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ActivityDetailVo;
import com.kuyu.vo.MailVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.borrow.PaymentResultData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sun.applet.Main;

/**
 * @Author jt_L
 * @Date 2017/10/25
 * @Description 财务共享回调Controller
 */
@Api(tags = "共享接口-共享财务导入付款结果")
@AOP_Controller_LOG
public class ReturnFinancialController{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TpmUserStatementService tpmUserStatementServiceImpl;

    @Autowired
    private WeiXinSendMessageService weiXinSendMessageServiceImpl;

    @Autowired
    private MailService mailService;

    /**
     * 导入付款结果
     *
     * @return
     */
    @PostMapping(value = "/out/financialpayment",produces = "application/json;charset=UTF-8")//00
    @ApiOperation(value = "共享财务导入付款结果信息")
    public String importPaymentResult(@RequestBody PaymentResultData resultData){

        log.info("付款结果回传：{}", JSON.toJSONString(resultData));
        String result_code = resultData.getResult_code();
        String payment_id = resultData.getPayment_id();
        TpmUserStatementModel userStatementModel =null;
        List<TpmUserStatementModel> list= tpmUserStatementServiceImpl.selectList(Condition.create().eq
                ("payment_id", payment_id));
        if (list !=null&& list.size()>0){
        	userStatementModel = list.get(0);
        }else{
        	log.info("付款结果回传失败,没有对应的流水号:{}",payment_id);
            return  StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_ERROR_CODE,null));
        }
        TpmUserStatementModel tpmUserStatementModel = new TpmUserStatementModel();
        if (CommonConstants.SHARE_PLATFORM_FINISH_CODE.equals(result_code)){
            tpmUserStatementModel.setPay_state(2);
            boolean ret = tpmUserStatementServiceImpl.update(tpmUserStatementModel, Condition.create().eq("payment_id", payment_id));
            if (!ret){
                log.info("数据库异常,更新结算单付款状态失败,请务必处理");
                return  StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_ERROR_CODE,null));
            }
            log.info("结算单流水号:{}，付款已成功",payment_id);
            //发送付款成功消息通知临促....
            weiXinSendMessageServiceImpl.sendMessagePaymentReturn(true,userStatementModel.getUuid());
        }else {
//            tpmUserStatementModel.setPay_state(0);
//            boolean ret = tpmUserStatementServiceImpl.update(tpmUserStatementModel, Condition.create().eq("payment_id", payment_id  ));
//            if (!ret){
//                log.info("数据库异常,更新结算单付款状态失败,请务必处理");
//                return  StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_ERROR_CODE,null));
//            }
            log.info("结算单流水号:{}，付款失败",payment_id);
            //发送付款失败消息通知临促....
            weiXinSendMessageServiceImpl.sendMessagePaymentReturn(false,userStatementModel.getUuid());
            //通过邮件发送失败信息给活动负责人和财务负责人
            MailVo mailVo = tpmUserStatementServiceImpl.findMailOfManagerAndFinacical(payment_id);
            if(mailVo.getMailOfManager()!=null&&mailVo.getMailOfFinance()!=null)
            {
//                String text = "临促："+mailVo.getUserName()+"(电话："+mailVo.getUserMobile()+")"+"因账号或其他原因支付失败，请及时处理";
                String text = mailVo.getRequestId()+"立项单下的临促----"+mailVo.getUserName()+",由于银行账号有误导致付款退回，请核实临促银行账号信息，并登录共享平台修改账号提交重新付款";
                mailService.sendEmail(mailVo.getMailOfFinance(),"临促付款失败！",text);
                mailService.sendEmail(mailVo.getMailOfManager(),"临促付款失败！",text);
            }
        }
        return StringUtil.toJsonResultVo(ResultVoUtils.toSharePlatform(CommonConstants.SHARE_PLATFORM_FINISH_CODE, null));
    }

 /*   @GetMapping(value = "/test")//00
    @ApiOperation(value = "测试发送邮件的方法")
    public ResultVo importPaymentResult() {
       MailVo mailVo = tpmUserStatementServiceImpl.findMailOfManagerAndFinacical("1000000000781744");
       ResultVo resultVo = new ResultVo("0","成功");
       resultVo.setData(mailVo);
       return resultVo;
    }*/
}
