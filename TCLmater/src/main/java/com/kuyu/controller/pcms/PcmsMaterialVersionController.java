package com.kuyu.controller.pcms;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.exception.ParamException;
import com.kuyu.model.pcms.PcmsMaterialVersionModel;
import com.kuyu.util.ResultVoUtils;
import com.kuyu.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jca.context.SpringContextResourceAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.swing.*;
import java.io.File;

/**
 * Created by pc on 2018/11/26
 */
@AOP_Controller_LOG
@RequestMapping("/materialVersion")
public class PcmsMaterialVersionController extends BaseController {

    @Value("${excel.path}")
    private String filePath;
    @Value("${excel.url}")
    private String fileUrl;
    @ApiOperation(value = "上传",response = PcmsMaterialVersionModel.class)
    @PostMapping("/uploadExcel")
    public ResultVo uploadExcel(@RequestParam(value="file", required=false) MultipartFile file) throws Exception {
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
        File tempfile = new File(filePath);
        if(!tempfile.exists()){
            tempfile.mkdirs();
        }
        String path = filePath + file.getOriginalFilename();
        File newFile = new File(path);
        file.transferTo(newFile);
        return null;
    }
}
