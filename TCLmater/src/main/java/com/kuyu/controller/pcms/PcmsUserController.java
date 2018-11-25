package com.kuyu.controller.pcms;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.model.pcms.PcmsSupplierMaterialModel;
import com.kuyu.model.pcms.PcmsSupplierModel;
import com.kuyu.model.pcms.PcmsUserModel;
import com.kuyu.service.PcmsSupplierService;
import com.kuyu.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by pc on 2018/11/19
 * 微信端,用户绑定供应商
 */
@AOP_Controller_LOG
@RequestMapping("/user")
public class PcmsUserController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(PcmsSupplierController.class);

    @Resource
    private PcmsSupplierService pcmsSupplierService;

    /**
     * 微信用户绑定供应商
     * @param vendor_name 公司名称
     * @param legal_person 法人
     * @param mobile 电话
     * @param openid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/weixinbinding", method = RequestMethod.GET)
    @ResponseBody
    public ResultVo bindingweixin(@RequestParam(value = "vendor_name") String vendor_name,
                                  @RequestParam(value = "legal_person") String legal_person,
                                  @RequestParam(value = "mobile") String mobile,
                                  @RequestParam(value = "openid") String openid) throws Exception{
        PcmsSupplierModel pcmsSupplierModel = new PcmsSupplierModel();
        pcmsSupplierModel.setMobile(mobile);
        pcmsSupplierModel.setLegal_person(legal_person);
        pcmsSupplierModel.setVendor_name(vendor_name);
        PcmsUserModel pcmsUserModel = new PcmsUserModel();
        pcmsUserModel.setOpenid(openid);
        return pcmsSupplierService.getPcmsSupplierFor(pcmsSupplierModel,pcmsUserModel);
    }
    @RequestMapping(value = "/findBySupplier", method = RequestMethod.GET)
    @ResponseBody
    public ResultVo findBySupplier(@RequestParam(value = "vendor_id") String vendor_id) throws Exception{
        return ResultVo.get(ResultVo.SUCCESS);
    }
}
