package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsSettlementModel;

/**
 * Created by pc on 2018/12/8
 */
public interface PcmsSettlementMapper extends BaseMapper<PcmsSettlementModel> {

    PcmsSettlementModel insertSettlement(PcmsSettlementModel pcmsSettlementModel);
}
