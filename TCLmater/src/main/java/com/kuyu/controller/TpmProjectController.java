package com.kuyu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.service.TpmProjectService;
import com.kuyu.task.TaskService;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ActivityDetailVo;
import com.kuyu.vo.ProjectActivityQuery;
import com.kuyu.vo.ProjectDetailVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.project.ProjectDetialModelVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 *
 *
 * @Author jt_L
 * @Date 2017-09-19
 * @Description 立项单Controller
 */
@Api(tags = "立项接口服务")
@AOP_Controller_LOG
@RequestMapping("/tpmProject")
public class TpmProjectController extends BaseController{

    @Autowired
    private TpmProjectService tpmProjectServiceImpl;

    @Autowired
    private TaskService taskServiceImpl;

	/**
     * 导入立项单
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "导入立项单")//00
    @PostMapping(value = "/out/import",produces = "application/json;charset=UTF-8")
    public String insertTpmProject(@ApiParam(value = "导入申请立项表VO", required = true)@RequestBody ProjectDetialModelVo projectvo)throws Exception{
        return tpmProjectServiceImpl.importProjectDetail(projectvo);
    }



    @ApiOperation(value = "立项申请单列表分页",response = ActivityDetailVo.class)
    @PostMapping("/project/activity/page/")
    public ResultVo findActivityDetailPageByQuery(@RequestBody ProjectActivityQuery query) throws Exception {
        return tpmProjectServiceImpl.findActivityDetailPageByQuery(getUserInfo(),query);
    }


    @ApiOperation(value = "立项申请单详情页",response = ProjectDetailVo.class)
    @GetMapping("/project/detial/{id}")
    public ResultVo findProjectDetailByRequestId(@ApiParam(value = "立项申请ID",required = true)@PathVariable("id") String requestId) throws Exception {
        return tpmProjectServiceImpl.findProjectDetailByRequestId(getUserInfo(),requestId);
    }

    @ApiOperation(value = "借款单导入")//00
    @GetMapping(value = "/out/importborrowmoney/",produces = "application/json;charset=UTF-8")
    public String taskImportBorrowMoney() throws Exception {
        return StringUtil.toJsonResultVo(taskServiceImpl.importBorrowMoney());
    }

}
