package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsFeedbackModel;

/**
 * Created by pc on 2018/12/10
 */
public interface PcmsFeedbackMapper extends BaseMapper<PcmsFeedbackModel> {

    PcmsFeedbackModel insertFeedback(PcmsFeedbackModel pcmsFeedbackModel);
}
