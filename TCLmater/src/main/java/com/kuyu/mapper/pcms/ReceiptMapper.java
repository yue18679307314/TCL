package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.pcms.ReceiptDetailModel;
import com.kuyu.model.pcms.ReceiptModel;
import com.kuyu.vo.pcms.*;
import com.kuyu.vo.query.ReceiptQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pc on 2018/11/21
 */
public interface ReceiptMapper extends BaseMapper<ReceiptModel> {
    /**分页查询*/
    List<ReceiptModel> findReceiptList(@Param("params") ReceiptQuery query, Page<ReceiptModel> page);
    /***/
    ReceiptDetailModel getReceiptDetail(@Param("itid") Integer itid);
    /**门店信息*/
    PcmsShopVo getPcmsShopInfo(@Param("itid") Integer itid);
    /**展台信息*/
    List<PcmsShowcaseVo> getPcmsShowcaseInfo(@Param("itid") Integer itid);
    /**其他终端信息*/
    List<PcmsOthertmVo> getPcmsOthertmInfo(@Param("itid") Integer itid);
    /**广告物料信息*/
    List<MaterialResult> getMaterialResultInfo(@Param("itid") Integer itid);

    /***/
    ReceiptDetailModel getItemDetail(@Param("itid") Integer itid);

    ReceiptDetailVo getDetail(@Param("itid") Integer itid);
}
