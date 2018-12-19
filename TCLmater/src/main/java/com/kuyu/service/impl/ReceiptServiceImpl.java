package com.kuyu.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.pcms.*;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.pcms.*;
import com.kuyu.service.PcmsUserService;
import com.kuyu.service.ReceiptService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.HttpRequest;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.pcms.*;
import com.kuyu.vo.query.FeedbackQuery;
import com.kuyu.vo.query.ReceiptQuery;
import com.kuyu.vo.query.SettlementQuery;
import com.kuyu.vo.query.TransferQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pc on 2018/11/21
 */
@Service
@Transactional
public class ReceiptServiceImpl extends ServiceImpl<ReceiptMapper, ReceiptModel>
        implements ReceiptService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${image.path}")
    private String filePath;

    @Value("${tpm.shareUser.url}")
    private String tpmUrl;

    @Autowired
    private PcmsUserService pcmsUserService;

    @Resource
    private PcmsShowcaseMapper pcmsShowcaseMapper;

    @Resource
    private PcmsItemMapper pcmsItemMapper;

    @Resource
    private PcmsPendingMaterialMapper pcmsPendingMaterialMapper;

    @Resource
    private PcmsRejectLogMapper pcmsRejectLogMapper;

    @Resource
    private PcmsMaterialImgMapper pcmsMaterialImgMapper;
    
    @Resource
    private PcmsItemLogMapper pcmsItemLogMapper;

    @Resource
    private PcmsTransferMapper pcmsTransferMapper;

    @Resource
    private PcmsFeedbackMapper pcmsFeedbackMapper;

    @Resource
    private PcmsFeedbackImgMapper pcmsFeedbackImgMapper;

    @Resource
    private PcmsTovoidItemMapper pcmsTovoidItemMapper;

    @Resource
    private PcmsMaterialMapper pcmsMaterialMapper;
    @Override
    public ResultVo findReceiptList(ReceiptQuery query) throws Exception {
        if(null == query.getOpenid() || "".equals(query.getOpenid())){
            throw new ParamException(ResultVo.getData("403","未绑定"));
        }
        /**查询该用户是否已绑定*/
        PcmsUserModel pcmsUserModel = new PcmsUserModel();
        pcmsUserModel.setOpenid(query.getOpenid());
        PcmsUserModel pcmsUserModel1 = pcmsUserService.selectPcmsUserModel(pcmsUserModel);
        if(null == pcmsUserModel1){
            throw new ParamException(ResultVo.getData("403","未绑定"));
        }
        query.setOpenid(query.getOpenid());
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
        if((receiptDetailModel.getType() == 1 && receiptDetailModel.getStatus()==2) || (receiptDetailModel.getType() == 1 && receiptDetailModel.getStatus()==4)){
            /**门店信息*/
            PcmsShopVo pcmsShopVo = baseMapper.getPcmsShopInfo(itid);
            /**展台展柜信息*/
            PcmsShowcaseVo pcmsShowcaseVo = baseMapper.getPcmsShowcaseInfo(itid);
            /**物料信息*/
            List<PcmsPendingMaterialModel> pcmsPendingMaterialModelList =  new ArrayList<>();
            List<PcmsPendingMaterialModel> list =  pcmsPendingMaterialMapper.selectByItid(itid);
            /**上传的图片信息*/
            for(PcmsPendingMaterialModel pcmsPendingMaterialModel : list){
                List<PcmsMaterialImgModel> pcmsMaterialImg = pcmsMaterialImgMapper.selectById(pcmsPendingMaterialModel.getId());
                pcmsPendingMaterialModel.setImgList(pcmsMaterialImg);
                pcmsPendingMaterialModelList.add(pcmsPendingMaterialModel);
            }
            /**获取驳回信息*/
            if(4 == receiptDetailModel.getStatus()){
                List<PcmsRejectLogModel> pcmsRejectLogModel = pcmsRejectLogMapper.selectRejectLogList(itid);
                receiptDetailModel.setPcmsRejectLogModelList(pcmsRejectLogModel);
            }
            receiptDetailModel.setPcmsShopVo(pcmsShopVo);
            receiptDetailModel.setPcmsShowcaseVo(pcmsShowcaseVo);
            receiptDetailModel.setPcmsPendingMaterialModelList(pcmsPendingMaterialModelList);
        }else if((receiptDetailModel.getType() == 1 && receiptDetailModel.getStatus()==0)){
            /**门店信息*/
            PcmsShopVo pcmsShopVo = baseMapper.getPcmsShopInfo(itid);
            /**展台展柜信息*/
            PcmsShowcaseVo pcmsShowcaseVo = baseMapper.getPcmsShowcaseInfo(itid);
            receiptDetailModel.setPcmsShopVo(pcmsShopVo);
            receiptDetailModel.setPcmsShowcaseVo(pcmsShowcaseVo);
        }else if(receiptDetailModel.getType() == 1 && receiptDetailModel.getStatus()==3){
            /**物料信息*/
            List<PcmsPendingMaterialModel> list =  pcmsPendingMaterialMapper.selectByItid(itid);
            receiptDetailModel.setPcmsPendingMaterialModelList(list);
        }else if(receiptDetailModel.getType() == 1 && receiptDetailModel.getStatus()==1){
            /**门店信息*/
            PcmsShopVo pcmsShopVo = baseMapper.getPcmsShopInfo(itid);
            /**展台展柜信息*/
            PcmsShowcaseVo pcmsShowcaseVo = baseMapper.getPcmsShowcaseInfo(itid);
            /**物料信息*/
            List<PcmsPendingMaterialModel> pcmsPendingMaterialModelList =  new ArrayList<>();
            List<PcmsPendingMaterialModel> list =  pcmsPendingMaterialMapper.selectByItid(itid);
            /**上传的图片信息*/
            if(null == list || list.size() == 0){
                PcmsPendingMaterialModel pcmsPendingMaterialModel = new PcmsPendingMaterialModel();
                list.add(pcmsPendingMaterialModel);
            }else{
                for(PcmsPendingMaterialModel pcmsPendingMaterialModel : list){
                    List<PcmsMaterialImgModel> pcmsMaterialImg = pcmsMaterialImgMapper.selectById(pcmsPendingMaterialModel.getId());
                    pcmsPendingMaterialModel.setImgList(pcmsMaterialImg);
                    pcmsPendingMaterialModelList.add(pcmsPendingMaterialModel);
                }
            }
            receiptDetailModel.setPcmsShopVo(pcmsShopVo);
            receiptDetailModel.setPcmsShowcaseVo(pcmsShowcaseVo);
            receiptDetailModel.setPcmsPendingMaterialModelList(pcmsPendingMaterialModelList);
        }
        if((receiptDetailModel.getType() == 2 && receiptDetailModel.getStatus()==2) || (receiptDetailModel.getType() == 2 && receiptDetailModel.getStatus()==4)){
            /**门店信息*/
            PcmsShopVo pcmsShopVo = baseMapper.getPcmsShopInfo(itid);
            /**其他展台*/
            List<PcmsOthertmVo> list = baseMapper.getPcmsOthertmInfo(itid);
            /**物料信息*/
            List<PcmsPendingMaterialModel> pcmsPendingMaterialModelList =  new ArrayList<>();
            List<PcmsPendingMaterialModel> list1 =  pcmsPendingMaterialMapper.selectByItid(itid);
            /**上传的图片信息*/
            for(PcmsPendingMaterialModel pcmsPendingMaterialModel : list1){
                List<PcmsMaterialImgModel> pcmsMaterialImg = pcmsMaterialImgMapper.selectById(pcmsPendingMaterialModel.getId());
                pcmsPendingMaterialModel.setImgList(pcmsMaterialImg);
                pcmsPendingMaterialModelList.add(pcmsPendingMaterialModel);
            }
            /**获取驳回信息*/
            if(4 == receiptDetailModel.getStatus()){
                List<PcmsRejectLogModel> pcmsRejectLogModel = pcmsRejectLogMapper.selectRejectLogList(itid);
                receiptDetailModel.setPcmsRejectLogModelList(pcmsRejectLogModel);
            }
            receiptDetailModel.setPcmsShopVo(pcmsShopVo);
            receiptDetailModel.setPcmsOthertmVoList(list);
            receiptDetailModel.setPcmsPendingMaterialModelList(pcmsPendingMaterialModelList);
        }else if((receiptDetailModel.getType() == 2 && receiptDetailModel.getStatus()==0)){
            /**门店信息*/
            PcmsShopVo pcmsShopVo = baseMapper.getPcmsShopInfo(itid);
            /**其他展台*/
            List<PcmsOthertmVo> list = baseMapper.getPcmsOthertmInfo(itid);
            receiptDetailModel.setPcmsShopVo(pcmsShopVo);
            receiptDetailModel.setPcmsOthertmVoList(list);
        }else if(receiptDetailModel.getType() == 2 && receiptDetailModel.getStatus()==3){
            /**物料信息*/
            List<PcmsPendingMaterialModel> list =  pcmsPendingMaterialMapper.selectByItid(itid);
            receiptDetailModel.setPcmsPendingMaterialModelList(list);
        }else if(receiptDetailModel.getStatus() == 2 && receiptDetailModel.getStatus()==1){
            /**门店信息*/
            PcmsShopVo pcmsShopVo = baseMapper.getPcmsShopInfo(itid);
            /**其他展台*/
            List<PcmsOthertmVo> list = baseMapper.getPcmsOthertmInfo(itid);
            /**物料信息*/
            List<PcmsPendingMaterialModel> pcmsPendingMaterialModelList =  new ArrayList<>();
            List<PcmsPendingMaterialModel> list1 =  pcmsPendingMaterialMapper.selectByItid(itid);
            /**上传的图片信息*/
            if(null == list1 || list1.size() == 0){
                PcmsPendingMaterialModel pcmsPendingMaterialModel = new PcmsPendingMaterialModel();
                list1.add(pcmsPendingMaterialModel);
            }else{
                for(PcmsPendingMaterialModel pcmsPendingMaterialModel : list1){
                    List<PcmsMaterialImgModel> pcmsMaterialImg = pcmsMaterialImgMapper.selectById(pcmsPendingMaterialModel.getId());
                    pcmsPendingMaterialModel.setImgList(pcmsMaterialImg);
                    pcmsPendingMaterialModelList.add(pcmsPendingMaterialModel);
                }
            }
            receiptDetailModel.setPcmsShopVo(pcmsShopVo);
            receiptDetailModel.setPcmsOthertmVoList(list);
            receiptDetailModel.setPcmsPendingMaterialModelList(pcmsPendingMaterialModelList);
        }
        if((receiptDetailModel.getType() == 3 && receiptDetailModel.getStatus()==2) || (receiptDetailModel.getType() == 3 && receiptDetailModel.getStatus()==4)){
            /**广告物料*/
            List<MaterialResult> list = baseMapper.getMaterialResultInfo(itid);
            /**物料信息*/
            List<PcmsPendingMaterialModel> list1 =  pcmsPendingMaterialMapper.selectByItid(itid);
            receiptDetailModel.setMaterialResultList(list);
            receiptDetailModel.setPcmsPendingMaterialModelList(list1);
            /**获取驳回信息*/
            if(4 == receiptDetailModel.getStatus()){
                List<PcmsRejectLogModel> pcmsRejectLogModel = pcmsRejectLogMapper.selectRejectLogList(itid);
                receiptDetailModel.setPcmsRejectLogModelList(pcmsRejectLogModel);
            }
        }else if((receiptDetailModel.getType() == 3 && receiptDetailModel.getStatus()==0)){
            /**广告物料*/
            List<MaterialResult> list = baseMapper.getMaterialResultInfo(itid);
            receiptDetailModel.setMaterialResultList(list);
        }else if(receiptDetailModel.getType() == 3 && receiptDetailModel.getStatus()==3){
            /**物料信息*/
            List<PcmsPendingMaterialModel> list =  pcmsPendingMaterialMapper.selectByItid(itid);
            receiptDetailModel.setPcmsPendingMaterialModelList(list);
        }else if(receiptDetailModel.getType() == 3 && receiptDetailModel.getStatus()==1){
            /**广告物料*/
            List<MaterialResult> list = baseMapper.getMaterialResultInfo(itid);
            /**物料信息*/
            List<PcmsPendingMaterialModel> pcmsPendingMaterialModelList =  new ArrayList<>();
            List<PcmsPendingMaterialModel> list1 =  pcmsPendingMaterialMapper.selectByItid(itid);
            /**上传的图片信息*/
            if(null == list1 || list1.size() == 0){
                PcmsPendingMaterialModel pcmsPendingMaterialModel = new PcmsPendingMaterialModel();
                list1.add(pcmsPendingMaterialModel);
            }else{
                for(PcmsPendingMaterialModel pcmsPendingMaterialModel : list1){
                    List<PcmsMaterialImgModel> pcmsMaterialImg = pcmsMaterialImgMapper.selectById(pcmsPendingMaterialModel.getId());
                    pcmsPendingMaterialModel.setImgList(pcmsMaterialImg);
                    pcmsPendingMaterialModelList.add(pcmsPendingMaterialModel);
                }
            }
            receiptDetailModel.setMaterialResultList(list);
            receiptDetailModel.setPcmsPendingMaterialModelList(pcmsPendingMaterialModelList);
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
        
        //增加立项单日志
	    PcmsItemLog log=new PcmsItemLog();
	    log.setItid(itid);
	    log.setStatus(2);
	    log.setNote("已提交验收");
	    log.setCreateTime(new Date());
	    pcmsItemLogMapper.insertSelective(log);
        return ResultVo.get(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo addPendingMaterial(PcmsPendingMaterialModel pcmsPendingMaterialModel) throws Exception {
        pcmsPendingMaterialMapper.insertPendingMaterial(pcmsPendingMaterialModel);
        String[] image = pcmsPendingMaterialModel.getImage();
        if(image.length>0 && image != null){
            for(String url:image){
                PcmsMaterialImgModel pcmsMaterialImgModel = new PcmsMaterialImgModel();
                pcmsMaterialImgModel.setImage(url);
                pcmsMaterialImgModel.setPending_material_id(pcmsPendingMaterialModel.getId());
                pcmsMaterialImgMapper.insert(pcmsMaterialImgModel);
            }
        }else{
            PcmsMaterialImgModel pcmsMaterialImgModel = new PcmsMaterialImgModel();
            pcmsMaterialImgModel.setPending_material_id(pcmsPendingMaterialModel.getId());
            pcmsMaterialImgMapper.insert(pcmsMaterialImgModel);
        }
        return ResultVo.get(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo updatePendingMaterial(PcmsPendingMaterialModel pcmsPendingMaterialModel) throws Exception {
        List<PcmsMaterialImgModel> pcmsMaterialImg = pcmsMaterialImgMapper.selectById(pcmsPendingMaterialModel.getId());
        List<Integer> ids = new ArrayList<>();
        if(pcmsMaterialImg.size()>0){
            for(PcmsMaterialImgModel pmi : pcmsMaterialImg){
                ids.add(pmi.getId());
            }
            pcmsMaterialImgMapper.deleteBatchIds(ids);
        }
        PcmsPendingMaterialModel pcmsPendingMaterialModels = pcmsPendingMaterialMapper.selectId(pcmsPendingMaterialModel.getId());
        pcmsPendingMaterialModels.setAll_price(pcmsPendingMaterialModel.getAll_price());
        pcmsPendingMaterialModels.setNumber(pcmsPendingMaterialModel.getNumber());
        pcmsPendingMaterialModels.setCategory(pcmsPendingMaterialModel.getCategory());
        pcmsPendingMaterialModels.setRanges(pcmsPendingMaterialModel.getRanges());
        pcmsPendingMaterialModels.setSpecifications(pcmsPendingMaterialModel.getSpecifications());
        pcmsPendingMaterialMapper.updateById(pcmsPendingMaterialModels);
        String[] image = pcmsPendingMaterialModel.getImage();
        if(image.length>0 && image != null){
            for(String url:image){
                PcmsMaterialImgModel pcmsMaterialImgModel = new PcmsMaterialImgModel();
                pcmsMaterialImgModel.setImage(url);
                pcmsMaterialImgModel.setPending_material_id(pcmsPendingMaterialModel.getId());
                pcmsMaterialImgMapper.insert(pcmsMaterialImgModel);
            }
        }else{
            PcmsMaterialImgModel pcmsMaterialImgModel = new PcmsMaterialImgModel();
            pcmsMaterialImgModel.setPending_material_id(pcmsPendingMaterialModel.getId());
            pcmsMaterialImgMapper.insert(pcmsMaterialImgModel);
        }
        return ResultVo.get(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo deletePendingMaterial(Integer id) throws Exception {
        pcmsPendingMaterialMapper.deleteById(id);
        return ResultVo.get(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo queryPendingMaterial(Integer id) throws Exception {
        PcmsPendingMaterialModel pcmsPendingMaterialModel = pcmsPendingMaterialMapper.selectId(id);
        List<PcmsMaterialImgModel> imgList = pcmsMaterialImgMapper.selectById(pcmsPendingMaterialModel.getId());
        pcmsPendingMaterialModel.setImgList(imgList);
        return ResultVo.getDataWithSuccess(pcmsPendingMaterialModel);
    }

    @Override
    public ResultVo selectRejectLog(Integer itid) throws Exception {
        PcmsRejectLogModel pcmsRejectLogModel = pcmsRejectLogMapper.selectRejectLog(itid);
        return ResultVo.getDataWithSuccess(pcmsRejectLogModel);
    }

    @Override
    public ResultVo getItemDetail(Integer itid) throws Exception {
        ReceiptDetailModel receiptDetailModel = baseMapper.getItemDetail(itid);
        /**1表示展台展柜，2表示其他终端，3表示广告物料*/
        /**待验收的展台展柜*/
        if((receiptDetailModel.getType() == 1 && receiptDetailModel.getStatus()==2) || (receiptDetailModel.getType() == 1 && receiptDetailModel.getStatus()==5)){
            /**门店信息*/
            PcmsShopVo pcmsShopVo = baseMapper.getPcmsShopInfo(itid);
            /**展台展柜信息*/
            PcmsShowcaseVo pcmsShowcaseVo = baseMapper.getPcmsShowcaseInfo(itid);
            /**物料信息*/
            List<PcmsPendingMaterialModel> pcmsPendingMaterialModelList =  new ArrayList<>();
            List<PcmsPendingMaterialModel> list =  pcmsPendingMaterialMapper.selectByItid(itid);
            for(PcmsPendingMaterialModel pcmsPendingMaterialModel : list){
                List<PcmsMaterialImgModel> pcmsMaterialImg = pcmsMaterialImgMapper.selectById(pcmsPendingMaterialModel.getId());
                pcmsPendingMaterialModel.setImgList(pcmsMaterialImg);
                pcmsPendingMaterialModelList.add(pcmsPendingMaterialModel);
            }
            receiptDetailModel.setPcmsShopVo(pcmsShopVo);
            receiptDetailModel.setPcmsShowcaseVo(pcmsShowcaseVo);
            receiptDetailModel.setPcmsPendingMaterialModelList(pcmsPendingMaterialModelList);
            /**制作中展台展柜*/
        }else if((receiptDetailModel.getType() == 1 && receiptDetailModel.getStatus()==1) || (receiptDetailModel.getStatus() == -1 && receiptDetailModel.getType()==1) || (receiptDetailModel.getStatus() == 0 && receiptDetailModel.getType()==1)){
            /**门店信息*/
            PcmsShopVo pcmsShopVo = baseMapper.getPcmsShopInfo(itid);
            /**展台展柜信息*/
            PcmsShowcaseVo pcmsShowcaseVo = baseMapper.getPcmsShowcaseInfo(itid);
            receiptDetailModel.setPcmsShopVo(pcmsShopVo);
            receiptDetailModel.setPcmsShowcaseVo(pcmsShowcaseVo);
        }else if(receiptDetailModel.getType() == 1 && receiptDetailModel.getStatus()==4){
            /**门店信息*/
            PcmsShopVo pcmsShopVo = baseMapper.getPcmsShopInfo(itid);
            /**展台展柜信息*/
            PcmsShowcaseVo pcmsShowcaseVo = baseMapper.getPcmsShowcaseInfo(itid);
            /**物料信息*/
            List<PcmsPendingMaterialModel> pcmsPendingMaterialModelList =  new ArrayList<>();
            List<PcmsPendingMaterialModel> list =  pcmsPendingMaterialMapper.selectByItid(itid);
            for(PcmsPendingMaterialModel pcmsPendingMaterialModel : list){
                List<PcmsMaterialImgModel> pcmsMaterialImg = pcmsMaterialImgMapper.selectById(pcmsPendingMaterialModel.getId());
                pcmsPendingMaterialModel.setImgList(pcmsMaterialImg);
                pcmsPendingMaterialModelList.add(pcmsPendingMaterialModel);
            }
            /**获取驳回信息*/
            List<PcmsRejectLogModel> pcmsRejectLogModel = pcmsRejectLogMapper.selectRejectLogList(itid);
            receiptDetailModel.setPcmsRejectLogModelList(pcmsRejectLogModel);
            receiptDetailModel.setPcmsShopVo(pcmsShopVo);
            receiptDetailModel.setPcmsShowcaseVo(pcmsShowcaseVo);
            receiptDetailModel.setPcmsPendingMaterialModelList(pcmsPendingMaterialModelList);
        }
        /**待验收的其他终端*/
        if(receiptDetailModel.getType() == 2 && receiptDetailModel.getStatus()==2 || (receiptDetailModel.getType() == 2 && receiptDetailModel.getStatus()==5)){
            /**门店信息*/
            PcmsShopVo pcmsShopVo = baseMapper.getPcmsShopInfo(itid);
            /**其他展台*/
            List<PcmsOthertmVo> list = baseMapper.getPcmsOthertmInfo(itid);
            /**物料信息*/
            List<PcmsPendingMaterialModel> pcmsPendingMaterialModelList =  new ArrayList<>();
            List<PcmsPendingMaterialModel> list1 =  pcmsPendingMaterialMapper.selectByItid(itid);
            for(PcmsPendingMaterialModel pcmsPendingMaterialModel : list1){
                List<PcmsMaterialImgModel> pcmsMaterialImg = pcmsMaterialImgMapper.selectById(pcmsPendingMaterialModel.getId());
                pcmsPendingMaterialModel.setImgList(pcmsMaterialImg);
                pcmsPendingMaterialModelList.add(pcmsPendingMaterialModel);
            }
            receiptDetailModel.setPcmsShopVo(pcmsShopVo);
            receiptDetailModel.setPcmsOthertmVoList(list);
            receiptDetailModel.setPcmsPendingMaterialModelList(pcmsPendingMaterialModelList);
            /**制作中的其他终端*/
        }else if((receiptDetailModel.getStatus() == 1 && receiptDetailModel.getType()==2) || (receiptDetailModel.getStatus() == 0 && receiptDetailModel.getType()==2) || (receiptDetailModel.getStatus() == -1 && receiptDetailModel.getType()==2)){
            /**门店信息*/
            PcmsShopVo pcmsShopVo = baseMapper.getPcmsShopInfo(itid);
            /**其他展台*/
            List<PcmsOthertmVo> list = baseMapper.getPcmsOthertmInfo(itid);
            receiptDetailModel.setPcmsShopVo(pcmsShopVo);
            receiptDetailModel.setPcmsOthertmVoList(list);
        }else if(receiptDetailModel.getType() == 2 && receiptDetailModel.getStatus()==4){
            /**门店信息*/
            PcmsShopVo pcmsShopVo = baseMapper.getPcmsShopInfo(itid);
            /**其他展台*/
            List<PcmsOthertmVo> list = baseMapper.getPcmsOthertmInfo(itid);
            /**物料信息*/
            List<PcmsPendingMaterialModel> pcmsPendingMaterialModelList =  new ArrayList<>();
            List<PcmsPendingMaterialModel> list1 =  pcmsPendingMaterialMapper.selectByItid(itid);
            for(PcmsPendingMaterialModel pcmsPendingMaterialModel : list1){
                List<PcmsMaterialImgModel> pcmsMaterialImg = pcmsMaterialImgMapper.selectById(pcmsPendingMaterialModel.getId());
                pcmsPendingMaterialModel.setImgList(pcmsMaterialImg);
                pcmsPendingMaterialModelList.add(pcmsPendingMaterialModel);
            }
            /**获取驳回信息*/
            List<PcmsRejectLogModel> pcmsRejectLogModel = pcmsRejectLogMapper.selectRejectLogList(itid);
            receiptDetailModel.setPcmsRejectLogModelList(pcmsRejectLogModel);
            receiptDetailModel.setPcmsShopVo(pcmsShopVo);
            receiptDetailModel.setPcmsOthertmVoList(list);
            receiptDetailModel.setPcmsPendingMaterialModelList(pcmsPendingMaterialModelList);
        }
        /**待验收的广告物料*/
        if(receiptDetailModel.getType() == 3 && receiptDetailModel.getStatus()==2 || (receiptDetailModel.getType() == 3 && receiptDetailModel.getStatus()==5)){
            /**广告物料*/
            List<MaterialResult> list = baseMapper.getMaterialResultInfo(itid);
            /**物料信息*/
            List<PcmsPendingMaterialModel> pcmsPendingMaterialModelList =  new ArrayList<>();
            List<PcmsPendingMaterialModel> list1 =  pcmsPendingMaterialMapper.selectByItid(itid);
            for(PcmsPendingMaterialModel pcmsPendingMaterialModel : list1){
                List<PcmsMaterialImgModel> pcmsMaterialImg = pcmsMaterialImgMapper.selectById(pcmsPendingMaterialModel.getId());
                pcmsPendingMaterialModel.setImgList(pcmsMaterialImg);
                pcmsPendingMaterialModelList.add(pcmsPendingMaterialModel);
            }
            receiptDetailModel.setMaterialResultList(list);
            receiptDetailModel.setPcmsPendingMaterialModelList(pcmsPendingMaterialModelList);
            /**制作中的广告物料*/
        }else if((receiptDetailModel.getType() == 3 && receiptDetailModel.getStatus()==1) || (receiptDetailModel.getStatus() == 0 && receiptDetailModel.getType()==2) || (receiptDetailModel.getStatus() == -1 && receiptDetailModel.getType()==2)){
            /**广告物料*/
            List<MaterialResult> list = baseMapper.getMaterialResultInfo(itid);
            receiptDetailModel.setMaterialResultList(list);
        }else if(receiptDetailModel.getType() == 3 && receiptDetailModel.getStatus()==4){
            /**广告物料*/
            List<MaterialResult> list = baseMapper.getMaterialResultInfo(itid);
            /**物料信息*/
            List<PcmsPendingMaterialModel> pcmsPendingMaterialModelList =  new ArrayList<>();
            List<PcmsPendingMaterialModel> list1 =  pcmsPendingMaterialMapper.selectByItid(itid);
            for(PcmsPendingMaterialModel pcmsPendingMaterialModel : list1){
                List<PcmsMaterialImgModel> pcmsMaterialImg = pcmsMaterialImgMapper.selectById(pcmsPendingMaterialModel.getId());
                pcmsPendingMaterialModel.setImgList(pcmsMaterialImg);
                pcmsPendingMaterialModelList.add(pcmsPendingMaterialModel);
            }
            /**获取驳回信息*/
            List<PcmsRejectLogModel> pcmsRejectLogModel = pcmsRejectLogMapper.selectRejectLogList(itid);
            receiptDetailModel.setPcmsRejectLogModelList(pcmsRejectLogModel);
            receiptDetailModel.setMaterialResultList(list);
            receiptDetailModel.setPcmsPendingMaterialModelList(pcmsPendingMaterialModelList);
        }
        return ResultVo.getDataWithSuccess(receiptDetailModel);
    }

    @Override
    public ResultVo getDetail(Integer itid) throws Exception {
        ReceiptDetailVo receiptDetailVo = baseMapper.getDetail(itid);
        /**门店信息*/
        PcmsShopVo pcmsShopVo = baseMapper.getPcmsShopInfo(itid);
        if(null != pcmsShopVo){
            receiptDetailVo.setPcmsShopVo(pcmsShopVo);
        }
        /**展台展柜信息*/
        PcmsShowcaseVo pcmsShowcaseVo = baseMapper.getPcmsShowcaseInfo(itid);
        if(null != pcmsShowcaseVo){
            receiptDetailVo.setPcmsShowcaseVo(pcmsShowcaseVo);
        }
        /**待验收物料信息*/
        List<PendingMaterialVo> pendingMaterialVoList =  new ArrayList<>();
        List<PendingMaterialVo> list =  pcmsPendingMaterialMapper.queryByItid(itid);
        List<Transfer> pcmsTransferVoList = new ArrayList<>();
        if(list.size()>0){
            for(PendingMaterialVo pendingMaterialVo : list){
                /**待验收物料图片信息*/
                List<PcmsMaterialImgVo> pcmsMaterialImg = pcmsMaterialImgMapper.queryById(pendingMaterialVo.getId());
                pendingMaterialVo.setPcmsMaterialImgVoList(pcmsMaterialImg);
                pendingMaterialVoList.add(pendingMaterialVo);
                /**转办信息*/
                pcmsTransferVoList = pcmsTransferMapper.queryDetailByPendingId(pendingMaterialVo.getId());
                for(Transfer transfer : pcmsTransferVoList){
                    TransferDetailVo transferDetailVo = pcmsTransferMapper.selectFeedbackDetail(transfer.getId());
                    List<FeedbackVo> listFeedbackVo = pcmsFeedbackMapper.selectByTransferId(transferDetailVo.getId());
                    for(FeedbackVo feedbackVo : listFeedbackVo){
                        List<FeedbackImageVo> list1 = pcmsFeedbackImgMapper.selectByFeedbackId(feedbackVo.getId());
                        feedbackVo.setList(list1);
                    }
                    transfer.setListFeedbackVo(listFeedbackVo);
                }
            }
            receiptDetailVo.setPcmsPendingMaterialList(pendingMaterialVoList);
            receiptDetailVo.setPcmsTransferVoList(pcmsTransferVoList);
        }

        /**获取驳回信息*/
        List<PcmsRejectLogVo> pcmsRejectLogVo = pcmsRejectLogMapper.queryRejectLogList(itid);
        if(list.size()>0){
            receiptDetailVo.setPcmsRejectLogList(pcmsRejectLogVo);
        }
        /**获取作废信息*/
        PcmsTovoidItemVo pcmsTovoidItemVo = pcmsTovoidItemMapper.selectdetailByItid(itid);
        if(null != pcmsTovoidItemVo){
            receiptDetailVo.setPcmsTovoidItemVo(pcmsTovoidItemVo);
        }

        if(receiptDetailVo.getType() == 2){
            /**表示其他终端*/
            List<PcmsOthertmVo> listPcmsOthertmVo = baseMapper.getPcmsOthertmInfo(itid);
            receiptDetailVo.setPcmsOthertmVoList(listPcmsOthertmVo);
        }else{
            /**广告物料*/
            List<MaterialResult> materialResultList = pcmsMaterialMapper.selectByItid(itid);
            if(materialResultList.size()>0){
                receiptDetailVo.setMaterialResultList(materialResultList);
            }
        }
        return ResultVo.getDataWithSuccess(receiptDetailVo);
    }

    @Override
    public ResultVo doReject(PcmsRejectLogModel pcmsRejectLogModel/*, LoginUserInfo userInfo*/) throws Exception {
        if(StringUtil.isEmpty(pcmsRejectLogModel.getContext())){
            throw new ParamException("驳回理由不能为空");
        }
        PcmsItem pcmsItem = pcmsItemMapper.selectByPrimaryKey(pcmsRejectLogModel.getItid());
        if(null == pcmsItem){
            throw new ParamException("立项单不存在");
        }
        pcmsItem.setStatus(4);
        pcmsRejectLogModel.setCreate_time(new Date());
        pcmsRejectLogModel.setType(2);
        pcmsRejectLogModel.setOperator(/*userInfo.getEmployeeModel().getPerson_name()*/"555");
        pcmsRejectLogModel.setContext(pcmsRejectLogModel.getContext());
        PcmsItemLog pcmsItemLog = new PcmsItemLog();
        pcmsItemLog.setItid(pcmsRejectLogModel.getItid());
        pcmsItemLog.setNote(pcmsRejectLogModel.getContext());
        pcmsItemLog.setStatus(4);
        pcmsItemLog.setCreateTime(new Date());
        pcmsItemLogMapper.insert(pcmsItemLog);
        pcmsRejectLogMapper.insert(pcmsRejectLogModel);
        pcmsItemMapper.updateByPrimaryKey(pcmsItem);
        return ResultVo.get(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo doRejectSuccess(Integer itid/*, LoginUserInfo userInfo*/) throws Exception {
        PcmsItem pcmsItem = pcmsItemMapper.selectByPrimaryKey(itid);
        if(null == pcmsItem){
            throw new ParamException("立项单不存在");
        }
        PcmsRejectLogModel pcmsRejectLogModel = new PcmsRejectLogModel();
        pcmsRejectLogModel.setItid(itid);
        pcmsRejectLogModel.setOperator(/*userInfo.getEmployeeModel().getPerson_name()*/"555");
        pcmsRejectLogModel.setCreate_time(new Date());
        pcmsRejectLogModel.setType(1);
        pcmsItem.setStatus(3);
        pcmsItemMapper.updateByPrimaryKey(pcmsItem);
        pcmsRejectLogMapper.insert(pcmsRejectLogModel);
        return ResultVo.get(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo selectPendingMaterial(Integer itid) throws Exception {
        List<PcmsPendingMaterialModel> pcmsPendingMaterialModelList =  new ArrayList<>();
        List<PcmsPendingMaterialModel> list =  pcmsPendingMaterialMapper.selectByItid(itid);
        for(PcmsPendingMaterialModel pcmsPendingMaterialModel : list){
            List<PcmsMaterialImgModel> pcmsMaterialImg = pcmsMaterialImgMapper.selectById(pcmsPendingMaterialModel.getId());
            pcmsPendingMaterialModel.setImgList(pcmsMaterialImg);
            pcmsPendingMaterialModelList.add(pcmsPendingMaterialModel);
        }
        return ResultVo.getDataWithSuccess(pcmsPendingMaterialModelList);
    }

    @Override
    public ResultVo updatePendingMaterialFor(List<PcmsPendingMaterialModel> list) throws Exception {
        if(list != null || list.size()>0){
            for(PcmsPendingMaterialModel pcmsPendingMaterialModel : list){
                PcmsPendingMaterialModel pcmsPendingMaterialModel1 = pcmsPendingMaterialMapper.selectId(pcmsPendingMaterialModel.getId());
                pcmsPendingMaterialModel1.setCategory(pcmsPendingMaterialModel.getCategory());
                pcmsPendingMaterialModel1.setSpecifications(pcmsPendingMaterialModel.getSpecifications());
                pcmsPendingMaterialModel1.setRanges(pcmsPendingMaterialModel.getRanges());
                pcmsPendingMaterialModel1.setNumber(pcmsPendingMaterialModel.getNumber());
                pcmsPendingMaterialModel1.setAll_price(pcmsPendingMaterialModel.getAll_price());
                pcmsPendingMaterialMapper.updateById(pcmsPendingMaterialModel1);
            }
        }
        return ResultVo.get(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo selectPendingMaterialByItid(Integer itid) throws Exception {

        List<PcmsPendingMaterialModel> list = pcmsPendingMaterialMapper.selectPendingMaterialByItid(itid);
        List<PcmsPendingMaterialModel> list1 = new ArrayList<>();
        if(list.size()>0){
            for(PcmsPendingMaterialModel pendingMaterialVo : list){
                /**待验收物料图片信息*/
                List<PcmsMaterialImgModel> pcmsMaterialImg = pcmsMaterialImgMapper.selectById(pendingMaterialVo.getId());
                pendingMaterialVo.setImgList(pcmsMaterialImg);
                list1.add(pendingMaterialVo);
            }
        }
        return ResultVo.getDataWithSuccess(list1);
    }

    @Override
    public ResultVo selectByName(String name, LoginUserInfo userInfo) throws Exception {
        String token = ("QTAyNERBNDkwMzEyRTgwRTkwOENDRDc5MEVBOTFFOURDNTEyNUY4ODMwRjIyQjM3QTVBNzEyQjk4MEUzNThFOQ==");
//        String userId = ("panwj");
//        String name = ("李国栋");
        String param = "token="+token+"&userId="+userInfo.getEmployeeModel().getItcode()+"&param="+ URLEncoder.encode(name,"utf-8");
        log.info("准备调用查询用户接口,参数为:{}",param);
        String message = HttpRequest.sendGet(tpmUrl, param);
        log.info("调用询用户接口结束，返回的数据为：{}",message);
        if("".equals(message)){
            return ResultVo.getDataWithSuccess(null);
        }
        MemberVo memberVo = JSON.parseObject(message, MemberVo.class);
        if("0".equals(memberVo.getErrcode())){
            List<UserVo> list = memberVo.getResult();
            return ResultVo.getDataWithSuccess(list);
        }
        return ResultVo.getDataWithSuccess(null);

    }

    @Override
    public ResultVo selectItemLog(Integer itid) throws Exception {
        List<PcmsItemLog> list = pcmsTransferMapper.selectItemLog(itid);
        return ResultVo.getDataWithSuccess(list);
    }

    @Override
    public ResultVo selectSettlement(SettlementQuery query) throws Exception {
        return null;
    }

    @Override
    public ResultVo addTransfer(PcmsTransferModel pcmsTransferModel) throws Exception {
        pcmsTransferModel.setCreate_time(new Date());
        pcmsTransferMapper.insert(pcmsTransferModel);
        PcmsPendingMaterialModel pcmsPendingMaterialModel = pcmsPendingMaterialMapper.selectId(pcmsTransferModel.getPending_id());
        pcmsPendingMaterialModel.setState(1);
        pcmsPendingMaterialMapper.updateById(pcmsPendingMaterialModel);
        /*PcmsItemLog pcmsItemLog = new PcmsItemLog();
        pcmsItemLog.setItid(pcmsPendingMaterialModel.getItid());
        pcmsItemLog.setNote(pcmsTransferModel.getContext());
        pcmsItemLog.setCreateTime(new Date());
        pcmsItemLog.setStatus(10);
        pcmsItemLogMapper.insert(pcmsItemLog);*/
        return ResultVo.getDataWithSuccess(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo selectTransfer(TransferQuery query/*,LoginUserInfo userInfo*/) throws Exception {
//        query.setPerson_name(userInfo.getEmployeeModel().getPerson_name());
        query = (TransferQuery) CheckParamUtils.trimWithObjectField(query);
        Page<TransferVo> page = new Page<>(query.getCurrent(),query.getSize());
        List<TransferVo> transferList = pcmsTransferMapper.selectByState(query,page);
        page.setRecords(transferList);
        return ResultVo.getDataWithSuccess(page);
    }

    @Override
    public ResultVo addFeedback(FeedbackQuery feedbackQuery) throws Exception {
        PcmsFeedbackModel pcmsFeedbackModel = new PcmsFeedbackModel();
        pcmsFeedbackModel.setContext(feedbackQuery.getContext());
        pcmsFeedbackModel.setTransfer_id(feedbackQuery.getTransfer_id());
        pcmsFeedbackModel.setNumber(feedbackQuery.getNumber());
        pcmsFeedbackMapper.insertFeedback(pcmsFeedbackModel);
        String[] image = feedbackQuery.getImage();
        if(image.length>0){
            for(String url:image){
                PcmsFeedbackImgModel pcmsFeedbackImgModel = new PcmsFeedbackImgModel();
                pcmsFeedbackImgModel.setFeedback_id(pcmsFeedbackModel.getId());
                pcmsFeedbackImgModel.setImg(url);
                pcmsFeedbackImgMapper.insert(pcmsFeedbackImgModel);
            }
        }
        PcmsTransferModel pcmsTransferModel = pcmsTransferMapper.selectById(feedbackQuery.getTransfer_id());
        PcmsPendingMaterialModel pcmsPendingMaterialModel = pcmsPendingMaterialMapper.selectId(pcmsTransferModel.getPending_id());
        pcmsPendingMaterialModel.setState(2);
        pcmsPendingMaterialMapper.updateById(pcmsPendingMaterialModel);
        return ResultVo.getDataWithSuccess(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo selectFeedbackDetail(Integer id) throws Exception {
        TransferDetailVo transferDetailVo = pcmsTransferMapper.selectFeedbackDetail(id);
        List<FeedbackVo> listFeedbackVo = pcmsFeedbackMapper.selectByTransferId(transferDetailVo.getId());
        for(FeedbackVo feedbackVo : listFeedbackVo){
            List<FeedbackImageVo> list = pcmsFeedbackImgMapper.selectByFeedbackId(feedbackVo.getId());
            feedbackVo.setList(list);
        }
        transferDetailVo.setListFeedbackVo(listFeedbackVo);
        return ResultVo.getDataWithSuccess(transferDetailVo);
    }

    @Override
    public ResultVo selectTransferDetail(Integer id) throws Exception {
        List<Transfer> list = pcmsTransferMapper.queryDetailByPendingId(id);
        for(Transfer transfer : list){
            TransferDetailVo transferDetailVo = pcmsTransferMapper.selectFeedbackDetail(transfer.getId());
            List<FeedbackVo> listFeedbackVo = pcmsFeedbackMapper.selectByTransferId(transferDetailVo.getId());
            for(FeedbackVo feedbackVo : listFeedbackVo){
                List<FeedbackImageVo> list1 = pcmsFeedbackImgMapper.selectByFeedbackId(feedbackVo.getId());
                feedbackVo.setList(list1);
            }
            transfer.setListFeedbackVo(listFeedbackVo);
        }
        return ResultVo.getDataWithSuccess(list);
    }

}
