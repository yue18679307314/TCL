package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.PcmsFeedbackImgModel;
import com.kuyu.vo.pcms.FeedbackImageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pc on 2018/12/10
 */
public interface PcmsFeedbackImgMapper extends BaseMapper<PcmsFeedbackImgModel> {

    List<FeedbackImageVo> selectByFeedbackId(@Param("id") Integer id);

}
