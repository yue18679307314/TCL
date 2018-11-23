package com.kuyu.controller.pcms;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.model.pcms.PcmsSupplierModel;
import com.kuyu.model.pcms.PcmsUserItemModel;
import com.kuyu.model.pcms.PcmsUserModel;
import com.kuyu.service.PcmsUserItemService;
import com.kuyu.service.ReceiptService;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.query.PcmsSupplierLogQuery;
import com.kuyu.vo.query.ReceiptQuery;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by pc on 2018/11/21
 */
@AOP_Controller_LOG
@RequestMapping("/receipt")
public class ReceiptController{

    @Resource
    private ReceiptService receiptService;
    @Resource
    private PcmsUserItemService pcmsUserItemService;
    /**
     * 分页查询
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "分页",response = ReceiptQuery.class)
    @PostMapping("/findReceiptList")
    public ResultVo findReceiptList(@RequestBody ReceiptQuery query) throws Exception {
        return receiptService.findReceiptList(query);
    }

    /**
     * 接单
     * @param pcmsUserItemModel
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "接单",response = PcmsUserItemModel.class)
    @PostMapping("/doReceipt")
    public ResultVo doReceipt(@RequestBody PcmsUserItemModel pcmsUserItemModel) throws Exception {
        return pcmsUserItemService.doReceipt(pcmsUserItemModel);
    }

    /**
     * 详情
     * @param itid
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "详情",response = PcmsUserItemModel.class)
    @GetMapping("/getReceiptDetail")
    public ResultVo getReceiptDetail(@RequestParam(value = "itid") Integer itid) throws Exception{
        return receiptService.getReceiptDetail(itid);
    }

}
