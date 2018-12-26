package com.kuyu.controller.pcms;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.service.PcmsReconciliationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by pc on 2018/12/26
 */
@AOP_Controller_LOG
@Api(tags = "对账接口")
@RequestMapping("/reconciliation")
public class ReconciliationController {
    @Resource
    private PcmsReconciliationService pcmsReconciliationService;

}
