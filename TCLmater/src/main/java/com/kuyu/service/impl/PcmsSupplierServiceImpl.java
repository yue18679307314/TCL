package com.kuyu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.mapper.PcmsSupplierMapper;
import com.kuyu.model.PcmsSupplierModel;
import com.kuyu.service.PcmsSupplierService;
import com.kuyu.vo.PcmsSupplierVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pc on 2018/11/14
 */

@Service
@Transactional
public class PcmsSupplierServiceImpl extends ServiceImpl<PcmsSupplierMapper, PcmsSupplierModel> implements PcmsSupplierService {

    @Override
    public void insertPcmsSupplier(PcmsSupplierVo pcmsSupplierVo) throws Exception {
        baseMapper.insertPcmsSupplier(pcmsSupplierVo);
    }
}
