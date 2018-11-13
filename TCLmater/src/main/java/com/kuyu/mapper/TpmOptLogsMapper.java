package com.kuyu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.TpmOptLogsModel;
import com.kuyu.vo.OperateLogsVo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author tang_zhen
 * @Date 2017/9/26
 * @Description
 */
@Mapper
public interface TpmOptLogsMapper  extends BaseMapper<TpmOptLogsModel>{

   void  insertOptLogsData(TpmOptLogsModel tpmOptLogsModel);

    List<TpmOptLogsModel> selectLogsByTypeOrTime(OperateLogsVo operateLogsVo, Page<TpmOptLogsModel> page);

    List<TpmOptLogsModel> selectLogs(OperateLogsVo operateLogsVo);
    List<TpmOptLogsModel> selectLogsPages(OperateLogsVo operateLogsVo,Page<TpmOptLogsModel> page);

    List<TpmOptLogsModel> branchAdminSelectLogsByTypeOrTime(@Param("personCode")String personCode, @Param("operateLogsVo")OperateLogsVo operateLogsVo, Page<TpmOptLogsModel> page);
}
