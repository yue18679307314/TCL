package com.kuyu.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.kuyu.common.CommonConstants;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.TpmActualActivityDataMapper;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmActualActivityDataModel;
import com.kuyu.service.TpmActivityService;
import com.kuyu.service.TpmActualActivityDataService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.DateUtils;
import com.kuyu.vo.ActualDataVo;
import com.kuyu.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @Author jt_L
 * @Date 2017-10-19
 * @Description 活动实际上报数据表 Service实现类
 */
@Service
@Transactional
public class TpmActualActivityDataServiceImpl extends ServiceImpl<TpmActualActivityDataMapper, TpmActualActivityDataModel> implements TpmActualActivityDataService{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TpmActivityService tpmActivityServiceImpl;

    @Override
    public ResultVo findActualData(LoginUserInfo userInfo, String activityUuid) throws Exception{

        Integer personType = CheckParamUtils.getPersonType(userInfo);
        if (personType < 3 || personType == 6){
            log.info("personType错误,为{}",personType);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }
        String managerNo = userInfo.getEmployeeModel().getPerson_code();
        managerNo = CheckParamUtils.trimWithString(managerNo);
        activityUuid = CheckParamUtils.trimWithString(activityUuid);
        int count = tpmActivityServiceImpl.selectCount(Condition.create().where("activity_uuid={0} and manager={1}", activityUuid, managerNo));
        if (count == 0){
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }
        TpmActualActivityDataModel actualActivityDataModel = new TpmActualActivityDataModel();
        actualActivityDataModel.setActivityUuid(activityUuid);
        actualActivityDataModel = baseMapper.selectOne(actualActivityDataModel);

        if (null == actualActivityDataModel){
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }
        return ResultVo.getDataWithSuccess(actualActivityDataModel);
    }

    @Override
    public ResultVo insertOrUpdateActualData(LoginUserInfo userInfo, ActualDataVo actualDataVo) throws Exception{
        actualDataVo = (ActualDataVo) CheckParamUtils.trimWithObjectField(actualDataVo);
        Integer personType = CheckParamUtils.getPersonType(userInfo);
        if (personType < 3 || personType == 6){
            log.info("personType错误,为{}",personType);
            return ResultVo.getByEnumCode(CommonConstants.NOT_PERMISSION_CODE);
//            throw new ParamException(ResultVoUtils.defaultException(CommonConstants.NOT_PERMISSION));
        }
        String managerNo = userInfo.getEmployeeModel().getPerson_code();

        String activityUuid = actualDataVo.getActivityUuid();
        if (StringUtils.isEmpty(activityUuid)){
           throw new ParamException("活动uuid不能为空");
        }
        Integer actualSalesNo = actualDataVo.getActualSalesNo();
        if (null == actualSalesNo){
            throw new ParamException("实际销售量不能为空");
        }
        Double actualSalesMoney = actualDataVo.getActualSalesMoney();
        if (null == actualSalesMoney){
            throw new ParamException("实际销售额不能为空");
        }
       /* Double actualCost = actualDataVo.getActualCost();
        if (null == actualCost){
            throw new ParamException("实际花费不能为空");
        }
        Double actualCostRate = actualDataVo.getActualCostRate();
        if (null == actualCostRate){
            throw new ParamException("实际费用率不能为空");
        }*/
        //CheckParamUtils.checkNotEmpty(actualDataVo,"activityUuid","actualSalesNo","actualSalesMoney","actualCost","actualCostRate");

        int count = tpmActivityServiceImpl.selectCount(Condition.create().where("activity_uuid={0} and manager={1}", activityUuid, managerNo));
        if (count == 0){
            log.info(CommonConstants.ACTIVITY_DATA_NOT_EXISTS_MSG);
            return ResultVo.getDataWithSuccess(null);
//            throw new ParamException(ResultVo.get(ResultVo.NOT_FOUND));
        }

        TpmActualActivityDataModel tpmActualActivityDataModel = new TpmActualActivityDataModel();
        tpmActualActivityDataModel.setActivityUuid(actualDataVo.getActivityUuid());
        tpmActualActivityDataModel = baseMapper.selectOne(tpmActualActivityDataModel);
        boolean ret;
        String currentTime = DateUtils.currentTime();
        if (null == tpmActualActivityDataModel){
            tpmActualActivityDataModel = new TpmActualActivityDataModel();
            BeanUtils.copyProperties(actualDataVo,tpmActualActivityDataModel);
            tpmActualActivityDataModel.setUpdateTime(currentTime);
            tpmActualActivityDataModel.setLastupdateUser(managerNo);
            tpmActualActivityDataModel.setCreateTime(currentTime);
//            tpmActualActivityDataModel.setActualCost(null);
//            tpmActualActivityDataModel.setActualCostRate(null);
            ret = tpmActualActivityDataModel.insert();
        }else {
            BeanUtils.copyProperties(actualDataVo,tpmActualActivityDataModel);
            tpmActualActivityDataModel.setUpdateTime(currentTime);
            tpmActualActivityDataModel.setLastupdateUser(managerNo);
//            tpmActualActivityDataModel.setActualCost(null);
//            tpmActualActivityDataModel.setActualCostRate(null);
            ret = tpmActualActivityDataModel.updateById();
        }
        if (ret){
            return ResultVo.getDataWithSuccess(null);
        }else {
            return ResultVo.getByEnumCode(CommonConstants.SYSTEM_ERROR_CODE);
        }

    }
}
