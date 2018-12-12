package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.pcms.PcmsPendingMaterialModel;
import com.kuyu.model.pcms.PcmsRejectLogModel;
import com.kuyu.model.pcms.PcmsTransferModel;
import com.kuyu.model.pcms.ReceiptModel;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.PcmsShowcaseVo;
import com.kuyu.vo.query.FeedbackQuery;
import com.kuyu.vo.query.ReceiptQuery;
import com.kuyu.vo.query.SettlementQuery;
import com.kuyu.vo.query.TransferQuery;

import java.util.List;

/**
 * Created by pc on 2018/11/21
 */
public interface ReceiptService extends IService<ReceiptModel> {

    /**分页查询*/
    ResultVo findReceiptList(ReceiptQuery query)throws Exception;

    /**挂单详情*/
    ResultVo getReceiptDetail(Integer itid)throws Exception;

    /**修改展台展柜信息*/
    ResultVo updateShowcase(PcmsShowcaseVo pcmsShowcaseVo)throws Exception;

    /**根据itid修改状态*/
    ResultVo updateType(Integer itid)throws Exception;

    /**添加待验收物料*/
    ResultVo addPendingMaterial(PcmsPendingMaterialModel pcmsPendingMaterialModel)throws Exception;

    /**修改待验收物料*/
    ResultVo updatePendingMaterial(PcmsPendingMaterialModel pcmsPendingMaterialModel)throws Exception;

    /**删除待验收物料*/
    ResultVo deletePendingMaterial(Integer id)throws Exception;

    /**删除待验收物料*/
    ResultVo queryPendingMaterial(Integer id)throws Exception;

    /**查看物料被驳回物料信息*/
    ResultVo selectRejectLog(Integer itid)throws Exception;

    /**市场人员查看立项单详情*/
    ResultVo getItemDetail(Integer itid)throws Exception;

    /**市场人员查看立项单详情*/
    ResultVo getDetail(Integer itid)throws Exception;

    /**市场人员驳回验收单*/
    ResultVo doReject(PcmsRejectLogModel pcmsRejectLogModel/*, LoginUserInfo userInfo*/)throws Exception;

    /**市场人员验收成功*/
    ResultVo doRejectSuccess(Integer itid/*, LoginUserInfo userInfo*/)throws Exception;

    /**市场人员查看立项物料清单*/
    ResultVo selectPendingMaterial(Integer itid)throws Exception;

    /**市场人员修改立项物料清单*/
    ResultVo updatePendingMaterialFor(List<PcmsPendingMaterialModel> list)throws Exception;

    /**根据ITID查询可转办的物料清单*/
    ResultVo selectPendingMaterialByItid(Integer itid)throws Exception;

    /**根据姓名查询员工*/
    ResultVo selectByName(String name,LoginUserInfo userInfo)throws Exception;

    /**日志信息*/
    ResultVo selectItemLog(Integer itid)throws Exception;

    /**市场人员查询批量结算列表*/
    ResultVo selectSettlement(SettlementQuery query)throws Exception;

    /**转办*/
    ResultVo addTransfer(PcmsTransferModel pcmsTransferModel)throws Exception;

    /**转办管理*/
    ResultVo selectTransfer(TransferQuery query,LoginUserInfo userInfo)throws Exception;

    /**反馈*/
    ResultVo addFeedback(FeedbackQuery feedbackQuery)throws Exception;

    /**反馈详情*/
    ResultVo selectFeedbackDetail(Integer id)throws Exception;



}
