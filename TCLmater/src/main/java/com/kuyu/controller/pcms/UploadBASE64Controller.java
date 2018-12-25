package com.kuyu.controller.pcms;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.model.pcms.PcmsMaterialVersionModel;
import com.kuyu.util.BASE64DecodedMultipartFile;
import com.kuyu.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 2018/12/24
 */
@AOP_Controller_LOG
@Api(tags = "BASE64上传图片")
@RequestMapping("/base")
public class UploadBASE64Controller {

    @Value("${image.path}")
    private String filePath;
    @Value("${image.url}")
    private String fileUrl;

    @ApiOperation(value = "上传",response = ResultVo.class)
    @PostMapping("/upload")
    public ResultVo uploadBase(@RequestParam(value = "fileStr",required=false)String fileStr) throws Exception {
        fileStr = fileStr.replace(" ", "+");
        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(fileStr);
        ResultVo resultVo = new ResultVo("0", "success");
        Map<String, Object> resultdata = new HashMap<String, Object>();
        int limitSize = 5 * 1024 * 1024; //最大为4M
        if (!file.isEmpty()) {
            // 文件大小
            if (file.getSize() > limitSize) {
                resultVo = ResultVo.get(ResultVo.FILE_TO_BIG);
                return resultVo;
            }
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
            // 文件格式
            if ("jpg".equalsIgnoreCase(suffix) || "gif".equalsIgnoreCase(suffix)|| "png".equalsIgnoreCase(suffix) || "jpeg".equalsIgnoreCase(suffix)|| "bmp".equalsIgnoreCase(suffix)) {
                try {
                    BufferedImage image = ImageIO.read(file.getInputStream());
                    String tempName = new Date().getTime()+"."+suffix;
                    File tempfile = new File(filePath+"/"+tempName);
                    if(!tempfile.exists()){
                        tempfile.mkdirs();
                    }
                    ImageIO.write(image, suffix, tempfile);
                    resultdata.put("fileUrl", fileUrl+"/"+tempName);
                    resultVo.setData(resultdata);
                } catch (Exception e) {
                    return ResultVo.get(ResultVo.FAIL);
                }
            }else {
                resultVo = ResultVo.get(ResultVo.SUFFIX_ERROR);
                return resultVo;
            }
        } else {
            return ResultVo.get(ResultVo.FILE_IS_NULL);
        }
        return resultVo;
    }
}
