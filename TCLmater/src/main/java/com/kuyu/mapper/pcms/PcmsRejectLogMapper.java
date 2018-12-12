package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsRejectLogModel;
import com.kuyu.vo.pcms.PcmsRejectLogVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pc on 2018/11/27
 */
public interface PcmsRejectLogMapper extends BaseMapper<PcmsRejectLogModel> {


    PcmsRejectLogModel selectRejectLog(@Param("itid") Integer itid);


    List<PcmsRejectLogModel> selectRejectLogList(@Param("itid") Integer itid);

    List<PcmsRejectLogVo> queryRejectLogList(@Param("itid") Integer itid);

}
