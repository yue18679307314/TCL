package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.pcms.PcmsSupplierUserModel;

/**
 * Created by pc on 2018/11/19
 */
public interface PcmsSupplierUserService extends IService<PcmsSupplierUserModel> {

    public void insertPcmsSupplierUserModel(PcmsSupplierUserModel pcmsSupplierUserModel) throws Exception;
}
