package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsRejectLogModel;
import org.apache.ibatis.annotations.Param;

/**
 * Created by pc on 2018/11/27
 */
public interface PcmsRejectLogMapper extends BaseMapper<PcmsRejectLogModel> {


    PcmsRejectLogModel selectRejectLog(@Param("itid") Integer itid);
}