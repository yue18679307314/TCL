package com.kuyu.controller.pcms;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.model.pcms.PcmsMaterialVersionModel;
import com.kuyu.model.pcms.PcmsSupplierMaterialModel;
import com.kuyu.service.PcmsSupplierMaterialService;
import com.kuyu.service.PcmsSupplierService;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.SupplierMaterialResultVo;
import com.kuyu.vo.pcms.SupplierMaterialVo;
import com.kuyu.vo.query.SupplierMaterialQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@AOP_Controller_LOG
@Api(tags = "供应商物料接口")
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
        pcmsSupplierMaterialService.insertPcmsSupplierMaterial(pcmsSupplierMaterialModel,getUserInfo());
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
        return pcmsSupplierMaterialService.findSupplierMaterialByPage(getUserInfo(),query);
    }

    /**
     * 查看刚刚导入供应商物料分页
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查看刚刚导入供应商物料分页",response = SupplierMaterialQuery.class)
    @PostMapping("/findBySupplierMaterialList")
    public ResultVo findBySupplierMaterialList(@RequestBody SupplierMaterialQuery query) throws Exception {
        return pcmsSupplierMaterialService.findBySupplierMaterialByPage(getUserInfo(),query);
    }

    /**
     * 供应商查询供应商物料信息
     * @param query
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "供应商查询供应商物料信息",response = PcmsSupplierMaterialModel.class)
    @PostMapping("/querySupplierMaterialList")
    public ResultVo querySupplierMaterialList(@RequestBody SupplierMaterialVo query) throws Exception {
        return pcmsSupplierMaterialService.querySupplierMaterialList(query);
    }

    /**
     *  市场人员查询供应商物料信息
     * @param supplierMaterialResultVo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "市场人员查询供应商物料信息",response = PcmsSupplierMaterialModel.class)
    @PostMapping("/queryBySupplierMaterialList")
    public ResultVo queryBySupplierMaterialList(@RequestBody SupplierMaterialResultVo supplierMaterialResultVo) throws Exception {
        return pcmsSupplierMaterialService.queryBySupplierMaterialList(supplierMaterialResultVo,getUserInfo());
    }

    /**
     * 供应商物料导出
     * @param vendor_id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取下载的url")
    @GetMapping("/getSupplierMaterialUrl")
    public ResultVo getSupplierMaterialUrl(@RequestParam(value = "vendor_id") String vendor_id) throws Exception {
        String file = null;
        if (StringUtil.isNotNull(vendor_id)) {
            file = pcmsSupplierMaterialService.getSupplierMaterialUrl(vendor_id,getUserInfo());
        }
        return ResultVo.getData(ResultVo.SUCCESS, file);
    }

    /**
     * 确定导入
     * @param vendor_id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "确定导入")
    @GetMapping("/confirmSupplierMaterial")
    public ResultVo confirmSupplierMaterial(@RequestParam(value = "vendor_id") String vendor_id)throws Exception{
        return pcmsSupplierMaterialService.confirmSupplierMaterial(vendor_id,getUserInfo());
    }

    /**
     * 放弃导入
     * @param vendor_id
     * @param url
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "放弃导入",response = PcmsMaterialVersionModel.class)
    @GetMapping("/giveUpSupplierMaterial")
    public ResultVo giveUpSupplierMaterial(@RequestParam(value = "vendor_id") String vendor_id,@RequestParam(value = "url") String url)throws Exception{
        return pcmsSupplierMaterialService.giveUpSupplierMaterial(vendor_id,url,getUserInfo());
    }



}
