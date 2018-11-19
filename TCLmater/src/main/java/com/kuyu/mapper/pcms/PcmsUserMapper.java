package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsUserModel;

/**
 * Created by pc on 2018/11/19
 */
public interface PcmsUserMapper extends BaseMapper<PcmsUserModel> {

    public PcmsUserModel selectPcmsUserModel(PcmsUserModel pcmsUserModel) throws Exception;
}
