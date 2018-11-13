package com.kuyu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.kuyu.mapper.TpmActivityMapper;
import com.kuyu.mapper.TpmFinancialMapper;
import com.kuyu.mapper.TpmUserStatementMapper;
import com.kuyu.mapper.UserRoleInfoMapper;
import com.kuyu.model.TpmFinancialModel;
import com.kuyu.model.TpmUserStatementModel;
import com.kuyu.service.TpmFinancialService;
import com.kuyu.task.FinancialSendMessageToWechatService;
import com.kuyu.vo.ActivityDetailVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.WechatSendMessageVo;

@Service
@Transactional
public class TpmFinancialServiceImpl extends ServiceImpl<TpmFinancialMapper, TpmFinancialModel>
		implements TpmFinancialService {

	@Autowired
	private TpmActivityMapper tpmActivityMapper;

	@Autowired
	private TpmUserStatementMapper tpmUserStatementMapper;

	@Autowired
	private UserRoleInfoMapper userRoleInfoMapper;

	@Autowired
	private FinancialSendMessageToWechatService financialSendMessageToWechatService;

	/**
	 * 根据财务人员编号查询所负责的所有活动
	 * 
	 * @param person_code
	 * @return
	 */
	@Override
	public Page<ActivityDetailVo> queryActivity(String person_code,String state, Integer current, Integer size) {
		Page<ActivityDetailVo> page = new Page<>(current, size);
		List<String> orgCodeLists = userRoleInfoMapper.findFinanceOrgCodeList(person_code);
		if (orgCodeLists == null || orgCodeLists.size() == 0) {
			return null;
		}
		List<ActivityDetailVo> advList = tpmActivityMapper.queryActivityDetail(orgCodeLists,state,page);
		if (advList.size() == 0 || advList == null) {
			return null;
		}
		for (ActivityDetailVo adv : advList) {
			String activity_uuid = adv.getActivityUuid();
			//int count = tpmActivityMapper.getCountOpenNumber(activity_uuid,"");
			//adv.setUser_statement_no(count);
			int userNo = tpmActivityMapper.findActivityVoById(activity_uuid).getUserNo();
			adv.setUser_no(userNo);
			Map<String, Object> au = new HashMap<>();
			au.put("activity_uuid", activity_uuid);
			List<TpmUserStatementModel> tusmList = tpmUserStatementMapper.attendanceManagement(au);
			adv.setAuditStatus(0);
			int countPass = 0;
			if (!CollectionUtils.isEmpty(tusmList)) {
				for (TpmUserStatementModel tpmUserStatementModel : tusmList) {
					if (tpmUserStatementModel.getRequest_state() == 4 || tpmUserStatementModel.getRequest_state() == 5) {
						countPass++;
					}
				}
				if (countPass == tusmList.size()) {
					adv.setAuditStatus(1);
				}
			}
			// adv.setActivityName(adv.getProjectName());
		}
		page.setRecords(advList);
		return page;
	}

	/**
	 * 财务人员审核结算单
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public ResultVo financialAudit(Map<String, Object> map) throws Exception {
		baseMapper.financialAudit(map);
		/*
		 * if(map.get("request_state").toString().equals("4")) { WechatSendMessageVo
		 * wcsmv =
		 * tpmUserStatementMapper.selectWechatStatementPassMessage(map.get("uuid").
		 * toString());
		 * financialSendMessageToWechatService.sendMessageFinancialPassed(wcsmv); } else
		 * {
		 */
		if (map.get("request_state").toString().equals("5")) {
			List<String> list = new ArrayList<>();
			String uuid = map.get("uuid").toString();
			list.add(uuid);
			List<WechatSendMessageVo> wcsmvList = tpmUserStatementMapper.selectWechatNotPassStatementMessage(list);
			if (wcsmvList != null && wcsmvList.size() > 0) {
				WechatSendMessageVo wcsmv = wcsmvList.get(0);
				// wcsmv.setBillCount(1);
				financialSendMessageToWechatService.sendMessageFinancialNotPassed(wcsmv);
			}
		}
		return ResultVo.get(ResultVo.SUCCESS);
	}

}
