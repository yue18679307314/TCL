package com.kuyu.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.pcms.*;
import com.kuyu.model.LoginUserInfo;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    @Value("${image.path}")
    private String filePath;

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
        pcmsMaterialImgMapper.deleteBatchIds(pcmsMaterialImg);
//        pcmsPendingMaterialMapper.insertPendingMaterial(pcmsPendingMaterialModel);
        pcmsPendingMaterialMapper.updateById(pcmsPendingMaterialModel);
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
    public ResultVo doReject(PcmsRejectLogModel pcmsRejectLogModel, LoginUserInfo userInfo) throws Exception {
        if(StringUtil.isNotNull(pcmsRejectLogModel.getContext())){
            throw new ParamException("驳回理由不能为空");
        }
        PcmsItem pcmsItem = pcmsItemMapper.selectByPrimaryKey(pcmsRejectLogModel.getItid());
        if(null == pcmsItem){
            throw new ParamException("立项单不存在");
        }
        pcmsItem.setStatus(4);
        pcmsRejectLogModel.setCreate_time(new Date());
        pcmsRejectLogModel.setType(2);
        pcmsRejectLogModel.setOperator(userInfo.getEmployeeModel().getPerson_name());
        pcmsRejectLogModel.setContext(pcmsRejectLogModel.getContext());
        pcmsRejectLogMapper.insert(pcmsRejectLogModel);
        pcmsItemMapper.updateByPrimaryKey(pcmsItem);
        return ResultVo.get(ResultVo.SUCCESS);
    }

    @Override
    public ResultVo doRejectSuccess(Integer itid, LoginUserInfo userInfo) throws Exception {
        PcmsItem pcmsItem = pcmsItemMapper.selectByPrimaryKey(itid);
        if(null == pcmsItem){
            throw new ParamException("立项单不存在");
        }
        PcmsRejectLogModel pcmsRejectLogModel = new PcmsRejectLogModel();
        pcmsRejectLogModel.setItid(itid);
        pcmsRejectLogModel.setOperator(userInfo.getEmployeeModel().getPerson_name());
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
                PcmsPendingMaterialModel pcmsPendingMaterialModel1 = pcmsPendingMaterialMapper.selectById(pcmsPendingMaterialModel.getId());
                pcmsPendingMaterialModel1.setCategory(pcmsPendingMaterialModel.getCategory());
                pcmsPendingMaterialModel1.setSpecifications(pcmsPendingMaterialModel.getSpecifications());
                pcmsPendingMaterialModel1.setRanges(pcmsPendingMaterialModel.getRanges());
                pcmsPendingMaterialMapper.updateById(pcmsPendingMaterialModel1);
            }
        }
        return ResultVo.get(ResultVo.SUCCESS);
    }

}
