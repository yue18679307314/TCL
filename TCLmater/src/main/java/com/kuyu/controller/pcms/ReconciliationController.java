package com.kuyu.controller.pcms;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.model.pcms.PcmsUserItemModel;
import com.kuyu.service.PcmsReconciliationService;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.query.ReconciliationQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by pc on 2018/12/26
 */
@AOP_Controller_LOG
@Api(tags = "对账接口")
@RequestMapping("/reconciliation")
public class ReconciliationController extends BaseController {
    @Resource
    private PcmsReconciliationService pcmsReconciliationService;

    @ApiOperation(value = "详情",response = PcmsUserItemModel.class)
    @GetMapping("/getReceiptDetail")
    public void getReceiptDetail(@RequestParam(value = "itid") Integer itid) throws Exception{
        pcmsReconciliationService.selectByTime();
    }

    @ApiOperation(value = "分页",response = ReconciliationQuery.class)
    @PostMapping("/findReconciliationList")
    public ResultVo findReconciliationList(@RequestBody ReconciliationQuery query) throws Exception {
        return pcmsReconciliationService.findReconciliationList(/*getUserInfo(),*/query);
    }

    @ApiOperation(value = "列表",response = PcmsUserItemModel.class)
    @GetMapping("/getReconciliation")
    public ResultVo getReconciliation(@RequestParam(value = "id") Integer id) throws Exception{
        return pcmsReconciliationService.selectCurrentDetail(id);
    }
}
