package com.kuyu.mapper;

import java.util.List;
import java.util.Map;

import com.kuyu.vo.*;
import com.kuyu.vo.borrow.BorrowOrderVo;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.TpmUserStatementModel;
import com.kuyu.vo.ActivityVo;
import com.kuyu.vo.AttendanceManagementVo;
import com.kuyu.vo.SettlementManagementVo;
import com.kuyu.vo.StatementDetailVo;
import com.kuyu.vo.WechatSendMessageVo;
import com.kuyu.vo.query.AttendanceManagementCheckQuery;
import com.kuyu.vo.query.AttendanceManagementQuery;
import com.kuyu.vo.query.SettlementManagementCheckQuery;
import com.kuyu.vo.query.SettlementManagementQuery;

public interface TpmUserStatementMapper extends BaseMapper<TpmUserStatementModel> {

	public TpmUserStatementModel queryUserStatement(TpmUserStatementModel tpmUserStatementModel);

	public List<TpmUserStatementModel> queryRecords(Map<String, Object> map);

	public Integer insertRecords(TpmUserStatementModel tpmUserStatementModel);

	public Integer updateRecords(TpmUserStatementModel tpmUserStatementModel);

	List<ActivityVo> findUserStatementActivityListByOpenid(String openId);

	double findSumRealSalaryByActivityUuid(@Param("openid") String openId, @Param("activityUuid") String activityUuid);

	List<StatementDetailVo> findStatementActivityDetailList(@Param("activityUuid") String activityUuid,
			@Param("openid") String openid);
	
	public List<TpmUserStatementModel>  attendanceManagement(Map<String,Object> map);
	
	public List<TpmUserStatementModel>  attendanceManagementBypage(Map<String,Object> map,Page<TpmUserStatementModel> page);
	
	public Integer adjustWorkingHours(TpmUserStatementModel tpmUserStatementModel);
	
	public Integer adjustMoney(TpmUserStatementModel tpmUserStatementModel);
	
	public List<AttendanceManagementVo>  queryAttendanceManagement(AttendanceManagementQuery amq,Page<AttendanceManagementVo> page);
	
	public List<AttendanceManagementVo>  queryAttendanceManagement(AttendanceManagementQuery amq);
	
	public Integer checkAttendanceManagement(AttendanceManagementCheckQuery amcq);
	
	public TpmUserStatementModel queryAttendanceDetails(String uuid);
	
	public List<SettlementManagementVo>  querySettlementManagement(SettlementManagementQuery smq,Page<SettlementManagementVo> page);
	
	public List<SettlementManagementVo>  querySettlementManagement(SettlementManagementQuery smq);
	
	public Integer checkSettlementManagement(SettlementManagementCheckQuery smcq);
	
	public SettlementManagementVo querySettlementDetails(String uuid);

	public WechatSendMessageVo selectWechatStatementPassMessage(String uuid);
	
	public List<WechatSendMessageVo> selectWechatNotPassStatementMessage(List<String> list);
	
	public List<WechatSendMessageVo> attendanceManagementTask();
	
	public List<WechatSendMessageVo> settlementManagementTask();
	
	public List<WechatSendMessageVo> attendanceRemindTask();
	
	public List<WechatSendMessageVo> financialManagementTask();

    List<PaymentErrorVo> findUserStatementPaymentError();

	double findRealSalaryByProjectId(String projectId);

	List<TpmUserStatementModel> findActivityUuidByProjectId(String projectId);
	
	List<TpmUserStatementModel> selectTusmByUuids(List<String> list);

    List<BorrowOrderVo> importBorrowMoneyList();

	double findTotalSalaryByProjectId(String projectId);

	public Double getIncentiveCount(String activity_uuid);
	
	public Integer selectFinancialToDoNo(String personCode);
	
	public Integer selectSettleMentToDoNo(@Param("personCode")String personCode,@Param("isAttendence")Integer isAttendence);
	
	public Integer selectActivityToDoNo(String personCode);
	
	public List<PaymentDataVo> getPaymentList();
	
	public List<AttendanceManagementVo> outputAttendance(AttendanceManagementQuery amq);
	
	public List<SettlementManagementVo> outputSettlement(SettlementManagementQuery smq);


	MailVo getMail(String paymentId);

}
