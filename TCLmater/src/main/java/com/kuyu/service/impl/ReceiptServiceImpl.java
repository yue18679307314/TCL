package com.kuyu.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.pcms.PcmsItemMapper;
import com.kuyu.mapper.pcms.PcmsShowcaseMapper;
import com.kuyu.mapper.pcms.ReceiptMapper;
import com.kuyu.model.pcms.*;
import com.kuyu.service.PcmsUserService;
import com.kuyu.service.ReceiptService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.MaterialResult;
import com.kuyu.vo.pcms.PcmsOthertmVo;
import com.kuyu.vo.pcms.PcmsShopVo;
import com.kuyu.vo.pcms.PcmsShowcaseVo;
import com.kuyu.vo.query.ReceiptQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pc on 2018/11/21
 */
@Service
@Transactional
public class ReceiptServiceImpl extends ServiceImpl<ReceiptMapper, ReceiptModel>
        implements ReceiptService {
    @Autowired
    private PcmsUserService pcmsUserService;

    @Autowired
    private PcmsShowcaseMapper pcmsShowcaseMapper;

    @Autowired
    private PcmsItemMapper pcmsItemMapper;

    @Override
    public ResultVo findReceiptList(ReceiptQuery query) throws Exception {
        if(StringUtil.isEmpty(query.getOpenid())){
            throw new ParamException("openid为空");
        }
        PcmsUserModel pcmsUserModel = new PcmsUserModel();
        pcmsUserModel.setOpenid(query.getOpenid());
        PcmsUserModel pcmsUserModel1 = pcmsUserService.selectPcmsUserModel(pcmsUserModel);
        if(null == pcmsUserModel1){
            throw new ParamException(ResultVo.getData("403","未绑定"));
        }
        query = (ReceiptQuery) CheckParamUtils.trimWithObjectField(query);
        Page<ReceiptModel> page = new Page<>(query.getCurrent(),query.getSize());
        List<ReceiptModel> receiptList = baseMapper.findReceiptList(query,page);
        page.setRecords(receiptList);
        return ResultVo.getDataWithSuccess(page);
    }

    @Override
    public ResultVo getReceiptDetail(Integer itid) throws Exception {
        ReceiptDetailModel receiptDetailModel = baseMapper.getReceiptDetail(itid);
        /**1表示展台展柜，2表示其他终端，3表示广告物料*/
        if(receiptDetailModel.getType() == 1){
            /**门店信息*/
            PcmsShopVo pcmsShopVo = baseMapper.getPcmsShopInfo(itid);
            /**展台展柜信息*/
            PcmsShowcaseVo pcmsShowcaseVo = baseMapper.getPcmsShowcaseInfo(itid);
            receiptDetailModel.setPcmsShopVo(pcmsShopVo);
            receiptDetailModel.setPcmsShowcaseVo(pcmsShowcaseVo);
        }else if(receiptDetailModel.getStatus() == 2){
            /**门店信息*/
            PcmsShopVo pcmsShopVo = baseMapper.getPcmsShopInfo(itid);
            /**其他展台*/
            List<PcmsOthertmVo> list = baseMapper.getPcmsOthertmInfo(itid);
            receiptDetailModel.setPcmsShopVo(pcmsShopVo);
            receiptDetailModel.setPcmsOthertmVoList(list);
        }else{
            /**广告物料*/
            List<MaterialResult> list = baseMapper.getMaterialResultInfo(itid);
            receiptDetailModel.setMaterialResultList(list);
        }
        return ResultVo.getDataWithSuccess(receiptDetailModel);
    }

    @Override
    public ResultVo updateShowcase(PcmsShowcaseVo pcmsShowcaseVo) throws Exception {
        PcmsShowcase pcmsShowcase = pcmsShowcaseMapper.selectByPrimaryKey(pcmsShowcaseVo.getScid());
        if(null != pcmsShowcase){
            pcmsShowcase.setChildren1Linear(pcmsShowcaseVo.getChildren1_linear());
            pcmsShowcase.setChildren1Buildtime(pcmsShowcaseVo.getChildren1_buildtime());
            pcmsShowcase.setChildren1Area(pcmsShowcaseVo.getChildren1_area());
            pcmsShowcaseMapper.updateByPrimaryKey(pcmsShowcase);
        }
        return ResultVo.get(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo updateType(Integer itid) throws Exception {
        PcmsItem pcmsItem  = pcmsItemMapper.selectByPrimaryKey(itid);
        pcmsItem.setStatus(2);
        pcmsItemMapper.updateByPrimaryKey(pcmsItem);
        return ResultVo.get(ResultVo.SUCCESS);
    }

}
