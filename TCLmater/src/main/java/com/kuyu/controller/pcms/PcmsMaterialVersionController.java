package com.kuyu.controller.pcms;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.exception.ParamException;
import com.kuyu.model.pcms.PcmsMaterialVersionModel;
import com.kuyu.model.pcms.PcmsSupplierMaterialModel;
import com.kuyu.service.PcmsMaterialVersionService;
import com.kuyu.util.ResultVoUtils;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.UploadExcelVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pc on 2018/11/26
 */
@AOP_Controller_LOG
@Api(tags = "历史物料查询与下载接口")
@RequestMapping("/materialVersion")
public class PcmsMaterialVersionController extends BaseController {

    @Resource
    private PcmsMaterialVersionService pcmsMaterialVersionService;

    @Value("${excel.path}")
    private String filePath;

    /**
     * 物料上传
     * @param file
     * @param vendor_id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "上传",response = PcmsMaterialVersionModel.class)
    @PostMapping("/uploadExcel")
    public ResultVo uploadExcel(@RequestParam(value="file", required=false) MultipartFile file,@RequestParam(value="vendor_id") String vendor_id) throws Exception {
        int limitSize = 5 * 1024 * 1024; //最大为4M
        if(file.isEmpty()) {
            throw new ParamException(ResultVoUtils.fail("文件路径及完整文件名为空:"+file));
        }
        if(file.getSize() > limitSize){
            throw new ParamException(ResultVoUtils.fail("文件太大:"+file));
        }
        if(!file.getOriginalFilename().toLowerCase().endsWith(".xls") && !file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
            throw new ParamException(ResultVoUtils.fail("文件不是Excel:"+file));
        }
        return pcmsMaterialVersionService.uploadAndInsert(file,vendor_id,getUserInfo());
    }

    /**
     * 查看历史版本物料清单
     * @param vendor_id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查看历史版本物料清单",response = PcmsMaterialVersionModel.class)
    @GetMapping("/selectMaterialVersion")
    public ResultVo selectMaterialVersion(@RequestParam(value = "vendor_id") String vendor_id) throws Exception{
        return pcmsMaterialVersionService.selectMaterialVersion(vendor_id,getUserInfo());
    }

    /**
     * 确定导入
     * @param uploadExcelVo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "确定导入",response = PcmsSupplierMaterialModel.class)
    @PostMapping("/confirmSupplierMaterial")
    public ResultVo confirmSupplierMaterial(@RequestBody UploadExcelVo uploadExcelVo)throws Exception{
        try{
            String vendor_id = uploadExcelVo.getVendor_id();
            String s = vendor_id.replace("&quot;","\"");
            String st = s.replace("&lt;","<");
            ObjectMapper mapper = new ObjectMapper();
            List<PcmsSupplierMaterialModel> beanList = mapper.readValue(st, new TypeReference<List<PcmsSupplierMaterialModel>>() {});
            return pcmsMaterialVersionService.confirmSupplierMaterial(beanList,getUserInfo());
        }catch (Exception e){
            String vendor_id = uploadExcelVo.getVendor_id();
            String url =  uploadExcelVo.getUrl();
            pcmsMaterialVersionService.giveUpSupplierMaterial(vendor_id,url,getUserInfo());
            throw new ParamException("导入失败");
        }
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
        return pcmsMaterialVersionService.giveUpSupplierMaterial(vendor_id,url,getUserInfo());
    }
}
