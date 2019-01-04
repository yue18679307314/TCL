package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kuyu.model.pcms.UnspecifiedDetailsModel;
import com.kuyu.vo.pcms.DetailListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pc on 2019/1/4
 */
public interface UnspecifiedDetailsMapper extends BaseMapper<UnspecifiedDetailsModel> {

    List<DetailListVo> selectByAllAndId(@Param("pcms_reconciliation_id") Integer pcms_reconciliation_id);
}
