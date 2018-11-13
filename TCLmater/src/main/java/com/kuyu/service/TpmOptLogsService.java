package com.kuyu.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.TpmOptLogsModel;
import com.kuyu.vo.OperateLogsVo;
import com.kuyu.vo.ResultVo;

/**
 * @Author tang_zhen
 * @Date 2017/9/26
 * @Description service接口
 */
public interface TpmOptLogsService extends IService<TpmOptLogsModel>{
    /**
     * 插入日志信息
     * @param tpmOptLogsModel
     * @return
     * @throws Exception
     */
    ResultVo insertOptLogs(TpmOptLogsModel tpmOptLogsModel) throws  Exception;

    /**
     * 查询日志信息
     *
     *
     * @param person_code
     * @param personType
     * @param operateLogsVo
     * @return
     * @throws Exception
     */
    Page<TpmOptLogsModel> selectLogsData(String person_code, Integer personType, OperateLogsVo operateLogsVo) throws Exception;

    ResultVo importService(OperateLogsVo operateLogsVo) throws  Exception;
}
