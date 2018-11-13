package com.kuyu.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kuyu.mapper.TpmUserStatementMapper;
import com.kuyu.util.SendWeiXinTemplateMSGUtil;
import com.kuyu.vo.WechatSendMessageVo;

@Service
public class WechatTimedReminderService {

	/*@Resource
	private SendWeiXinTemplateMSGUtil sendWeiXinTemplateMSGUtil;

	@Resource
	private TpmUserStatementMapper tpmUserStatementMapper;

	// 临促考勤签到，签退提醒，固定时间推送 早上8点 晚上6点
	@Value("${attendance.tempId}")
	private String attendanceTempId;
	@Value("${attendance.headContent}")
	private String attendanceHeadContent;
	@Value("${attendance.tailContent}")
	private String attendanceTailContent;
	@Value("${attendance.url}")
	private String attendanceUrl;
	// @Value("${attendance.time}")
	// private String attendanceTime;

	// 待审核活动考勤信息提醒,固定时间按活动推送 晚上7点
	@Value("${attendanceManagement.tempId}")
	private String attendanceManagementTempId;
	@Value("${attendanceManagement.headContent}")
	private String attendanceManagementHeadContent;
	@Value("${attendanceManagement.tailContent}")
	private String attendanceManagementTailContent;
	@Value("${attendanceManagement.url}")
	private String attendanceManagementUrl;
	// @Value("${attendanceManagement.time}")
	// private String attendanceManagementTime;

	// 待审核活动结算信息提醒,固定时间按活动推送 活动结束后次日早上8点
	@Value("${settlementManagement.tempId}")
	private String settlementManagementTempId;
	@Value("${settlementManagement.headContent}")
	private String settlementManagementHeadContent;
	@Value("${settlementManagement.tailContent}")
	private String settlementManagementTailContent;
	@Value("${settlementManagement.url}")
	private String settlementManagementUrl;
	// @Value("${settlementManagement.time}")
	// private String settlementManagementTime;

	// 财务待审核结算单提醒,固定时间按活动推送
	@Value("${financialManagement.tempId}")
	private String financialManagementTempId;
	@Value("${financialManagement.headContent}")
	private String financialManagementHeadContent;
	@Value("${financialManagement.tailContent}")
	private String financialManagementTailContent;
	@Value("${financialManagement.url}")
	private String financialManagementUrl;
	// @Value("${financialManagement.time}")
	// private String financialManagementTime;
*/
	/*// 临促考勤签到，签退提醒，固定时间推送 早上8点 晚上6点
	@Scheduled(cron = "${attendance.time}")
	public void attendanceRemindTask() {
		List<WechatSendMessageVo> wcsmvList = tpmUserStatementMapper.attendanceRemindTask();
		for (WechatSendMessageVo wcsmv : wcsmvList) {
			String templateId = attendanceTempId;
			String toUserOpenid = wcsmv.getOpenid();
			String firstStr = attendanceHeadContent + "\n\n";
			String endStr = "\n" + attendanceTailContent + "\n";
			String[] args = { wcsmv.getActivity_name() + "\n", wcsmv.getCity() + "\n", wcsmv.getDistrict() + "\n",
					wcsmv.getPerson_name() + "\n", wcsmv.getActivity_time() + "\n" };
			sendWeiXinTemplateMSGUtil.sendMSG(templateId, toUserOpenid, firstStr, args, endStr);
		}
	}*/

	/*// 待审核活动考勤信息提醒,固定时间按活动推送 晚上7点
	@Scheduled(cron = "${attendanceManagement.time}")
	public void attendanceManagementTask() {
		List<WechatSendMessageVo> wcsmvList = tpmUserStatementMapper.attendanceManagementTask();
		Map<String, WechatSendMessageVo> map = new HashMap<>();
		for (WechatSendMessageVo wcsmv : wcsmvList) {
			if (map.get(wcsmv.getOpenid()) != null) {
				String aname = map.get(wcsmv.getOpenid()).getActivity_name();
				String[] anames = aname.split(";");
				for (String string : anames) {
					if (string.equals(wcsmv.getActivity_name()))
						continue;
					aname += ";" + wcsmv.getActivity_name();
					wcsmv.setActivity_name(aname);
				}
			}
			map.put(wcsmv.getOpenid(), wcsmv);
		}
		for (String key : map.keySet()) {
			WechatSendMessageVo wcsmv = map.get(key);
			String templateId = attendanceManagementTempId;
			String toUserOpenid = wcsmv.getOpenid();
			String firstStr = attendanceManagementHeadContent + "\n\n";
			String endStr = "\n" + attendanceManagementTailContent + "\n";
			String[] args = { wcsmv.getActivity_name() + "\n", wcsmv.getCity() + "\n", wcsmv.getDistrict() + "\n",
					wcsmv.getPerson_name() + "\n", wcsmv.getActivity_time() + "\n" };
			sendWeiXinTemplateMSGUtil.sendMSG(templateId, toUserOpenid, firstStr, args, endStr);
		}
	}*/

	/*// 待审核活动结算信息提醒,固定时间按活动推送 活动结束后次日早上8点
	@Scheduled(cron = "${settlementManagement.time}")
	public void settlementManagementTask() {
		List<WechatSendMessageVo> wcsmvList = tpmUserStatementMapper.settlementManagementTask();
		Map<String, WechatSendMessageVo> map = new HashMap<>();
		for (WechatSendMessageVo wcsmv : wcsmvList) {
			if (map.get(wcsmv.getOpenid()) != null) {
				String aname = map.get(wcsmv.getOpenid()).getActivity_name();
				String[] anames = aname.split(";");
				for (String string : anames) {
					if (string.equals(wcsmv.getActivity_name()))
						continue;
					aname += ";" + wcsmv.getActivity_name();
					wcsmv.setActivity_name(aname);
				}
			}
			map.put(wcsmv.getOpenid(), wcsmv);
		}
		for (String key : map.keySet()) {
			WechatSendMessageVo wcsmv = map.get(key);
			String templateId = attendanceManagementTempId;
			String toUserOpenid = wcsmv.getOpenid();
			String firstStr = attendanceManagementHeadContent + "\n\n";
			String endStr = "\n" + attendanceManagementTailContent + "\n";
			String[] args = { wcsmv.getActivity_name() + "\n", wcsmv.getCity() + "\n", wcsmv.getDistrict() + "\n",
					wcsmv.getPerson_name() + "\n", wcsmv.getActivity_time() + "\n" };
			sendWeiXinTemplateMSGUtil.sendMSG(templateId, toUserOpenid, firstStr, args, endStr);
		}
	}*/

	/*// 财务待审核结算单提醒,固定时间按活动推送
	@Scheduled(cron = "${financialManagement.time}")
	public void financialManagementTask() {
		List<WechatSendMessageVo> wcsmvList = tpmUserStatementMapper.financialManagementTask();
		Map<Map<String, String>, WechatSendMessageVo> map = new HashMap<>();
		for (WechatSendMessageVo wcsmv : wcsmvList) {
			Map<String, String> opavmap = new HashMap<>();
			opavmap.put(wcsmv.getOpenid(), wcsmv.getActivity_uuid());
			wcsmv.setBillCount(1);
			if (map.get(opavmap) != null) {
				wcsmv.setBillCount(map.get(opavmap).getBillCount() + 1);
			}
			map.put(opavmap, wcsmv);
		}
		for (Map.Entry<Map<String, String>, WechatSendMessageVo> entry : map.entrySet()) {
			WechatSendMessageVo wcsmv = entry.getValue();
			String templateId = financialManagementTempId;
			String toUserOpenid = wcsmv.getOpenid();
			String firstStr = financialManagementHeadContent + "\n\n";
			String endStr = "\n" + financialManagementTailContent + "\n";
			String[] args = { wcsmv.getActivity_name() + "\n", wcsmv.getCity() + "\n", wcsmv.getDistrict() + "\n",
					wcsmv.getPerson_name() + "\n", wcsmv.getBillCount() + "张\n" };
			sendWeiXinTemplateMSGUtil.sendMSG(templateId, toUserOpenid, firstStr, args, endStr);
		}
	}*/
}
