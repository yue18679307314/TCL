package com.kuyu.controller.pcms;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.model.pcms.*;
import com.kuyu.service.ReceiptService;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.ReceiptDetailVo;
import com.kuyu.vo.pcms.TransferDetailVo;
import com.kuyu.vo.pcms.TransferVo;
import com.kuyu.vo.pcms.UserVo;
import com.kuyu.vo.query.FeedbackQuery;
import com.kuyu.vo.query.SettlementQuery;
import com.kuyu.vo.query.TransferQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pc on 2018/11/27
 */
@AOP_Controller_LOG
@Api(tags = "市场人员接口")
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
    @ApiOperation(value = "市场人员查看立项单详情",response = ReceiptDetailVo.class)
    @GetMapping("/getItemDetail")
    public ResultVo getItemDetail(@RequestParam(value = "itid") Integer itid) throws Exception{
        return receiptService.getDetail(itid);
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

    /**
     * 根据姓名查询员工
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据姓名查询员工",response = UserVo.class)
    @GetMapping("/selectByName")
    public ResultVo selectByName(@RequestParam(value = "name") String name)throws Exception{
        return receiptService.selectByName(name,getLoginUserInfo());
    }

    /**
     * 日志信息
     * @param itid
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "日志信息",response = PcmsItemLog.class)
    @GetMapping("/selectItemLog")
    public ResultVo selectItemLog(@RequestParam(value = "itid") Integer itid)throws Exception{
        return receiptService.selectItemLog(itid);
    }


    /**
     * 市场人员查询批量结算列表
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "市场人员查询批量结算列表",response = PcmsPendingMaterialModel.class)
    @PostMapping("/selectSettlement")
    public ResultVo selectSettlement(@RequestBody SettlementQuery query)throws Exception{
        return receiptService.selectSettlement(query);
    }

    /**
     * 根据ITID查询可转办的物料清单
     * @param itid
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据ITID查询可转办的物料清单",response = PcmsPendingMaterialModel.class)
    @GetMapping("/selectPendingMaterialByItid")
    public ResultVo selectPendingMaterialByItid(@RequestParam(value = "itid") Integer itid)throws Exception{
        return receiptService.selectPendingMaterialByItid(itid);
    }

    /**
     * 转办
     * @param pcmsTransferModel
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "转办",response = PcmsTransferModel.class)
    @PostMapping("/addTransfer")
    public ResultVo addTransfer(@RequestBody PcmsTransferModel pcmsTransferModel)throws Exception{
        return receiptService.addTransfer(pcmsTransferModel);
    }

    /**
     * 转办管理
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "转办管理",response = TransferVo.class)
    @GetMapping("/selectTransfer")
    public ResultVo selectTransfer(TransferQuery query)throws Exception{
        return receiptService.selectTransfer(query,getLoginUserInfo());
    }

    /**
     * 反馈
     * @param feedbackQuery
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "反馈",response = PcmsFeedbackModel.class)
    @PostMapping("/addFeedback")
    public ResultVo addFeedback(@RequestBody FeedbackQuery feedbackQuery)throws Exception{
        return receiptService.addFeedback(feedbackQuery);
    }

    /**
     * 反馈详情
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "反馈详情",response = TransferDetailVo.class)
    @GetMapping("/selectFeedbackDetail")
    public ResultVo selectFeedbackDetail(@RequestParam(value = "id") Integer id)throws Exception{
        return receiptService.selectFeedbackDetail(id);
    }

}
