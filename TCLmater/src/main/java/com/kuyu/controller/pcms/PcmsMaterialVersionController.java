package com.kuyu.controller.pcms;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.exception.ParamException;
import com.kuyu.model.pcms.PcmsMaterialVersionModel;
import com.kuyu.service.PcmsMaterialVersionService;
import com.kuyu.util.ResultVoUtils;
import com.kuyu.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by pc on 2018/11/26
 */
@AOP_Controller_LOG
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
        return pcmsMaterialVersionService.uploadAndInsert(file,vendor_id);
    }

    /**
     * 物料下载
     * @param url
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "下载",response = PcmsMaterialVersionModel.class)
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public void downloadExcel(@RequestParam("url") String url, @RequestParam("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (url != null) {
            String fileUrl = url.substring(url.lastIndexOf("/")+1);
            //设置文件路径
            File file = new File(filePath , fileUrl);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName +".xls");// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
//                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
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

}
