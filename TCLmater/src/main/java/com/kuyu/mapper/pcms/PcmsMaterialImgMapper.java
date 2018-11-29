package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsMaterialImgModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pc on 2018/11/29
 */
public interface PcmsMaterialImgMapper extends BaseMapper<PcmsMaterialImgModel> {

    List<PcmsMaterialImgModel> selectById(@Param("id") Integer id);
}
