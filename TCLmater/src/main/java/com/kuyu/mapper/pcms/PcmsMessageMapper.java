package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsMessageModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pc on 2019/1/3
 */
public interface PcmsMessageMapper extends BaseMapper<PcmsMessageModel> {

    List<PcmsMessageModel> selectByVendorId(@Param("openid") String openid);

    List<PcmsMessageModel> selectByState(@Param("openid") String openid,@Param("state") Integer state);
}
