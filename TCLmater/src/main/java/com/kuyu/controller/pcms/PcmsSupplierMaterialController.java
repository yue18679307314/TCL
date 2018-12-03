package com.kuyu.controller.pcms;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.model.pcms.PcmsSupplierMaterialModel;
import com.kuyu.service.PcmsSupplierMaterialService;
import com.kuyu.service.PcmsSupplierService;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.SupplierMaterialVo;
import com.kuyu.vo.query.SupplierMaterialQuery;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@AOP_Controller_LOG
@RequestMapping("/supplierMaterial")
public class PcmsSupplierMaterialController extends BaseController {

    @Resource
    private PcmsSupplierMaterialService pcmsSupplierMaterialService;

    @Resource
    private PcmsSupplierService pcmsSupplierService;

    /**
     * 添加物料
     * @param pcmsSupplierMaterialModel
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "供应商物料信息", notes = "新增供应商物料信息", response = PcmsSupplierMaterialModel.class)
    @ApiParam(name = "PcmsSupplierMaterialModel", value = "供应商物料信息")
    @RequestMapping(value = "/addPcmsSupplierMaterial", method = { RequestMethod.POST })
    public ResultVo addSupplierMaterial(@RequestBody PcmsSupplierMaterialModel pcmsSupplierMaterialModel) throws Exception {
        pcmsSupplierMaterialService.insertPcmsSupplierMaterial(pcmsSupplierMaterialModel,getLoginUserInfo());
        return ResultVo.get(ResultVo.SUCCESS);
    }

    /**
     * 删除物料
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "删除",response = PcmsSupplierMaterialModel.class)
    @GetMapping("/deletePcmsSupplierMaterial")
    public ResultVo deleteSupplierMaterial(@RequestParam(value = "id") Integer id) throws Exception{
        pcmsSupplierMaterialService.deletePcmsSupplierMaterial(id);
        return ResultVo.get(ResultVo.SUCCESS);
    }

    /**
     * 供应商物料分页
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "分页",response = SupplierMaterialQuery.class)
    @PostMapping("/findSupplierMaterialList")
    public ResultVo findSupplierMaterialList(@RequestBody SupplierMaterialQuery query) throws Exception {
        return pcmsSupplierMaterialService.findSupplierMaterialByPage(getLoginUserInfo(),query);
    }

    /**
     * 根据openid查询供应商物料信息
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据openid查询供应商物料信息",response = PcmsSupplierMaterialModel.class)
    @PostMapping("/querySupplierMaterialList")
    public ResultVo querySupplierMaterialList(@RequestBody SupplierMaterialVo query) throws Exception {
        return pcmsSupplierMaterialService.querySupplierMaterialList(query);
    }

    @ApiOperation(value = "获取下载的url")
    @PostMapping("/getSupplierMaterialUrl")
    public ResultVo getSupplierMaterialUrl(@RequestBody List<PcmsSupplierMaterialModel> supplierMaterialList) throws Exception {
        String file = null;
        if (StringUtil.isNotNull(supplierMaterialList)) {
            if (supplierMaterialList.size() > 0) {
                file = pcmsSupplierMaterialService.getSupplierMaterialUrl(supplierMaterialList,getUserInfo());
            }
        }
        return ResultVo.getData(ResultVo.SUCCESS, file);
    }

}
