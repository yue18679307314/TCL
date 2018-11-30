package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsMaterialVersionModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pc on 2018/11/26
 */
public interface PcmsMaterialVersionMapper extends BaseMapper<PcmsMaterialVersionModel> {

    List<PcmsMaterialVersionModel> selectMaterialVersion(@Param("vendor_id") String vendor_id,@Param("company") String company);
}
