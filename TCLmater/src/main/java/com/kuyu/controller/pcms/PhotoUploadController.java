package com.kuyu.controller.pcms;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.util.ImageMarkLogoByIcon;
import com.kuyu.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2018/12/1
 */
@Api(tags = "上传图片")
@AOP_Controller_LOG
@RequestMapping("/photoUpload")
public class PhotoUploadController extends BaseController {

    @Value("${image.path}")
    private String filePath;
    @Value("${image.url}")
    private String fileUrl;

    @RequestMapping(value="/upload", method= RequestMethod.POST)
    @ApiOperation("上传图片")
    @ResponseBody
    public ResultVo handleFileUpload(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        ResultVo resultVo = new ResultVo("0", "success");
        Map<String, Object> resultdata = new HashMap<String, Object>();
        int limitSize = 5 * 1024 * 1024; //最大为4M
        if(files.size()>0)
        {
            MultipartFile file = files.get(0);
            if (!file.isEmpty())
            {
                // 文件大小
                if (file.getSize() > limitSize) {
                    resultVo = ResultVo.get(ResultVo.FILE_TO_BIG);
                    return resultVo;
                }
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
                // 文件格式
                if ("jpg".equalsIgnoreCase(suffix) || "gif".equalsIgnoreCase(suffix)|| "png".equalsIgnoreCase(suffix) || "jpeg".equalsIgnoreCase(suffix)|| "bmp".equalsIgnoreCase(suffix))
                {
                    try {
                        BufferedImage image = ImageIO.read(file.getInputStream());
                        Date date = new Date();
                        String tempName = date.getTime()+"."+suffix;
                        File tempfile = new File(filePath+"/"+tempName);
                        if(!tempfile.exists()){
                            tempfile.mkdirs();
                        }
                        ImageIO.write(image, suffix, tempfile);
                        resultdata.put("fileUrl", fileUrl+"/"+tempfile.getName());
                        resultVo.setData(resultdata);
                    } catch (Exception e) {
                        return ResultVo.get(ResultVo.FAIL);
                    }
                } else {
                    resultVo = ResultVo.get(ResultVo.SUFFIX_ERROR);
                    return resultVo;
                }
            } else {
                return ResultVo.get(ResultVo.FILE_IS_NULL);
            }
        }
        return resultVo;
    }
}
