package com.kuyu.controller;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.common.BasePageQuery;
import com.kuyu.common.CommonConstants;
import com.kuyu.model.TpmActualActivityDataModel;
import com.kuyu.service.TpmActualActivityDataService;
import com.kuyu.vo.ActualDataVo;
import com.kuyu.vo.AcvitityUserVo;
import com.kuyu.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 *
 * @Author jt_L
 * @Date 2017-10-19
 * @Description 活动实际上报数据表Controller
 */
@Api(tags = "上报数据服务")
@AOP_Controller_LOG
@RequestMapping("/tpmActualActivityData")
public class TpmActualActivityDataController extends BaseController{

    @Autowired
    private TpmActualActivityDataService tpmActualActivityDataServiceImpl;

    @ApiOperation(value = "活动负责人查询实际销售情况上报数据",response = TpmActualActivityDataModel.class)
    @GetMapping("/manager/activity/actualdata/get/{id}")
    public ResultVo actualdataBymanager(@ApiParam(value = "活动uuid",required = true)@PathVariable("id") String activityUuid)throws Exception{
        return tpmActualActivityDataServiceImpl.findActualData(getUserInfo(),activityUuid);
    }

    @ApiOperation(value = "活动负责人实际销售情况上报")
    @PostMapping("/manager/activity/actualdata/addorupdate/")
    public ResultVo actualdataBymanager1(@RequestBody ActualDataVo actualDataVo)throws Exception{
        if(actualDataVo.getActualSalesNo()!=0)
        {
            if(actualDataVo.getActualSalesNo()>2147483646||actualDataVo.getActualSalesNo()<0)
                return ResultVo.getByEnumCode(CommonConstants.PARAM_IS_ERROR_CODE);
        }
        return tpmActualActivityDataServiceImpl.insertOrUpdateActualData(getUserInfo(),actualDataVo);
    }
	
}
