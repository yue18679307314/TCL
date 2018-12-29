package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsPtatisticsModel;
import org.apache.ibatis.annotations.Param;

/**
 * Created by pc on 2018/12/28
 */
public interface PcmsPtatisticsMapper extends BaseMapper<PcmsPtatisticsModel> {

    PcmsPtatisticsModel selectByReconciliationId(@Param("id") Integer id);
}
