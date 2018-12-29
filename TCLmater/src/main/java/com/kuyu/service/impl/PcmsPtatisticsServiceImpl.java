package com.kuyu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.mapper.pcms.PcmsPtatisticsMapper;
import com.kuyu.model.pcms.PcmsPtatisticsModel;
import com.kuyu.service.PcmsPtatisticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pc on 2018/12/28
 */
@Service
@Transactional
public class PcmsPtatisticsServiceImpl extends ServiceImpl<PcmsPtatisticsMapper, PcmsPtatisticsModel>
        implements PcmsPtatisticsService {
}
