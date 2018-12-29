package com.kuyu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.mapper.pcms.PcmsCurrentDetailMapper;
import com.kuyu.model.pcms.PcmsCurrentDetailModel;
import com.kuyu.service.PcmsCurrentDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pc on 2018/12/27
 */
@Service
@Transactional
public class PcmsCurrentDetailServiceImpl extends ServiceImpl<PcmsCurrentDetailMapper, PcmsCurrentDetailModel>
        implements PcmsCurrentDetailService {
}
