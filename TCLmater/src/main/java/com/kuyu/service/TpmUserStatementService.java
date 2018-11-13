package com.kuyu.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmUserStatementModel;
import com.kuyu.vo.*;
import com.kuyu.vo.borrow.BorrowOrderVo;
import com.kuyu.vo.query.AttendanceManagementCheckQuery;
import com.kuyu.vo.query.AttendanceManagementQuery;
import com.kuyu.vo.query.SettlementManagementCheckQuery;
import com.kuyu.vo.query.SettlementManagementQuery;

public interface TpmUserStatementService extends IService<TpmUserStatementModel>{

	/**
	 * 查询考勤记录
	 * @param tpmUserStatementModel
	 * @return
	 * @throws Exception
	 */
	public ResultVo queryRecords(TpmUserStatementModel tpmUserStatementModel)throws Exception;

	/**
	 * 根据openid获取活动列表
	 * @param userInfo
	 * @return
	 */
	ResultVo findUserStatementActivityListByOpenid(LoginUserInfo userInfo)throws Exception;

	/**
	 * 根据活动uuid和openid查询活动的收益明细列表
	 * @param userInfo
	 * @param activityUuid
	 * @return
	 * @throws Exception
	 */
	ResultVo findStatementActivityDetailList(LoginUserInfo userInfo, String activityUuid)throws Exception;
	

	/**
	 * 根据activity_uuid，openid查询单条记录
	 * @param tpmUserStatementModel
	 * @return
	 */
	public TpmUserStatementModel queryUserStatement(TpmUserStatementModel tpmUserStatementModel);
	
	/**
	 * 添加考勤记录，如果没有上下班时间则新增
	 * @param tpmUserStatementModel
	 * @return
	 * @throws Exception
	 */
	public ResultVo insertRecords(TpmUserStatementModel tpmUserStatementModel)throws Exception;
	
	/**
	 * 更新考勤记录，如果已经有下班打卡时间，则更新
	 * @param tpmUserStatementModel
	 * @return
	 * @throws Exception
	 */
	public ResultVo updateRecords(TpmUserStatementModel tpmUserStatementModel)throws Exception;
	
	/**
	 * 根据活动id和活动时间查询
	 * @param map
	 * @return
	 */
	public List<TpmUserStatementModel>  attendanceManagement(Map<String,Object> map);
	
	/* 根据活动id和活动时间查询
	* @param map
	* @return
	*/
	public List<TpmUserStatementModel> attendanceManagementBypage(Map<String, Object> map,Page<TpmUserStatementModel> page);
	
	/**
	 * 调整工时
	 * @param tpmUserStatementModel
	 * @return
	 */
	public Integer adjustWorkingHours(TpmUserStatementModel tpmUserStatementModel,LoginUserInfo userInfo) throws Exception;
	
	/**
	 * 调整工资，扣除或者奖励
	 * @param
	 * @return
	 */
	public ResultVo adjustMoney(TpmUserStatementQuery tpmUserStatementQuery,LoginUserInfo userInfo) throws Exception;

	/**
	 * 考勤管理查询
	 * @param
	 * @return
	 */
	public Page<AttendanceManagementVo> queryAttendanceManagement(LoginUserInfo userInfo,AttendanceManagementQuery amq)throws Exception;
	
	public TpmUserStatementModel queryAttendanceDetails(String uuid);
	
	public Page<SettlementManagementVo> querySettlementManagement(LoginUserInfo userInfo,SettlementManagementQuery smq) throws Exception;
	
	public ResultVo checkAttendanceManagement(LoginUserInfo userInfo,AttendanceManagementCheckQuery amcq) throws Exception;
	
	public ResultVo checkSettlementManagement(LoginUserInfo userInfo,SettlementManagementCheckQuery smcq) throws Exception;
	
	public SettlementManagementVo querySettlementDetails(String uuid);

    List<PaymentErrorVo> findUserStatementPaymentError();

	double findRealSalaryByProjectId(String projectId);

    List<TpmUserStatementModel> findActivityUuidByProjectId(String projectId);

	List<BorrowOrderVo> importBorrowMoneyList();

    double findTotalSalaryByProjectId(String projectId);

    public ResultVo getToDoList(LoginUserInfo userInfo);

    
    public ResultVo outputAttendance(LoginUserInfo userInfo,AttendanceManagementQuery amq) throws Exception;
    
    public ResultVo outputSettlement(LoginUserInfo userInfo,SettlementManagementQuery smq) throws Exception;
    
    public String downloadSettlement(String path);
	/**
	 * 根据payment_id获取活动负责人的邮箱和财务负责人的邮箱
	 * @param paymentId
	 * @return
	 */
	MailVo findMailOfManagerAndFinacical(String paymentId);

}
