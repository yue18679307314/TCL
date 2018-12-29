package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.pcms.PcmsReconciliationModel;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.query.ReconciliationQuery;

/**
 * Created by pc on 2018/12/26
 */
public interface PcmsReconciliationService extends IService<PcmsReconciliationModel> {


    void selectByTime();

    ResultVo findReconciliationList(/*LoginUserInfo userInfo,*/ ReconciliationQuery query)throws Exception;

    ResultVo selectCurrentDetail(Integer id/*,LoginUserInfo userInfo*/);

}
