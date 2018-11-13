package com.kuyu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.TpmActivityModel;
import com.kuyu.model.TpmPromotionFeeOriginalModel;
import com.kuyu.model.TpmUserActivityModel;
import com.kuyu.model.TpmUserStatementModel;
import com.kuyu.vo.ActivityDetailVo;
import com.kuyu.vo.ActivityJoinDetailVo;
import com.kuyu.vo.ActivityVo;
import com.kuyu.vo.AcvitityUserVo;
import com.kuyu.vo.ProjectActivityQuery;
import com.kuyu.vo.query.ActivityJoinQuery;
import com.kuyu.vo.query.ActivityQuery;

public interface TpmActivityMapper extends BaseMapper<TpmActivityModel> {

    List<ActivityVo> findActivityListByCity(@Param("city") String city);

    ActivityVo findActivityVoById(String activityUuid);

    List<ActivityVo> findActivityListByUser(@Param("openid") String openid,@Param("requestState") int requestState);

    String findActivityNameByUuid(String activityUuid);

	public TpmActivityModel queryTpmActivityModel(String activityUuid);

    List<ActivityVo> findManagerActivityList(String managerNo);

    ActivityDetailVo findManagerActivityDeatil(@Param("managerNo") String managerNo,@Param("acitvityUuid") String activityUuid);

    List<AcvitityUserVo> activityUserList(Page<AcvitityUserVo> page, @Param("managerNo") String managerNo, @Param("acitvityUuid") String activityUuid, @Param("requestActivityDay")String requestActivityDay, @Param("userNo") int userNo, @Param("user_no") int user_no);

    AcvitityUserVo findUserInfoByOpenid(String openid);
    
    public List<ActivityDetailVo> queryActivityDetail(@Param("list")List<String> list,@Param("state")String state,Page<ActivityDetailVo> page);
	
	public  TpmUserStatementModel getManagerInfo(Map<String,Object> map);
    
    public Integer getCountOpenNumber(@Param("activity_uuid")String activity_uuid,@Param("requestActivityDay")String requestActivityDay);
	
	List<AcvitityUserVo> findUserListByAcitvityUuid(String activityUuid);

    List<ActivityDetailVo> findActivityDetailPage(@Param("personType") Integer personType, @Param("personCode") String personCode, @Param("deptList") List<String> childsDeptList, @Param("params") ProjectActivityQuery query, Page<ActivityDetailVo> page);

    Integer selectCountByManager(@Param("personType") Integer personType, @Param("personCode") String managerNo, @Param("uuid") String activityUuid, @Param("openid") String openid ,@Param("requestActivityDay") String requestActivityDay);

    List<ActivityJoinDetailVo> findActivityJoinListMyPage(@Param("personType") Integer personType, @Param("personCode") String person_code, @Param("deptList") List<String> childsDeptList, @Param("params") ActivityJoinQuery query,Page<ActivityJoinDetailVo> page);

    ActivityDetailVo findActivityDetail(@Param("personType") Integer personType, @Param("personCode") String person_code, @Param("deptList") List<String> childsDeptList, @Param("uuid") String activityUuid);

    List<ActivityDetailVo> selectActivityListMyPage(@Param("personType") Integer personType, @Param("personCode") String person_code, @Param("deptList") List<String> childsDeptList, @Param("params") ActivityQuery query, Page<ActivityDetailVo> page);



    String findFinancialPersoncodeByActivityUuid(String activity_uuid);
    
    List<ActivityVo> selectUserNoAndCount(List<String> uuidList);

    Integer selectCountByDay(@Param("activityUuid")String activityUuid, @Param("activityDay")String activityDay);

    Integer  selectCountByOpenidAndDay(@Param("activityUuid")String activityUuid, @Param("openid") String openid, @Param("activityDay") String today);

    Integer isRefusedOrNot(TpmUserActivityModel userActivityModel);

    void updateRefusedRequest(TpmUserActivityModel userActivityModel);

    int selectRequestState(@Param("activityUuid")String activityUuid, @Param("openid") String openid, @Param("activityDay") String activityDay);

    String getApprovalReason(@Param("activityUuid")String activityUuid,@Param("activityDay") String activityDay ,@Param("openid") String openid);

    Integer updateRejectFlag(TpmUserActivityModel userActivityModel);

    Integer selectTotalUserNo(@Param("uuid")String uuid);

    List<ActivityVo> findActivityListByUserHomePage(@Param("openid") String openid,@Param("requestActivityDay")String requestActivityDay);

    List<TpmUserActivityModel> selectActivityList();

    TpmActivityModel selectTime(String activityUuid);

    void updateFirstActivityData(TpmUserActivityModel tpmUserActivityModel);

    void insertActivityData(TpmUserActivityModel tpmUserActivityModel);

    Integer isRejectOrNot(TpmUserActivityModel userActivityModel);

    Integer updateRejectUser(TpmUserActivityModel userActivityModel);
    
    List<TpmPromotionFeeOriginalModel> selectPromotionFeeOriginalByProjectId(String projectId);
    
    String selectManager(Map<String,String> map);

    List<AcvitityUserVo> findAllApprovingUserListByAcitvityUuid(String activityUuid);

    Integer stopActivityById(String activityUuid);

    Integer selectCountBinding(String openid);

    String getBindingJoinFlag();
}