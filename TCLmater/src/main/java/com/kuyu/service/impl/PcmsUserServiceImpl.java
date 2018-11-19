package com.kuyu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.mapper.pcms.PcmsUserMapper;
import com.kuyu.model.pcms.PcmsUserModel;
import com.kuyu.service.PcmsUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pc on 2018/11/19
 */
@Service
@Transactional
public class PcmsUserServiceImpl extends ServiceImpl<PcmsUserMapper, PcmsUserModel>
        implements PcmsUserService {


    @Override
    public void insertPcmsUserModel(PcmsUserModel pcmsUserModel) throws Exception {
        baseMapper.insert(pcmsUserModel);
    }

    @Override
    public PcmsUserModel selectPcmsUserModel(PcmsUserModel pcmsUserModel) throws Exception {
        return baseMapper.selectPcmsUserModel(pcmsUserModel);
    }
}
