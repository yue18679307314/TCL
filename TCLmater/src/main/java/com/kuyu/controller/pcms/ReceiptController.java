package com.kuyu.controller.pcms;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.service.ReceiptService;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.query.PcmsSupplierLogQuery;
import com.kuyu.vo.query.ReceiptQuery;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by pc on 2018/11/21
 */
@AOP_Controller_LOG
@RequestMapping("/receipt")
public class ReceiptController{

    @Resource
    private ReceiptService receiptService;
    /**
     * 分页查询
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "分页",response = PcmsSupplierLogQuery.class)
    @PostMapping("/findReceiptList")
    public ResultVo findReceiptList(@RequestBody ReceiptQuery query) throws Exception {
        return receiptService.findReceiptList(query);
    }

}
