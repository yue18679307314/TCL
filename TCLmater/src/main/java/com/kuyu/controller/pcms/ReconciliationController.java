package com.kuyu.controller.pcms;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.model.pcms.*;
import com.kuyu.service.PcmsReconciliationService;
import com.kuyu.vo.ReconciliationVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.CurrentDetailModelVo;
import com.kuyu.vo.query.ReconciliationQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 分页
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "分页",response = ReconciliationQuery.class)
    @PostMapping("/findReconciliationList")
    public ResultVo findReconciliationList(@RequestBody ReconciliationQuery query) throws Exception {
        return pcmsReconciliationService.findReconciliationList(/*getUserInfo(),*/query);
    }

    /**
     * 往来数据列表
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "列表",response = CurrentDetailModelVo.class)
    @GetMapping("/getReconciliation")
    public ResultVo getReconciliation(@RequestParam(value = "id") Integer id) throws Exception{
        return pcmsReconciliationService.selectCurrentDetail(id);
    }

    /**
     * 新增往来数据
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "新增往来数据",response = PcmsCurrentDetailModel.class)
    @PostMapping("/addCurrentDetail")
    public ResultVo addCurrentDetail(@RequestBody PcmsCurrentDetailModel query) throws Exception {
        return pcmsReconciliationService.addCurrentDetail(query);
    }

    /**
     * 确定生成
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "确定生成",response = PcmsIinitializationModel.class)
    @PostMapping("/addIinitialization")
    public ResultVo addIinitialization(@RequestBody PcmsIinitializationModel query) throws Exception {
        return pcmsReconciliationService.addIinitialization(query,getLoginUserInfo());
    }

    /**
     * 发起对账
     * @param ids
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "发起对账",response = ReconciliationVo.class)
    @GetMapping("/reconciliation")
    public ResultVo reconciliation(@RequestParam(value = "ids") String ids)throws Exception{
        String s = ids.replace("&quot;","\"");
        ObjectMapper mapper = new ObjectMapper();
        List<PcmsReconciliationModel> beanList = mapper.readValue(s, new TypeReference<List<PcmsReconciliationModel>>() {});
        return pcmsReconciliationService.selectReconciliationList(beanList);
    }

    /**
     * 确定发起对账
     * @param reconciliationVo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "确定发起对账",response = ReconciliationVo.class)
    @GetMapping("/sureReconciliation")
    public ResultVo sureReconciliation(@RequestParam(value = "reconciliationVo") String reconciliationVo)throws Exception{
        String s = reconciliationVo.replace("&quot;","\"");
        ObjectMapper mapper = new ObjectMapper();
        List<ReconciliationVo> list = mapper.readValue(s, new TypeReference<List<ReconciliationVo>>() {});
        return pcmsReconciliationService.sureReconciliation(list);
    }



}
