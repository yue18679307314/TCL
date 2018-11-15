package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.PcmsSupplierModel;
import com.kuyu.vo.PcmsSupplierVo;

/**
 * Created by pc on 2018/11/14
 */
public interface PcmsSupplierService extends IService<PcmsSupplierModel> {

    public void insertPcmsSupplier(PcmsSupplierVo pcmsSupplierVo) throws Exception;
}
