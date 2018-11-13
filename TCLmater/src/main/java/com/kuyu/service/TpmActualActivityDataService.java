package com.kuyu.service;

import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmActualActivityDataModel;
import com.baomidou.mybatisplus.service.IService;
import com.kuyu.vo.ActualDataVo;
import com.kuyu.vo.ResultVo;

/**
 *
 * @Author jt_L
 * @Date 2017-10-19
 * @Description 活动实际上报数据表 Service类
 */
public interface TpmActualActivityDataService extends IService<TpmActualActivityDataModel> {

    ResultVo findActualData(LoginUserInfo userInfo, String activityUuid)throws Exception;

    ResultVo insertOrUpdateActualData(LoginUserInfo userInfo, ActualDataVo actualDataVo)throws Exception;
}
