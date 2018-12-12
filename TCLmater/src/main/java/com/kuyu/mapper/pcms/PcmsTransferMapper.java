package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.pcms.PcmsItemLog;
import com.kuyu.model.pcms.PcmsTransferModel;
import com.kuyu.vo.pcms.Transfer;
import com.kuyu.vo.pcms.TransferDetailVo;
import com.kuyu.vo.pcms.TransferVo;
import com.kuyu.vo.query.TransferQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pc on 2018/12/10
 */
public interface PcmsTransferMapper extends BaseMapper<PcmsTransferModel> {

    List<TransferVo> selectByState(@Param("params") TransferQuery query, Page<TransferVo> page);


    TransferDetailVo selectFeedbackDetail(@Param("id") Integer id);


    List<PcmsItemLog> selectItemLog(@Param("itid") Integer itid);

    List<Transfer> queryDetailByPendingId(@Param("pendingId") Integer pendingId);
}
