package com.kuyu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.mapper.pcms.PcmsMaterialVersionMapper;
import com.kuyu.model.pcms.PcmsMaterialVersionModel;
import com.kuyu.service.PcmsMaterialVersionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pc on 2018/11/26
 */
@Service
@Transactional
public class PcmsMaterialVersionServiceImpl extends ServiceImpl<PcmsMaterialVersionMapper, PcmsMaterialVersionModel>
        implements PcmsMaterialVersionService {
}
