package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsTovoidItemModel;
import com.kuyu.vo.pcms.PcmsTovoidItemVo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by pc on 2018/12/6
 */
public interface PcmsTovoidItemMapper extends BaseMapper<PcmsTovoidItemModel> {

    PcmsTovoidItemVo selectdetailByItid(@Param("itid") Integer itid);
}
