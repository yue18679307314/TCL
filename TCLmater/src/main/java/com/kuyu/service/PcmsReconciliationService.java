package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.pcms.PcmsCurrentDetailModel;
import com.kuyu.model.pcms.PcmsIinitializationModel;
import com.kuyu.model.pcms.PcmsMessageModel;
import com.kuyu.model.pcms.PcmsReconciliationModel;
import com.kuyu.vo.ReconciliationVo;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.PcmsReconciliationVo;
import com.kuyu.vo.pcms.ReplyMessageVo;
import com.kuyu.vo.query.ReconciliationQuery;

import java.util.List;

/**
 * Created by pc on 2018/12/26
 */
public interface PcmsReconciliationService extends IService<PcmsReconciliationModel> {

    //
    void selectByTime();
    //分页
    ResultVo findReconciliationList(LoginUserInfo userInfo,ReconciliationQuery query)throws Exception;
    //往来数据列表
    ResultVo selectCurrentDetail(Integer id,LoginUserInfo userInfo);
    //新增往来数据
    ResultVo addCurrentDetail(PcmsCurrentDetailModel pcmsCurrentDetailModel);
    //确定生成
    ResultVo addIinitialization(PcmsIinitializationModel query,LoginUserInfo userInfo);
    //发起对账
    ResultVo selectReconciliationList(String[] ids);
    //确定发起对账
    ResultVo sureReconciliation(List<PcmsReconciliationVo> list);
    //获取对账单对账函
    ResultVo getAccountStatement(Integer id,LoginUserInfo userInfo);
    //确定对账
    ResultVo confirmReconciliation(Integer id);
    //发送未结明细
    ResultVo sendDetail(PcmsMessageModel pcmsMessageModel);
    //根据供应商ID查询消息记录
    ResultVo selectByVendorId(String openid);
    //查看消息详情
    ResultVo selectMessageDetail(Integer id,String company);
    //供应商消息回复
    ResultVo replyMessage(ReplyMessageVo replyMessageVo);
    //未结明细列表
    ResultVo selectDetailList(Integer id);
    //查看物料信息
    ResultVo selectPendingMaterial(Integer id);
    //有付款记录,但没有点击对账的,自动对账
    void automaticReconciliation();
    //自动统计表上上个月余额为0的
    void automaticStatistics();
    //自动生成未结明细记录
    void automaticDetailList();


}
