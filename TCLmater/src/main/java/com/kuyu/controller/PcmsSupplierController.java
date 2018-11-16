//package com.kuyu.controller;
//
//import com.kuyu.annotation.AOP_Controller_LOG;
//import com.kuyu.model.PcmsSupplierModel;
//import com.kuyu.service.PcmsSupplierService;
//import com.kuyu.vo.FinancialResultVo;
//import com.kuyu.vo.PcmsSupplierVo;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * Created by zyl on 2018/11/14
// */
//@AOP_Controller_LOG
//@RequestMapping("/supplier")
//public class PcmsSupplierController extends BaseController{
//
//    private static final Logger log = LoggerFactory.getLogger(PcmsSupplierController.class);
//
//    @Resource
//    private PcmsSupplierService pcmsSupplierService;
//
//    /**
//     * 查询供应商信息，如果查到则更新，否则新增
//     *
//     * @param
//     * @return
//     * @throws Exception
//     */
//    @ApiOperation(value = "供应商信息操作", notes = "根据供应商编号查询供应商信息，如果查到则更新，否则新增", response = PcmsSupplierModel.class)
//    @ApiParam(name = "PcmsSupplierVo", value = "供应商信息实体类参数")
//    @RequestMapping(value = "/doPcmsSupplier", method = { RequestMethod.POST })
//    public FinancialResultVo doPcmsSupplier(@RequestBody List<PcmsSupplierVo> supplierList) throws Exception {
//        for (PcmsSupplierVo supplier : supplierList) {
//            pcmsSupplierService.insertPcmsSupplier(supplier);
//        }
//        return FinancialResultVo.get(FinancialResultVo.SUCCESS);
//    }
//}
