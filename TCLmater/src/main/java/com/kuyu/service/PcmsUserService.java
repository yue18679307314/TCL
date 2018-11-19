package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.pcms.PcmsUserModel;

/**
 * Created by pc on 2018/11/19
 */
public interface PcmsUserService extends IService<PcmsUserModel> {

    public void insertPcmsUserModel(PcmsUserModel pcmsUserModel) throws Exception;

    public PcmsUserModel selectPcmsUserModel(PcmsUserModel pcmsUserModel) throws Exception;
}
