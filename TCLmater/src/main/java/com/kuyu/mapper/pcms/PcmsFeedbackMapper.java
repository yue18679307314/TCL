package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsFeedbackModel;
import com.kuyu.vo.pcms.FeedbackVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pc on 2018/12/10
 */
public interface PcmsFeedbackMapper extends BaseMapper<PcmsFeedbackModel> {

    PcmsFeedbackModel insertFeedback(PcmsFeedbackModel pcmsFeedbackModel);

    List<FeedbackVo> selectByTransferId(@Param("id") Integer id);
}
