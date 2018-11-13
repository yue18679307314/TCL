package com.kuyu.util;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.kuyu.model.TpmUserActivityModel;
import com.kuyu.vo.ActivityVo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author jt_L
 * @Date 2017/10/26
 * @Description
 */
public class ActivityUtils {


    /**
     * 设置申请和活动状态
     * @param activityVo
     * @param currentTime 当前时间
     */
    public static void setStateByTime(boolean isGetActivityState, String openid, ActivityVo activityVo, String currentTime)  throws  Exception{
        int dateTimeType = getDateTimeType(activityVo, currentTime);

        if(isGetActivityState)
        {
            int totalCount = activityVo.getUserNo();
            String today = activityVo.getStartTime();
            for(;today.compareTo(activityVo.getEndTime())<=0;today = getNextDay(today))
            {
                int joinCount = activityVo.getCount();

            }
        }
        /*if (isGetActivityState){
            int joinCount = activityVo.getCount();
            int totalNo = activityVo.getUserNo();
            if (dateTimeType == 0){//当前时间小于开始时间
                if (joinCount < totalNo){
                    activityVo.setActivityState(0);//招募中(未开始)
                }else {
                    activityVo.setActivityState(1);//招募完成(未开始)
                }
            }else if (dateTimeType == 1){//当前时间在开始与结束时间之间
            	if (joinCount < totalNo){
                    activityVo.setActivityState(2);//招募中(进行中)
                }else {
                    activityVo.setActivityState(4);//招募完成(进行中)
                }
            }else{
                activityVo.setActivityState(3);//已结束
            }
        }*/
        if (StringUtils.isNotEmpty(openid)){
            TpmUserActivityModel userActivityModel = new TpmUserActivityModel().selectOne(Condition.create().where("openid={0} and activity_uuid={1}",openid,activityVo.getActivityUuid()));
            if (null == userActivityModel) {
                activityVo.setRequestState(-1);//未申请
            }else {
                Integer requestState = userActivityModel.getRequestState();
                if (requestState > 2){
                    if (dateTimeType == 0){//当前时间小于开始时间
                        activityVo.setRequestState(1);//未开始,已申请
                    }else if (dateTimeType == 1){//当前时间在开始与结束时间之间
                        activityVo.setRequestState(3);//进行中,,已参加
                    }else{
                        activityVo.setRequestState(4);//已结束
                    }
                }else {
                    activityVo.setRequestState(requestState);
                }
            }
        }

    }


    /**
     * 改变时间"-"为"."
     * @param activityVo
     * @return
     */
    public static void changeDateTimeStr(ActivityVo activityVo){
        String startTime = activityVo.getStartTime();
        String endTime = activityVo.getEndTime();
        activityVo.setStartTime(startTime.replaceAll("-","."));
        activityVo.setEndTime(endTime.replaceAll("-","."));
    }

    public static int getDateTimeType(ActivityVo activityVo, String currentTime){
        String startTime = activityVo.getStartTime();
        String endTime = activityVo.getEndTime();
        long startDiff = DateUtils.getDateDiff(currentTime.substring(0, 10), startTime);
        long endDiff = DateUtils.getDateDiff(currentTime.substring(0, 10), endTime);
        boolean isStartTime = (startDiff == 0) ? true : false;
        boolean isEndTime = (endDiff == 0) ? true : false;
        boolean littleStartTime = DateUtils.compareTime(currentTime, startTime);
        boolean littleEndTime = DateUtils.compareTime(currentTime, endTime);
        if (littleStartTime){//当前时间小于开始时间
            return 0;
        }else if (isStartTime || littleEndTime || isEndTime){//当前时间在开始与结束时间之间
            return 1;
        }else{
            return 2;
        }
    }
    public static  String getNextDay(String time) throws  Exception
    {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //String riqi = "2017-12-31";
        Date currentTime = formatter.parse(time);
        calendar.setTime(currentTime);
        //"2017-12-29"
        calendar.add(Calendar.DATE, 1);
        Date date = calendar.getTime();
        String dateString = formatter.format(date);
        //System.out.println(dateString);
        return  dateString;
    }

}
