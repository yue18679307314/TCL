package com.kuyu.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.TpmProjectModel;
import com.kuyu.vo.ProjectActivityQuery;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.project.ProjectDetialModelVo;

/**
 *
 * @Author jt_L
 * @Date 2017-09-19
 * @Description 立项单 Service类
 */
public interface TpmProjectService extends IService<TpmProjectModel> {

    /**
     * 导入立项单
     * @param vo 立项单JSON数据
     * @return
     */
    String importProjectDetail(ProjectDetialModelVo vo)throws Exception;

    ResultVo findActivityDetailPageByQuery(LoginUserInfo userInfo, ProjectActivityQuery query)throws Exception;

    ResultVo findProjectDetailByRequestId(LoginUserInfo userInfo, String requestId)throws Exception;

}
