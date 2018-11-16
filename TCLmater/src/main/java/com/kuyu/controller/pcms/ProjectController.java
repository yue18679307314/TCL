package com.kuyu.controller.pcms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.service.PcmsProjectService;
import com.kuyu.service.TpmProjectService;
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
    @RequestBody String projectvo)throws Exception{
//        return pcmsProjectService.importProjectDetail(projectvo);
    	return null;
    }
    
    
	
}
