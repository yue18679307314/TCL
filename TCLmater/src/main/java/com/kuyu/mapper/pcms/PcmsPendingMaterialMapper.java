package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsPendingMaterialModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pc on 2018/11/27
 */
public interface PcmsPendingMaterialMapper extends BaseMapper<PcmsPendingMaterialModel> {

    List<PcmsPendingMaterialModel> selectByItid(@Param("itid") Integer itid);

    int insertPendingMaterial(PcmsPendingMaterialModel pcmsPendingMaterialModel);
}
