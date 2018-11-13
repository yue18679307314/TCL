package com.kuyu.task;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kuyu.util.SendWeiXinTemplateMSGUtil;
import com.kuyu.vo.WechatSendMessageVo;
//@PropertySource(value = "classpath:application-dev.properties",encoding = "utf-8")
@Component
public class FinancialSendMessageToWechatService {

	@Resource
	private SendWeiXinTemplateMSGUtil sendWeiXinTemplateMSGUtil;
	/*//财务人员审核结算信息后推送
	@Value("${financial.passed.tempId}")
	private String financialPassedTempId;
	@Value("${financial.passed.headContent}")
	private String financialPassedHeadContent;
	@Value("${financial.passed.tailContent}")
	private String financialPassedTailContent;
	@Value("${financial.passed.url}")
	private String financialPassedUrl;*/
	
	//#结算单被财务驳回后推送
	@Value("${financial.notpassed.tempId}")
	private String financialNotpassedTempId;
	@Value("${financial.notpassed.headContent}")
	private String financialNotpassedHeadContent;
	@Value("${financial.notpassed.tailContent}")
	private String financialNotpassedTailContent;
	@Value("${financial.notpassed.url}")
	private String financialNotpassedUrl;
	
	/*//财务审核通过后推送给临促人员
	public void sendMessageFinancialPassed(WechatSendMessageVo wcsmv) {
		String templateId = financialPassedTempId;
		String toUserOpenid = wcsmv.getOpenid();
		String firstStr = financialPassedHeadContent+"\n\n";
		String endStr = "\n"+financialPassedTailContent+"\n";
		String[] args = {wcsmv.getActivity_name()+"\n",wcsmv.getCity()+"\n",wcsmv.getDistrict()+"\n",wcsmv.getPerson_name()+"\n",wcsmv.getActivity_time()+"\n"};
		sendWeiXinTemplateMSGUtil.sendMSG(templateId,toUserOpenid, firstStr,args,endStr);
	}*/
	//结算单被驳回提醒活动负责人
	public void sendMessageFinancialNotPassed(WechatSendMessageVo wcsmv) {
		String templateId = financialNotpassedTempId;
		String toUserOpenid = wcsmv.getOpenid();
		String firstStr = financialNotpassedHeadContent+"\n\n";
		String endStr = "\n"+financialNotpassedTailContent+"\n";
		String[] args = {wcsmv.getActivity_name()+"\n",wcsmv.getActivity_time()+"\n",wcsmv.getPerson_name()+"\n",wcsmv.getReal_salary()+"元\n"};
		sendWeiXinTemplateMSGUtil.sendMSG(templateId,toUserOpenid, firstStr,args,endStr);
	}
}
