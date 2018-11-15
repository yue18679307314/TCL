package com.kuyu.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.PcmsSupplierModel;
import com.kuyu.vo.PcmsSupplierVo;

/**
 * Created by pc on 2018/11/14
 */
public interface PcmsSupplierMapper extends BaseMapper<PcmsSupplierModel> {
    public Boolean insertPcmsSupplier(PcmsSupplierVo pcmsSupplierVo);
}
