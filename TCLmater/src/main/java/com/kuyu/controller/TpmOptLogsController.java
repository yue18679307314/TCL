package com.kuyu.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.common.CommonConstants;
import com.kuyu.exception.ParamException;
import com.kuyu.model.TpmOptLogsModel;
import com.kuyu.service.TpmOptLogsService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.ResultVoUtils;
import com.kuyu.vo.OperateLogsVo;
import com.kuyu.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author tang_zhen
 * @Date 2017/9/27
 * @Description
 */
@AOP_Controller_LOG
@Api(tags = "日志操作接口")
@RequestMapping("/tpmOtpLogs")
public class TpmOptLogsController extends BaseController {

    @Autowired
    private TpmOptLogsService tpmOptLogsService;

    /**
     * 日志操作存入数据库
     * type类型含义：1 修改银行信息；2 pc端考勤审核；
     * 3 移动端考勤申请；4 pc端结算审核；5 移动端结算审核
     * @param tpmOptLogsModel
     * @return
     * @throws Exception
     */
    @ApiOperation("将日志操作记录存入数据库")
    public ResultVo insertLogsData(@ApiParam("日志操作的记录信息。type是日志操作的类型：" +
            "1 修改银行信息；2 pc端考勤审核；3 移动端考勤申请；4 pc端结算审核；5 移动端结算审核")
             @RequestBody TpmOptLogsModel tpmOptLogsModel) throws Exception {
        return  tpmOptLogsService.insertOptLogs(tpmOptLogsModel);
    }

    /**
     * 查询日志操作的信息
     */
    @ApiOperation("按照不同方式查询日志操作信息")
    @PostMapping("/findLogs")
    public ResultVo findLogsByType (@ApiParam("查询日志记录的相关参数。时间的格式：2017-09-27 17:06:04，type在1-5中") @RequestBody OperateLogsVo operateLogsVo) throws  Exception
    {
        Integer personType = CheckParamUtils.getPersonType(this.getLoginUserInfo());
        if (!(personType == 1||personType==0||personType==6)) {
          Page<TpmOptLogsModel> page = new Page<TpmOptLogsModel>(operateLogsVo.getCurrent(),operateLogsVo.getSize());
          page.setRecords(null);
          ResultVo rs = ResultVo.get(ResultVo.SUCCESS);
          rs.setData(page);
            return rs;
        }
        ResultVo rs = ResultVo.get(ResultVo.SUCCESS);
        rs.setData(tpmOptLogsService.selectLogsData(this.getLoginUserInfo().getEmployeeModel().getPerson_code(),personType,operateLogsVo));
        return rs;
    }


    @ApiOperation("按照不同方式查询日志操作信息")
    @PostMapping("/importLogs")
    public  ResultVo importExcel(@ApiParam(value = "查询日志记录的相关参数",required = true) @RequestBody  OperateLogsVo operateLogsVo) throws Exception
    {
        return  tpmOptLogsService.importService(operateLogsVo);
    }

}
