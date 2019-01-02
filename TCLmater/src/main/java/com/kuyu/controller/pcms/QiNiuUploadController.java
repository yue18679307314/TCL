package com.kuyu.controller.pcms;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.controller.BaseController;
import com.kuyu.service.PcmsQiNiuService;
import com.kuyu.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 2018/12/26
 */
@Api(tags = "上传视频")
@AOP_Controller_LOG
@RequestMapping("/qiNiuUpload")
public class QiNiuUploadController extends BaseController {

    @Resource
    private PcmsQiNiuService pcmsQiNiuService;

//    @RequestMapping(value = "uploadFile")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ApiOperation("上传视频")
    @ResponseBody
    public ResultVo uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        ResultVo resultVo = new ResultVo("0", "success");
        Map<String, Object> resultdata = new HashMap<String, Object>();
        if (!file.isEmpty()) {
            try {
                String result = pcmsQiNiuService.put(file.getInputStream());
                resultdata.put("url",result);
                resultVo.setData(resultdata);
                return resultVo;
            } catch (IOException e) {
                e.printStackTrace();
                return ResultVo.get(ResultVo.FILE_TO_BIG);
            }
        } else {
            return ResultVo.get(ResultVo.FILE_TO_BIG);
        }
    }

    @RequestMapping(value = "/uploadFile1", method = RequestMethod.GET)
    @ApiOperation("获取token")
    @ResponseBody
    protected Map<String,String> doPost(){
        return pcmsQiNiuService.getQiniuInfo();
    }

}
