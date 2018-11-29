package com.kuyu.controller.pcms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.service.PcmsProjectService;
import com.kuyu.service.TpmProjectService;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.PcmsProjectVo;
import com.kuyu.vo.project.ProjectDetialModelVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "pcms立项接口服务")
@AOP_Controller_LOG
@RequestMapping("/pcmsProject")
public class ProjectController {

	
	@Autowired
    private PcmsProjectService pcmsProjectService;
	
	
	/**
     * 同步立项单
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "同步立项单")//00
    @PostMapping(value = "/import",produces = "application/json;charset=UTF-8")
    public String insertTpmProject(@ApiParam(value = "同步立项单数据", required = true)
    @RequestBody PcmsProjectVo projectvo)throws Exception{
    	
    	System.out.println(JSON.toJSONString(projectvo));
    	
        return pcmsProjectService.importProjectDetail(projectvo);
    }
    
    /**
     * 查看原始立项单
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查看原始立项单")
    @RequestMapping(value = "/detail",produces = "application/json;charset=UTF-8")
    public ResultVo detail(String requestId)throws Exception{
    	PcmsProjectVo result= pcmsProjectService.getProjectDeatil(requestId);
    		
    		return ResultVo.getData(ResultVo.SUCCESS, result);
    }
	
}
