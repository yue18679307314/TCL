package com.kuyu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.mapper.pcms.PcmsSupplierUserMapper;
import com.kuyu.mapper.pcms.PcmsUserMapper;
import com.kuyu.model.pcms.PcmsSupplierUserModel;
import com.kuyu.model.pcms.PcmsUserModel;
import com.kuyu.service.PcmsSupplierUserService;
import com.kuyu.vo.pcms.PcmsSupplierUserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pc on 2018/11/19
 */
@Service
@Transactional
public class PcmsSupplierUserServiceImpl extends ServiceImpl<PcmsSupplierUserMapper, PcmsSupplierUserModel>
        implements PcmsSupplierUserService {
    @Resource
    private PcmsUserMapper pcmsUserMapper;
    @Override
    public void insertPcmsSupplierUserModel(PcmsSupplierUserModel pcmsSupplierUserModel) throws Exception {
        baseMapper.insert(pcmsSupplierUserModel);
    }

    @Override
    public List<PcmsSupplierUserVo> findBySupplier(String vendor_id) throws Exception {
        return baseMapper.findBySupplier(vendor_id);
    }

    @Override
    public PcmsUserModel selectUserByOpenId(String openid) {
        PcmsUserModel pcmsUserModel = pcmsUserMapper.selectUserByOpenId(openid);
        return pcmsUserModel;
    }
}
