package com.kuyu.controller.pcms;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.model.pcms.PcmsSupplierCompanyModel;
import com.kuyu.model.pcms.PcmsSupplierModel;
import com.kuyu.service.PcmsSupplierCompanyService;
import com.kuyu.service.PcmsSupplierService;
import com.kuyu.util.DateUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.FinancialResultVo;
import com.kuyu.vo.PcmsSupplierQuert;
import com.kuyu.vo.PcmsSupplierVo;
import com.kuyu.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyl on 2018/11/14
 */
@AOP_Controller_LOG
@RequestMapping("/supplier")
public class PcmsSupplierController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(PcmsSupplierController.class);

    @Resource
    private PcmsSupplierService pcmsSupplierService;

    @Resource
    private PcmsSupplierCompanyService pcmsSupplierCompanyService;

    /**
     * 查询供应商详情
     *
     * @param vendor_id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据供应商编号查询供应商详情", notes = "根据供应商编号查询供应商信息详情", response = PcmsSupplierModel.class)
    @RequestMapping(value = "/getPcmsSupplierCode", method = { RequestMethod.GET })
    public ResultVo getEmployeeByPersonCode(
            @ApiParam(value = "供应商编号", required = true) @RequestParam(required = true) String vendor_id)
            throws Exception {
        if (StringUtil.isEmpty(vendor_id)) {
            log.info("vendor_id错误,为{}",vendor_id);
            return ResultVo.getData(ResultVo.SUCCESS,null);
        }
        PcmsSupplierVo supplier = new PcmsSupplierVo();
        supplier.setVendor_id(vendor_id);
        PcmsSupplierModel pcmsSupplierModel = pcmsSupplierService.getPcmsSupplier(supplier);
        ResultVo resultVo = new ResultVo();
        resultVo.setData(pcmsSupplierModel);
        resultVo.setCode(ResultVo.SUCCESS);
        return resultVo;
    }


    /**
     * 同步应商信息，如果查到则更新，否则新增
     *
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "供应商信息操作", notes = "根据供应商编号查询供应商信息，如果查到则更新，否则新增", response = PcmsSupplierModel.class)
    @ApiParam(name = "PcmsSupplierVo", value = "供应商信息实体类参数")
    @RequestMapping(value = "/doPcmsSupplier", method = { RequestMethod.POST })
    public FinancialResultVo doPcmsSupplier(@RequestBody List<PcmsSupplierVo> supplierList) throws Exception {
        for (PcmsSupplierVo supplier : supplierList) {
            PcmsSupplierModel pcmsSupplierModel = pcmsSupplierService.getPcmsSupplier(supplier);
            if(pcmsSupplierModel != null){
                pcmsSupplierService.updatePcmsSupplier(supplier);
                PcmsSupplierCompanyModel pcmsSupplierCompanyModel = new PcmsSupplierCompanyModel();
                pcmsSupplierCompanyModel.setCompany(supplier.getCompany());
                pcmsSupplierCompanyModel.setVendor_id(supplier.getVendor_id());
                PcmsSupplierCompanyModel pcmsSupplierCompany = pcmsSupplierCompanyService.selectByVendorIdAndCompany(pcmsSupplierCompanyModel);
                if(pcmsSupplierCompany == null){
                    pcmsSupplierCompanyModel.setCreate_time(DateUtils.getLongDateStr());
                    pcmsSupplierCompanyService.insertPcmsSupplierCompany(pcmsSupplierCompanyModel);
                }
            }else{
                PcmsSupplierCompanyModel pcmsSupplierCompanyModel = new PcmsSupplierCompanyModel();
                pcmsSupplierCompanyModel.setCompany(supplier.getCompany());
                pcmsSupplierCompanyModel.setVendor_id(supplier.getVendor_id());
                pcmsSupplierCompanyModel.setCreate_time(DateUtils.getLongDateStr());
                pcmsSupplierCompanyService.insertPcmsSupplierCompany(pcmsSupplierCompanyModel);
                pcmsSupplierService.insertPcmsSupplier(supplier);
            }
        }
        return FinancialResultVo.get(FinancialResultVo.SUCCESS);
    }


    /**
     * 同步应商信息，如果查到则更新，否则新增
     *
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "供应商信息操作", notes = "根据供应商编号查询供应商信息，如果查到则更新，否则新增", response = PcmsSupplierModel.class)
    @ApiParam(name = "PcmsSupplierVo", value = "供应商信息实体类参数")
    @RequestMapping(value = "/getPcmsSupplier", method = { RequestMethod.POST })
    public FinancialResultVo getPcmsSupplier() throws Exception {
        List<PcmsSupplierVo> supplierList = new ArrayList<>();
        for (PcmsSupplierVo supplier : supplierList) {
            PcmsSupplierModel pcmsSupplierModel = pcmsSupplierService.getPcmsSupplier(supplier);
            if(pcmsSupplierModel != null){
                pcmsSupplierService.updatePcmsSupplier(supplier);
            }else{
                PcmsSupplierCompanyModel pcmsSupplierCompanyModel = new PcmsSupplierCompanyModel();
                pcmsSupplierCompanyModel.setCompany(supplier.getCompany());
                pcmsSupplierCompanyModel.setVendor_id(supplier.getVendor_id());
                pcmsSupplierCompanyModel.setCreate_time(DateUtils.getLongDateStr());
                pcmsSupplierCompanyService.insertPcmsSupplierCompany(pcmsSupplierCompanyModel);
                pcmsSupplierService.insertPcmsSupplier(supplier);
            }
        }
        return FinancialResultVo.get(FinancialResultVo.SUCCESS);
    }



    /**
     * 更新供应商信息
     *
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "更新供应商信息", notes = "更新供应商信息", response = PcmsSupplierModel.class)
    @ApiParam(name = "PcmsSupplierModel", value = "供应商信息实体类参数")
    @RequestMapping(value = "/updatePcmsSupplier", method = { RequestMethod.POST })
    public FinancialResultVo updatePcmsSupplier(@RequestBody PcmsSupplierModel pcmsSupplierModel) throws Exception {
        pcmsSupplierService.updatePcmsSupplierModel(pcmsSupplierModel);
        return FinancialResultVo.get(FinancialResultVo.SUCCESS);
    }

    /**
     * 供应商分页查询
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "供应商表页分页",response = PcmsSupplierQuert.class)
    @PostMapping("/findPcmsSupplierListByPage")
    public ResultVo findPcmsSupplierListByPage(@RequestBody PcmsSupplierQuert query) throws Exception {
        return pcmsSupplierService.findPcmsSupplierListByPage(getUserInfo(),query);
    }


    /**
     * 导出供应商excel
     * @param vendoridList
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取下载的url")
    @RequestMapping(value = "/getPcmsSupplierUrl", method = RequestMethod.GET)
    public ResultVo getPcmsSupplierUrl(@ApiParam(required = true,value = "供应商的vendor_id列表") @RequestParam List<String> vendoridList,
                                   @ApiParam(required = true,value = "xls格式") @RequestParam String pdfOrxls) throws Exception {
        String file = null;
        if (StringUtil.isNotNull(vendoridList)) {
            if (vendoridList.size() > 0) {
//                file = tpmUserAccountInfoService.getBankInfoUrl(openidList,pdfOrxls, this.getLoginUserInfo());
            }
        }
        return ResultVo.getData(ResultVo.SUCCESS, file);
    }
}
