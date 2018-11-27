package com.kuyu.controller.pcms;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.model.pcms.PcmsUserItemModel;
import com.kuyu.service.PcmsUserItemService;
import com.kuyu.service.ReceiptService;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.PcmsShowcaseVo;
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

    /**
     * 修改展台展柜信息
     * @param pcmsShowcaseVo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "修改展台展柜信息",response = PcmsShowcaseVo.class)
    @PostMapping("/updateShowcase")
    public ResultVo updateShowcase(@RequestBody PcmsShowcaseVo pcmsShowcaseVo) throws Exception {
        return receiptService.updateShowcase(pcmsShowcaseVo);
    }

    /**
     * 根据itid修改状态
     * @param itid
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据itid修改状态",response = PcmsUserItemModel.class)
    @GetMapping("/updateType")
    public ResultVo updateType(@RequestParam(value = "itid") Integer itid) throws Exception{
        return receiptService.getReceiptDetail(itid);
    }

}
