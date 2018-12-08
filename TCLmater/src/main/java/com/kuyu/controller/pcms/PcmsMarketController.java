package com.kuyu.controller.pcms;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.model.pcms.PcmsPendingMaterialModel;
import com.kuyu.model.pcms.PcmsRejectLogModel;
import com.kuyu.model.pcms.PcmsSupplierMaterialModel;
import com.kuyu.model.pcms.PcmsUserItemModel;
import com.kuyu.service.ReceiptService;
import com.kuyu.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pc on 2018/11/27
 */
@AOP_Controller_LOG
@RequestMapping("/market")
public class PcmsMarketController extends BaseController {

    @Resource
    private ReceiptService receiptService;
    /**
     * 市场人员查看立项单详情
     * @param itid
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "市场人员查看立项单详情",response = PcmsUserItemModel.class)
    @GetMapping("/getItemDetail")
    public ResultVo getItemDetail(@RequestParam(value = "itid") Integer itid) throws Exception{
        return receiptService.getItemDetail(itid);
    }

    /**
     * 市场人员驳回验收单
     * @param pcmsRejectLogModel
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "驳回",response = PcmsRejectLogModel.class)
    @PostMapping("/doRejectFail")
    public ResultVo doReject(@RequestBody PcmsRejectLogModel pcmsRejectLogModel)throws Exception{
        return receiptService.doReject(pcmsRejectLogModel/*,getUserInfo()*/);
    }

    /**
     * 验收成功
     * @param itid
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "验收成功",response = PcmsRejectLogModel.class)
    @GetMapping("/doRejectSuccess")
    public ResultVo doRejectSuccess(@RequestParam(value = "itid") Integer itid)throws Exception{
        return receiptService.doRejectSuccess(itid/*,getUserInfo()*/);
    }

    /**
     * 市场人员查看立项物料清单
     * @param itid
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "市场人员查看立项物料清单",response = PcmsPendingMaterialModel.class)
    @GetMapping("/selectPendingMaterial")
    public ResultVo selectPendingMaterial(@RequestParam(value = "itid") Integer itid)throws Exception{
        return receiptService.selectPendingMaterial(itid);
    }

    /**
     * 市场人员修改立项物料清单
     * @param vendor_id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "市场人员修改立项物料清单",response = PcmsPendingMaterialModel.class)
    @GetMapping("/updatePendingMaterialFor")
    public ResultVo updatePendingMaterialFor(@RequestParam(value = "vendor_id") String vendor_id)throws Exception{
        String s = vendor_id.replace("&quot;","\"");
        ObjectMapper mapper = new ObjectMapper();
        List<PcmsPendingMaterialModel> list = mapper.readValue(s, new TypeReference<List<PcmsPendingMaterialModel>>() {});
        return receiptService.updatePendingMaterialFor(list);
    }



}
