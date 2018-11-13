package com.kuyu.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmActivityModel;
import com.kuyu.model.TpmUserStatementModel;
import com.kuyu.vo.ActivityVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.query.ActivityJoinQuery;
import com.kuyu.vo.query.ActivityQuery;

/**
 *
 * @Author jt_L
 * @Date 2017-09-19
 * @Description 活动列表 Service类
 */
public interface TpmActivityService extends IService<TpmActivityModel> {

	public TpmActivityModel queryTpmActivityModel(String activityUuid);

    ActivityVo findActivityById(Boolean flag ,String openid, String activityUuid) throws Exception;

    ResultVo joinActivityByUser(LoginUserInfo userInfo, String activityUuid,String activityDay)throws Exception;

    public  ResultVo joinActivityByUserVerify(LoginUserInfo userInfo, String activityUuid,String requestActivityDay);

    ResultVo findActivityListByUser(LoginUserInfo userInfo, Integer requestState)throws Exception;

    ResultVo findActivityListByCity(LoginUserInfo userInfo, Integer type, String city)throws Exception;

    /**
     * 通过活动uuid查询活动名称
     * @param activityUuid
     * @return
     */
    String findActivityNameByUuid(String activityUuid);

    /**
     * 查询活动负责人的活动列表
     * @param userInfo
     * @return
     */
    ResultVo managerActivityList(LoginUserInfo userInfo)throws Exception;

    ResultVo managerActivityDeatil(LoginUserInfo userInfo, String activityUuid)throws Exception;

    ResultVo activityUserList(LoginUserInfo userInfo, String activityUuid, String requestActivityDay, Integer current, Integer size)throws Exception;

    ResultVo activityUserDetailByManager(LoginUserInfo userInfo, String activityUuid,String requestActivityDay, String openid)throws Exception;

    ResultVo findActivityDetailById(LoginUserInfo userInfo, String activityUuid)throws Exception;
    
//    public List<ActivityDetailVo> queryActivityDetail(Map<String,Object> map);

	public  TpmUserStatementModel getManagerInfo(String activityUuid) throws Exception;

    ResultVo auditUserByManager(LoginUserInfo userInfo, String activityUuid, String openid, String activityDay,Integer type, String reason)throws Exception;

    ResultVo findActivityUserAuditList(LoginUserInfo userInfo, String activityUuid, boolean booleanFlag) throws Exception;

    ResultVo findActivityListByPage(LoginUserInfo userInfo, ActivityQuery query)throws Exception;

    ResultVo findActivityDetail(LoginUserInfo userInfo, String activityUuid)throws Exception;

    ResultVo findActivityJoinListByPage(LoginUserInfo userInfo, ActivityJoinQuery query)throws Exception;

    String findFinancialPersoncodeByActivityUuid(String activity_uuid);

    ResultVo modifyActivityManager(LoginUserInfo userInfo, String activityUuid, String personCode)throws Exception;
    
    List<ActivityVo> selectUserNoAndCount(List<String> uuidList);

    ResultVo rejectUser(LoginUserInfo userInfo, String activityUuid, String activityDay, String openid);

    Integer  selectCountByDay(String uuid,String activityDay);

    Integer selectTotalUserNo(String uuid);

    ResultVo conversionActivity(LoginUserInfo userInfo) throws  Exception;

    ResultVo stopActivity(LoginUserInfo userInfo, String activityUuid);
}
