package com.kuyu.controller.pcms;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.service.PcmsSupplierLogService;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.query.PcmsSupplierLogQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by pc on 2018/11/21
 */
@AOP_Controller_LOG
@Api(tags = "供应商日志接口")
@RequestMapping("/supplierLog")
public class PcmsSupplierLogController  extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(PcmsSupplierLogController.class);

    @Resource
    private PcmsSupplierLogService pcmsSupplierLogService;

    /**
     * 分页查询
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "分页",response = PcmsSupplierLogQuery.class)
    @PostMapping("/findSupplierLogList")
    public ResultVo findSupplierLogList(@RequestBody PcmsSupplierLogQuery query) throws Exception {
        return pcmsSupplierLogService.findSupplierLogList(query);
    }
}
